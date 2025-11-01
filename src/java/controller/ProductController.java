/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import dao.StoreDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.Product;
import model.Store;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    private void processViewProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtStoreId = request.getParameter("storeID");

        ArrayList<Product> listOfProduct = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        listOfProduct = productDAO.getAllProductByStoreId(txtStoreId);
        HttpSession session = request.getSession();
        session.setAttribute("listOfProduct", listOfProduct);

        if (listOfProduct.isEmpty()) {
            request.setAttribute("error_listProduct", "Danh sách sản phẩm rỗng! Vui lòng thêm sản phẩm");
            request.getRequestDispatcher("store/products.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("store/products.jsp").forward(request, response);
    }

    private void processAddProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtProductName = request.getParameter("productName");
        String txtProductPrice = request.getParameter("productPrice");
        String txtProductDesc = request.getParameter("productDesc");
        String txtCategoryId = request.getParameter("categoryID");
        String txtStoreId = request.getParameter("storeId");

        if (txtCategoryId == null || txtStoreId == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        CategoryDAO cateDAO = new CategoryDAO();
        Category cate = cateDAO.getCateByCateID(txtCategoryId);
        StoreDAO storeDAO = new StoreDAO();
        Store store = storeDAO.getStoreByID(txtStoreId);

        boolean hasError = false;
        String error_productName = "";
        String error_productPrice = "";
        String error_productDesc = "";

        if (txtProductName == null || txtProductName.trim().isEmpty()) {
            error_productName = "Tên sản phẩm không được để trống!";
            hasError = true;
        }
        if (txtProductPrice == null || txtProductPrice.trim().isEmpty()) {
            error_productPrice = "Giá sản phẩm không được để trống!";
            hasError = true;

        }
        if (txtProductDesc == null || txtProductDesc.trim().isEmpty()) {
            error_productDesc = "Vui lòng nhập mô tả sản phẩm!";
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("txtProductName", txtProductName);
            request.setAttribute("txtProductPrice", txtProductPrice);
            request.setAttribute("txtProductDesc", txtProductDesc);
            request.getRequestDispatcher("store/formProduct.jsp").forward(request, response);
            return;
        }

        try {
            ProductDAO productDAO = new ProductDAO();
            Product product = new Product(txtProductName, Double.parseDouble(txtProductPrice), txtProductDesc, cate, store);

            if (!productDAO.insert(product)) {
                request.setAttribute("error_producAdd", "Quá trình thêm thất bại! Vui lòng thử lại sau!");
                request.getRequestDispatcher("store/formProduct.jsp").forward(request, response);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect back product list
        response.sendRedirect("MainController?action=viewProduct&storeID=" + txtStoreId);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("addProduct")) {
            processAddProduct(request, response);
        } else if (action.equals("viewProduct")) {
            processViewProduct(request, response);
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
