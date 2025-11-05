<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="header">
    <div class="container header__container">

        <c:if test="${u.roleID.roleID eq 'S001'}">
            <a href="${pageContext.request.contextPath}/admin/dashboard.jsp">
                <img class="header__img" src="${pageContext.request.contextPath}/assets/img/Logo.svg" alt="Logo">
            </a>
        </c:if>
        <c:if test="${u.roleID.roleID ne 'S001'}">
            <a href="${pageContext.request.contextPath}/index.jsp">
                <img class="header__img" src="${pageContext.request.contextPath}/assets/img/Logo.svg" alt="Logo">
            </a>
        </c:if>


        <nav class="header-nav">
            <c:if test="${u.roleID.roleID eq 'S004'}">
                <a href="${pageContext.request.contextPath}/auth/register.jsp?action=signUpStore">Đăng kí cửa hàng</a>

            </c:if>

            <a href="${pageContext.request.contextPath}/auth/register.jsp?action=signUpDelivery">Đăng kí tài xế</a>
            <a href="#">About</a> 
            <a href="#">Page</a> 
        </nav>
        <%--
        <%
            model.User loginUser = (model.User) session.getAttribute("LOGIN_USER");
            if (loginUser != null) {
        %>
        <h3>Xin chào, <%= loginUser.getUserFullName()%>!</h3>
        <%
            }
        %>
        --%>

        <c:if test="${not empty sessionScope.u}">
            <div class="user-info">
                <!-- Giỏ hàng riêng -->
                <a href="${pageContext.request.contextPath}/MainController?action=goToCart&userId=${sessionScope.u.userID}" class="header__cart">
                    <img src="${pageContext.request.contextPath}/assets/img/cart-shopping-solid-full.svg"
                         alt="Giỏ hàng" class="header__cart-icon">
                </a>

                <!-- Khu vực người dùng -->
                <div class="header__user">
                    <h3 class="header__name">${sessionScope.u.userFullName}</h3>
                    <ul class="user-dropDown">
                        <li><a href="">Thông tin tài khoản</a></li>
                        <li><a href="url">Đổi mật khẩu</a></li>
                        <li><a href="${pageContext.request.contextPath}/MainController?action=logout">Đăng xuất</a></li>
                    </ul>
                </div>
            </div>
        </c:if>



        <c:if test="${empty u}">
            <div class="header-action">
                <a class="btn header-action__link" href="${pageContext.request.contextPath}/auth/register.jsp?action=signUpUser">Đăng kí</a>
                <a class="btn header-action__btn" href="${pageContext.request.contextPath}/auth/login.jsp">Đăng nhập</a>
            </div>
        </c:if>


    </div>
</header>