<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="sidebar store__sidebar">
    <!-- Logo -->
    <a href="${pageContext.request.contextPath}/delivery/dashboard.jsp" class="sidebar__logo-link">
        <img src="${pageContext.request.contextPath}/assets/img/Logo.svg" alt="Logo" class="sidebar__logo">
    </a>

    <!-- Navigation List -->
    <ul class="sidebar__list">

        <!-- Dashboard -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/dashboard.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/delivery/dashboard.jsp" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/Home_icon.svg" alt="Dashboard" class="sidebar__icon">
                <span class="sidebar__text">Trang chủ</span>
            </a>
        </li>
        
        <!-- Order List -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/orderList.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/delivery/orderList.jsp" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/orderList-icon.svg" alt="orderList" class="sidebar__icon">
                <span class="sidebar__text">Danh sách đơn</span>
            </a>
        </li>

        <!-- Map -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/map.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/delivery/map.jsp" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/map-icon.svg" alt="Map" class="sidebar__icon">
                <span class="sidebar__text">Bản đồ</span>
            </a>
        </li>

        <!-- Order Detail -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/orderDetail.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/delivery/orderDetail.jsp" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/orderDetail-icon.svg" alt="Order Detail" class="sidebar__icon">
                <span class="sidebar__text">Chi tiết đơn hàng</span>
            </a>
        </li>
        
        <!-- Earnings -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/earnings.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/delivery/earnings.jsp" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/earnings-icon.svg" alt="Earnings" class="sidebar__icon">
                <span class="sidebar__text">Thu nhập</span>
            </a>
        </li>
        
        <!-- Profile -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/profile.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/delivery/profile.jsp" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/profile-icon.svg" alt="Profile" class="sidebar__icon">
                <span class="sidebar__text">Thông tin</span>
            </a>
        </li>
        
        <!-- Setting -->
        <li class="sidebar__item ${pageContext.request.requestURI.endsWith('/setting.jsp') ? 'sidebar__item--active' : ''}">
            <a href="${pageContext.request.contextPath}/delivery/setting.jsp" class="sidebar__link">
                <img src="${pageContext.request.contextPath}/assets/img/setting-icon.svg" alt="Setting" class="sidebar__icon">
                <span class="sidebar__text">Cài đặt</span>
            </a>
        </li>
    </ul>
</nav>