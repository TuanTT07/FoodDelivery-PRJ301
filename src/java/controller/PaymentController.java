/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import common.Config;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.OrderDAO;
import dao.UserDAO;
import model.Order;
import model.User;
import model.enums.OrderStatus;

/**
 *
 * @author ACER
 */
@WebServlet(name = "PaymentController", urlPatterns = {"/PaymentController"})
public class PaymentController extends HttpServlet {

    private void processPlaceOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String payment = request.getParameter("payment"); // "cod" hoặc "VNpay"
        String userID = request.getParameter("uID");
        String txtTotal = request.getParameter("grandTotal");

        if (txtTotal == null || txtTotal.trim().isEmpty()) {
            // Nếu không có tổng tiền, quay lại trang giỏ hàng
            response.sendRedirect("MainController?action=goToCheckout");
            return;
        }

        double totalAmount = Double.parseDouble(txtTotal);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByID(userID);

        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order(
                null,
                totalAmount,
                OrderStatus.PENDING,
                LocalDateTime.now(),
                user.getUserAddress(),
                user,
                null,
                null
        );

        // ======================
        // CASE 1: COD
        // ======================
        if ("cod".equalsIgnoreCase(payment)) {
            orderDAO.insertOrder(order);
            request.setAttribute("message", "Đặt hàng thành công! Bạn sẽ thanh toán khi nhận hàng.");
            request.getRequestDispatcher("user/order.jsp").forward(request, response);
            return;
        } // ======================
        // CASE 2: VNPAY
        // ======================
        else if ("VNpay".equalsIgnoreCase(payment)) {

            // Lưu đơn hàng trước (chưa có PaymentID)
            orderDAO.insertOrder(order);

            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String orderType = "other";

            String vnp_TxnRef = Config.getRandomNumber(8);
            String vnp_IpAddr = Config.getIpAddress(request);
            String vnp_TmnCode = Config.vnp_TmnCode;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf((long) totalAmount * 100));
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang: " + order.getOrderID());
            vnp_Params.put("vnp_OrderType", orderType);
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            // Ngày tạo và hết hạn
            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            // ====== Build query & hash ======
            List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
            Collections.sort(fieldNames);

            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();

            for (Iterator<String> itr = fieldNames.iterator(); itr.hasNext();) {
                String fieldName = itr.next();
                String fieldValue = vnp_Params.get(fieldName);
                if (fieldValue != null && !fieldValue.isEmpty()) {
                    hashData.append(fieldName).append('=').append(fieldValue);
                    query.append(URLEncoder.encode(fieldName, "US-ASCII"))
                            .append('=')
                            .append(URLEncoder.encode(fieldValue, "US-ASCII"));

                    if (itr.hasNext()) {
                        hashData.append('&');
                        query.append('&');
                    }
                }
            }

            // Tạo secure hash
            String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());

            query.append("&vnp_SecureHash=").append(vnp_SecureHash);

            String paymentUrl = Config.vnp_PayUrl + "?" + query.toString();

            response.sendRedirect(paymentUrl);
            return;

        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action.equals("placeOrder")) {
            processPlaceOrder(request, response);
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
