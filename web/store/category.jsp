<%-- 
    Document   : dashboardStore
    Created on : Oct 22, 2025
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <div class="store">

            <jsp:include page="sidebar.jsp"/>
            <div class="store__content">
                <jsp:include page="headerDashboard.jsp"/>
                <!-- Main Content -->
                <template id="popzy-3">
                    <form action="${pageContext.request.contextPath}/MainController" method="post" class="event-popup">
                        <input hidden type="text" name="action" value="addCate">
                        <input  hidden type="text" name="storeID" value="${sessionScope.store.storeID}">

                        <h3 class="popup__title">Thêm Thực Đơn</h3>
                        <div class="event-popup__input">
                            <input type="type" name="cateName" value="${error_name}" required="" placeholder="Tên thực đơn">
                        </div>
                        <button type="submit" >Hoàn thành</button>
                    </form>
                </template>


                <c:if test="${not empty error_cate}">
                    <script>alert("${error_cate}");</script>
                </c:if>

                <div class="layout">

                    <h2 class="layout__title">Thực đơn</h2>
                    <p class="layout__subtitle">Experience our finest dishes of the day</p>

                    <div class="layout__grid">
                        <div class="layout__card--add">
                            <div class="layout__add">
                                <button class="layout__add-button">+</button>
                                <p class="layout__add-text">Thêm thực đơn mới</p>
                            </div>
                        </div>


                        <c:if test="${error_listOfCate}">
                            <p>${error_listOfCate}</p>
                        </c:if>
                        <template id="popzy-4">
                            <form action="${pageContext.request.contextPath}/MainController" method="post" class="event-popup">
                                <input hidden type="text" name="action" value="updateCate">
                                <input hidden type="text" name="storeID" value="${sessionScope.store.storeID}">
                                <input hidden type="text" name="idCate" class="cate-id">
                                <h3 class="popup__title">Chỉnh Sửa Thực Đơn</h3>
                                <div class="event-popup__input">
                                    <input type="text" name="cateName" class="cate-name" required placeholder="Tên thực đơn">
                                </div>
                                <button type="submit">Hoàn thành</button>
                            </form>
                        </template>

                        <c:forEach var="cate" items="${listOfCate}">

                            <div class="layout__card" 
                                 data-id="${cate.categoryID}" 
                                 data-name="${cate.categoryName}">
                                <h3 class="layout__name">${cate.categoryName}</h3>
                                <p class="${cate.isActive? "layout__active": "layout__stop"}">${cate.isActive? "Đang hoạt động": "Tạm ngưng"}</p>
                                <div class="layout__actions">
                                    <a class="layout__actions--edit" href="#">Edit</a>
                                    <a href="${pageContext.request.contextPath}/MainController?action=deleteCate&id=${cate.categoryID}">Delete</a>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/assets/js/event.js"></script>

    </body>
</html>