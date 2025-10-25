<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="admin-nav">
    <!-- Logo -->
    <a href="${pageContext.request.contextPath}/admin/dashboard.jsp">
        <img src="${pageContext.request.contextPath}/assets/img/Logo.svg" alt="Logo" class="nav-logo"/>
    </a>

    <ul class="nav-list">

        <li class="${pageContext.request.requestURI.endsWith('/dashboard.jsp') ? 'active' : ''}">
            <a href="${pageContext.request.contextPath}/admin/dashboard.jsp">
                <img src="${pageContext.request.contextPath}/assets/img/Home_icon.svg" alt="Home"/>
                Dashboard
            </a>
        </li>

        <li class="${pageContext.request.requestURI.endsWith('/orderList.jsp') ? 'active' : ''}">
            <a href="${pageContext.request.contextPath}/admin/orderList.jsp">
                <img src="${pageContext.request.contextPath}/assets/img/Oder_list_icon.svg" alt="Order list"/>
                Order List
            </a>
        </li>

        <li class="${pageContext.request.requestURI.endsWith('/orderDetail.jsp') ? 'active' : ''}">
            <a href="${pageContext.request.contextPath}/admin/orderDetail.jsp">
                <img src="${pageContext.request.contextPath}/assets/img/oder_detail.svg" alt="Order detail"/>
                Order Detail
            </a>
        </li>

        <li class="${pageContext.request.requestURI.endsWith('/customer.jsp') ? 'active' : ''}">
            <a href="${pageContext.request.contextPath}/admin/customer.jsp">
                <img src="${pageContext.request.contextPath}/assets/img/customer.svg" alt="Customer"/>
                Customer
            </a>
        </li>

        <li class="${pageContext.request.requestURI.endsWith('/reviews.jsp') ? 'active' : ''}">
            <a href="${pageContext.request.contextPath}/admin/reviews.jsp">
                <img src="${pageContext.request.contextPath}/assets/img/reviews.svg" alt="Reviews"/>
                Reviews
            </a>
        </li>

        <li class="${pageContext.request.requestURI.endsWith('/foods.jsp') ? 'active' : ''}">
            <a href="${pageContext.request.contextPath}/admin/foods.jsp">
                <img src="${pageContext.request.contextPath}/assets/img/food_icon.svg" alt="Foods"/>
                Foods
            </a>
        </li>

        <li class="${pageContext.request.requestURI.endsWith('/vouchers.jsp') ? 'active' : ''}">
            <a href="${pageContext.request.contextPath}/admin/vouchers.jsp">
                <img src="${pageContext.request.contextPath}/assets/img/voucher_icon.svg" alt="Vouchers"/>
                Vouchers
            </a>
        </li>
    </ul>
</nav>
