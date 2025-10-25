<%-- 
    Document   : home
    Created on : Oct 22, 2025, 2:16:43 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                        <ul id="categoryList">
                            <li>
                                <a href="#" data-id="1">
                                    <div class="cate__bg">
                                        <img class="cate__img" src="${pageContext.request.contextPath}/assets/img/hamburger_icon.svg" alt="hamburger"/>
                                    </div>
                                    <p>Burger</p>
                                </a>
                            </li>
                            <li>
                                <a href="url" data-id="2">
                                    <div class="cate__bg">
                                        <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/pizza_icon.svg" alt="pizza"/>
                                    </div>
                                    <p>Pizza</p>
                                </a>
                            </li>
                            <li>
                                <a href="url" data-id="3"> 
                                    <div class="cate__bg">
                                        <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/sandwiches.svg" alt="sandwiches"/>
                                    </div>
                                    <p>Sandwiches</p>
                                </a>
                            </li>
                            <li>
                                <a href="url" data-id="4">
                                    <div class="cate__bg">
                                        <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/wings_icon.svg" alt="wings_icon"/>

                                    </div>
                                    <p>Wings</p>
                                </a>
                            </li>
                            <li>
                                <a href="url" data-id="5">
                                    <div class="cate__bg">
                                        <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/coffee_icon.svg" alt="coffee_icon"/>

                                    </div>

                                    <p>Coffee & Tea</p>
                                </a>
                            </li>
                            <li>
                                <a href="url" data-id="6">
                                    <div class="cate__bg">
                                        <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/indian_icon.svg" alt="indian_icon"/>

                                    </div>

                                    <p>Indian</p>
                                </a>
                            </li>
                            <li>
                                <a href="url" data-id="7">
                                    <div class="cate__bg">
                                        <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/chinese_icon.svg" alt="chinese_icon"/>
                                    </div>

                                    <p>Chinese</p>
                                </a>
                            </li>
                            <li>
                                <a href="url" data-id="8">
                                    <div class="cate__bg">
                                        <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/Thai_icon.svg" alt="Thai_icon"/>

                                    </div>

                                    <p>Thai</p>
                                </a>
                            </li>
                            <li>
                                <a href="url"data-id="9">
                                    <div class="cate__bg">
                                        <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/american_icon.svg" alt="american_iconlt"/>

                                    </div>

                                    <p>American</p>
                                </a>
                            </li>
                            <li>
                                <a href="url"data-id="10"> 
                                    <div class="cate__bg">
                                        <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/japan_icon.webp" alt="sushi_icon"/>

                                    </div>

                                    <p>Japan</p>
                                </a>
                            </li>
                        </ul>

                    </div>
                    <div class="line"></div>


                    <div class="info">
                        <p class="info__address">Địa chỉ: </p>
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
                        
                    </div>

                </div>
            </section>
        </main>


        <jsp:include page="/includes/footer.jsp"/>
        <script>
            const contextPath = '${pageContext.request.contextPath}';
        </script>
        <script type="module" src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    </body>
</html>
