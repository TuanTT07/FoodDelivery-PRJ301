/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import dao.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.ProductDetail;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ProductDetailController", urlPatterns = {"/ProductDetailController"})
public class ProductDetailController extends HttpServlet {

    private void processAddProductDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtProductID = request.getParameter("productID");
        String txtSize = request.getParameter("size");
        String txtCombo = request.getParameter("combo");
        String txtExtraInfo = request.getParameter("extraInfo");

        boolean hasError = false;
        String error_Size = "";
        String error_Com = "";
        String error_ExtraInfo = "";
        String error = "";

        if (txtSize == null || txtSize.trim().isEmpty()) {
            error_Size = "Vui lòng chọn kích cỡ sản phẩm!";
            hasError = true;
        }
        if (txtCombo == null || txtCombo.trim().isEmpty()) {
            error_Com = "Vui lòng đề xuất combo cho khách hàng!";
            hasError = true;
        }
        if (txtExtraInfo == null || txtExtraInfo.trim().isEmpty()) {
            error_ExtraInfo = "Vui lòng nhập mô tả!";
            hasError = true;
        }

        ProductDAO pDAO = new ProductDAO();
        Product p = pDAO.getProductByProductID(txtProductID);
        if (p == null) {
            error = "Không tìm thấy sản phẩm thích hợp!";
            hasError = true;
        }
        if (hasError) {
            request.setAttribute("error_Size", error_Size);
            request.setAttribute("error_Com", error_Com);
            request.setAttribute("error_ExtraInfo", error_ExtraInfo);
            request.setAttribute("error", error);
            request.getRequestDispatcher("/MainController?action=goToProductDetailForm&productID=" + txtProductID).forward(request, response);
            return;

        }

        ProductDetail pDetail = new ProductDetail(txtSize, txtCombo, txtExtraInfo, p);
        ProductDetailDAO pdDAO = new ProductDetailDAO();
        if (!pdDAO.insertProductDetail(pDetail)) {
            error = "Quá trình thêm chi tiết sản phẩm bị lỗi. Vui lòng thử lại!";
            request.getRequestDispatcher("/MainController?action=goToProductDetailForm&productID=" + txtProductID).forward(request, response);
            return;
        }
        request.setAttribute("success", "Đã thêm chi tiết sản phẩm thành công");
        request.getRequestDispatcher("/MainController?action=goToProductDetailForm&productID=" + txtProductID).forward(request, response);
        return;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        if (action.equals("addProductDetail")) {
            processAddProductDetail(request, response);
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
