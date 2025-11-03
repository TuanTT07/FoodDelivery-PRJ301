<%-- 
    Document   : dashboardStore
    Created on : Oct 22, 2025
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Dashboard</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/responsive.css"/>
        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">
    </head>
    <body>

        <div>
            <!-- SIDEBAR  -->
            <jsp:include page="sidebar.jsp"/>
            <!--  MAIN CONTENT  -->
            <div class="store__content">

                <!-- Header -->
                <jsp:include page="headerDashboard.jsp"/>

                <!-- Product Content -->

                <div class="layout">
                    <h2 class="layout__title">Món ăn</h2>
                    <p class="layout__subtitle">Trải nghiệm những món ăn ngon của chúng tôi hôm nay</p>

                    <div class="layout__grid">

                        <div class="layout__card--add">
                            <a href="${pageContext.request.contextPath}/MainController?action=goToProductForm&storeID=${sessionScope.store.storeID}" class="layout__add">
                                <button class="layout__add-button">+</button>
                                <p class="layout__add-text">Thêm món ăn mới</p>
                            </a>
                        </div>

                        <c:forEach  var="p" items="${listOfProduct}">
                            <c:if test="${p.isActive}">
                                <div class="layout__card">
                                    <img src="${pageContext.request.contextPath}/assets/img/spagheti.png" alt="Spaghetti" class="products__img">
                                    <h3 class="layout__name">${p.productName}</h3>
                                    <p class="layout__price">${p.productPrice}VND</p>
                                    <p class="layout__category">Danh mục:</p>
                                    <div class="layout__actions">
                                        <a href="${pageContext.request.contextPath}/MainController?action=editProduct&productID=${p.productID}">
                                            Chỉnh sửa
                                        </a>

                                        <a href="${pageContext.request.contextPath}/MainController?action=deleteProduct&productID=${p.productID}&storeID=${sessionScope.store.storeID}"
                                           onclick="return confirm('Xác nhận xoá sản phẩm này?')">Xóa</a>

                                    </div>
                                    <a href="#" class="">
                                        Thêm thông tin chi tiết
                                    </a>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>