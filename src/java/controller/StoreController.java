/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.StoreDAO;
import dao.UserDAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.BankAccount;
import model.Store;
import model.User;
import utils.Validation;

/**
 *
 * @author ACER
 */
@WebServlet(name = "StoreController", urlPatterns = {"/StoreController"})
public class StoreController extends HttpServlet {

    private void processAddStore(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy username của chủ cửa hàng
        String username = request.getParameter("username");

        // Thông tin cửa hàng
        String storeName = request.getParameter("storeName");
        String street = request.getParameter("street");
        String district = request.getParameter("district");
        String cityCode = request.getParameter("city");
        String cityName = "";
        switch (cityCode) {
            case "1":
                cityName = "TP. Hồ Chí Minh";
            case "2":
                cityName = "Hà Nội";
            case "3":
                cityName = "Đà Nẵng";
            case "4":
                cityName = "Cần Thơ";

        }

        String storeAddress = street + ", " + district + ", " + cityName;
        String description = request.getParameter("description");
        String openTime = request.getParameter("openTime");
        String closeTime = request.getParameter("closeTime");
        boolean is24Hours = "on".equals(request.getParameter("is24Hours"));

        // Thông tin ngân hàng
        String bankAccountName = request.getParameter("bankAccountName");
        String bankAccountNumber = request.getParameter("bankAccountNumber");
        String bankName = request.getParameter("bankName");

        String cate = request.getParameter("cate");

        String avatarBase64 = request.getParameter("txtAvatarBase64");
        String coverImageBase64 = request.getParameter("txtCoverImageBase64");

        UserDAO userDAO = new UserDAO();
        User owner = userDAO.getUserByUsername(username);
        if (owner == null) {
            request.setAttribute("error_message", "Không tìm thấy tài khoản chủ cửa hàng!");
            request.getRequestDispatcher("registerStore.jsp").forward(request, response);
            return;
        }
     
        StoreDAO storeDAO = new StoreDAO();
        Store newStore = new Store(storeName, storeAddress, cityName, district, description, 0, openTime, closeTime, is24Hours, bankAccountName, bankAccountNumber, bankName, avatarBase64, coverImageBase64, true, owner);
        boolean hasError = false;
        String error_storeName = "";
        String error_address = "";
        String error_category = "";
        String error_logo = "";
        String error_openTime = "";
        String error_closeTime = "";
        if (newStore.getStoreName() == null || newStore.getStoreName().trim().isEmpty()) {
            error_storeName = "Tên cửa hàng không được để trống!";
            hasError = true;
        }

        if (street == null || street.trim().isEmpty() || district == null || district.trim().isEmpty() || cityCode == null || cityCode.isEmpty()) {
            error_address = "Địa chỉ không hợp lệ!";
            hasError = true;
        }

        if (cate == null || cate.isEmpty()) {
            error_category = "Bạn phải chọn danh mục cửa hàng!";
            hasError = true;
        }

        if (openTime == null || openTime.isEmpty()) {
            error_openTime = "Chọn giờ mở cửa!";
            hasError = true;
        }

        if (closeTime == null || closeTime.isEmpty()) {
            error_closeTime = "Chọn giờ đóng cửa!";
            hasError = true;
        }


        // Nếu có lỗi thì quay lại form
        if (hasError) {
            request.setAttribute("error_storeName", error_storeName);
            request.setAttribute("error_address", error_address);
            request.setAttribute("error_category", error_category);
            request.setAttribute("error_openTime", error_openTime);
            request.setAttribute("error_closeTime", error_closeTime);
            request.setAttribute("error_logo", error_logo);

            // Lưu lại dữ liệu người dùng đã nhập
            request.setAttribute("storeName", storeName);
            request.setAttribute("street", street);
            request.setAttribute("district", district);
            request.setAttribute("city", cityCode);
            request.setAttribute("description", description);
            request.setAttribute("openTime", openTime);
            request.setAttribute("closeTime", closeTime);
            request.setAttribute("is24Hours", is24Hours);
            request.setAttribute("cate", cate);
            request.setAttribute("s", newStore);
            request.getRequestDispatcher("registerStore.jsp").forward(request, response);
            return;
        }

        try {

            storeDAO.insertStore(newStore);

            // Nếu có thông tin ngân hàng thì thêm vào bảng BankAccount
//            if (bankAccountName != null && !bankAccountName.trim().isEmpty()
//                    && bankAccountNumber != null && !bankAccountNumber.trim().isEmpty()) {
//                BankAccount bank = new BankAccount(bankAccountName, bankAccountNumber, bankName, ownerID);
//                bankDAO.insertBank(bank);
//            }
            // Redirect về trang thành công
            response.sendRedirect("store/dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error_message", "Đăng ký cửa hàng thất bại: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("signUpStore")) {
            processAddStore(request, response);
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
