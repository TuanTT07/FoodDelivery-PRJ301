<%-- 
    Document   : dashboard
    Created on : Oct 22, 2025, 2:17:43 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    

                    <div class="admin-info">
                        <p>Hello, <span>Admin</span></p>
                        <img src="${pageContext.request.contextPath}/assets/img/avatar.svg" alt="Avatar" class="admin-avatar"/>
                    </div>
                </div>

                <!-- Content Section -->
                <div class="admin-wrap">
                    <h2>Customer</h2>  
                </div>
                <form action="${pageContext.request.contextPath}/MainController" method="post" class="search-form" >
                    <input type="hidden" name="action" value="searchUser">
                    <input type="text" name="name" placeholder="Tìm kiếm..." value="${name}">
                    <button type="submit">
                        <img src="${pageContext.request.contextPath}/assets/img/search.svg" alt="search"/> 
                    </button>
                    <jsp:include page="/admin/users.jsp"/>
                </form>
            </div>        
        </div>
        
    </body>
</html>
