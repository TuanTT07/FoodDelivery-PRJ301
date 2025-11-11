<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Voucher List</title>

    </head>
    <body>
        <table class="table">
            <tr>
                <th>Voucher ID</th>
                <th>Voucher Code</th>
                <th>Description</th>
                <th>Discount(%)</th>
                <th>MinOderAmount($)</th>
                <th>MaxDiscountAmount($)</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Active</th>
                <th>Created Date</th>
                <th>Action</th>

            </tr>

            <c:forEach var="v" items="${listOfVoucher}">
                <tr>
                    <td>${v.voucherID}</td>
                    <td>${v.voucherCode}</td>
                    <td>${v.description}</td>
                    <td>${v.discountPercent}</td>
                    <td>${v.minOrderAmount}</td>
                    <td>${v.maxDiscountAmount}</td>
                    <td>${v.startDate}</td>
                    <td>${v.endDate}</td>
                    <td>
                        <c:choose>
                            <c:when test="${v.isActive}">
                                <span style="color:green;">Active</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color:red;">Inactive</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${v.createdAt}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/MainController?action=callUpdateVoucher&voucherID=${v.voucherID}">
                            Update
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
