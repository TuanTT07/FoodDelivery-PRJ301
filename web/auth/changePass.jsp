<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <!-- Update Form -->
            <div class="auth">
                <div class="container auth-container">
                    <div class="auth-wrapp">
                        <h2 class="auth-title">Thay đổi mật khẩu</h2>
                        <form action="${pageContext.request.contextPath}/MainController" method="POST" accept-charset="UTF-8">
                            <input type="hidden" name="action" value="changePass">
                            <div class="login-content">
                                <label class="label_address">Mật khẩu hiện tại</label>
                                <div>
                                    <input class="inputPassword" type="password" name="oldPassword" placeholder="Mật khẩu hiện tại" >
                                    <span style="color:red">${error_oldPass}</span>
                                </div>
                                <label class="label_address">Mật khẩu mới</label>
                                <div>
                                    <input class="inputPassword" type="password" name="newPassword" placeholder="Mật khẩu mới" value="${pass}" >
                                    <span style="color:red">${error_newPass}</span>
                                </div>
                                <label class="label_address">Xác nhận mật khẩu mới</label>
                                <div>
                                    <input class="inputPassword" type="password" name="confirmPassword" placeholder="Nhập lại mật khẩu mới" >
                                    <span style="color:red">${error_comPass}</span>
                                </div>
                                <div>
                                    <button type="submit" class="btn btn-login">Save</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>       
        </main>              
        <jsp:include page="/includes/footer.jsp"/>
        <script src="${pageContext.request.contextPath}/assets/js/handleFile.js"></script>
    </body>
</html>