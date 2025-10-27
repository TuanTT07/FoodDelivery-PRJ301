<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food Detail Page</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/responsive.css"/>
        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&family=Roboto+Serif:ital,opsz,wght@0,8..144,100..900;1,8..144,100..900&display=swap" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>

        <main>
            <section class="food-detail">
                <div class="container">
                    <a href="#" class="food-detail__back">
                        <img src="${pageContext.request.contextPath}/assets/img/arrow_left.svg" alt="arrow_left"/>
                        <span>Trở về cửa hàng</span>
                    </a>

                    <div class="food-detail__content">
                        <div class="food-detail__image-wrapper">
                            <img class="food-detail__image" src="${pageContext.request.contextPath}/assets/img/bannerStore.png" alt="Ảnh minh hoạ sản phẩm"/>
                        </div>

                        <div class="food-detail__info">
                            <h3 class="food-detail__title">Eggs Benedict Burger</h3>
                            <p class="food-detail__desc">
                                Ground beef, hollandaise sauce mix, stone ground mustard, with an egg — it’s so tasty you’ll love this burger.
                            </p>
                            <p class="food-detail__price">Giá tiền: 100K</p>

                            <form action="${pageContext.request.contextPath}/MainController" method="post" class="food-detail__form">
                                <input type="hidden" name="action" value="addToCart"/>
                                <input type="hidden" name="productId" value="P001"/>
                                <c:if test="${not empty toppings}">
                                    <div class="food-detail__toppings">
                                        <h3 class="food-detail__subheading">Chọn topping:</h3>

                                        <ul class="food-detail__topping-list">
                                            <c:forEach var="t" items="${toppings}">
                                                <li class="food-detail__topping-item">
                                                    <label class="food-detail__topping-label">
                                                        <input type="checkbox" name="topping" value="${t.id}" class="food-detail__topping-checkbox">
                                                        <span class="food-detail__topping-name">${t.name}</span>
                                                        <span class="food-detail__topping-price">${t.price}</span>
                                                    </label>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </c:if>
                                
                                <textarea class="food-detail__note" name="note" rows="4" cols="50" placeholder="Yêu cầu riêng..."></textarea>

                                <div class="food-detail__actions">
                                    <div class="food-detail__quantity">
                                        <button type="button" class="quantity__btn" onclick="decrease()">−</button>
                                        <span id="quantity" class="quantity__value">1</span>
                                        <button type="button" class="quantity__btn" onclick="increase()">+</button>
                                    </div>

                                    <input type="hidden" id="quantityInput" name="quantity" value="1">
                                    <button type="submit" class="btn btn--add-to-cart">Thêm vào giỏ hàng</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </main>

        <jsp:include page="/includes/footer.jsp"/>
        <script src="${pageContext.request.contextPath}/assets/js/productDetail.js"></script>
    </body>
</html>
