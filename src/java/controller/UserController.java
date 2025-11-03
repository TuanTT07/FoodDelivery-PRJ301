/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.RoleDAO;
import dao.StoreDAO;
import dao.UserDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Role;
import model.Store;
import model.User;
import utils.Validation;

/**
 *
 * @author ACER
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private void processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("userName");
        String password = request.getParameter("password");

        boolean isEmail = Pattern.matches(Validation.EMAIL_REGEX, username);

        UserDAO userDAO = new UserDAO();
        User user = isEmail ? userDAO.loginByEmail(username, password)
                : userDAO.loginByUsername(username, password);

        if (user == null) {
            request.setAttribute("msg", "Invalid username or password!");

            if (isEmail) {
                request.setAttribute("email", username);
            } else {
                request.setAttribute("username", username);
            }
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            return;
        }
        //kiem tra status, neu status la 0
        if (user.isStatus() == false) {
            request.setAttribute("msg", "Tài khoản của bạn đang bị khóa. Vui lòng liên hệ quản trị viên!");
            if (isEmail) {
                request.setAttribute("email", username);
            } else {
                request.setAttribute("username", username);
            }
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            return;
        }
        //neu status la 1
        HttpSession session = request.getSession();
        session.setAttribute("u", user);

        String role = user.getRoleID() != null ? user.getRoleID().getRoleID() : "";

        if (Validation.ROLE_ADMIN.equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
        } else if (Validation.ROLE_STORE_OWNER.equalsIgnoreCase(role)) {
            //xử lí store
            response.sendRedirect(request.getContextPath() + "/MainController?action=getStore");
        } else if (Validation.ROLE_DRIVER.equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/delivery/dashboard.jsp");
        } else if (Validation.ROLE_MEMBER.equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            request.setAttribute("msg", "Invalid account!");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        }
    }

    private void processAddUser(HttpServletRequest request, HttpServletResponse response, boolean isDelivery)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String fullName = request.getParameter("Fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("Phone");
        String avatar = request.getParameter("txtAvatarUser");

        //set role
        RoleDAO rDAO = new RoleDAO();
        Role role = rDAO.setRole(action);

        // Địa chỉ tách 3 phần
        String street = request.getParameter("street"); // số/đường
        String ward = request.getParameter("district");   // phường/xã
        String cityCode = request.getParameter("city");   // 1/2/3/4

        String cityName = "";
        if ("1".equals(cityCode)) {
            cityName = "TP. Hồ Chí Minh";
        } else if ("2".equals(cityCode)) {
            cityName = "Hà Nội";
        } else if ("3".equals(cityCode)) {
            cityName = "Đà Nẵng";
        } else if ("4".equals(cityCode)) {
            cityName = "Cần Thơ";
        }

        // Ghép địa chỉ
        StringBuilder addr = new StringBuilder();
        if (street != null && !street.trim().isEmpty()) {
            addr.append(street.trim());
        }
        if (ward != null && !ward.trim().isEmpty()) {
            addr.append(addr.length() > 0 ? ", " : "").append(ward.trim());
        }
        if (cityName != null && !cityName.isEmpty()) {
            addr.append(addr.length() > 0 ? ", " : "").append(cityName);
        }
        String address = addr.toString();

        User user = new User(username, fullName, email, password, phone, address, avatar, role);
        UserDAO userDAO = new UserDAO();

        String error_username = "";
        String error_password = "";
        String error_fullName = "";
        String error_email = "";
        String error_phone = "";
        String error_address = "";
        String error_avatar = "";

        boolean hasError = false;
        // Validate 
        if (username == null || username.trim().isEmpty()) {
            error_username = "Username cannot be empty!";
            hasError = true;
        } else if (userDAO.getUserByUsername(username) != null) {
            error_username = "Username already used!";
            hasError = true;
        }

        if (password == null || password.trim().isEmpty()) {
            error_password = "Password cannot be empty!";
            hasError = true;
        } else if (!password.matches(Validation.PASSWORD_REGEX)) {
            error_password = "mật khẩu có ít nhất 1 chữ in hoa, 1 chữ số, 1 ký tự đặc biệt, tối thiểu 8 ký tự";
            hasError = true;
        }

        if (fullName == null || fullName.trim().isEmpty()) {
            error_fullName = "Full name is required!";
            hasError = true;
        } else if (!fullName.matches(Validation.FULLNAME_REGEX)) {
            error_fullName = "bắt buộc có ít nhất 2 từ (ví dụ \"Nguyen Van\")";
            hasError = true;
        }

        if (email == null || email.trim().isEmpty()) {
            error_email = "Email is required!";
            hasError = true;
        } else if (!email.matches(Validation.EMAIL_REGEX)) {
            error_email = "email sai";
            hasError = true;
        } else if (userDAO.existsByEmail(email)) {
            error_email = "email used";
            hasError = true;
        }

        if (phone == null || phone.trim().isEmpty()) {
            error_phone = "Phone is required!";
            hasError = true;
        } else if (!phone.matches(Validation.PHONE_REGEX)) {
            error_phone = "đầu số của các nhà mạng hợp pháp (03, 05, 07, 08, 09) và tối đa là 10 số";
            hasError = true;
        } else if (userDAO.existsByPhone(phone)) {
            error_phone = "phone number used";
            hasError = true;
        }

        if (avatar == null || avatar.trim().isEmpty()) {
            error_avatar = "Vui lòng chọn ảnh đại diện!";
            hasError = true;
        }

        if (!isDelivery) {
            if (street == null || street.trim().isEmpty() || ward == null || ward.trim().isEmpty() || cityName == null || cityName.trim().isEmpty()) {
                error_address = "Vui lòng điền đầy đủ địa chỉ!";
                hasError = true;
            }
        }

        if (hasError) {
            request.setAttribute("u", user);
            request.setAttribute("error_username", error_username);
            request.setAttribute("error_password", error_password);
            request.setAttribute("error_fullName", error_fullName);
            request.setAttribute("error_email", error_email);
            request.setAttribute("error_phone", error_phone);
            request.setAttribute("error_address", error_address);
            request.setAttribute("error_avatar", error_avatar);
            request.setAttribute("street", street);
            request.setAttribute("ward", ward);
            request.setAttribute("city", cityCode);
            request.setAttribute("avatar", avatar);
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
            return;
        }

        boolean done = userDAO.insert(user);
        if (!done) {
            request.setAttribute("u", user);
            if (!isDelivery) {
                request.setAttribute("msg", "Thêm khách hàng bị lỗi. Vui lòng thử lại!");

            } else {
                request.setAttribute("msg", "Đăng kí tài xế bị lỗi. Vui lòng thử lại!");

            }
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
            return;
        }

        // sau khi tạo thành công thì tự động lấy thông tin đăng nhập
        User userF = userDAO.getUserByUsername(username);
        HttpSession session = request.getSession();
        session.setAttribute("u", userF);
        if (isDelivery) {
            request.getRequestDispatcher("/delivery/dashboard.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        }
    }

    private void processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // Huy tat ca nhung cai dang co trong session
        response.sendRedirect("index.jsp");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "loginUser";
        }
        if (action.equals("loginUser")) {
            processLogin(request, response);
        } else if (action.equals("logout")) {
            processLogout(request, response);
        } else if (action.equals("signUpUser")) {
            processAddUser(request, response, false);
        } else if (action.equals("signUpDelivery")) {
            processAddUser(request, response, true);
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
