/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.StoreCategoryDAO;
import dao.StoreDAO;
import dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoryStore;
import model.Store;
import model.User;

/**
 *
 * @author ACER
 */
@WebServlet(name = "StoreController", urlPatterns = {"/StoreController"})
public class StoreController extends HttpServlet {

    private void processAddStore(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy username của chủ cửa hàng
        String txtUsername = request.getParameter("username");
        String txtEmail = request.getParameter("email");
        String txtPhone = request.getParameter("Phone");
        // Thông tin cửa hàng
        String txtStoreName = request.getParameter("storeName");
        String txtDesc = request.getParameter("description");
        String txtStreet = request.getParameter("street");
        String txtDistrict = request.getParameter("district");
        String cityCode = request.getParameter("city");
        String txtCityName = "";
        switch (cityCode) {
            case "1":
                txtCityName = "TP. Hồ Chí Minh";
                break;
            case "2":
                txtCityName = "Hà Nội";
                break;
            case "3":
                txtCityName = "Đà Nẵng";
                break;
            case "4":
                txtCityName = "Cần Thơ";
                break;
            default:
                txtCityName = "";
                break;
        }
        // nếu mà được tick thì nó sẽ trả về on => true
        boolean txtIs24Hours = "on".equals(request.getParameter("is24Hours"));
        String Cate = request.getParameter("cate");
        StoreCategoryDAO storeCategoryDAO = new StoreCategoryDAO();
        CategoryStore txtCate = storeCategoryDAO.setCategoryStore(Cate);
        String txtOpenTime = request.getParameter("timeOpen");
        String txtCloseTime = request.getParameter("timeClose");
        String txtAvatarBase64 = request.getParameter("txtAvatarBase64");
        String txtCoverImageBase64 = request.getParameter("txtCoverImageBase64");

        // Thông tin ngân hàng
        String txtBankAccountName = request.getParameter("bankAccountName");
        String txtBankAccountNumber = request.getParameter("bankAccountNumber");
        String txtBankName = request.getParameter("bankName");
        switch (txtBankName) {
            case "1":
                txtBankName = "Vietcombank";
                break;
            case "2":
                txtBankName = "Techcombank";
                break;
            case "3":
                txtBankName = "BIDV";
                break;
            case "4":
                txtBankName = "ACB";
                break;
            case "5":
                txtBankName = "MB Bank";
                break;
        }

        String avatarBase64 = request.getParameter("txtAvatarBase64");
        String coverImageBase64 = request.getParameter("txtCoverImageBase64");

        UserDAO userDAO = new UserDAO();
        User owner = userDAO.getUserByUsername(txtUsername);
        if (owner == null) {
            request.setAttribute("error_message", "Tài khoản chưa đăng kí thành viên!");
            request.getRequestDispatcher("auth/register.jsp?action=signUpUser").forward(request, response);
            return;
        }
        boolean hasError = false;

        String error_storeName = "";
        String error_address = "";
        String error_category = "";
        String error_logo = "";
        String error_openTime = "";
        String error_closeTime = "";
        String error_bankAccoutName = "";
        String error_bankAccoutNumber = "";
        String error_bankName = "";

        if (txtStoreName == null || txtStoreName.trim().length() == 0) {
            error_storeName = "Tên cửa hàng không được để trống!";
            hasError = true;
        }
        if (txtDesc == null || txtDesc.trim().length() == 0) {
            error_storeName = "Vui lòng nhập mô tả!";
            hasError = true;
        }
        if (txtStreet == null || txtStreet.trim().length() == 0 || txtDistrict == null || txtDistrict.trim().length() == 0 || txtCityName.trim().length() == 0) {
            error_address = "Địa chỉ không hợp lệ!";
            hasError = true;
        }

        if (txtCate == null) {
            error_category = "Bạn phải chọn danh mục cửa hàng!";
            hasError = true;
        }

        if (txtOpenTime == null || txtOpenTime.trim().length() == 0) {
            error_openTime = "Chọn giờ mở cửa!";
            hasError = true;
        }

        if (txtCloseTime == null || txtCloseTime.trim().length() == 0) {
            error_closeTime = "Chọn giờ đóng cửa!";
            hasError = true;
        }
        if (txtBankAccountName == null || txtBankAccountName.trim().length() == 0) {
            error_bankAccoutName = "Vui lòng nhập tên tài khoản ngân hàng";
            hasError = true;
        }
        if (txtBankAccountNumber == null || txtBankAccountNumber.trim().length() == 0) {
            error_bankAccoutNumber = "Vui lòng nhập số tài khoản!";
            hasError = true;
        }
        if (txtBankName == null || txtBankName.trim().length() == 0) {
            error_bankName = "Vui lòng chọn ngân hàng!";
            hasError = true;
        }

        // Nếu có lỗi thì quay lại form
        if (hasError) {
            Store store1 = new Store(txtStoreName, txtStreet, txtCityName, txtDistrict, txtOpenTime, txtCloseTime, owner, txtCate, txtPhone, txtEmail, txtDesc, txtBankAccountName, txtBankAccountNumber, txtBankName, txtAvatarBase64, txtCoverImageBase64, txtIs24Hours);
            System.out.println(store1.toString());
            request.setAttribute("error_storeName", error_storeName);
            request.setAttribute("error_address", error_address);
            request.setAttribute("error_category", error_category);
            request.setAttribute("error_openTime", error_openTime);
            request.setAttribute("error_closeTime", error_closeTime);
            request.setAttribute("error_logo", error_logo);
            request.setAttribute("error_bankAccoutName", error_bankAccoutName);
            request.setAttribute("error_bankAccoutNumber", error_bankAccoutNumber);
            request.setAttribute("error_bankName", error_bankName);

            // Lưu lại dữ liệu người dùng đã nhập
            request.setAttribute("storeName", txtStoreName);
            request.setAttribute("street", txtStreet);
            request.setAttribute("district", txtDistrict);

            request.setAttribute("city", cityCode);
            request.setAttribute("description", txtDesc);
            request.setAttribute("openTime", txtOpenTime);
            request.setAttribute("closeTime", txtCloseTime);
            request.setAttribute("is24Hours", txtIs24Hours);
            request.setAttribute("cate", txtCate);
            request.getRequestDispatcher("auth/register.jsp?action=signUpStore").forward(request, response);
            return;
        }
        Store store = new Store(txtStoreName, txtStreet, txtCityName, txtDistrict, txtOpenTime, txtCloseTime, owner, txtCate, txtPhone, txtEmail, txtDesc, txtBankAccountName, txtBankAccountNumber, txtBankName, txtAvatarBase64, txtCoverImageBase64, txtIs24Hours);
        StoreDAO storeDAO = new StoreDAO();
        try {
            System.out.println(">>> processAddStore() được gọi");

            storeDAO.insertStore(store);
            response.sendRedirect("store/dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error_message", "Đăng ký cửa hàng thất bại: " + e.getMessage());
            request.getRequestDispatcher("auth/register.jsp?action=signUpStore").forward(request, response);
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
