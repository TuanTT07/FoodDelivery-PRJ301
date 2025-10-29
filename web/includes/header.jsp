<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="header">
    <div class="container header__container">

        <c:if test="${user.roleID eq 'S001'}">
            <a href="${pageContext.request.contextPath}/admin/dashboard.jsp">
                <img class="header__img" src="${pageContext.request.contextPath}/assets/img/Logo.svg" alt="Logo">
            </a>
        </c:if>
        <c:if test="${user.roleID ne 'S001'}">
            <a href="${pageContext.request.contextPath}/index.jsp">
                <img class="header__img" src="${pageContext.request.contextPath}/assets/img/Logo.svg" alt="Logo">
            </a>
        </c:if>


        <nav class="header-nav">
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

        <c:if test="${not empty sessionScope.user}">
            <div class="header__user">
                <a href="${pageContext.request.contextPath}/cart.jsp" class="header__cart">
                    <img src="${pageContext.request.contextPath}/assets/img/cart-icon.png" alt="Giỏ hàng" class="header__cart-icon">
                </a>
                <h3 class="header__name">${sessionScope.user.userFullName}</h3>
            </div>
        </c:if>

        <c:if test="${empty user}">
            <div class="header-action">
                <a class="btn header-action__link" href="${pageContext.request.contextPath}/auth/register.jsp?action=signUpUser">Đăng kí</a>
                <a class="btn header-action__btn" href="${pageContext.request.contextPath}/auth/login.jsp">Đăng nhập</a>
            </div>
        </c:if>


    </div>
</header>