/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import dao.CartIteamDAO;
import dao.ProductDAO;
import dao.StoreDAO;
import dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.CartItem;
import model.Product;
import model.Store;
import model.User;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CartItemController", urlPatterns = {"/CartItemController"})
public class CartItemController extends HttpServlet {

    private void processAddToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtUserId = request.getParameter("uID");
        String txtProductID = request.getParameter("productId");
        String txtQuantity = request.getParameter("quantity");

        CartDAO cartDAO = new CartDAO();
        Cart cart = cartDAO.getCartByUserId(txtUserId);
        System.out.println(cart.toString());
        if (cart == null) {
            UserDAO uDAO = new UserDAO();
            User u = uDAO.getUserByID(txtUserId);
            cart = new Cart(null, u, 0);
            cartDAO.insertCart(cart);
            cart = cartDAO.getCartByUserId(txtUserId);
        }

        ProductDAO pDAO = new ProductDAO();
        Product p = pDAO.getProductByProductID(txtProductID);
        System.out.println(p);
        UserDAO uDAO = new UserDAO();
        User u = uDAO.getUserByID(txtUserId);
        CartItem cartItem = new CartItem(null, cart, p, Integer.parseInt(txtQuantity));

        CartIteamDAO cartIDAO = new CartIteamDAO();
        if (cartIDAO.insertCartItem(cartItem)) {
            response.sendRedirect(request.getContextPath() + "/MainController?storeId=" + p.getStoreID().getStoreID() + "&action=goToStoreDetail");
        } else {
            request.setAttribute("error", "Không thể thêm sản phẩm vào giỏ hàng.");

            request.getRequestDispatcher(request.getContextPath() + "/MainController?action=goToProductDetail&productID=" + txtProductID)
                    .forward(request, response);
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action.equals("addToCart")) {
            processAddToCart(request, response);
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
