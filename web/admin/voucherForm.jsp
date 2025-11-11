<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thêm Voucher</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reponsive.css"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>

        <main>
            <div class="auth">
                <div class="container auth-container">
                    <div class="auth-wrapp">
                        <h2 class="auth-title">Thêm Voucher</h2>

                        <form action="${pageContext.request.contextPath}/MainController" method="POST" class="login-content">
                            <input type="hidden" name="action" value="addVoucher"/>

                            <div>
                                <input class="inputUserName" type="text" name="voucherCode" placeholder="Mã voucher (VD: SAVE20)"
                                       value="${voucherCode}">
                                <span style="color:red">${error_voucherCode}</span>
                            </div>

                            <div>
                                <textarea class="store__desc" name="description" rows="3" placeholder="Mô tả voucher">${description}</textarea>
                                <span style="color:red">${error_description}</span>
                            </div>

                            <div>
                                <input class="inputFullname" type="number" step="0.01" min="0" max="100" name="discountPercent" placeholder="Giảm (%)"
                                       value="${discountPercent}">
                                <span style="color:red">${error_discountPercent}</span>
                            </div>

                            <div>
                                <input class="inputCity" type="number" step="0.01" min="0" name="minOrderAmount" placeholder="Đơn tối thiểu (₫)"
                                       value="${minOrderAmount}">
                                <span style="color:red">${error_minOrderAmount}</span>
                            </div>

                            <div>
                                <input class="inputCity" type="number" step="0.01" min="0" name="maxDiscountAmount" placeholder="Giảm tối đa (₫)"
                                       value="${maxDiscountAmount}">
                                <span style="color:red">${error_maxDiscountAmount}</span>
                            </div>

                            <div>
                                <label class="label_cate">Ngày bắt đầu:</label>
                                <input class="inputTimeOpen" type="datetime-local" name="startDate" value="${startDateStr}">
                                <span style="color:red">${error_startDate}</span>
                            </div>

                            <div>
                                <label class="label_cate">Ngày kết thúc:</label>
                                <input class="inputTimeClose" type="datetime-local" name="endDate" value="${endDateStr}">
                                <span style="color:red">${error_endDate}</span>
                            </div>

                            <div>
                                <label>Kích hoạt ngay:</label>
                                <input type="checkbox" name="isActive" ${isActive ? 'checked' : ''}>
                            </div>

                            <div><button type="submit" class="btn btn-login">Tạo voucher</button></div>
                        </form>
                    </div>
                </div>
            </div>
        </main>

        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
