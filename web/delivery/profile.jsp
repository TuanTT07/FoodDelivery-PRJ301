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
                        <p class="store__user-greeting">Hello, <span class="store__user-name">Delivery person</span></p>
                        <img src="${pageContext.request.contextPath}/assets/img/avatar.svg" alt="Avatar" class="store__avatar"/>
                    </div>
                </div>
                <!-- Profile Content -->
                <div class="delivery__profile">

                    <h2 class="delivery__title">Thông tin tài xế</h2>

                    <div class="delivery__profile-header">
                        <img src="${pageContext.request.contextPath}/assets/img/avatar.svg" alt="Driver Avatar" class="delivery__profile-avatar"/>
                        <div class="delivery__profile-info">
                            <h3 class="delivery__profile-name">Nguyen Van A</h3>
                            <p class="delivery__profile-role">Tài xế</p>
                        </div>
                    </div>

                    <div class="delivery__profile-section">
                        <h3 class="delivery__section-title">Thông tin cá nhân</h3>
                        <div class="delivery__profile-details">
                            <p><strong>Số điện thoại:</strong> 0901 234 567</p>
                            <p><strong>Email:</strong> nguyenvana@example.com</p>
                            <p><strong>Biển số xe:</strong> 59X3-123.45</p>
                            <p><strong>Tên phương tiện:</strong> Winner X</p>
                        </div>
                    </div>

                    <div class="delivery__profile-section">
                        <h3 class="delivery__section-title">Giấy tờ và thông tin ngân hàng</h3>
                        <div class="delivery__profile-details">
                            <p><strong>Giấy phép lái xe</strong> 123456789 - Có hiệu lực đến năm 2028</p>
                            <p><strong>Tên ngân hàng</strong> BIDV Bank</p>
                            <p><strong>Tài khoản ngân hàng</strong> 0123456789</p>
                        </div>
                    </div>

                    <div class="delivery__profile-actions">
                        <button class="delivery__edit-btn">Chỉnh sửa thông tin</button>
                    </div>

                </div>

            </div>
        </div>
    </body>
</html>