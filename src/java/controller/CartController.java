package controller;

import dao.CartDAO;
import dao.CartItemDAO;
import dao.ProductDAO;
import dao.ProductDetailDAO;
import dao.UserDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.CartItem;
import model.Product;
import model.User;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

    private void processGoToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String txtUserID = request.getParameter("userId");

        CartDAO cartDAO = new CartDAO();
        Cart c = cartDAO.getCartByUserId(txtUserID);

        // Nếu chưa có thì tạo mới
        if (c == null) {
            UserDAO uDAO = new UserDAO();
            User u = uDAO.getUserByID(txtUserID);
            c = new Cart(null, u, 0);
            cartDAO.insertCart(c);
            c = cartDAO.getCartByUserId(txtUserID); // lấy lại ID sau insert
        }

        // lấy cart items
        CartItemDAO ciDAO = new CartItemDAO();
        ArrayList<CartItem> listOfItems = ciDAO.getCartItemsByCartId(c.getCartID());
        request.setAttribute("cart", c);
        request.setAttribute("cartItems", listOfItems);

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action.equals("goToCart")) {
            processGoToCart(request, response);
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
