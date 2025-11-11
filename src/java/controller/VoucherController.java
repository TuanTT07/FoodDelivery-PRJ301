/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
import dao.StoreDAO;
import dao.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.Store;
import model.Voucher;
import dao.VoucherDAO;

/**
 *
 * @author ACER
 */
@WebServlet(name = "VoucherController", urlPatterns = {"/VoucherController"})
public class VoucherController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processCallAddVoucher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/admin/voucherForm.jsp").forward(request, response);
    }

    private void processAddVoucher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1) Lấy dữ liệu từ form
        String code = request.getParameter("voucherCode");
        String description = request.getParameter("description");
        String discountStr = request.getParameter("discountPercent");
        String minOrderStr = request.getParameter("minOrderAmount");
        String maxDiscountStr = request.getParameter("maxDiscountAmount");
        String startStr = request.getParameter("startDate");   // yyyy-MM-dd'T'HH:mm (datetime-local)
        String endStr = request.getParameter("endDate");
        boolean isActive = request.getParameter("isActive") != null;

        // 2) Chuẩn bị biến lỗi
        String error_voucherCode = "";
        String error_description = "";
        String error_discount = "";
        String error_minOrder = "";
        String error_maxDiscount = "";
        String error_startDate = "";
        String error_endDate = "";
        String message = "";

        boolean hasError = false;

        // 3) Validate
        VoucherDAO vDAO = new VoucherDAO();

        // code
        if (code == null || code.trim().isEmpty()) {
            error_voucherCode = "Mã voucher không được để trống!";
            hasError = true;
        } else if (code.length() > 50) {
            error_voucherCode = "Mã voucher tối đa 50 ký tự!";
            hasError = true;
        } else if (vDAO.existsByCode(code.trim())) { // cần triển khai trong DAO
            error_voucherCode = "Mã voucher đã tồn tại!";
            hasError = true;
        }

        // description 
        if (description != null && description.length() > 255) {
            error_description = "Mô tả tối đa 255 ký tự!";
            hasError = true;
        }

        double discount = 0, minOrder = 0, maxDiscount = 0;

        // discount %
        try {
            discount = Double.parseDouble(discountStr);
            if (discount <= 0 || discount > 100) {
                error_discount = "Giá trị giảm phải trong (0, 100] %";
                hasError = true;
            }
        } catch (Exception e) {
            error_discount = "Giá trị giảm không hợp lệ!";
            hasError = true;
        }

        // min order
        try {
            minOrder = (minOrderStr == null || minOrderStr.isEmpty()) ? 0 : Double.parseDouble(minOrderStr);
            if (minOrder < 0) {
                error_minOrder = "Đơn tối thiểu không âm!";
                hasError = true;
            }
        } catch (Exception e) {
            error_minOrder = "Đơn tối thiểu không hợp lệ!";
            hasError = true;
        }

        // max discount
        try {
            maxDiscount = (maxDiscountStr == null || maxDiscountStr.isEmpty()) ? 0 : Double.parseDouble(maxDiscountStr);
            if (maxDiscount < 0) {
                error_maxDiscount = "Giảm tối đa không âm!";
                hasError = true;
            }
        } catch (Exception e) {
            error_maxDiscount = "Giảm tối đa không hợp lệ!";
            hasError = true;
        }

        // ngày giờ
        DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime startDate = null, endDate = null;

        if (startStr != null && !startStr.isEmpty()) {
            try {
                startDate = LocalDateTime.parse(startStr, FMT);
            } catch (Exception e) {
                error_startDate = "Ngày bắt đầu không hợp lệ!";
                hasError = true;
            }
        } else {
            error_startDate = "Vui lòng chọn ngày bắt đầu!";
            hasError = true;
        }

        if (endStr != null && !endStr.isEmpty()) {
            try {
                endDate = LocalDateTime.parse(endStr, FMT);
            } catch (Exception e) {
                error_endDate = "Ngày kết thúc không hợp lệ!";
                hasError = true;
            }
        } else {
            error_endDate = "Vui lòng chọn ngày kết thúc!";
            hasError = true;
        }

        if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
            error_endDate = "Ngày kết thúc phải sau ngày bắt đầu!";
            hasError = true;
        }

        // 4) Nếu lỗi → trả về form, giữ lại dữ liệu đã nhập
        if (hasError) {
            request.setAttribute("message", "Vui lòng kiểm tra lại thông tin!");
            request.setAttribute("error_voucherCode", error_voucherCode);
            request.setAttribute("error_description", error_description);
            request.setAttribute("error_discountPercent", error_discount);
            request.setAttribute("error_minOrderAmount", error_minOrder);
            request.setAttribute("error_maxDiscountAmount", error_maxDiscount);
            request.setAttribute("error_startDate", error_startDate);
            request.setAttribute("error_endDate", error_endDate);

            // trả lại các giá trị form
            request.setAttribute("voucherCode", code);
            request.setAttribute("description", description);
            request.setAttribute("discountPercent", discountStr);
            request.setAttribute("minOrderAmount", minOrderStr);
            request.setAttribute("maxDiscountAmount", maxDiscountStr);
            request.setAttribute("startDateStr", startStr);
            request.setAttribute("endDateStr", endStr);
            request.setAttribute("isActive", isActive);

            request.getRequestDispatcher("/admin/voucher.jsp").forward(request, response);
            return;
        }

        Voucher v = new Voucher();

        v.setVoucherCode(code.trim());
        v.setDescription(description);
        v.setDiscountPercent(discount);
        v.setMinOrderAmount(minOrder);
        v.setMaxDiscountAmount(maxDiscount);
        v.setStartDate(startDate);
        v.setEndDate(endDate);
        v.setIsActive(isActive);
        v.setCreatedAt(LocalDateTime.now());

        boolean ok = vDAO.insertVoucher(v);

        // 6) Điều hướng/hiển thị message
        if (ok) {
            request.setAttribute("message", "Thêm voucher thành công!");
            request.getRequestDispatcher("/admin/vouchers.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Thêm voucher thất bại! Vui lòng thử lại.");
            // giữ lại data khi fail DB
            request.setAttribute("voucherCode", code);
            request.setAttribute("description", description);
            request.setAttribute("discountPercent", discountStr);
            request.setAttribute("minOrderAmount", minOrderStr);
            request.setAttribute("maxDiscountAmount", maxDiscountStr);
            request.setAttribute("startDateStr", startStr);
            request.setAttribute("endDateStr", endStr);
            request.setAttribute("isActive", isActive);
            request.getRequestDispatcher("/admin/voucherForm.jsp").forward(request, response);
        }
    }

    private void processSearchVoucher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        VoucherDAO voucherDao = new VoucherDAO();
        ArrayList<Voucher> listOfVoucher = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            listOfVoucher = voucherDao.getAllVoucher();
        } else {
            //listOfVoucher = storeDao.getAllStoreByName(name);
        }

        request.setAttribute("listOfVoucher", listOfVoucher);
        request.setAttribute("name", name);
        request.getRequestDispatcher("/admin/voucher.jsp").forward(request, response);
    }

    private void processCallUpdateVoucher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("voucherID");
        VoucherDAO dao = new VoucherDAO();
        Voucher v = dao.getVoucherByID(id);

        request.setAttribute("v", v);
        request.getRequestDispatcher("/admin/voucherEdit.jsp").forward(request, response);
    }

    private void processUpdateVoucher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String id = request.getParameter("voucherID");
        String code = request.getParameter("voucherCode");
        String description = request.getParameter("description");
        String discountStr = request.getParameter("discountPercent");
        String minOrderStr = request.getParameter("minOrderAmount");
        String maxDiscountStr = request.getParameter("maxDiscountAmount");
        String startStr = request.getParameter("startDate");
        String endStr = request.getParameter("endDate");
        boolean isActive = request.getParameter("isActive") != null;

        
        String error_voucherCode = "";
        String error_description = "";
        String error_discount = "";
        String error_minOrder = "";
        String error_maxDiscount = "";
        String error_startDate = "";
        String error_endDate = "";
        String message = "";

        boolean hasError = false;
        VoucherDAO vDAO = new VoucherDAO();

        // 3️⃣ Validate cơ bản
        if (code == null || code.trim().isEmpty()) {
            error_voucherCode = "Mã voucher không được để trống!";
            hasError = true;
        } else if (code.length() > 50) {
            error_voucherCode = "Mã voucher tối đa 50 ký tự!";
            hasError = true;
        }

        if (description != null && description.length() > 255) {
            error_description = "Mô tả tối đa 255 ký tự!";
            hasError = true;
        }

        double discount = 0, minOrder = 0, maxDiscount = 0;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime startDate = null, endDate = null;

        try {
            discount = Double.parseDouble(discountStr);
            if (discount <= 0 || discount > 100) {
                error_discount = "Giảm (%) phải trong (0,100]!";
                hasError = true;
            }
        } catch (Exception e) {
            error_discount = "Giá trị giảm không hợp lệ!";
            hasError = true;
        }

        try {
            minOrder = (minOrderStr == null || minOrderStr.isEmpty()) ? 0 : Double.parseDouble(minOrderStr);
            if (minOrder < 0) {
                error_minOrder = "Đơn tối thiểu không âm!";
                hasError = true;
            }
        } catch (Exception e) {
            error_minOrder = "Đơn tối thiểu không hợp lệ!";
            hasError = true;
        }

        try {
            maxDiscount = (maxDiscountStr == null || maxDiscountStr.isEmpty()) ? 0 : Double.parseDouble(maxDiscountStr);
            if (maxDiscount < 0) {
                error_maxDiscount = "Giảm tối đa không âm!";
                hasError = true;
            }
        } catch (Exception e) {
            error_maxDiscount = "Giảm tối đa không hợp lệ!";
            hasError = true;
        }

        try {
            if (startStr != null && !startStr.isEmpty()) {
                startDate = LocalDateTime.parse(startStr, fmt);
            }
            if (endStr != null && !endStr.isEmpty()) {
                endDate = LocalDateTime.parse(endStr, fmt);
            }
            if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
                error_endDate = "Ngày kết thúc phải sau ngày bắt đầu!";
                hasError = true;
            }
        } catch (Exception e) {
            error_startDate = "Ngày không hợp lệ!";
            hasError = true;
        }

       
        if (hasError) {
            request.setAttribute("error_voucherCode", error_voucherCode);
            request.setAttribute("error_description", error_description);
            request.setAttribute("error_discountPercent", error_discount);
            request.setAttribute("error_minOrderAmount", error_minOrder);
            request.setAttribute("error_maxDiscountAmount", error_maxDiscount);
            request.setAttribute("error_startDate", error_startDate);
            request.setAttribute("error_endDate", error_endDate);

            Voucher v = new Voucher(id, code, description, discount, minOrder, maxDiscount, startDate, endDate, isActive, null);
            request.setAttribute("v", v);
            request.setAttribute("message", "Vui lòng kiểm tra lại thông tin!");

            request.getRequestDispatcher("/admin/voucherEdit.jsp").forward(request, response);
            return;
        }

        
        Voucher v = new Voucher();
        v.setVoucherID(id);
        v.setVoucherCode(code.trim());
        v.setDescription(description);
        v.setDiscountPercent(discount);
        v.setMinOrderAmount(minOrder);
        v.setMaxDiscountAmount(maxDiscount);
        v.setStartDate(startDate);
        v.setEndDate(endDate);
        v.setIsActive(isActive);

        boolean ok = vDAO.updateVoucher(v);

        
        if (ok) {
            message = "Cập nhật voucher thành công!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/admin/voucher.jsp").forward(request, response);
        } else {
            message = "Cập nhật thất bại! Vui lòng thử lại.";
            request.setAttribute("v", v);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/admin/voucherEdit.jsp").forward(request, response);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("callAddVoucher")) {
            processCallAddVoucher(request, response);
        } else if (action.equals("addVoucher")) {
            processAddVoucher(request, response);
        } else if (action.equals("searchVoucher")) {
            processSearchVoucher(request, response);
        } else if (action.equals("callUpdateVoucher")) {
            processCallUpdateVoucher(request, response);
        } else if (action.equals("updateVoucher")) {
            processUpdateVoucher(request, response);
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
