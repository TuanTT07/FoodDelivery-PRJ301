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
                        <p class="store__user-greeting">Xin chào, <span class="store__user-name"></span></p>
                        <img src="${pageContext.request.contextPath}/assets/img/avatar.svg" alt="Avatar" class="store__avatar"/>
                    </div>
                </div>
                <!-- Earnings Content -->
                <div class="delivery__earnings">
                    <h2 class="delivery__title">Tổng quan thu nhập</h2>

                    <div class="delivery__summary">
                        <div class="summary__card">
                            <h3>Hôm nay</h3>
                            <p class="summary__amount">200.000VNĐ</p>
                        </div>
                        <div class="summary__card">
                            <h3>Tuần này</h3>
                            <p class="summary__amount">1.200.000VNĐ</p>
                        </div>
                        <div class="summary__card">
                            <h3>Tháng này</h3>
                            <p class="summary__amount">4.600.000VNĐ</p>
                        </div>
                    </div>

                    <h3 class="delivery__section-title">Thu nhập tính theo từng khách hàng</h3>
                    <table class="delivery__table">
                        <thead>
                            <tr>
                                <th>Mã khách hàng</th>
                                <th>Thời gian</th>
                                <th>Phí vận chuyển</th>
                                <th>Thưởng</th>
                                <th>Tiền tip</th>
                                <th>Tổng</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>#A123</td>
                                <td>2025-10-25</td>
                                <td>80.000VNĐ</td>
                                <td>20.000VNĐ</td>
                                <td>10.000VNĐ</td>
                                <td>110.000VNĐ</td>
                            </tr>
                            <tr>
                                <td>#A124</td>
                                <td>2025-10-26</td>
                                <td>65.000VNĐ</td>
                                <td>18.000VNĐ</td>
                                <td>8.000VNĐ</td>
                                <td>93.000VNĐ</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>