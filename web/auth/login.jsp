<%-- 
    Document   : AuthView
    Created on : Oct 22, 2025, 2:12:48 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reponsive.css"/>
        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&family=Roboto+Serif:ital,opsz,wght@0,8..144,100..900;1,8..144,100..900&display=swap" rel="stylesheet">
    </head>
    <body>

        <jsp:include page="/includes/header.jsp"/>

        <main>
            <!-- Login Form -->
            <div class="auth">
                <div class="container auth-container">
                    <div class="auth-wrapp">
                        <h2 class="auth-title">Đăng nhập</h2>
                        <c:set var="username" value="${not empty username ? username : ''}" />
                        <c:set var="email" value="${not empty email ? email : ''}" />
                        <form action="${pageContext.request.contextPath}/MainController" method="POST">
                            <input type="hidden" name="action" value="loginUser">
                            <div class="login-conttent">
                                <div>
                                    <input class="inputUserName" 
                                           type="text" 
                                           name="userName" 
                                           value="<c:out value='${not empty userName ? userName : (not empty email ? email : username)}'/>" 
                                           placeholder="Tên đăng nhập hoặc email" required>
                                </div>
                                <div>
                                    <input class="inputPassword" type="password" name="password" placeholder="Mật khẩu" required>
                                </div>
                                <c:if test="${not empty msg}">
                                    ${msg}
                                </c:if>
                                <div class="login-float">
                                    <div class="float-left">
                                        <input type="checkbox" id="RememberMe" checked="">
                                        <label for="RememberMe">Lưu thông tin đăng nhập</label>
                                    </div>
                                    <div class="float-right">
                                        <a class="float-right__forgotPass" href="#">Quên mật khẩu?</a>
                                    </div>
                                </div>
                                <div>
                                    <button class="btn btn-login">Đăng nhập</button>
                                </div>

                            </div>
                        </form>
                        <div class="login-res">
                            <p class="login-res__text">Nếu chưa có tài khoản?  <a href="${pageContext.request.contextPath}/auth/register_member.jsp">Đăng kí tài khoản</a></p>
                        </div>
                    </div>
                </div>
            </div>       
        </main>              
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
