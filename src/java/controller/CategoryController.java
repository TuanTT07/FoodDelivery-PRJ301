/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
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
import model.Store;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryController"})
public class CategoryController extends HttpServlet {

    private void processAddCate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtCateName = request.getParameter("cateName");
        String txtStoreId = request.getParameter("storeID");
        StoreDAO storeDAO = new StoreDAO();
        Store store = storeDAO.getStoreByID(txtStoreId);

        if (txtCateName == null || txtCateName.trim().length() == 0) {
            request.setAttribute("error_cate", "không được để trống tên danh mục!");
            request.getRequestDispatcher("/store/category.jsp").forward(request, response);
            return;
        }
        CategoryDAO cateDAO = new CategoryDAO();

        Category cate = new Category(txtCateName, store);
        boolean check = cateDAO.getCateByName(txtCateName);
        if (check) {
            request.setAttribute("error_cate", "Tên danh mục đã tồn tại! Vui lòng nhập tên khác.");
            request.setAttribute("error_name", txtCateName);
            request.getRequestDispatcher("/store/category.jsp").forward(request, response);
            return;
        }
        if (!cateDAO.insertCate(cate)) {
            request.setAttribute("error_cate", "Quá trình thêm danh mục bị lỗi!");
            request.getRequestDispatcher("/store/category.jsp").forward(request, response);

        }
        processViewCate(request, response);
    }

    private void processViewCate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String txtStoreID = request.getParameter("storeID");

        CategoryDAO cateDAO = new CategoryDAO();
        ArrayList<Category> listOfCate = cateDAO.getAllCateByStoreID(txtStoreID);

        HttpSession session = request.getSession();
        session.setAttribute("listOfCate", listOfCate);

        // Nếu user bấm thêm sản phẩm
        if ("goToProductForm".equals(action)) {

            if (listOfCate == null || listOfCate.isEmpty()) {
                request.setAttribute("error_listOfCate", "Bạn cần tạo Category trước khi thêm món ăn!");
                request.getRequestDispatcher("store/category.jsp").forward(request, response);
                return;
            }

            request.getRequestDispatcher("store/formProduct.jsp").forward(request, response);
            return;
        }

        // Mặc định -> show category list
        if (listOfCate.isEmpty()) {
            request.setAttribute("error_listOfCate", "Chưa có thực đơn nào!");
        }

        request.getRequestDispatcher("store/category.jsp").forward(request, response);
    }

    private void processUpdateCate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtIdCate = request.getParameter("idCate");
        String txtCateName = request.getParameter("cateName");
        if (txtCateName == null || txtCateName.trim().length() == 0) {
            request.setAttribute("error_cate", "không được để trống tên danh mục");
            processViewCate(request, response);
            return;
        }
        CategoryDAO cateDAO = new CategoryDAO();
        boolean checkUpdate = cateDAO.updateCate(txtCateName, txtIdCate);
        if (!checkUpdate) {
            request.setAttribute("error_cate", "Quá trình chỉnh sửa danh mục bị lỗi!");
            processViewCate(request, response);
            return;
        }
        processViewCate(request, response);
    }

    private void processDeleteCate(HttpServletRequest request, HttpServletResponse response, boolean delete)
            throws ServletException, IOException {
        String txtIdCate = request.getParameter("idCate");
        CategoryDAO cateDAO = new CategoryDAO();
        if (delete) {
            boolean checkDelete = cateDAO.softDelete(txtIdCate);
            if (!checkDelete) {
                request.setAttribute("error_cate", "Quá trình xoa danh mục bị lỗi!");
                processViewCate(request, response);
                return;
            }
        } else {
            boolean checkActive = cateDAO.activeCate(txtIdCate);
            if (!checkActive) {
                request.setAttribute("error_cate", "Quá trình kích hoạt danh mục bị lỗi!");
                processViewCate(request, response);
                return;
            }
        }

        processViewCate(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action.contains("addCate")) {
            processAddCate(request, response);
        } else if (action.equals("viewCate")) {
            processViewCate(request, response);
        } else if (action.equals("updateCate")) {
            processUpdateCate(request, response);
        } else if (action.equals("deleteCate")) {
            processDeleteCate(request, response, true);
        } else if (action.equals("activeCate")) {
            processDeleteCate(request, response, false);
        } else if (action.equals("goToProductForm")) {
            processViewCate(request, response);
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
