/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //request user action
        String txtAction = request.getParameter("txtAction");
        //create a default URL that can change depending on the user’s action
        String url = "index.jsp";
        
        //Group actions for each controller
        String[] roleActions = {};
        String[] userActions = {"login", "logout", "searchUser", "addUser", "updateUser", "deleteUser"};
        String[] storeActions = {"registerStore", "viewStore", "updateStore"};
        String[] productActions = {"addProduct", "deleteProduct", "searchProduct", "viewProductList"};
        String[] productDetailActions = {"viewProductDetail"};       
        String[] productOptionActions = {"addProductOption"};
        String[] toppingActions = {"addTopping", "updateTopping"};
        String[] productToppingActions = {};
        String[] cartActions = {"viewCart", "clearCart"};
        String[] cartItemActions = {"addToCart", "updateCartItem", "deleteCartItem"};
        String[] categoryActions = {"addCategory", "updateCategory", "viewCategories"};       
        String[] orderActions = {"placeOrder", "viewOrderHistory", "cancelOrder", "confirmDelivery"};//confirmDelivery for shiper
        String[] orderDetailActions = {"viewOrderDetail"};
        String[] paymentActions = {"processPayment", "paymentSuccess", "paymentFail"};
        String[] paymentGatewayActions = {};        
        String[] reviewActions = {"submitReview", "viewReviews"};        
        String[] eventActions = {"createEvent", "applyEvent"}; 
        String[] voucherActions = {"applyVoucher", "createVoucher"}; //applyVoucher for user, createVoucher for admin or store owner
        
        
        
        if (Arrays.asList(userActions).contains(txtAction)) {
            url = "RoleController";
        }else if (Arrays.asList(roleActions).contains(txtAction)) {
            url = "UserController";
        }else if (Arrays.asList(storeActions).contains(txtAction)) {
            url = "StoreController";
        }else if (Arrays.asList(productActions).contains(txtAction)) {
            url = "ProductController";
        }else if (Arrays.asList(productDetailActions).contains(txtAction)) {
            url = "ProductDetailController";
        }else if (Arrays.asList(productOptionActions).contains(txtAction)) {
            url = "ProductOptionController";
        }else if (Arrays.asList(toppingActions).contains(txtAction)) {
            url = "ToppingController";
        }else if (Arrays.asList(productToppingActions).contains(txtAction)) {
            url = "ProductToppingController";
        }else if (Arrays.asList(cartActions).contains(txtAction)) {
            url = "CartController";
        }else if (Arrays.asList(cartItemActions).contains(txtAction)) {
            url = "CartItemController";
        }else if (Arrays.asList(categoryActions).contains(txtAction)) {
            url = "CategoryController";
        }else if (Arrays.asList(orderActions).contains(txtAction)) {
            url = "OrderController";
        }else if (Arrays.asList(orderDetailActions).contains(txtAction)) {
            url = "OrderDetailController";
        }else if (Arrays.asList(paymentActions).contains(txtAction)) {
            url = "PaymentController";
        }else if (Arrays.asList(paymentGatewayActions).contains(txtAction)) {
            url = "PaymentGatewayController";
        }else if (Arrays.asList(reviewActions).contains(txtAction)) {
            url = "ReviewController";
        }else if (Arrays.asList(eventActions).contains(txtAction)) {
            url = "EventController";
        }else if (Arrays.asList(voucherActions).contains(txtAction)) {
            url = "VoucherController";
        }
        request.getRequestDispatcher(url).forward(request, response);
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


