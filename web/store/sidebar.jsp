<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="sidebar store__sidebar">
    <!-- Logo -->
    <a href="${pageContext.request.contextPath}/store/dashboard.jsp" class="sidebar__logo-link">
        <img src="${pageContext.request.contextPath}/assets/img/Logo.svg" alt="Logo" class="sidebar__logo">
    </a>

    <!-- Navigation List -->
    <ul class="sidebar__list">

        <!-- Dashboard -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/dashboard.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/store/dashboard.jsp" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/Home_icon.svg" alt="Dashboard" class="sidebar__icon">
                <span class="sidebar__text">Dashboard</span>
            </a>
        </li>
        
        <!-- Order List -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/orderList.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/store/orderList.jsp" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/orderList-icon.svg" alt="Review" class="sidebar__icon">
                <span class="sidebar__text">Order List</span>
            </a>
        </li>

        <!-- Food Menu -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/products.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/MainController?action=viewProduct&storeID=${sessionScope.store.storeID}" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/food_icon.svg" alt="Food Menu" class="sidebar__icon">
                <span class="sidebar__text">Product</span>
            </a>
        </li>

        
        <!-- Category -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/category.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/MainController?action=viewCate&storeID=${sessionScope.store.storeID}" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/category.svg" alt="Review" class="sidebar__icon">
                <span class="sidebar__text">Menu</span>
            </a>
        </li>
        
        <!-- Review -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/review.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/store/review.jsp" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/review-icon.svg" alt="Review" class="sidebar__icon">
                <span class="sidebar__text">Review</span>
            </a>
        </li>
        
        <!-- Review -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/wallet.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/store/wallet.jsp" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/wallet-icon.svg" alt="Review" class="sidebar__icon">
                <span class="sidebar__text">Wallet</span>
            </a>
        </li>
    </ul>
</nav>