<%-- 
    Document   : dashboardStore
    Created on : Oct 22, 2025
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delivery Dashboard</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/responsive.css"/>
        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">
    </head>

    <body>
        <div class="store">

            <jsp:include page="sidebar.jsp"/>

            <div class="store__content">

                <div class="store__header">
                    <form action="SearchController" method="post" class="store__search-form">
                        <input type="text" name="keyword" placeholder="Search..." class="store__search-input">
                        <button type="submit" class="store__search-button">
                            <img src="${pageContext.request.contextPath}/assets/img/search.svg" alt="search" class="store__search-icon"/> 
                        </button>
                    </form>

                    <div class="store__user-info">
                        <p class="store__user-greeting">Xin chào, <span class="store__user-name"></span></p>
                        <img src="${pageContext.request.contextPath}/assets/img/avatar.svg" alt="Avatar" class="store__avatar"/>
                    </div>
                </div>
                    <!-- Dashboard Content -->
                <div class="delivery__order-list">
                    <h1 class="delivery__section-title">Danh sách đơn</h1>

                    <div class="delivery__section">
                        <h2 class="delivery__section-subtitle">Đơn mới</h2>
                        <div class="delivery__cards">
                            <div class="delivery__card">
                                <p><strong>Mã đơn:</strong> #ORD3011</p>
                                <p><strong>Khách hàng:</strong> Nguyễn Văn Linh</p>
                                <p><strong>Nơi nhận đơn:</strong> Pizza House, 12 Baker St</p>
                                <p><strong>Địa chỉ giao:</strong> 88 Main Road</p>
                                <p><strong>Thanh toán:</strong> 200.000VNĐ</p>
                                <div class="delivery__card-buttons">
                                    <button class="delivery__btn delivery__btn--accept">Nhận đơn</button>
                                    <button class="delivery__btn delivery__btn--cancel">Hủy</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="delivery__section">
                        <h2 class="delivery__section-subtitle">Đơn đang giao</h2>
                        <div class="delivery__cards">
                            <div class="delivery__card">
                                <p><strong>Mã đơn:</strong> #ORD3010</p>
                                <p><strong>Khách hàng:</strong> Nguyễn Giao Long</p>
                                <p><strong>Nơi nhận đơn: </strong> Burger King, 45 Central Ave</p>
                                <p><strong>Địa chỉ giao:</strong> 12 Park Lane</p>
                                <p><strong>Thanh toán:</strong> 120.000VNĐ</p>
                                <div class="delivery__card-buttons">
                                    <button class="delivery__btn delivery__btn--complete">Hoàn thành</button>
                                    <button class="delivery__btn delivery__btn--cancel">Hủy</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Đơn đã hoàn thành -->
                    <div class="delivery__section">
                        <h2 class="delivery__section-subtitle">Đơn giao thành công</h2>
                        <div class="delivery__cards">
                            <div class="delivery__card">
                                <p><strong>Mã đơn:</strong> #ORD3008</p>
                                <p><strong>Khách hàng:</strong> Kevin Tran</p>
                                <p><strong>Nơi nhận đơn:</strong> Sushi Go, 32 Maple St</p>
                                <p><strong>Địa chỉ giao:</strong> 22 Lakeview</p>
                                <p><strong>Thanh toán:</strong> 60.000VNĐ</p>
                                <div class="delivery__card-buttons">
                                    <button class="delivery__btn delivery__btn--details">
                                        <a href="${pageContext.request.contextPath}/delivery/orderDetail.jsp">
                                        Chi tiết
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
