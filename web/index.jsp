<%-- 
    Document   : index
    Created on : Oct 22, 2025, 2:14:26 PM
    Author     : ACER
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Home</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reponsive.css"/>
        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com"/>
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&family=Roboto+Serif:ital,opsz,wght@0,8..144,100..900;1,8..144,100..900&display=swap" rel="stylesheet"/>
    </head>
    <body>

        <!-- Template popup sự kiện -->
        <template id="popzy-2">
            <div>
                <div class="event-popup__img">
                    <img src="${pageContext.request.contextPath}/assets/img/event_img.svg" alt="hình ảnh minh hoạ"/>
                </div>

                <div>
                    <p class="event-title">Giảm giá 5%</p>
                    <p class="event-date">Từ ngày 25/10/2025 - 10/11/2025</p>
                </div>

            </div>
        </template>
        <jsp:include page="/includes/header.jsp"/>

        <main>
            <!--=======
            Hero banner 
            ========-->
            <section class="section-hero">
                <div class="container hero-container">
                    <div class="hero-wrap">
                        <!-- Content left -->
                        <div class="hero-left">
                            <label class="hero-left__label ">#The Best in Town</label>
                            <h1 class="hero-left__title">Get food
                                <img src="${pageContext.request.contextPath}/assets/img/pizza_icon.svg" alt="Pizza"/> 
                                delivery and more 
                                <img src="${pageContext.request.contextPath}/assets/img/hamburger_icon.svg" alt="hamburger"/></h1>
                            <p class="hero-left__desc">You want it. We get it. Food, drinks, groceries, and more available for delivery and pickup.</p>

                            <div>
                                <!-- ===================
                                Form tìm kiếm Location
                                ======================-->
                                <form action="MainController" method="POST" class="hero__form">
                                    <input hidden type="text" value="searchStoreByLocation" name="action">
                                    <div class="form-content">
                                        <div class="input__location">
                                            <img class="input__location--img" src="${pageContext.request.contextPath}/assets/img/location_icon.svg" alt="location icon"/>
                                            <select class="input__location--input" name="location">

                                                <option value="">Chọn tỉnh/thành phố</option>
                                                <option value="1">TP. Hồ Chí Minh</option>
                                                <option value="2">Hà Nội</option>
                                                <option value="3">Đà Nẵng</option>
                                                <option value="4">Cần Thơ</option>
                                            </select>

                                        </div>
                                        <button class="btn hero__form--btn">Tìm kiếm</button>
                                    </div>
                                    <c:if test="${not empty messErrorLocation}">
                                        <div class="hero__form--error">
                                            <p class="hero__form--error-msg">${messErrorLocation}</p>
                                        </div>
                                    </c:if>

                                </form>
                            </div>
                        </div>

                        <!-- Content right -->
                        <div class="hero-right">
                            <img class="hero-right__img" src="${pageContext.request.contextPath}/assets/img/deliveryHero.png" alt="deliveryMan"/> 
                        </div>

                    </div>
                </div>
            </section>

            <!--====
            Services
            ========-->
            <section class="section-services">
                <div class="container">
                    <div class="services_info">
                        <h2 class="section__title services_title">Your everyday, right away</h2>
                        <p class="section__desc">Order food and grocery delivery online from hundreds of restaurants and shops nearby.</p>
                    </div>

                    <div class="services_card">
                        <ul class="card__list">
                            <li class="card-item">
                                <img src="${pageContext.request.contextPath}/assets/img/food-item.svg" alt="Food Items"/>
                                <p class="card-item__title">Food Items</p>
                                <p class="card-item__desc">Find deals, free delivery, and more from our restaurant partners.</p>
                                <a class="card-item__link" href="${pageContext.request.contextPath}">Khám phá</a>
                            </li>
                            <li class="card-item"> 
                                <img src="${pageContext.request.contextPath}/assets/img/groceries.svg" alt="groceries Items"/>
                                <p class="card-item__title">Food Items</p>
                                <p class="card-item__desc">Find deals, free delivery, and more from our restaurant partners.</p>
                                <a class="card-item__link" href="url">Khám phá</a>
                            </li>
                            <li class="card-item"> 
                                <img src="${pageContext.request.contextPath}/assets/img/flower-Items.svg" alt="flower Items"/>
                                <p class="card-item__title">Food Items</p>
                                <p class="card-item__desc">Find deals, free delivery, and more from our restaurant partners.</p>
                                <a class="card-item__link" href="url">Khám phá</a>
                            </li>
                        </ul>

                    </div>
                </div>
            </section>

            <!--====
              About   
            ======-->
            <section class="section-about">
                <div class="container">
                    <div class="about-wrap">
                        <div class="about-left">
                            <img src="${pageContext.request.contextPath}/assets/img/about-img.png" alt="about-img"/>
                        </div>

                        <div class="about-right">
                            <h2 class="section__title about-title">About Foodeats</h2>
                            <p class="section__desc about-desc">Foodeats helps you find and order food from wherever you are. How it works: you type in an address, we tell you the restaurants that deliver to that locale as well as showing you droves of pickup restaurants near you.</p>
                            <p class="section__desc about-desc"> Want to be more specific? Search by cuisine, restaurant name or menu item. We'll filter your results accordingly.</p>

                            <a class="btn about-btn" href="url" >Xem thêm</a>
                        </div>
                    </div>
                </div>
            </section>

            <!--====
            feature  
            ======-->
            <section class="section-feature">
                <div class="container">
                    <div class="services_info">
                        <h2 class="section__title feature_title">It’s all here. All in one platform.</h2>
                        <p class="section__desc">Order food and grocery delivery online from hundreds of restaurants and shops nearby.</p>
                    </div>

                    <div class="feature_card">
                        <ul class="card__list">
                            <li class="card-item">
                                <img src="${pageContext.request.contextPath}/assets/img/delivery-icon.svg" alt="Food Items"/>
                                <p class="card-item__title">Deliver With Us</p>
                                <p class="card-item__desc">Sign up today, start earning tomorrow. Build a new career in delivery service with us.</p>
                                <a class="card-item__link btn" href="#">Tìm hiểu thêm</a>
                            </li>
                            <li class="card-item"> 
                                <img src="${pageContext.request.contextPath}/assets/img/partner-icon.svg" alt="groceries Items"/>
                                <p class="card-item__title">Partner With Us</p>
                                <p class="card-item__desc">Sign up today, start earning tomorrow. Build a new career in delivery service with us.</p>
                                <a class="card-item__link btn" href="url">Tìm hiểu thêm</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </section>

            <!--====
            subscribe 
            ======-->       

            <section class="section-sub">
                <div class="container">
                    <div class="sub-wrap">
                        <img class="sub-deco_ham" src="${pageContext.request.contextPath}/assets/img/hamburger_icon.svg" alt="hamburger"/>
                        <img  class="sub-deco_pizza"src="${pageContext.request.contextPath}/assets/img/pizza_icon.svg" alt="hamburger"/>

                        <div class="sub_info">
                            <h2 class="section__title sub_title">Subscribe newsletter to get updates</h2>
                            <p class="section__desc sub_desc">Download the Just Eat app for faster ordering and more personalised recommendations.</p>
                            <div class="sub-cta">
                                <input type="email" name="name" placeholder="Nhập Email của bạn">
                                <button type="submit" class="btn sub-btn">Subscribe</button>
                            </div>
                        </div>
                    </div>
                </div>

            </section>
        </main>

        <jsp:include page="/includes/footer.jsp"/>

        <!-- Gắn sau footer.jsp -->
        <script src="${pageContext.request.contextPath}/assets/js/event.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/home.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

    </body>
</html>
