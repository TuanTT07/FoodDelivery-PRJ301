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
                    <!-- Order Detail Content -->
                <div class="delivery__order-detail">
                  
                    <h1 class="delivery__title">Chi tiết đơn hàng</h1>

                    <div class="delivery__order-info">
                        <p><strong>Mã đơn hàng:</strong> #12345</p>
                        <p><strong>Tên khách hàng:</strong> Nguyễn Văn A</p>
                        <p><strong>Địa chỉ:</strong> 123 Lê Lợi, Quận 1, TP.HCM</p>
                        <p><strong>Số điện thoại:</strong> 0909 123 456</p>
                    </div>

                    <div class="delivery__order-items">
                        <h2 class="delivery__section-title">Các món đã đặt</h2>
                        <table class="delivery__table">
                            <thead>
                                <tr>
                                    <th>Món ăn</th>
                                    <th>Số lượng</th>
                                    <th>Giá</th>
                                    <th>Tổng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Burger Combo</td>
                                    <td>2</td>
                                    <td>120,000₫</td>
                                    <td>240,000₫</td>
                                </tr>
                                <tr>
                                    <td>Pepsi</td>
                                    <td>1</td>
                                    <td>20,000₫</td>
                                    <td>20,000₫</td>
                                </tr>
                            </tbody>
                        </table>

                        <div class="delivery__order-total">
                            <p><strong>Tổng cộng</strong> 260,000₫</p>
                        </div>
                    </div>

                    <div class="delivery__order-note">
                        <h2 class="delivery__section-title">Ghi chú</h2>
                        <p>Vui lòng giao trước 6h tối, gọi trước khi đến.</p>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>