<%-- 
    Document   : StoreDetail
    Created on : Oct 27, 2025, 9:19:57 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                                <h2 class="info-store__name">Tên cửa hàng</h2>

                                <div class="info-store__details">
                                    <p>Phí giao hàng: <span>100K</span></p>
                                    <p>Thời gian: <span>20 phút</span></p>
                                    <p>Loại đồ ăn: <span>Hamburger</span></p>
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

                    <div class="menu-tabs">
                        <c:forEach var="c" items="${categories}">
                            <button class="tab-btn" onclick="scrollToCategory('${c.categoryName}')">
                                ${c.categoryName}
                            </button>
                        </c:forEach>
                    </div>

                    <c:forEach var="c" items="${categories}">
                        <section id="${c.categoryName.replace(' ', '-')}">
                            <h2>${c.categoryName}</h2>
                            <div class="product-grid">
                                <c:forEach var="p" items="${productMap[c.categoryName]}">
                                    <div class="product-card">
                                        <img src="${pageContext.request.contextPath}/assets/img/${p.image}" alt="${p.productName}">
                                        <h3>${p.productName}</h3>
                                        <p>${p.description}</p>
                                        <span>$${p.price}</span>
                                    </div>
                                </c:forEach>
                            </div>
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
