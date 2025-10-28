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
        <title>Delivery Dashboard</title>

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
                        <p class="store__user-greeting">Xin chào, <span class="store__user-name">Delivery person</span></p>
                        <img src="${pageContext.request.contextPath}/assets/img/avatar.svg" alt="Avatar" class="store__avatar"/>
                    </div>
                </div>
                <!-- Settings Content -->
                <div class="delivery__settings">

                    <h2 class="delivery__title">Cài đặt</h2>

                    <div class="delivery__settings-section">
                        <h3 class="delivery__section-title">Thông báo</h3>
                        <label class="delivery__toggle">
                            <input type="checkbox" checked>
                            <span class="delivery__slider"></span>
                            Bật/tắt thông báo
                        </label>
                    </div>

                    <div class="delivery__settings-section">
                        <h3 class="delivery__section-title">Đổi mật khẩu</h3>
                        <form class="delivery__password-form">
                            <input type="password" placeholder="Mật khẩu hiện tại" class="delivery__input">
                            <input type="password" placeholder="Mật khẩu mới" class="delivery__input">
                            <input type="password" placeholder="Xác nhận mật khẩu mới" class="delivery__input">
                            <button type="submit" class="delivery__btn">Cập nhật mật khẩu</button>
                        </form>
                    </div>

                    <div class="delivery__settings-section">
                        <h3 class="delivery__section-title">Ngôn ngữ</h3>
                        <select class="delivery__select">
                            <option>English</option>
                            <option>Tiếng việt</option>
                        </select>
                    </div>

                    <div class="delivery__settings-section">
                        <button class="delivery__logout-btn">Đăng xuất</button>
                    </div>

                </div>

            </div>
        </div>
    </body>
</html>