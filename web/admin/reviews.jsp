<%-- 
    Document   : dashboard
    Created on : Oct 22, 2025, 2:17:43 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reponsive.css"/>

        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">
    </head>

    <body>
        <div class= "admin-container">
            <!-- Sidebar -->
            <jsp:include page="sidebar.jsp"/>

            <!-- Main Content -->
            <div class="admin-content">
                <!-- Header -->
                <div class="admin-header">

                    <div class="admin-info">
                        <p>Hello, <span>Admin</span></p>
                        <img src="${u.avatarURL}" alt="avatar" class="admin-avatar"/>
                    </div>
                </div>

                <!-- Content Section -->
                <div class="review__list">
                    <h2 class="review__title">Đánh giá Trang Wed</h2>
                    <div class="review__item">
                        <div class="review__top">
                            <div class="review__user">
                                <p class="review__name">Nguyen Huy</p>
                                <p class="review__date">November 10, 2025</p>
                            </div>
                            <div class="review__rating">⭐⭐⭐⭐⭐</div>
                        </div>
                        <p class="review__text">
                            Trang web rất dễ sử dụng, tốc độ tải nhanh và giao diện thân thiện. Tôi rất hài lòng!
                        </p>
                    </div>

                    <div class="review__item">
                        <div class="review__top">
                            <div class="review__user">
                                <p class="review__name">Tran Mai</p>
                                <p class="review__date">November 8, 2025</p>
                            </div>
                            <div class="review__rating">⭐⭐⭐⭐</div>
                        </div>
                        <p class="review__text">
                            Dịch vụ chăm sóc khách hàng phản hồi nhanh, hỗ trợ tận tình. Mình thấy rất chuyên nghiệp.
                        </p>
                    </div>

                    <div class="review__item">
                        <div class="review__top">
                            <div class="review__user">
                                <p class="review__name">Le Thanh</p>
                                <p class="review__date">November 7, 2025</p>
                            </div>
                            <div class="review__rating">⭐⭐⭐⭐⭐</div>
                        </div>
                        <p class="review__text">
                            Giao diện website đẹp, bố cục rõ ràng. Trải nghiệm mua hàng rất mượt mà!
                        </p>
                    </div>

                    <div class="review__item">
                        <div class="review__top">
                            <div class="review__user">
                                <p class="review__name">Pham An</p>
                                <p class="review__date">November 5, 2025</p>
                            </div>
                            <div class="review__rating">⭐⭐⭐</div>
                        </div>
                        <p class="review__text">
                            Đôi khi web hơi chậm lúc tải nhiều hình, nhưng tổng thể vẫn rất tốt.
                        </p>
                    </div>

                    <div class="review__item">
                        <div class="review__top">
                            <div class="review__user">
                                <p class="review__name">Hoang Linh</p>
                                <p class="review__date">November 3, 2025</p>
                            </div>
                            <div class="review__rating">⭐⭐⭐⭐⭐</div>
                        </div>
                        <p class="review__text">
                            Rất thích tính năng đặt hàng nhanh và theo dõi đơn hàng trực tuyến của trang web!
                        </p>
                    </div>

                    <div class="review__item">
                        <div class="review__top">
                            <div class="review__user">
                                <p class="review__name">Do Nhat</p>
                                <p class="review__date">November 1, 2025</p>
                            </div>
                            <div class="review__rating">⭐⭐⭐⭐</div>
                        </div>
                        <p class="review__text">
                            Hệ thống thanh toán tiện lợi, dễ thao tác. Mình sẽ tiếp tục sử dụng dịch vụ!
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
