<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food Detail Page</title>
        <!-- CSS -->
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
            <section class="product-detail">
                <div class="container">
                    <a href="#" class="product-detail__back">
                        <img src="${pageContext.request.contextPath}/assets/img/arrow_left.svg" alt="arrow_left"/>
                        <span>Trở về cửa hàng</span>
                    </a>

                    <div class="product-detail__content">
                        <div class="product-detail__image-wrapper">
                            <c:forEach var="pic" items="${listOfPictures}">
                                <c:if test="${pic.isMain}">
                                    <img class="product-detail__image main-image"
                                         src="${pic.pictureURL}"
                                         alt="${product.productName}" />
                                </c:if>
                            </c:forEach>

                            <div class="product-detail__thumbnails">
                                <c:forEach var="pic" items="${listOfPictures}">
                                    <c:if test="${not pic.isMain}">
                                        <img src="${pic.pictureURL}"
                                             alt="Thumbnail"
                                             onclick="document.querySelector('.main-image').src = this.src">
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>



                        <div class="product-detail__info">
                            <h3 class="product-detail__title">${product.productName}</h3>
                            <p class="product-detail__desc">${product.productDesc}</p>
                            <p class="product-detail__price">Giá tiền: ${product.productPrice} VND</p>


                            <form action="${pageContext.request.contextPath}/MainController" method="post" class="product-detail__form">
                                <input type="hidden" name="action" value="addToCart"/>
                                <input type="hidden" name="productId" value="P001"/>
                                <c:if test="${not empty listOfDetails}">
                                    <div class="product-detail__toppings">
                                        <h3 class="product-detail__subheading">Chọn thêm:</h3>

                                        <ul class="product-detail__topping-list">
                                            <c:forEach var="d" items="${listOfDetails}">
                                                <li class="product-detail__topping-item">
                                                    <label class="product-detail__topping-label">
                                                        <input type="checkbox" name="detail" value="${d.detailID}" class="product-detail__topping-checkbox">
                                                        <span class="product-detail__topping-name">${d.size} - ${d.combo}</span>
                                                        <span class="product-detail__topping-price">${d.extraInfo}</span>
                                                    </label>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </c:if>

                                <c:if test="${not empty listOfOptions}">
                                    <div class="product-detail__toppings">
                                        <h3 class="product-detail__subheading">Chọn topping:</h3>

                                        <ul class="product-detail__topping-list">
                                            <c:forEach var="t" items="${listOfOptions}">
                                                <li class="product-detail__topping-item">
                                                    <label class="product-detail__topping-label">
                                                        <input type="checkbox" name="topping" value="${t.optionID}" class="product-detail__topping-checkbox">
                                                        <span class="product-detail__topping-name">${t.optionValue}</span>
                                                        <span class="product-detail__topping-price">+ ${t.extraPrice} VND</span>
                                                    </label>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </c:if>


                                <textarea class="product-detail__note" name="note" rows="4" cols="50" placeholder="Yêu cầu riêng..."></textarea>

                                <div class="product-detail__actions">
                                    <div class="product-detail__quantity">
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
        <script>
                                            document.querySelector('.main-image').src = this.src
        </script>
    </body>
</html>
