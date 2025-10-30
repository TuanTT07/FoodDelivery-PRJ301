<%-- 
    Document   : home
    Created on : Oct 22, 2025, 2:16:43 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Home</title>
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
            <section class="result">
                <div class="container">
                    <div>
                        <div>
                            <p class="cate__label">Khám phá theo danh mục</p>
                        </div>

                        <jsp:include page="sidebarCate.jsp"/>

                        <div class="line"></div>


                        <div class="info">
                            <p class="info__address">Địa chỉ:${location}</p>
                            <div class="info__sort">
                                <p>Lọc theo: </p>
                                <select id="sortStore" >
                                    <option value="population">Phổ biến</option>
                                    <option value="rate">Đánh giá</option>
                                    <option value="Recommended">Đề xuất</option>
                                </select>
                            </div>
                        </div>

                        <div id="storeList" class="store-container">

                            <!-- Ưu tiên hiển thị list đã lọc theo category -->
                            <c:choose>
                                <c:when test="${not empty listStoreByCate}">
                                    <c:forEach var="store" items="${listStoreByCate}">
                                        <div class="store-card">
                                            <img class="store-img" src="${store.bannerURL}" alt="Logo store"/>
                                            <h3 class="store-name">${store.storeName}</h3>
                                            <p class="store-address">${store.storeAddress}</p>
                                            <p class="store-time__text">
                                                Thời gian hoạt động: 
                                                <span>${fn:substring(store.openTime, 0, 5)} - ${fn:substring(store.closeTime, 0, 5)}</span>
                                            </p>
                                            <p class="store-cate">
                                                Thể loại thức ăn: <span>${store.storeCategoryId.storeCategoryName}</span>
                                            </p>
                                            <p class="store-rating">
                                                ${store.storeRating}
                                                <img class="star" src="${pageContext.request.contextPath}/assets/img/star_icon.svg" alt="alt"/>
                                            </p>
                                        </div>
                                    </c:forEach>
                                </c:when>

                                <c:when test="${not empty listOfStore}">
                                    <c:forEach var="store" items="${listOfStore}">
                                        <div class="store-card">
                                            <img class="store-img" src="${store.bannerURL}" alt="Logo store"/>
                                            <h3 class="store-name">${store.storeName}</h3>
                                            <p class="store-address">${store.storeAddress}</p>
                                            <p class="store-time__text">
                                                Thời gian hoạt động: 
                                                <span>${fn:substring(store.openTime, 0, 5)} - ${fn:substring(store.closeTime, 0, 5)}</span>
                                            </p>
                                            <p class="store-cate">
                                                Thể loại thức ăn: <span>${store.storeCategoryId.storeCategoryName}</span>
                                            </p>
                                            <p class="store-rating">
                                                ${store.storeRating}
                                                <img class="star" src="${pageContext.request.contextPath}/assets/img/star_icon.svg" alt="alt"/>
                                            </p>
                                        </div>
                                    </c:forEach>
                                </c:when>


                                <c:otherwise>
                                    <p>${not empty messErrorLocation ? messErrorLocation : error_storeByCate}</p>
                                </c:otherwise>
                            </c:choose>
                        </div>

                    </div>
            </section>
        </main>
        <jsp:include page="/includes/footer.jsp"/>

    </body>
</html>
