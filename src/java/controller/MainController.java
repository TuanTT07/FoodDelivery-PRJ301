/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //request user action
        String action = request.getParameter("action");
        System.out.println(action);
        //create a default URL that can change depending on the user’s action
        String url = "index.jsp";
        //Group actions for each controller
        String[] roleActions = {};
        String[] userActions = {"loginUser", "logout", "searchUser", "signUpUser", "callUpdateUser", "updateUser", "deleteUser", "signUpDelivery", "changePass", "resetPass", "viewInfoUser", "sendVerifyEmail", "verify"};
        String[] storeActions = {"signUpStore", "getStore", "updateStore", "searchStoreByCate", "searchStoreByLocation", "goToStoreDetail", "goToProductDetailForm", "searchStoreByName"};
        String[] productActions = {"addProduct", "deleteProduct", "searchProduct", "viewProduct", "editProduct", "updateProduct", "goToProductDetail"};
        String[] productDetailActions = {"addProductDetail"};
        String[] productOptionActions = {"addProductOption"};
        String[] toppingActions = {};
        String[] productToppingActions = {};
        String[] pictureActions = {"addProductPicture"};
        String[] cartActions = {"goToCart"};
        String[] cartItemActions = {"addToCart"};
        String[] checkoutAction = {"goToCheckout"};
        String[] categoryActions = {"addCategory", "updateCate", "viewCate", "addCate", "deleteCate", "activeCate", "goToProductForm"};
        String[] orderActions = {};
        String[] orderDetailActions = {};
        String[] paymentActions = {"placeOrder"};
        String[] paymentGatewayActions = {};
        String[] reviewActions = {};
        String[] eventActions = {};
        String[] voucherActions = {"callAddVoucher", "addVoucher", "searchVoucher", "callUpdateVoucher", "updateVoucher"};

        if (Arrays.asList(userActions).contains(action)) {
            url = "UserController";
        } else if (Arrays.asList(roleActions).contains(action)) {
            url = "RoleController";
        } else if (Arrays.asList(storeActions).contains(action)) {
            url = "StoreController";
        } else if (Arrays.asList(productActions).contains(action)) {
            url = "ProductController";
        } else if (Arrays.asList(productDetailActions).contains(action)) {
            url = "ProductDetailController";
        } else if (Arrays.asList(productOptionActions).contains(action)) {
            url = "ProductOptionController";
        } else if (Arrays.asList(toppingActions).contains(action)) {
            url = "ToppingController";
        } else if (Arrays.asList(productToppingActions).contains(action)) {
            url = "ProductToppingController";
        } else if (Arrays.asList(pictureActions).contains(action)) {
            url = "PictureController";
        } else if (Arrays.asList(cartActions).contains(action)) {
            url = "CartController";
        } else if (Arrays.asList(cartItemActions).contains(action)) {
            url = "CartItemController";
        } else if (Arrays.asList(categoryActions).contains(action)) {
            url = "CategoryController";
        } else if (Arrays.asList(orderActions).contains(action)) {
            url = "OrderController";
        } else if (Arrays.asList(checkoutAction).contains(action)) {
            url = "CheckoutController";
        } else if (Arrays.asList(orderDetailActions).contains(action)) {
            url = "OrderDetailController";
        } else if (Arrays.asList(paymentActions).contains(action)) {
            url = "PaymentController";
        } else if (Arrays.asList(paymentGatewayActions).contains(action)) {
            url = "PaymentGatewayController";
        } else if (Arrays.asList(reviewActions).contains(action)) {
            url = "ReviewController";
        } else if (Arrays.asList(eventActions).contains(action)) {
            url = "EventController";
        } else if (Arrays.asList(voucherActions).contains(action)) {
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
