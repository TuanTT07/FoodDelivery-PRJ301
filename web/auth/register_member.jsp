<%-- 
   Document   : register_member
   Created on : Oct 22, 2025, 2:16:07 PM
   Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            <!-- Login/SignUp Form -->
            <div class="auth">
                <div class="container auth-container">
                    <div class="auth-wrapp">
                        <h2 class="auth-title">Đăng kí</h2>
                        <form action="MainController" method="POST">
                            <input type="text" hidden name="action" value="signUpUser">
                            <div class="login-content">
                                <div>
                                    <input class="inputUserName" type="text" name="userName" placeholder="Tên đăng nhập hoặc email">
                                </div>
                                <div>
                                    <input class="inputPassword" type="password" name="password" placeholder="mật khẩu">
                                </div>

                                <div>
                                    <input class="inputFullname" type="text" name="Fullname" placeholder="Họ và tên">
                                </div>
                                <div>
                                    <input class="inputAddress" type="text" name="Address" placeholder="Địa chỉ...">
                                </div>
                                <div>
                                    <button class="btn btn-login">Đăng kí</button>
                                </div>
                            </div>
                        </form>

                        <div class="auth-div">
                            <p>Hoặc</p>
                        </div>
                        <div>
                            <a class="auth-gg" href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/Food_Delivery/login&response_type=code&client_id=1086538568061-d81so0t394v7vmgcr1aqqo9bo2r570gk.apps.googleusercontent.com&approval_prompt=force">
                                <img src="${pageContext.request.contextPath}/assets/img/gg_icon.svg" alt="Google icon"/>
                                <span> Đăng kí với Google</span>
                            </a>
                        </div>
                    </div>

                </div>
            </div>       
        </main>              
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
