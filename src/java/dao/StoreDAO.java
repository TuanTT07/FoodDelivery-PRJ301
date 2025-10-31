package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Store;
import utils.DBUtils;

public class StoreDAO {

    public StoreDAO() {
    }

    public String getLastStoreID() {
        String sql = "SELECT TOP 1 StoreID FROM tblStore ORDER BY CAST(SUBSTRING(StoreID, 3, LEN(StoreID)) AS INT) DESC";
        try ( Connection c = DBUtils.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("StoreID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateStoreID() {
        String last = getLastStoreID();
        if (last == null || last.isEmpty()) {
            return "ST001";
        }
        int n = Integer.parseInt(last.substring(2)) + 1;
        return String.format("ST%03d", n);
    }

    public boolean insertStore(Store store) {

        String sql = "INSERT INTO tblStore ("
                + "StoreID, StoreName, StoreAddress, City, District, StoreRating, "
                + "OpenTime, CloseTime, OwnerUserID, StoreCategoryID, StorePhone, StoreEmail, "
                + "Description, Status, BankAccountName, BankAccountNumber, BankName, "
                + "LogoURL, BannerURL, Is24Hours, CreatedAt"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                 Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {
            // Thông tin cơ bản
            pst.setString(1, generateStoreID());
            pst.setString(2, store.getStoreName());
            pst.setString(3, store.getStoreAddress());
            pst.setString(4, store.getCity());
            pst.setString(5, store.getDistrict());
            pst.setDouble(6, store.getStoreRating());

            // Giờ mở & đóng cửa (chuyển từ String -> SQL Time)
            // Nếu null, tránh lỗi valueOf()
            if (store.getOpenTime() != null && !store.getOpenTime().trim().isEmpty()) {
                String openStr = store.getOpenTime().trim();
                if (openStr.length() == 5) { // dạng HH:mm
                    openStr += ":00";
                }
                pst.setTime(7, java.sql.Time.valueOf(openStr));
            } else {
                pst.setNull(7, java.sql.Types.TIME);
            }

            if (store.getCloseTime() != null && !store.getCloseTime().trim().isEmpty()) {
                String closeStr = store.getCloseTime().trim();
                if (closeStr.length() == 5) {
                    closeStr += ":00";
                }
                pst.setTime(8, java.sql.Time.valueOf(closeStr));
            } else {
                pst.setNull(8, java.sql.Types.TIME);
            }

            // Khóa ngoại
            pst.setString(9, (store.getOwnerUserID() != null) ? store.getOwnerUserID().getUserID() : null);
            pst.setString(10, (store.getStoreCategoryId() != null) ? store.getStoreCategoryId().getStoreCategoryId() : null);

            // Thông tin liên hệ & mô tả
            pst.setString(11, store.getStorePhone());
            pst.setString(12, store.getStoreEmail());
            pst.setString(13, store.getDescription());

            // 5Trạng thái & ngân hàng
            pst.setBoolean(14, store.isStatus());
            pst.setString(15, store.getBankAccountName());
            pst.setString(16, store.getBankAccountNumber());
            pst.setString(17, store.getBankName());

            // Ảnh & tùy chọn 24h
            pst.setString(18, store.getLogoURL());
            pst.setString(19, store.getBannerURL());
            pst.setBoolean(20, store.isIs24Hours());

            //  Ngày tạo
            if (store.getCreatedAt() != null) {
                pst.setTimestamp(21, java.sql.Timestamp.valueOf(store.getCreatedAt()));
            } else {
                pst.setTimestamp(21, new java.sql.Timestamp(System.currentTimeMillis()));
            }

            // Thực thi câu lệnh
            boolean success = pst.executeUpdate() > 0;
            return success;

        } catch (SQLException e) {
            System.err.println("SQL Error inserting store: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    //Read
    public ArrayList<Store> getAllStore() {
        ArrayList<Store> listStore = new ArrayList<>();
        String sql = "SELECT * FROM tblStore";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql);  ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Store store = new Store();

                store.setStoreID(rs.getString("StoreID"));
                store.setStoreName(rs.getString("StoreName"));
                store.setStoreAddress(rs.getString("StoreAddress"));
                store.setCity(rs.getString("City"));
                store.setDistrict(rs.getString("District"));
                store.setDescription(rs.getString("Description"));
                store.setStoreRating(rs.getDouble("StoreRating"));
                store.setOpenTime(rs.getString("OpenTime"));
                store.setCloseTime(rs.getString("CloseTime"));

                // Boolean fields
                store.setIs24Hours(rs.getBoolean("Is24Hours"));
                store.setStatus(rs.getBoolean("Status"));

                // Bank info
                store.setBankAccountName(rs.getString("BankAccountName"));
                store.setBankAccountNumber(rs.getString("BankAccountNumber"));
                store.setBankName(rs.getString("BankName"));

                // URLs
                System.out.println(rs.getString("LogoURL"));

                store.setLogoURL(rs.getString("LogoURL"));
                store.setBannerURL(rs.getString("BannerURL"));

                // Contact info
                store.setStorePhone(rs.getString("StorePhone"));
                store.setStoreEmail(rs.getString("StoreEmail"));

                // Category and timestamps
                StoreCategoryDAO storeCateDAO = new StoreCategoryDAO();
                store.setStoreCategoryId(storeCateDAO.setCategoryStore(rs.getString("StoreCategoryID")));

                // Get owner info from UserDAO
                UserDAO uDAO = new UserDAO();
                store.setOwnerUserID(uDAO.getUserByID(rs.getString("OwnerUserID")));

                listStore.add(store);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listStore;
    }

    public Store getStoreByUserOwner(String userOwnerID) {
        Store store = new Store();
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblStore WHERE OwnerUserID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userOwnerID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                store.setStoreID(rs.getString("StoreID"));
                store.setStoreName(rs.getString("StoreName"));
                store.setStoreAddress(rs.getString("StoreAddress"));
                store.setCity(rs.getString("City"));
                store.setDistrict(rs.getString("District"));
                store.setDescription(rs.getString("Description"));
                store.setStoreRating(rs.getDouble("StoreRating"));
                store.setOpenTime(rs.getString("OpenTime"));
                store.setCloseTime(rs.getString("CloseTime"));

                // Boolean fields
                store.setIs24Hours(rs.getBoolean("Is24Hours"));
                store.setStatus(rs.getBoolean("Status"));

                // Bank info
                store.setBankAccountName(rs.getString("BankAccountName"));
                store.setBankAccountNumber(rs.getString("BankAccountNumber"));
                store.setBankName(rs.getString("BankName"));

                store.setLogoURL(rs.getString("LogoURL"));
                store.setBannerURL(rs.getString("BannerURL"));

                // Contact info
                store.setStorePhone(rs.getString("StorePhone"));
                store.setStoreEmail(rs.getString("StoreEmail"));

                // Category and timestamps
                StoreCategoryDAO storeCateDAO = new StoreCategoryDAO();
                store.setStoreCategoryId(storeCateDAO.setCategoryStore(rs.getString("StoreCategoryID")));

                // Get owner info from UserDAO
                UserDAO uDAO = new UserDAO();
                store.setOwnerUserID(uDAO.getUserByID(rs.getString("OwnerUserID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return store;
    }

    //Update
    public boolean updateStoreInfo(Store store) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "UPDATE tblStore"
                    + "SET StoreID = ?"
                    + " ,StoreName = ?"
                    + " ,StoreAddress = ?"
                    + " ,StoreRating = ?"
                    + " ,OpenTime = ?"
                    + " ,CloseTime = ?"
                    + " ,OwnerUserID = ?"
                    + " WHERE StoreID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, store.getStoreID());
            pst.setString(2, store.getStoreName());
            pst.setString(3, store.getStoreAddress());
            pst.setDouble(4, store.getStoreRating());
            pst.setTime(5, Time.valueOf(store.getOpenTime()));
            pst.setTime(6, Time.valueOf(store.getCloseTime()));
            pst.setString(7, store.getOwnerUserID().getUserID());
            //send an UPDATE command to the database.
            int i = pst.executeUpdate();
            return i > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public Store getStoreByID(String storeID) {
        try {
            //connect database
            Connection conn = DBUtils.getConnection();
            //create SQL statement
            String sql = "SELECT * FROM tblStore WHERE StoreID = ?";
            //create statement
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, storeID);
            //
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Store store = new Store();
                store.setStoreID(rs.getString("StoreID"));
                store.setStoreName(rs.getString("StoreName"));
                store.setStoreAddress(rs.getString("StoreAddress"));
                store.setCity(rs.getString("City"));
                store.setDistrict(rs.getString("District"));
                store.setDescription(rs.getString("Description"));
                store.setStoreRating(rs.getDouble("StoreRating"));
                store.setOpenTime(rs.getString("OpenTime"));
                store.setCloseTime(rs.getString("CloseTime"));

                // Boolean fields
                store.setIs24Hours(rs.getBoolean("Is24Hours"));
                store.setStatus(rs.getBoolean("Status"));

                // Bank info
                store.setBankAccountName(rs.getString("BankAccountName"));
                store.setBankAccountNumber(rs.getString("BankAccountNumber"));
                store.setBankName(rs.getString("BankName"));

                store.setLogoURL(rs.getString("LogoURL"));
                store.setBannerURL(rs.getString("BannerURL"));

                // Contact info
                store.setStorePhone(rs.getString("StorePhone"));
                store.setStoreEmail(rs.getString("StoreEmail"));

                // Category and timestamps
                StoreCategoryDAO storeCateDAO = new StoreCategoryDAO();
                store.setStoreCategoryId(storeCateDAO.setCategoryStore(rs.getString("StoreCategoryID")));

                // Get owner info from UserDAO
                UserDAO uDAO = new UserDAO();
                store.setOwnerUserID(uDAO.getUserByID(rs.getString("OwnerUserID")));
                return store;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Store> selectStoreByLocation(String location) {
        ArrayList<Store> listStore = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblStore WHERE City LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + location.trim() + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Store store = new Store();

                store.setStoreID(rs.getString("StoreID"));
                store.setStoreName(rs.getString("StoreName"));
                store.setStoreAddress(rs.getString("StoreAddress"));
                store.setCity(rs.getString("City"));
                store.setDistrict(rs.getString("District"));
                store.setDescription(rs.getString("Description"));
                store.setStoreRating(rs.getDouble("StoreRating"));

//                Time openTime = rs.getTime("OpenTime");
//                Time closeTime = rs.getTime("CloseTime");
//
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//                store.setOpenTime(openTime.toLocalTime().format(formatter));
//                store.setCloseTime(closeTime.toLocalTime().format(formatter));
                store.setOpenTime(rs.getString("OpenTime"));
                store.setCloseTime(rs.getString("CloseTime"));

                // Boolean fields
                store.setIs24Hours(rs.getBoolean("Is24Hours"));
                store.setStatus(rs.getBoolean("Status"));

                // Bank info
                store.setBankAccountName(rs.getString("BankAccountName"));
                store.setBankAccountNumber(rs.getString("BankAccountNumber"));
                store.setBankName(rs.getString("BankName"));

                // URLs
                System.out.println(rs.getString("LogoURL"));

                store.setLogoURL(rs.getString("LogoURL"));
                store.setBannerURL(rs.getString("BannerURL"));

                // Contact info
                store.setStorePhone(rs.getString("StorePhone"));
                store.setStoreEmail(rs.getString("StoreEmail"));

                // Category and timestamps
                StoreCategoryDAO storeCateDAO = new StoreCategoryDAO();
                store.setStoreCategoryId(storeCateDAO.getStoreCateByID(rs.getString("StoreCategoryID")));

                // Get owner info from UserDAO
                UserDAO uDAO = new UserDAO();
                store.setOwnerUserID(uDAO.getUserByID(rs.getString("OwnerUserID")));

                listStore.add(store);
            }
        } catch (Exception e) {
        }
        return listStore;
    }

}
