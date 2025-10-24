<%-- 
   Document   : 404
   Created on : Oct 22, 2025, 2:15:06 PM
   Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>404 Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reponsive.css"/>
        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&family=Roboto+Serif:ital,opsz,wght@0,8..144,100..900;1,8..144,100..900&display=swap" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="includes/header.jsp"/>
        <main>
            <div class="container">
                <div class="error-content">
                    <img class="error__img" src="${pageContext.request.contextPath}/assets/img/404_img.svg" alt="404"/>
                    <p class="error__text">Sorry, the page you’re looking for doesn’t exist. If you think something is broken, report a porblem</p>
                    <a class="btn error__btn" href="${pageContext.request.contextPath}/index.jsp">Go To Home</a>
                </div>
            </div>
        </main>


    </body>
</html>
