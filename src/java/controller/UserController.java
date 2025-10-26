/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        String role = user.getRoleID() != null ? user.getRoleID().getRoleID() : "";

        if (Validation.ROLE_ADMIN.equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
        } else if (Validation.ROLE_STORE_OWNER.equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/store/dashboard.jsp");
        } else if (Validation.ROLE_DRIVER.equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/delivery/dashboard.jsp");
        } else if (Validation.ROLE_MEMBER.equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            request.setAttribute("msg", "Invalid account!");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        }
    }

    private void processSearchStoreByLoaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String location = request.getParameter("location");
        if (location.equalsIgnoreCase("1")) {
            location = "TP. Hồ Chí Minh";
        } else if (location.equalsIgnoreCase("2")) {
            location = "Hà Nội";

        } else if (location.equalsIgnoreCase("3")) {
            location = "Đà Nẵng";

        } else if (location.equalsIgnoreCase("4")) {
            location = "Cần Thơ";
        }
        String url = "";
        String messErrorLocation = "";
        StoreDAO sDAO = new StoreDAO();
        if (location == null || location.trim().length() == 0) {
            url = "index.jsp";
            messErrorLocation = "Vui lòng điền thành phố mà bạn đang sinh sống!";
            request.setAttribute("messErrorLocation", messErrorLocation);
        } else {
            ArrayList<Store> listOfStore = new ArrayList<>();
            listOfStore = sDAO.selectStoreByLocation(location);
            request.setAttribute("location", location);
            request.setAttribute("listOfStore", listOfStore);
            url = "/user/userHome.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // Huy tat ca nhung cai dang co trong session
        response.sendRedirect("login.jsp");
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
        } else if (action.equals("searchStoreByLocation")) {
            processSearchStoreByLoaction(request, response);
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
