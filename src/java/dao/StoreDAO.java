package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Store;
import utils.DBUtils;

public class StoreDAO {

    public StoreDAO() {
    }

    public String getLastStoreID() {
        String sql = "SELECT TOP 1 StoreID FROM tblStore ORDER BY StoreID DESC";
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
        String last = getLastStoreID(); // ví dụ U004
        if (last == null || last.isEmpty()) {
            return "ST001";
        }
        int n = Integer.parseInt(last.substring(1)) + 1;
        return String.format("ST%03d", n);
    }

    //Create
    public boolean insertStore(Store store) {
        String sql = "INSERT INTO tblStore "
                + "(StoreID, StoreName, StoreAddress, StoreRating, OpenTime, CloseTime, OwnerUserID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                 Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, store.getStoreID());
            pst.setString(2, store.getStoreName());
            pst.setString(3, store.getStoreAddress());
            pst.setDouble(4, store.getStoreRating());
            pst.setTime(5, java.sql.Time.valueOf(store.getOpenTime()));
            pst.setTime(6, java.sql.Time.valueOf(store.getCloseTime()));
            pst.setString(7, store.getOwnerUserID().getUserID());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting store: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Read
    public ArrayList<Store> getAllStore() {
        ArrayList<Store> listStore = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblStore";
            PreparedStatement pst = conn.prepareStatement(sql);
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
                store.setOpenTime(rs.getString("OpenTime"));
                store.setCloseTime(rs.getString("CloseTime"));
                String checkIs24H = rs.getString("Is24Hours");
                if (checkIs24H.equals("1")) {
                    store.setIs24Hours(true);
                } else {
                    store.setIs24Hours(false);

                }
                store.setBankAccountName(rs.getString("BankAccountName"));
                store.setBankAccountNumber(rs.getString("BankAccountNumber"));
                store.setBankName(rs.getString("BankName"));
                store.setLogoURL(rs.getString("LogoURL"));
                store.setCoverURL(rs.getString("CoverURL"));
                String checkStatus = rs.getString("Status");

                if (checkStatus.equals("1")) {
                    store.setStatus(true);
                } else {
                    store.setStatus(false);

                }

                UserDAO uDAO = new UserDAO();
                store.setOwnerUserID(uDAO.getUserByID(rs.getString("OwnerUserID")));
                listStore.add(store);
            }
        } catch (Exception e) {
        }
        return listStore;
    }

    public ArrayList<Store> getStoreByUserOwner(String userOwnerID) {
        ArrayList<Store> listStore = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblStore WHERE OwnerUserID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userOwnerID);
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
                store.setOpenTime(rs.getString("OpenTime"));
                store.setCloseTime(rs.getString("CloseTime"));
                String checkIs24H = rs.getString("Is24Hours");
                if (checkIs24H.equals("1")) {
                    store.setIs24Hours(true);
                } else {
                    store.setIs24Hours(false);

                }
                store.setBankAccountName(rs.getString("BankAccountName"));
                store.setBankAccountNumber(rs.getString("BankAccountNumber"));
                store.setBankName(rs.getString("BankName"));
                store.setLogoURL(rs.getString("LogoURL"));
                store.setCoverURL(rs.getString("CoverURL"));
                String checkStatus = rs.getString("Status");

                if (checkStatus.equals("1")) {
                    store.setStatus(true);
                } else {
                    store.setStatus(false);

                }

                UserDAO uDAO = new UserDAO();
                store.setOwnerUserID(uDAO.getUserByID(rs.getString("OwnerUserID")));
                listStore.add(store);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStore;
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
            String sql = "";
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
                store.setStoreRating(rs.getDouble("StoreRating"));
                /*
                opentime, closetime(SQL) variables type is Time
                opentime, closetime(model) variables type is LocalTime
                 */
                //convert from java.sql.Time to java.time.LocalTime
                if (rs.getTime("OpenTime") != null) {
//                    store.setOpenTime(rs.getTime("OpenTime").toLocalTime());
                }
                if (rs.getTime("CloseTime") != null) {
//                    store.setCloseTime(rs.getTime("CloseTime").toLocalTime());
                }
                return store;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Store> selectStoreByLocation(String location) {
        ArrayList<Store> ketQua = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblStore WHERE StoreAddress LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + location.trim() + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Store store = new Store();
                store.setStoreID(rs.getString("StoreID"));
                store.setStoreName(rs.getString("StoreName"));
                store.setStoreAddress(rs.getString("StoreAddress"));
                store.setStoreRating(rs.getDouble("StoreRating"));
                /*
                opentime, closetime(SQL) variables type is Time
                opentime, closetime(model) variables type is LocalTime
                 */
                //convert from java.sql.Time to java.time.LocalTime
                if (rs.getTime("OpenTime") != null) {
//                    store.setOpenTime(rs.getTime("OpenTime").toLocalTime());
                }
                if (rs.getTime("CloseTime") != null) {
//                    store.setCloseTime(rs.getTime("CloseTime").toLocalTime());
                }
//                store.setOwnerUserID(new User(rs.getString("OwnerUserID")));
                ketQua.add(store);

            }
        } catch (Exception e) {
        }
        return ketQua;
    }

}
