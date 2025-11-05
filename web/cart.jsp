<%-- 
    Document   : cart
    Created on : Oct 22, 2025, 2:14:35 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/responsive.css"/>
        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <main class="cart">
            <div class="cart__container">

                <div class="cart__header">
                    <span><input type="checkbox" /> Sản phẩm</span>
                    <span>Đơn giá</span>
                    <span>Số lượng</span>
                    <span>Số tiền</span>
                    <span>Thao tác</span>
                </div>

                <div class="cart__shop">
                    <div class="cart__shop-header">
                        <input type="checkbox" />
                        <span class="cart__shop-name">Tên cửa hàng</span>
                    </div>

                    <!-- Product 1 -->
                    <div class="cart__item">
                        <input type="checkbox" />
                        <img src="https://images.unsplash.com/photo-1608138266130-0e31e4d48cf1?w=100" alt="Ảnh món ăn" />
                        <div class="cart__item-info">
                            <p class="cart__item-name">Tên món ăn</p>
                            <p class="cart__item-type">Loại:</p>
                        </div>
                        <span class="cart__item-price">0 VNĐ</span>
                        <div class="cart__quantity">
                            <button>-</button>
                            <input type="number" value="1" />
                            <button>+</button>
                        </div>
                        <span class="cart__item-total">0 VNĐ</span>
                        <button class="cart__remove">Xóa</button>
                    </div>
                </div>
            </div>


            <div class="cart__summary" id="cartSummary">
                <div class="cart__voucher">
                    <div class="cart__voucher-row">
                        <div class="cart__voucher-left">
                            <span>Voucher cửa hàng</span>
                        </div>
                        <a href="#">Chọn hoặc nhập mã</a>
                    </div>
                </div>

                <div class="cart__summary-bottom">
                    <div class="cart__summary-left">
                        <input type="checkbox" /> <span>Chọn tất cả</span>
                        <button>Xóa</button>
                    </div>
                    <div class="cart__summary-right">
                        <p>Tổng cộng: <span class="cart__total">0 VNĐ</span></p>
                        <a href="checkout.jsp" class="btn-buy">Mua hàng</a>
                    </div>
                </div>
            </div>

        </main>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
