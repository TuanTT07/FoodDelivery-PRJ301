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

        <div>
            <!-- SIDEBAR  -->
            <jsp:include page="sidebar.jsp"/>
            <!--  MAIN CONTENT  -->
            <div class="store__content">

                <!-- Header -->
                 <jsp:include page="headerDashboard.jsp"/>

                <!-- Product Content -->

                <div class="layout">
                    <h2 class="layout__title">Món ăn</h2>
                    <p class="layout__subtitle">Experience our finest dishes of the day</p>

                    <div class="layout__grid">

                        <div class="layout__card--add">
                            <div class="layout__add">
                                <button class="layout__add-button">+</button>
                                <p class="layout__add-text">Thêm món ăn mới</p>
                            </div>
                        </div>


                        <div class="layout__card">
                            <img src="${pageContext.request.contextPath}/assets/img/spagheti.png" alt="Spaghetti" class="products__img">
                            <h3 class="layout__name">Spaghetti</h3>
                            <p class="layout__price">$12.50</p>
                            <div class="layout__actions">
                                <button>Edit</button>
                                <button>Delete</button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>