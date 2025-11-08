package common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VnpayQueryServlet", urlPatterns = {"/VnpayQueryServlet"})
public class vnpayQuery extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy tham số từ request
        String vnp_TxnRef = request.getParameter("order_id"); // order ID
        String vnp_TransactionDate = request.getParameter("trans_date"); // ngày giao dịch VNPay

        // Thông số cố định
        String vnp_RequestId = Config.getRandomNumber(8);
        String vnp_Version = "2.1.0";
        String vnp_Command = "querydr";
        String vnp_TmnCode = Config.vnp_TmnCode;
        String vnp_OrderInfo = "Kiem tra ket qua GD OrderId: " + vnp_TxnRef;
        String vnp_CreateDate = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7")).getTime());
        String vnp_IpAddr = Config.getIpAddress(request);

        // Dùng LinkedHashMap để giữ thứ tự đúng
        Map<String, String> vnp_Params = new LinkedHashMap<>();
        vnp_Params.put("vnp_RequestId", vnp_RequestId);
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_TransactionDate", vnp_TransactionDate);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);

        // Tạo chuỗi hash theo thứ tự field
        StringBuilder hashData = new StringBuilder();
        for (Map.Entry<String, String> entry : vnp_Params.entrySet()) {
            if (hashData.length() > 0) {
                hashData.append('&');
            }
            hashData.append(entry.getKey()).append('=').append(entry.getValue());
        }

        // Tạo chữ ký HMAC SHA512
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        vnp_Params.put("vnp_SecureHash", vnp_SecureHash);

        // Build dữ liệu gửi POST x-www-form-urlencoded
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> entry : vnp_Params.entrySet()) {
            if (postData.length() > 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        // Gửi POST request
        URL url = new URL(Config.vnp_ApiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);

        try ( DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.writeBytes(postData.toString());
            wr.flush();
        }

        int responseCode = conn.getResponseCode();
        System.out.println("Sending 'POST' request to URL : " + url);
        System.out.println("Post Data : " + postData);
        System.out.println("Response Code : " + responseCode);

        // Đọc kết quả trả về
        StringBuilder responseText = new StringBuilder();
        try ( BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                responseText.append(line);
            }
        }

        System.out.println("Response from VNPay: " + responseText.toString());

        // Trả về kết quả cho client nếu cần
        response.setContentType("application/json");
        response.getWriter().write(responseText.toString());
    }
}
