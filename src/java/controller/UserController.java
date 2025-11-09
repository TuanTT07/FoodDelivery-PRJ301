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
import utils.PasswordUtils;
import utils.Validation;

/**
 *
 * @author ACER
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private void processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");   // có thể là username hoặc email
        String password = request.getParameter("password");  // plaintext

        UserDAO userDAO = new UserDAO();

        // 1) Xác định kiểu đăng nhập: email hay username
        boolean isEmail = Pattern.matches(Validation.EMAIL_REGEX, userName);

        // 2) Lấy user theo loại đã xác định 
        User user = isEmail ? userDAO.getUserByEmail(userName) : userDAO.getUserByUsername(userName);
        if (user == null) {
            user = isEmail ? userDAO.getUserByUsername(userName) : userDAO.getUserByEmail(userName);
        }

        // 3) Không tồn tại user
        if (user == null) {
            request.setAttribute("msg", "Sai tên đăng nhập hoặc mật khẩu!");
            request.setAttribute("userName", userName);
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            return;
        }

        // 4) So khớp mật khẩu (bcrypt)
        if (!PasswordUtils.matches(password, user.getUserPassword())) {
            request.setAttribute("msg", "Sai tên đăng nhập hoặc mật khẩu!");
            request.setAttribute("userName", userName);
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            return;
        }

        // 5) Kiểm tra trạng thái
        if (!user.isStatus()) {
            request.setAttribute("msg", "Tài khoản bị khóa. Vui lòng liên hệ quản trị viên!");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            return;
        }

        // 6) Lưu session + điều hướng theo role
        HttpSession session = request.getSession();
        session.setAttribute("u", user);

        String role = (user.getRoleID() != null) ? user.getRoleID().getRoleID() : "";
        if (Validation.ROLE_ADMIN.equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
        } else if (Validation.ROLE_STORE_OWNER.equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/MainController?action=getStore");
        } else if (Validation.ROLE_DRIVER.equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/delivery/dashboard.jsp");
        } else if (Validation.ROLE_MEMBER.equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            request.setAttribute("msg", "Không xác định được vai trò người dùng!");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        }
    }

    private void processAddUser(HttpServletRequest request, HttpServletResponse response, boolean isDelivery)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String hashedPassword = PasswordUtils.hash(password);
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

        User user = new User(username, fullName, email, hashedPassword, phone, address, avatar, role);
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

    private void processSearchUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        UserDAO userDAO = new UserDAO();

        ArrayList<User> listOfUsers = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            listOfUsers = userDAO.getAllUser();
        } else {
            listOfUsers = userDAO.getAllUserByFullName(name);
        }

        request.setAttribute("listOfUsers", listOfUsers);
        request.setAttribute("name", name);

        //Forward về customer.jsp 
        request.getRequestDispatcher("/admin/customer.jsp").forward(request, response);
    }

    private void processCallUpdateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        String uid = request.getParameter("userID");
        User user = userDao.getUserByID(uid);
        if (user != null) {
            request.setAttribute("update", true);
            request.setAttribute("u", user);
        }
        request.getRequestDispatcher("/auth/update.jsp").forward(request, response);
    }

    private void processUpdateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1) Lấy tham số
        String userName = request.getParameter("userName");
        String fullName = request.getParameter("Fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("Phone");

        String street = request.getParameter("street");
        String ward = request.getParameter("ward");
        String cityCode = request.getParameter("city");
        String avatar = request.getParameter("txtAvatarUser");

        String status = request.getParameter("status");
        boolean checkStatus = (status != null);;

        String roleId = request.getParameter("roleId");

        // 2) Map city
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

        UserDAO userDAO = new UserDAO();
        User current = userDAO.getUserByUsername(userName);
        if (current == null) {
            request.setAttribute("msg", "Không tìm thấy tài khoản cần cập nhật.");
            request.getRequestDispatcher("/auth/register.jsp?action=signUpUser").forward(request, response);
            return;
        }

        // 4) Validate 
        boolean hasError = false;
        String error_fullName = "";
        String error_email = "";
        String error_phone = "";
        String error_address = "";
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
        } else if (!email.equalsIgnoreCase(current.getUserEmail()) && userDAO.existsByEmail(email)) {
            error_email = "email used";
            hasError = true;
        }

        if (phone == null || phone.trim().isEmpty()) {
            error_phone = "Phone is required!";
            hasError = true;
        } else if (!phone.matches(Validation.PHONE_REGEX)) {
            error_phone = "đầu số hợp lệ (03, 05, 07, 08, 09)";
            hasError = true;
        } else if (!phone.equalsIgnoreCase(current.getUserPhone()) && userDAO.existsByPhone(phone)) {
            error_phone = "phone number used";
            hasError = true;
        }
        // Địa chỉ: chỉ bắt buộc nếu người dùng có nhập bất kỳ trường mới nào
        String address = current.getUserAddress(); // mặc định giữ địa chỉ cũ
        boolean hasNewAddress = false;

        // nếu có nhập bất kỳ phần nào trong ba trường
        if ((street != null && !street.trim().isEmpty())
                || (ward != null && !ward.trim().isEmpty())
                || (cityCode != null && !cityCode.trim().isEmpty())) {
            hasNewAddress = true;
        }
        if (hasNewAddress) {
            if (street == null || street.trim().isEmpty() || ward == null || ward.trim().isEmpty() || cityName.isEmpty()) {
                error_address = "Nhập địa chỉ mới, cần đủ Số/đường, Phường/Xã và Tỉnh/TP.";
                hasError = true;
            } else {
                address = street.trim() + ", " + ward.trim() + ", " + cityName;
            }
        }
        // Nếu có lỗi thì quay lại form
        if (hasError) {

            request.setAttribute("error_fullName", error_fullName);
            request.setAttribute("error_email", error_email);
            request.setAttribute("error_phone", error_phone);
            request.setAttribute("error_address", error_address);

            request.setAttribute("street", street);
            request.setAttribute("ward", ward);
            request.setAttribute("city", cityCode);

            // Lưu lại dữ liệu người dùng đã nhập
            request.setAttribute("fullName", fullName);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("u", current);
            request.getRequestDispatcher("/auth/update.jsp").forward(request, response);
            return;
        }

        // 6) Apply changes to 'current'
        current.setUserFullName(fullName);  // dùng đúng tên getter/setter mà DAO đang gọi
        current.setUserEmail(email);
        current.setUserPhone(phone);
        current.setUserAddress(address);
        current.setStatus(checkStatus);

        if (avatar != null && !avatar.trim().isEmpty()) {
            current.setAvatarURL(avatar.trim()); // DAO gọi getAvatarURL()
        }

        if (roleId != null && !roleId.trim().isEmpty()) {
            RoleDAO rDAO = new RoleDAO();
            Role role = rDAO.getById(roleId);
            if (role != null) {
                current.setRoleID(role); // DAO gọi getRoleID().getRoleID()
            }
        }

        boolean ok = userDAO.updateByUserName(current);
        if (ok) {
            request.setAttribute("msg_success", "Cập nhật thành công!");
            User refreshed = userDAO.getUserByUsername(userName);
            request.getRequestDispatcher("/admin/customer.jsp").forward(request, response);
        } else {
            request.setAttribute("error_message", "Update thất bại (không có dòng nào bị ảnh hưởng).");
            request.setAttribute("u", current);
            request.getRequestDispatcher("/auth/update.jsp").forward(request, response);
        }
    }

    private void processDeleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uid = request.getParameter("userID");
        System.out.println(uid);
        UserDAO userDAO = new UserDAO();
        userDAO.softDelete(uid);
        processSearchUser(request, response);
    }

    private void processChangePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1) Yêu cầu đăng nhập
        HttpSession session = request.getSession(false);
        User current = (session != null) ? (User) session.getAttribute("u") : null;
        if (current == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
            return;
        }

        // 2) Lấy input
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String confirm = request.getParameter("confirmPassword");

        // 3) Biến lỗi
        String error_oldPass = null;
        String error_newPass = null;
        String error_comPass = null;
        boolean hasError = false;

        // 4) Validate mật khẩu cũ (KIỂM TRA TRÊN RAW, KHÔNG HASH LẠI)
        if (oldPass == null || oldPass.trim().isEmpty()) {
            error_oldPass = "Vui lòng nhập mật khẩu cũ!";
            hasError = true;
        } else if (!PasswordUtils.matches(oldPass, current.getUserPassword())) {
            // matches(rawInput, hashedFromDB)
            error_oldPass = "Mật khẩu cũ không đúng!";
            hasError = true;
        }

        // 5) Validate mật khẩu mới
        if (newPass == null || newPass.trim().isEmpty()) {
            error_newPass = "Vui lòng nhập mật khẩu mới!";
            hasError = true;
        } else if (!newPass.matches(Validation.PASSWORD_REGEX)) {
            error_newPass = "Mật khẩu ≥8 ký tự, có chữ hoa, số, ký tự đặc biệt.";
            hasError = true;
        }

        // 6) Validate confirm
        if (confirm == null || confirm.trim().isEmpty()) {
            error_comPass = "Vui lòng nhập lại mật khẩu!";
            hasError = true;
        } else if ((error_newPass == null || error_newPass.isEmpty())
                && !newPass.trim().equals(confirm.trim())) {
            error_comPass = "Xác nhận mật khẩu không khớp!";
            hasError = true;
        }
        

        // 7) Nếu có lỗi → forward về form
        if (hasError) {
            request.setAttribute("error_oldPass", error_oldPass);
            request.setAttribute("error_newPass", error_newPass);
            request.setAttribute("error_comPass", error_comPass);
            request.setAttribute("pass", newPass);
            request.getRequestDispatcher("/auth/changePass.jsp").forward(request, response);
            return;
        }

        // 8) Cập nhật mật khẩu mới (hash rồi lưu DB)
        String hashedNew = PasswordUtils.hash(newPass);
        boolean ok = new UserDAO().updatePasswordByUserName(current.getUserName(), hashedNew);

        if (!ok) {
            request.setAttribute("msg_error", "Đổi mật khẩu thất bại. Thử lại sau.");
            request.getRequestDispatcher("/auth/changePass.jsp").forward(request, response);
            return;
        }

        // 9) Đăng xuất và chuyển về trang login
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp?msg=changed");
    }
    
    private void processResetPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String newPass = request.getParameter("newPassword");
        String confirm = request.getParameter("confirmPassword");
        UserDAO userDAO = new UserDAO();

        User user = userDAO.getUserByUsername(username);

        String msg = null;
        String error_newPass = null;
        String error_comPass = null;
        boolean hasError = false;
        //  Không tồn tại user
        if (user == null) {
            msg = "Sai username hoặc không tồn tại!";
            hasError = true;
        }
        //  Validate mật khẩu mới
        if (newPass == null || newPass.trim().isEmpty()) {
            error_newPass = "Vui lòng nhập mật khẩu mới!";
            hasError = true;
        } else if (!newPass.matches(Validation.PASSWORD_REGEX)) {
            error_newPass = "Mật khẩu ≥8 ký tự, có chữ hoa, số, ký tự đặc biệt.";
            hasError = true;
        }
        //  Validate confirm
        if (confirm == null || confirm.trim().isEmpty()) {
            error_comPass = "Vui lòng nhập lại mật khẩu!";
            hasError = true;
        } else if ((error_newPass == null || error_newPass.isEmpty())
                && !newPass.trim().equals(confirm.trim())) {
            error_comPass = "Xác nhận mật khẩu không khớp!";
            hasError = true;
        }

        //  Nếu có lỗi → forward về form
        if (hasError) {
            request.setAttribute("userName", username);
            request.setAttribute("pass", newPass);
            request.setAttribute("error_newPass", error_newPass);
            request.setAttribute("error_comPass", error_comPass);
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/auth/resetPass.jsp").forward(request, response);
            return;
        }
        
        //  Cập nhật mật khẩu mới (hash rồi lưu DB)
        String hashedNew = PasswordUtils.hash(newPass);
        boolean ok = new UserDAO().updatePasswordByUserName(user.getUserName(), hashedNew);

        if (!ok) {
            request.setAttribute("msg_error", "Đổi mật khẩu thất bại. Thử lại sau.");
            request.getRequestDispatcher("/auth/resetPass.jsp").forward(request, response);
            return;
        }

        //  Đăng xuất và chuyển về trang login
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");

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
        } else if (action.equals("searchUser")) {
            processSearchUser(request, response);
        } else if (action.equals("callUpdateUser")) {
            processCallUpdateUser(request, response);
        } else if (action.equals("updateUser")) {
            processUpdateUser(request, response);
        } else if (action.equals("deleteUser")) {
            processDeleteUser(request, response);
        } else if (action.equals("changePass")) {
            processChangePassword(request, response);
        } else if (action.equals("resetPass")) {
            processResetPassword(request, response);
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
