<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cập nhật Voucher</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reponsive.css"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>

        <main>
            <div class="auth">
                <div class="container auth-container">
                    <div class="auth-wrapp">
                        <h2 class="auth-title">Cập nhật Voucher</h2>

                        <form action="${pageContext.request.contextPath}/MainController" method="POST" class="login-content">
                            <input type="hidden" name="action" value="updateVoucher"/>
                            <input type="hidden" name="voucherID" value="${v.voucherID}"/>

                            <div>
                                <input class="inputUserName" type="text" name="voucherCode" placeholder="Mã voucher"
                                       value="${v.voucherCode}">
                                <span style="color:red">${error_voucherCode}</span>
                            </div>

                            <div>
                                <textarea class="store__desc" name="description" rows="3" placeholder="Mô tả voucher">${v.description}</textarea>
                                <span style="color:red">${error_description}</span>
                            </div>

                            <div>
                                <input class="inputFullname" type="number" step="0.01" min="0" max="100" name="discountPercent" placeholder="Giảm (%)"
                                       value="${v.discountPercent}">
                                <span style="color:red">${error_discountPercent}</span>
                            </div>

                            <div>
                                <input class="inputCity" type="number" step="0.01" min="0" name="minOrderAmount" placeholder="Đơn tối thiểu (₫)"
                                       value="${v.minOrderAmount}">
                                <span style="color:red">${error_minOrderAmount}</span>
                            </div>

                            <div>
                                <input class="inputCity" type="number" step="0.01" min="0" name="maxDiscountAmount" placeholder="Giảm tối đa (₫)"
                                       value="${v.maxDiscountAmount}">
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
                                <label>Kích hoạt:</label>
                                 <input type="checkbox" name="isActive" ${isActive ? 'checked' : ''}>
                            </div>

                            <div><button type="submit" class="btn btn-login">Save</button></div>
                        </form>
                    </div>
                </div>
            </div>
        </main>

        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>