<%-- 
    Document   : StoreDetail
    Created on : Oct 27, 2025, 9:19:57 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Name Store Page</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reponsive.css"/>

        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&family=Roboto+Serif:ital,opsz,wght@0,8..144,100..900;1,8..144,100..900&display=swap" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"></jsp:include>
            <main>
                <section class="section-info">
                    <div class="container">
                        <div class="info-wrap">
                            <div class="info-header">
                                <img class="info-header__banner" src="${pageContext.request.contextPath}/assets/img/bannerStore.png" alt="Banner Store"/>
                            <img class="info-header__logo" src="${pageContext.request.contextPath}/assets/img/logo_store_test.png" alt="Logo Store"/>
                        </div>

                        <!-- Store info -->
                        <div class="info-store">
                            <div>
                                <h2 class="info-store__name">${store.storeName}</h2>

                                <div class="info-store__details">
                                    <p>Địa chỉ: <span>${store.storeAddress}</span></p>
                                    <p>Thời gian hoạt động: <span>${fn:substring(store.openTime, 0, 5)} - ${fn:substring(store.closeTime, 0, 5)}</span></p>
                                    <p>Loại đồ ăn: <span>${store.storeCategoryId.storeCategoryName}</span></p>
                                </div>


                                <div class="info-store__ratings">
                                    <p><span>5.0</span> Đánh giá (100)</p>
                                    <p><span>100%</span> Mức độ ngon</p>                            
                                    <p><span>100%</span> Mức độ giao hàng đúng giờ</p>
                                    <p><span>100%</span> Mức độ hoàn thành</p>
                                </div>
                            </div>
                            <!-- Call to action -->
                            <div class="info-cta">
                                <a href="url" class="btn btn--primary"><img src="${pageContext.request.contextPath}/assets/img/circle-info-solid-full.svg" alt="circle-info"/>Thông tin cửa hàng</a>
                                <a href="url"class="btn btn--secondary"><img src="${pageContext.request.contextPath}/assets/img/heart-circle-plus-solid-full.svg" alt="heart-circle-plus"/>Thêm vào yêu thích</a>
                            </div>
                        </div>
                    </div>
                    <div class="line"></div>

                    <!-- Menu -->
                    <div class="menu-tabs">
                        <c:forEach var="c" items="${listOfCate}">
                            <c:if test="${c.isActive}">
                                <button class="tab-btn" onclick="scrollToCategory('${c.categoryName}')">
                                    ${c.categoryName}
                                </button>
                            </c:if>
                        </c:forEach>
                    </div>

                    <c:forEach var="entry" items="${productMap}">
                        <c:set var="category" value="${entry.key}" />
                        <c:set var="products" value="${entry.value}" />

                        <section id="${fn:replace(category.categoryName,' ','-')}">
                            <h2>${category.categoryName}</h2>
                            <c:forEach var="p" items="${products}">
                                <div class="product-card">
                                    <%--<img src="${pageContext.request.contextPath}/assets/img/${p.image != null ? p.image : 'default.png'}" alt="${p.productName}">--%>
                                    <h3>${p.productName}</h3>
                                    <p>${p.productDesc}</p>
                                    <span>${p.productPrice} VND</span>
                                </div>
                            </c:forEach>
                        </section>
                    </c:forEach>


            </section>
        </main>
        <jsp:include page="/includes/footer.jsp"></jsp:include>
        <script>
            function scrollToCategory(categoryName) {
                const id = categoryName.replaceAll(' ', '-');
                const section = document.getElementById(id);
                if (section) {
                    section.scrollIntoView({behavior: 'smooth', block: 'start'});
                }
            }
        </script>
    </body>
</html>
