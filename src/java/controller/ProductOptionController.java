/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import dao.ProductOptionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.ProductOption;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ProductOptionController", urlPatterns = {"/ProductOptionController"})
public class ProductOptionController extends HttpServlet {

    public void processAddProductOption(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtProductID = request.getParameter("productID");

        String[] optionTypes = request.getParameterValues("optionType");
        String[] optionValues = request.getParameterValues("optionValue");
        String[] extraPrices = request.getParameterValues("extraPrice");
        boolean hasError = false;
        String error = "";

        if (optionTypes == null || optionTypes.length == 0) {
            error = "Vui lòng nhập ít nhất một tùy chọn sản phẩm!";
            hasError = true;
        }

        ProductDAO pDAO = new ProductDAO();
        Product p = pDAO.getProductByProductID(txtProductID);
        if (p == null) {
            error = "Không tìm thấy sản phẩm!";
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/MainController?action=goToProductDetailForm&productID=" + txtProductID)
                    .forward(request, response);
            return;
        }

        // Insert từng option
        ProductOptionDAO optDAO = new ProductOptionDAO();
        int successCount = 0;
        for (int i = 0; i < optionTypes.length; i++) {
            String txtType = optionTypes[i].trim();
            String txtValue = optionValues[i].trim();
            String txtPriceStr = extraPrices[i];
            double price = 0;

            if (!txtPriceStr.isEmpty()) {
                try {
                    price = Double.parseDouble(txtPriceStr);
                    if (price < 0) {
                        price = 0;
                    }
                } catch (NumberFormatException e) {
                    price = 0;
                }
            }
            System.out.println("Type:" + txtType);
            System.out.println("Value:" + txtValue);
            System.out.println("price:" + txtPriceStr);
            ProductOption option = new ProductOption(txtType, txtValue, price, p);

            if (optDAO.insertProductOption(option)) {
                successCount++;
            }
        }

        if (successCount == optionTypes.length) {
            request.setAttribute("successOption", "Đã thêm " + successCount + " tùy chọn sản phẩm thành công!");
        } else {
            request.setAttribute("errorOption", "Một số tùy chọn thêm không thành công!");
        }

        request.getRequestDispatcher("/MainController?action=goToProductDetailForm&productID=" + txtProductID)
                .forward(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action.equals("addProductOption")) {
            processAddProductOption(request, response);
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
