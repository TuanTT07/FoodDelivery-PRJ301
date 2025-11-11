<%-- 
    Document   : dashboardStore
    Created on : Oct 22, 2025
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en>
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
                        <p class="store__user-greeting">Hello, <span class="store__user-name">${u.userFullName}</span></p>
                        <img src="${u.avatarURL}" alt="avatar" class="store__avatar"/>
                    </div>
                </div>
                <!-- Dashboard Content -->
                <div class="delivery__dashboard">

                    <div class="delivery__dashboard">

                        <div class="delivery__status">
                            <h2 class="delivery__section-title">Trạng thái tài xế</h2>
                            <p class="delivery__status-text">Trạng thái: <span class="delivery__status-current">Đang hoạt động</span></p>
                            <button class="delivery__status-button">
                                <a href="${pageContext.request.contextPath}/delivery/orderList.jsp">
                                Bắt đầu nhận đơn
                                </a>
                            </button>
                        </div>

                        <div class="delivery__stats">
                            <div class="delivery__stat-card">
                                <p class="delivery__stat-title">Đơn đã giao hôm nay</p>
                                <p class="delivery__stat-value">8</p>
                            </div>
                            <div class="delivery__stat-card">
                                <p class="delivery__stat-title">Thu nhập hôm nay</p>
                                <p class="delivery__stat-value">130.000VNĐ</p>
                            </div>
                            <div class="delivery__stat-card">
                                <p class="delivery__stat-title">Thu nhập tuần này</p>
                                <p class="delivery__stat-value">300.000VNĐ</p>
                            </div>
                            <div class="delivery__stat-card">
                                <p class="delivery__stat-title">Thu nhập tháng này</p>
                                <p class="delivery__stat-value">$1.600.000VNĐ</p>
                            </div>
                        </div>

                        <div class="delivery__rating">
                            <h2 class="delivery__section-title">Đánh giá khách hàng</h2>
                            <p class="delivery__rating-score">4.8 / 5.0</p>
                            <p class="delivery__rating-desc">Đánh giá dựa trên 120 khách hàng</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>