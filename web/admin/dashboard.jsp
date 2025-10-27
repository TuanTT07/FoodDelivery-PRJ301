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
                        <p>Hello, <span> ${user.userFullName}</span></p>
                        <img src="${pageContext.request.contextPath}/assets/img/avatar.svg" alt="Avatar" class="admin-avatar"/>
                    </div>
                </div>

                <!-- Content Section -->
                <div class="admin-wrap">
                    <h2>Dashboard Overview</h2>
                    <p>Welcome back, ${user.userFullName}!</p>

                    <div class="dashboard-cards">
                        <div class="card">
                            <h3>75</h3>
                            <p>Total Orders</p>
                        </div>
                        <div class="card">
                            <h3>357</h3>
                            <p>Total Delivered</p>
                        </div>
                        <div class="card">
                            <h3>65</h3>
                            <p>Total Canceled</p>
                        </div>
                        <div class="card">
                            <h3>$128</h3>
                            <p>Total Revenue</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
