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
        <title>Store Dashboard</title>

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
                        <p class="store__user-greeting">Hello, <span class="store__user-name">Store's owner</span></p>
                        <img src="${pageContext.request.contextPath}/assets/img/avatar.svg" alt="Avatar" class="store__avatar"/>
                    </div>
                </div>

                <!-- Dashboard Content -->
                <div class="orders">
                    <h2 class="orders__title">List of orders</h2>
                    <div class="orders__grid">
                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/spaghetti.png" alt="Spaghetti" class="products__img">
                            <h3 class="products__name">Spaghetti</h3>
                            <p class="products__price">$12.50</p>
                            <p class="orders__status">Completed</p>
                        </div>
                            
                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/beef-burger.jpg" alt="Beef Burger" class="products__img">
                            <h3 class="products__name">Beef Burger</h3>
                            <p class="products__price">$8.90</p>
                            <p class="orders__status">Delivering</p>
                        </div>
                            
                        <div class="orders__card">
                            <img src="${pageContext.request.contextPath}/assets/img/pizza-margherita.jpg" alt="Pizza Margherita" class="products__img">
                            <h3 class="products__name">Pizza Margherita</h3>
                            <p class="products__price">$10.00</p>
                            <p class="orders__status">Canceled</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
