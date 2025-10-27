<%-- 
    Document   : dashboardStore
    Created on : Oct 22, 2025
    Author     : ACER
--%>

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
        <div class="store">

            <jsp:include page="sidebar.jsp"/>

            <div class="store__content">

                <div class="store__header">
                    <form action="SearchController" method="post" class="store__search-form">
                        <input type="text" name="keyword" placeholder="Search..." class="store__search-input">
                        <button type="submit" class="store__search-button">
                            <img src="${pageContext.request.contextPath}/assets/img/search.svg" alt="search" class="store__search-icon"/> 
                        </button>
                    </form>

                    <div class="store__user-info">
                        <p class="store__user-greeting">Hello, <span class="store__user-name">Store's owner</span></p>
                        <img src="${pageContext.request.contextPath}/assets/img/avatar.svg" alt="Avatar" class="store__avatar"/>
                    </div>
                </div>

                <!-- Dashboard Content -->
                <div class="category__list">
                    <h2 class="category__title">Category</h2>
                    <div class="category__edit">
                        <div class="category__item">
                            <div class="category__top">
                                <div class="category__product">
                                    <div>
                                        <p class="category__name">Food name</p>
                                        <p class="category__date">Date</p>
                                    </div>
                                </div>
                            </div>
                            <p class="category__text">
                                Description
                            </p>
                            <div class="category__actions">
                                <button class="category__add">+</button>
                            </div>
                        </div>
                    </div>
                    <div class="category__list">
                        <div class="category__item">
                            <div class="category__top">
                                <div class="category__product">
                                    <div>
                                        <p class="category__name">Beef Burger</p>
                                        <p class="category__date">October 25, 2025</p>
                                    </div>
                                </div>
                            </div>
                            <p class="category__text">
                                Beef burger là món ăn gồm một miếng thịt bò xay được nướng hoặc chiên chín, kẹp giữa hai lát bánh mì tròn mềm. Bên trong thường có phô mai, rau xà lách, cà chua, dưa leo muối, hành tây và sốt mayonnaise hoặc ketchup. Hương vị hòa quyện giữa thịt bò đậm đà, bánh mì thơm mềm và rau tươi mát, tạo nên món ăn đặc trưng của ẩm thực phương Tây.
                            </p>
                            <div class="category__actions">
                                <button>Edit</button>
                                <button>Delete</button>
                            </div>
                        </div>

                        <div class="category__item">
                            <div class="category__top">
                                <div class="category__user">
                                    <div>
                                        <p class="category__name">Pizza Margherita</p>
                                        <p class="category__date">October 24, 2025</p>
                                    </div>
                                </div>
                            </div>
                            <p class="category__text">
                                Pizza Margherita là món pizza truyền thống của Ý, được làm với đế bánh mỏng giòn, phủ nước sốt cà chua tươi, phô mai mozzarella tan chảy, và lá húng quế xanh. Món ăn nổi bật với ba màu đỏ – trắng – xanh tượng trưng cho quốc kỳ Ý, mang hương vị thanh nhẹ, béo ngậy và thơm đặc trưng của phô mai và húng quế tươi.
                            </p>  
                            <div class="category__actions">
                                <button>Edit</button>
                                <button>Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>