<%-- 
   Document   : register_member
   Created on : Oct 22, 2025, 2:16:07 PM
   Author     : ACER
--%>

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
            <!-- Login/SignUp Form -->
            <div class="auth">
                <div class="container auth-container">
                    <div class="auth-wrapp">
                        <h2 class="auth-title">Đăng kí</h2>
                        <form action="${pageContext.request.contextPath}/MainController" method="POST" accept-charset="UTF-8">
                            <input type="hidden" name="action" value="signUpUser">
                            <div class="login-content">
                                <div>
                                    <input class="inputUserName" type="text" name="userName" placeholder="Tên đăng nhập" value="${u.userName}" >
                                    <span style="color:red">${error_username}</span>
                                </div>
                                <div>
                                    <input class="inputPassword" type="password" name="password" placeholder="mật khẩu" >
                                    <span style="color:red">${error_password}</span>
                                </div>
                                <div>
                                    <input class="inputFullname" type="text" name="Fullname" placeholder="Họ và tên" value="${u.userFullName}" >
                                    <span style="color:red">${error_fullName}</span>
                                </div>
                                <div>
                                    <input class="inputEmail" type="text" name="email" placeholder="Email" value="${u.userEmail}" >
                                    <span style="color:red">${error_email}</span>
                                </div>
                                <div>
                                    <input class="inputPhone" type="text" name="Phone" placeholder="Số điện thoại" value="${u.userPhone}" >
                                    <span style="color:red">${error_phone}</span>
                                </div>
                                <div>
                                    <label>Địa chỉ</label>
                                    <div class="Form_Address">
                                        <input class="inputStreet" type="text" name="street" placeholder="Số/ đường" value="${street != null ? street : param.street}" >
                                        <input class="inputWard" type="text" name="ward" placeholder="Phường/Xã" value="${ward != null ? ward : param.ward}" >

                                        <select class="inputCity" name="city">
                                            <option value="">Chọn tỉnh/thành phố</option>
                                            <option value="1" ${city == '1' ? 'selected' : ''}>TP. Hồ Chí Minh</option>
                                            <option value="2" ${city == '2' ? 'selected' : ''}>Hà Nội</option>
                                            <option value="3" ${city == '3' ? 'selected' : ''}>Đà Nẵng</option>
                                            <option value="4" ${city == '4' ? 'selected' : ''}>Cần Thơ</option>
                                        </select>                                      
                                    </div>
                                    <c:if test="${not empty error_address}">
                                        <span style="color: red">${error_address}</span>
                                    </c:if>
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
                            <a class="auth-gg" href="#">
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
