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

                <!-- Wallet Content -->
                <div class="wallet">
                    <h2 class="wallet__title">Revenue</h2>
                    <div class="wallet__stat">
                        <div class="wallet__card">
                            <h3 class="wallet__card-text">Revenue</h3>
                            <p class="wallet__card-number">$300.00</p>
                        </div>
                      
                        <div class="wallet__card">
                            <h3 class="wallet__card-text">Profit</h3>
                            <p class="wallet__card-number">$123.00</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
