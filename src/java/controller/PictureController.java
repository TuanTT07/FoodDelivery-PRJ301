/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.PictureDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Picture;
import model.Product;

/**
 *
 * @author ACER
 */
@WebServlet(name = "PictureController", urlPatterns = {"/PictureController"})
public class PictureController extends HttpServlet {

    private void processAddProductPicture(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtProductID = request.getParameter("productID");
        String base64List = request.getParameter("base64List");
        String mainIndexStr = request.getParameter("mainIndex");
        int txtMainIndex = mainIndexStr != null ? Integer.parseInt(mainIndexStr) : -1;
        List<String> pictures = new Gson().fromJson(base64List, new TypeToken<List<String>>() {
        }.getType());

        PictureDAO picDAO = new PictureDAO();
        ProductDAO productDAO = new ProductDAO();
        Product p = productDAO.getProductByProductID(txtProductID);

        if (p == null) {
            request.setAttribute("errorPicture", "Không tìm thấy sản phẩm!");
            request.getRequestDispatcher("/MainController?action=goToProductDetailForm&productID=" + txtProductID)
                    .forward(request, response);
            return;
        }
        int successCount = 0;
        for (int i = 0; i < pictures.size(); i++) {
            String base64 = pictures.get(i);

            Picture pic = new Picture();
            pic.setProductId(p);
            pic.setPictureURL(base64);
            pic.setIsMain(i == txtMainIndex);
            boolean check = picDAO.insertPicture(pic);
            System.out.println(check);
            if (check) {
                successCount++;
            }
        }

        request.setAttribute("successPicture", "Đã thêm " + successCount + " hình ảnh thành công!");
        request.getRequestDispatcher("/MainController?action=goToProductDetailForm&productID=" + txtProductID)
                .forward(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action.equals("addProductPicture")) {
            processAddProductPicture(request, response);
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
