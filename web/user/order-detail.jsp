<%-- 
    Document   : order-detail
    Created on : Oct 22, 2025, 2:17:03 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/responsive.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/pagesCss/orderDetail.css"/>
        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <section class="order-detail">
            <div class="order-detail__container">
                <h2 class="order-detail__title">Chi tiết đơn hàng OrderID</h2>
                <div class="order-info">
                    <p>Mã đơn hàng: orderID</p>
                    <p>Delivery status: <span class="status">Status</span></p>
                    <p>Delivery address:Address</p>
                    <p>Receiver: User name</p>
                    <p>User numberphone: </p>
                    <p>Mã giảm giá: coupon</p>
                    <p>Payment status:</p>
                </div>

                <div class="order-products">
                    <h3>Danh sách sản phẩm</h3>
                    <table class="order-table">
                        <thead>
                            <tr>
                                <th>Sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Giá</th>
                                <th>Tổng</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Product name</td>
                                <td>Quantity</td>
                                <td>Price on each</td>
                                <td>Total price</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="order-total">
                    <p><strong>Tổng cộng:</strong>Price</p>
                </div>

                <a href="orders.jsp" class="back-btn">← Quay lại danh sách đơn hàng</a>
            </div>
        </section>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
