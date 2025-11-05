<%-- 
    Document   : orders
    Created on : Oct 22, 2025, 2:16:56 PM
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
        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <section class="orders">
            <div class="container">
                <h2 class="orders__title">Danh sách đơn hàng</h2>

                <a href="order-detail.jsp" class="order-card">
                    <div class="order-card__header">
                        <h3>Mã đơn hàng: OrderID</h3>
                        <span class="order-status">Delivery status</span>
                    </div>

                    <div class="order-card__body">
                        <p>Người nhận: User name</p>
                        <p>Số điện thoại: Phone number</p>
                        <p>Địa chỉ giao hàng: address</p>
                        <p>Mã giảm giá: coupon</p>
                        <p>Tổng giá: 0 VNĐ</p>
                        <p>Thanh toán: Payment status</p>
                    </div>
                </a>
            </div>
        </section>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
