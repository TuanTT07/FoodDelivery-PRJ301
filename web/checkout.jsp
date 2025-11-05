<%-- 
    Document   : checkout
    Created on : Oct 22, 2025, 2:14:44 PM
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

        <main class="checkout">
            <h1 class="checkout__title">Thanh toán đơn hàng</h1>

            <form method="post" class="checkout__form">

                <section class="checkout__form--info">
                    <h2 class="checkout__heading">1. Thông tin khách hàng</h2>

                    <div class="form__group">
                        <label for="fullName" class="form__label">Họ và tên</label>
                        <input type="text" class="form__input" required>
                    </div>

                    <div class="form__group">
                        <label for="phone" class="form__label">Số điện thoại</label>
                        <input type="tel" class="form__input" required>
                    </div>

                    <div class="form__group">
                        <label for="email" class="form__label">Email</label>
                        <input type="email" class="form__input" required>
                    </div>
                </section>

                <section class="checkout__form--address">
                    <h2 class="checkout__heading">2. Địa chỉ giao hàng</h2>

                    <div class="form__group">
                        <label for="address" class="form__label">Địa chỉ</label>
                        <input type="text" class="form__input" required>
                    </div>

                    <div class="form__group">
                        <label for="ward" class="form__label">Phường/Xã</label>
                        <input type="text" class="form__input" required>
                    </div>

                    <div class="form__group">
                        <label for="city" class="form__label">Tỉnh/Thành phố</label>
                        <input type="text" class="form__input" required>
                    </div>
                </section>

                <section class="checkout__form--shipping">
                    <h2 class="checkout__heading">3. Phương thức giao hàng</h2>

                    <div class="form__radio-group">
                        <label class="form__radio">
                            <input type="radio" name="shipping" value="standard" checked>
                            Giao tiêu chuẩn (30–45 phút)
                        </label>
                        <label class="form__radio">
                            <input type="radio" name="shipping" value="express">
                            Giao nhanh (15 phút)
                        </label>
                        <label class="form__radio">
                            <input type="radio" name="shipping" value="instant">
                            Ưu tiên (Đơn hàng được xử lý trước)
                        </label>
                    </div>
                </section>

                <section class="checkout__form--payment">
                    <h2 class="checkout__heading">4. Phương thức thanh toán</h2>

                    <div class="form__radio-group">
                        <label class="form__radio">
                            <input type="radio" name="payment" value="cod" checked>
                            Thanh toán khi nhận hàng (COD)
                        </label>
                        <label class="form__radio">
                            <input type="radio" name="payment" value="bank">
                            Chuyển khoản ngân hàng
                        </label>
                        <label class="form__radio">
                            <input type="radio" name="payment" value="momo">
                            Ví Momo
                        </label>
                    </div>
                </section>

                <section class="checkout__form--summary">
                    <h2 class="checkout__heading">5. Tóm tắt đơn hàng</h2>

                    <table class="summary">
                        <thead class="summary__head">
                            <tr>
                                <th>Sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Giá</th>
                                <th>Tạm tính</th>
                            </tr>
                        </thead>
                        <tbody class="summary__body">
                            <tr>
                                <td>Tên sản phẩm</td>
                                <td>Số lượng</td>
                                <td>Đơn giá</td>
                                <td>Tổng giá</td>
                            </tr>
                        </tbody>
                        <tfoot class="summary__foot">
                            <tr>
                                <td colspan="3">Tổng tiền hàng:</td>
                                <td>0 VNĐ</td>
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
                                <td><strong>0 VNĐ</strong></td>
                            </tr>
                        </tfoot>
                    </table>
                </section>

                <section class="checkout__form--confirm">
                    <label class="form__checkbox">
                        <input type="checkbox" required> Tôi đồng ý với điều khoản & chính sách
                    </label>
                    <button type="submit" class="checkout__btn">Đặt hàng</button>
                </section>

            </form>
        </main>

        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
