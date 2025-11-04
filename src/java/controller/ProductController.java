/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
import dao.PictureDAO;
import dao.ProductDAO;
import dao.ProductDetailDAO;
import dao.ProductOptionDAO;
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
import model.Picture;
import model.Product;
import model.ProductDetail;
import model.ProductOption;
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

    private void processAddProduct(HttpServletRequest request, HttpServletResponse response, boolean update)
            throws ServletException, IOException {
        String txtProductName = request.getParameter("productName");
        String txtProductPrice = request.getParameter("productPrice");
        String txtProductDesc = request.getParameter("productDesc");
        String txtCategoryId = request.getParameter("categoryID");
        String txtStoreId = request.getParameter("storeId");
        ProductDAO productDAO = new ProductDAO();

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
        String error_productCate = "";
        String error_ProductInCate = "";

        if (productDAO.checkProductInCate(txtCategoryId, txtProductName)) {
            error_ProductInCate = "Sản phẩm đã có trong danh mục! Vui lòng chọn danh mục khác hoặc đặt tên khác.";
            hasError = true;

        }

        if (productDAO.getProductByProductname(txtProductName) != null) {
            error_productName = "Tên sản phẩm đã tồn tại! Không được trùng!";
            hasError = true;
        }

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
        if (cate == null) {
            error_productCate = "Vui lòng chọn danh mục sản phẩm!";
            hasError = true;

        }

        if (hasError) {
            request.setAttribute("error_productName", error_productName);
            request.setAttribute("error_productPrice", error_productPrice);
            request.setAttribute("error_productDesc", error_productDesc);
            request.setAttribute("error_productCate", error_productCate);
            request.setAttribute("error_ProductInCate", error_ProductInCate);

            // giữ lại giá trị user nhập để không phải nhập lại
            request.setAttribute("txtProductName", txtProductName);
            request.setAttribute("txtProductPrice", txtProductPrice);
            request.setAttribute("txtProductDesc", txtProductDesc);

            request.getRequestDispatcher("store/formProduct.jsp").forward(request, response);
            return;
        }

        try {
            Product product = new Product(txtProductName, Double.parseDouble(txtProductPrice), txtProductDesc, cate, store);

            boolean success = false;
            if (update) { // nếu đang update
                String productID = request.getParameter("productID");
                product.setProductID(productID);
                success = productDAO.updateProduct(product);

            } else { // đang add
                success = productDAO.insert(product);
            }

            if (!success) {
                request.setAttribute("error_producAdd", update ? "Cập nhật thất bại!" : "Thêm thất bại!");
                request.getRequestDispatcher("store/formProduct.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect back product list
        response.sendRedirect("MainController?action=viewProduct&storeID=" + txtStoreId);

    }

    private void processGotoFormProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productID = request.getParameter("productID");
        ProductDAO pdao = new ProductDAO();
        Product product = pdao.getProductByProductID(productID);

        CategoryDAO cateDAO = new CategoryDAO();
        HttpSession session = request.getSession();
        session.setAttribute("listOfCate", cateDAO.getAllCateByStoreID(product.getStoreID().getStoreID()));

        request.setAttribute("product", product);
        request.setAttribute("isUpdate", true);
        request.getRequestDispatcher("store/formProduct.jsp").forward(request, response);
    }

    private void processDeleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productID = request.getParameter("productID");
        ProductDAO pdao = new ProductDAO();

        // dùng hard delete hoặc softDelete tùy m chọn
        pdao.softDelete(productID);

        String storeID = request.getParameter("storeID");
        response.sendRedirect("MainController?action=viewProduct&storeID=" + storeID);
    }

    private void processGoToProductDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtProductID = request.getParameter("productID");

        ProductDAO proDAO = new ProductDAO();
        Product p = proDAO.getProductByProductID(txtProductID);

        ProductDetailDAO pdDAO = new ProductDetailDAO();

        ProductOptionDAO poDAO = new ProductOptionDAO();

        PictureDAO picDAO = new PictureDAO();
        if (p == null) {
            request.setAttribute("error", "Không tìm thấy sản phẩm này!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        ArrayList<Picture> listOfPictures = picDAO.getProductPictureByPID(txtProductID);
        ArrayList<ProductDetail> listOfDetails = pdDAO.getProductDetailByPID(txtProductID);
        ArrayList<ProductOption> listOfOptions = poDAO.getProductOptionByPID(txtProductID);

        request.setAttribute("p", p);
        request.setAttribute("listOfPictures", listOfPictures);
        request.setAttribute("listOfDetails", listOfDetails);
        request.setAttribute("listOfOptions", listOfOptions);

        request.getRequestDispatcher("product/productDetail.jsp").forward(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("addProduct")) {
            processAddProduct(request, response, false);
        } else if (action.equals("viewProduct")) {
            processViewProduct(request, response);
        } else if (action.equals("editProduct")) {
            processGotoFormProduct(request, response);
        } else if (action.equals("updateProduct")) {
            processAddProduct(request, response, true);
        } else if (action.equals("deleteProduct")) {
            processDeleteProduct(request, response);
        } else if (action.equals("goToProductDetail")) {
            processGoToProductDetail(request, response);
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
