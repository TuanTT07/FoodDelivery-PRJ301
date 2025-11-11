<%-- 
    Document   : dashboard
    Created on : Oct 22, 2025, 2:17:43 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reponsive.css"/>

        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">
    </head>

    <body>
        <div class= "admin-container">
            <!-- Sidebar -->
            <jsp:include page="sidebar.jsp"/>

            <!-- Main Content -->
            <div class="admin-content">
                <!-- Header -->
                <div class="admin-header">
                    <form action="MainController" method="post" class="search-form">
                        <input type="text" name="keyword" placeholder="Tìm kiếm...">
                        <button type="submit">
                            <img src="${pageContext.request.contextPath}/assets/img/search.svg" alt="search"/> 
                        </button>
                    </form>

                    <div class="admin-info">
                        <p>Hello, <span>Admin</span></p>
                        <img src="${u.avatarURL}" alt="avatar" class="admin-avatar"/>
                    </div>
                </div>

                <!-- Content Section -->
                <div class="orders">
                    <h2 class="orders__title">Danh sách Các Món Được Đặt</h2>
                    <div class="orders__grid">
                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/spaghetti.png" alt="Spaghetti" class="products__img">
                            <h3 class="products__name">Spaghetti</h3>
                            <p class="products__price">$12.50</p>
                            <p class="orders__status done">Đã hoàn thành</p>
                        </div>

                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/beef-burger.jpg" alt="Beef Burger" class="products__img">
                            <h3 class="products__name">Beef Burger</h3>
                            <p class="products__price">$8.90</p>
                            <p class="orders__status shipping">Đang giao</p>
                        </div>

                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/pizza.jpg" alt="Pizza" class="products__img">
                            <h3 class="products__name">Pizza</h3>
                            <p class="products__price">$15.00</p>
                            <p class="orders__status pending">Đang xử lý</p>
                        </div>

                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/salad.jpg" alt="Salad" class="products__img">
                            <h3 class="products__name">Fresh Salad</h3>
                            <p class="products__price">$6.50</p>
                            <p class="orders__status done">Đã hoàn thành</p>
                        </div>

                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/sushi.jpg" alt="Sushi" class="products__img">
                            <h3 class="products__name">Sushi Set</h3>
                            <p class="products__price">$22.00</p>
                            <p class="orders__status shipping">Đang giao</p>
                        </div>

                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/steak.jpg" alt="Steak" class="products__img">
                            <h3 class="products__name">Grilled Steak</h3>
                            <p class="products__price">$18.40</p>
                            <p class="orders__status cancelled">Đã hủy</p>
                        </div>

                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/fried-chicken.jpg" alt="Fried Chicken" class="products__img">
                            <h3 class="products__name">Fried Chicken</h3>
                            <p class="products__price">$9.20</p>
                            <p class="orders__status done">Đã hoàn thành</p>
                        </div>

                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/ramen.jpg" alt="Ramen" class="products__img">
                            <h3 class="products__name">Japanese Ramen</h3>
                            <p class="products__price">$11.80</p>
                            <p class="orders__status pending">Đang xử lý</p>
                        </div>

                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/taco.jpg" alt="Taco" class="products__img">
                            <h3 class="products__name">Taco</h3>
                            <p class="products__price">$7.30</p>
                            <p class="orders__status done">Đã hoàn thành</p>
                        </div>

                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/icecream.jpg" alt="Ice Cream" class="products__img">
                            <h3 class="products__name">Ice Cream</h3>
                            <p class="products__price">$4.90</p>
                            <p class="orders__status shipping">Đang giao</p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
