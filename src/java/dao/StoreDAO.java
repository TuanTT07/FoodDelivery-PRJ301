package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import model.Store;
import model.User;
import utils.DBUtils;

public class StoreDAO {

    public StoreDAO() {
    }

    //Create
    public boolean insertStore(Store store) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO tblStore (StoreID , StoreName, StoreAddress, StoreRating, OpenTime, CloseTime, OwnerUserID)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, store.getStoreID());
            pst.setString(2, store.getStoreName());
            pst.setString(3, store.getStoreAddress());
            pst.setDouble(4, store.getStoreRating());
            pst.setTime(5, Time.valueOf(store.getOpenTime()));
            pst.setTime(6, Time.valueOf(store.getCloseTime()));
            pst.setString(7, store.getOwnerUserID().getUserID());
            //send an INSERT command to the database.
            int i = pst.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            e.printStackTrace();
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
                store.setStoreRating(rs.getDouble("StoreRating"));
                /*
                opentime, closetime(SQL) variables type is Time
                opentime, closetime(model) variables type is LocalTime
                 */
                //convert from java.sql.Time to java.time.LocalTime
                if (rs.getTime("OpenTime") != null) {
                    store.setOpenTime(rs.getTime("OpenTime").toLocalTime());
                }
                if (rs.getTime("CloseTime") != null) {
                    store.setCloseTime(rs.getTime("CloseTime").toLocalTime());
                }
                store.setOwnerUserID(new User(rs.getString("OwnerUserID")));
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
                store.setStoreRating(rs.getDouble("StoreRating"));
                /*
                opentime, closetime(SQL) variables type is Time
                opentime, closetime(model) variables type is LocalTime
                 */
                //convert from java.sql.Time to java.time.LocalTime
                if (rs.getTime("OpenTime") != null) {
                    store.setOpenTime(rs.getTime("OpenTime").toLocalTime());
                }
                if (rs.getTime("CloseTime") != null) {
                    store.setCloseTime(rs.getTime("CloseTime").toLocalTime());
                }
                store.setOwnerUserID(new User(rs.getString("OwnerUserID")));
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
                    store.setOpenTime(rs.getTime("OpenTime").toLocalTime());
                }
                if (rs.getTime("CloseTime") != null) {
                    store.setCloseTime(rs.getTime("CloseTime").toLocalTime());
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
                    store.setOpenTime(rs.getTime("OpenTime").toLocalTime());
                }
                if (rs.getTime("CloseTime") != null) {
                    store.setCloseTime(rs.getTime("CloseTime").toLocalTime());
                }
                store.setOwnerUserID(new User(rs.getString("OwnerUserID")));
                ketQua.add(store);

            }
        } catch (Exception e) {
        }
        return ketQua;
    }

}
