/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.ProductDAO;
import dao.UserDAO;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CheckoutItem;
import model.Product;
import model.SelectedItem;
import model.User;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    private void processGoToCheckout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedItemsJson = request.getParameter("selectedItems");
        String userID = request.getParameter("uID");
        System.out.println("SelectedItems JSON = " + selectedItemsJson);

        UserDAO uDAO = new UserDAO();
        User u = uDAO.getUserByID(userID);
        if (u != null) {
            // Cắt địa chỉ thành 3 phần nếu có dấu phẩy
            String fullAddress = u.getUserAddress(); // "25 Thành Thiện, Long Bình, Cần Thơ"
            String address = "", ward = "", city = "";
            if (fullAddress != null && !fullAddress.isEmpty()) {
                String[] parts = fullAddress.split(",");
                if (parts.length > 0) {
                    address = parts[0].trim();
                }
                if (parts.length > 1) {
                    ward = parts[1].trim();
                }
                if (parts.length > 2) {
                    city = parts[2].trim();
                }
            }

            // Gửi dữ liệu qua JSP
            request.setAttribute("user", u);
            request.setAttribute("addr", address);
            request.setAttribute("ward", ward);
            request.setAttribute("city", city);
        }

        if (selectedItemsJson == null || selectedItemsJson.trim().isEmpty()) {
            // không có item => redirect về cart
            response.sendRedirect("cart.jsp");
            return;
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<List<SelectedItem>>() {
        }.getType();
        List<SelectedItem> selected = gson.fromJson(selectedItemsJson, listType);

        ProductDAO productDAO = new ProductDAO();
        List<CheckoutItem> checkoutItems = new ArrayList<>();
        System.out.println("Checkout items count = " + checkoutItems.size());

        double grandTotal = 0.0;

        for (SelectedItem s : selected) {
            Product p = productDAO.getProductByProductID(s.getProductId());
            if (p == null) {
                continue;
            }
            double price = p.getProductPrice();
            double subtotal = price * s.getQuantity();
            checkoutItems.add(new CheckoutItem(p.getProductID(), p.getProductName(), price, s.getQuantity()));
            grandTotal += subtotal;
        }

        request.setAttribute("checkoutItems", checkoutItems);
        request.setAttribute("grandTotal", grandTotal);
        request.setAttribute("userID", userID);
        request.getRequestDispatcher("/checkout.jsp").forward(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action.equals("goToCheckout")) {
            processGoToCheckout(request, response);
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
