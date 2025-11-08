<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fns" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thanh toán đơn hàng</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/responsive.css"/>
    </head>
    <body>
        <fmt:setLocale value="vi_VN" />
        <jsp:include page="/includes/header.jsp"/>

        <main class="checkout">
            <h1 class="checkout__title">Thanh toán đơn hàng</h1>

            <!-- form gửi tới controller xử lý đặt hàng -->
            <form method="post" action="MainController?action=placeOrder" class="checkout__form">
                <!-- gửi user id nếu cần -->
                <input type="hidden" name="uID" value="${userID != null ? userID : sessionScope.u.userID}" />

                <!-- 1. Thông tin khách hàng -->
                <section class="checkout__form--info">
                    <h2 class="checkout__heading">1. Thông tin khách hàng</h2>

                    <div class="form__group">
                        <label for="fullName" class="form__label">Họ và tên</label>
                        <input id="fullName" name="fullName" type="text" class="form__input"
                               value="${sessionScope.u != null ? sessionScope.u.userFullName : ''}" required>
                    </div>

                    <div class="form__group">
                        <label for="phone" class="form__label">Số điện thoại</label>
                        <input id="phone" name="phone" type="tel" class="form__input"
                               value="${sessionScope.u != null ? sessionScope.u.userPhone : ''}" required>
                    </div>

                    <div class="form__group">
                        <label for="email" class="form__label">Email</label>
                        <input id="email" name="email" type="email" class="form__input"
                               value="${sessionScope.u != null ? sessionScope.u.userEmail : ''}" required>
                    </div>
                </section>

                <!-- 2. Địa chỉ giao hàng -->
                <section class="checkout__form--address">
                    <h2 class="checkout__heading">2. Địa chỉ giao hàng</h2>

                    <div class="form__group">
                        <label for="address" class="form__label">Địa chỉ</label>
                        <input id="address" name="address" type="text" class="form__input"
                               value="${addr}" required>
                    </div>

                    <div class="form__group">
                        <label for="ward" class="form__label">Phường/Xã</label>
                        <input id="ward" name="ward" type="text" class="form__input"
                               value="${ward}" required>
                    </div>

                    <div class="form__group">
                        <label for="city" class="form__label">Tỉnh/Thành phố</label>
                        <input id="city" name="city" type="text" class="form__input"
                               value="${city}" required>
                    </div>
                </section>

                <!-- 3. Shipping -->
                <section class="checkout__form--shipping">
                    <h2 class="checkout__heading">3. Phương thức giao hàng</h2>

                    <div class="form__radio-group">
                        <label class="form__radio">
                            <input type="radio" name="shipping" value="standard" checked> Giao tiêu chuẩn (30–45 phút)
                        </label>
                        <label class="form__radio">
                            <input type="radio" name="shipping" value="express"> Giao nhanh (15 phút)
                        </label>
                        <label class="form__radio">
                            <input type="radio" name="shipping" value="instant"> Ưu tiên (xử lý trước)
                        </label>
                    </div>
                </section>

                <!-- 4. Payment -->
                <section class="checkout__form--payment">
                    <h2 class="checkout__heading">4. Phương thức thanh toán</h2>

                    <div class="form__radio-group">
                        <label class="form__radio">
                            <input type="radio" name="payment" value="cod" checked> Thanh toán khi nhận hàng (COD)
                        </label>
                        <label class="form__radio">
                            <input type="radio" name="payment" value="VNpay"> VNPay
                        </label>
                        <label class="form__radio">
                            <input type="radio" name="payment" value="momo"> Ví Momo
                        </label>
                    </div>
                </section>

                <!-- 5. Tóm tắt đơn hàng (dữ liệu từ CheckoutController) -->
                <section class="checkout__form--summary">
                    <h2 class="checkout__heading">5. Tóm tắt đơn hàng</h2>

                    <c:choose>
                        <c:when test="${empty checkoutItems}">
                            <p>Không có sản phẩm nào để thanh toán. <a href="cart.jsp">Quay lại giỏ hàng</a></p>
                        </c:when>
                        <c:otherwise>
                            <table class="summary">
                                <thead class="summary__head">
                                    <tr>
                                        <th>Sản phẩm</th>
                                        <th>Số lượng</th>
                                        <th>Đơn giá</th>
                                        <th>Tạm tính</th>
                                    </tr>
                                </thead>
                                <tbody class="summary__body">
                                    <c:forEach var="item" items="${checkoutItems}" varStatus="loop">
                                        <tr>
                                            <td>${item.productName}</td>
                                            <td>${item.quantity}</td>
                                            <td><fmt:formatNumber value="${item.price}" pattern="#,##0"/> VNĐ</td>
                                            <td><fmt:formatNumber value="${item.price * item.quantity}" pattern="#,##0"/> VNĐ</td>

                                    <input type="hidden" name="productId[${loop.index}]" value="${item.productId}" />
                                    <input type="hidden" name="quantity[${loop.index}]" value="${item.quantity}" />
                                    </tr>

                                </c:forEach>

                                </tbody>
                                <tfoot class="summary__foot">

                                    <tr>
                                        <td colspan="3">Tổng tiền hàng:</td>
                                        <td>
                                            <fmt:formatNumber value="${grandTotal}" pattern="#,##0"/> VNĐ
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">Phí vận chuyển:</td>
                                        <td>Miễn phí</td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">Voucher đã áp dụng:</td>
                                        <td>0 VNĐ</td>
                                    </tr>
                                    <tr>
                                        <td colspan="3"><strong>Tổng thanh toán:</strong></td>
                                        <td><strong><fmt:formatNumber value="${grandTotal}" pattern="#,##0"/> VNĐ</strong></td>
                                    </tr>
                                </tfoot>

                            </table>
                        </c:otherwise>
                    </c:choose>
                </section>

                <!-- 6. Xác nhận -->
                <section class="checkout__form--confirm">
                    <label class="form__checkbox">
                        <input type="checkbox" name="agree" required> Tôi đồng ý với điều khoản & chính sách
                    </label>
                    <input type="hidden" name="grandTotal" value="${grandTotal}" />
                    <button type="submit" class="checkout__btn">Đặt hàng</button>
                </section>
            </form>
        </main>

        <jsp:include page="/includes/footer.jsp"/>
        <script>
            document.querySelector('.checkout__form').addEventListener('submit', e => {
                if (!document.querySelector('[name="agree"]').checked) {
                    alert('Vui lòng đồng ý với điều khoản trước khi đặt hàng!');
                    e.preventDefault();
                }
            });
        </script>
    </body>
</html>
