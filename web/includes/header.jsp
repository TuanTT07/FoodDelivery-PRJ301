
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header class="header">
    <div class="container header__container">
        <a href="${pageContext.request.contextPath}/index.jsp">
            <img class="header__img" src="${pageContext.request.contextPath}/assets/img/Logo.svg" alt="Logo">
        </a>

        <nav class="header-nav">
            <a href="#">Đăng kí tài xế</a>
            <a href="#">About</a> 
            <a href="#">Page</a> 
        </nav>

        <div class="header-action">
            <a class="btn header-action__link" href="${pageContext.request.contextPath}/auth/register_member.jsp">Đăng kí</a>
            <a class="btn header-action__btn" href="${pageContext.request.contextPath}/auth/login.jsp">Đăng nhập</a>
        </div>
    </div>
</header>

