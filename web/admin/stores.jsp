<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/pagesCss/users.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Stores List</title>
</head>
<body>
    <table class="table">
        <tr>
            
            <th>StoreID</th>
            <th>Store Owner</th>
            <th>Store Name</th>
            <th>Address</th>
            <th>City</th>
            <th>Rating</th>
            <th>OpenTime</th>
            <th>CloseTime</th>
            <th>Phone Contact</th>
            <th>Email Contact</th>
            <th>Status</th>
            <th>CreatedAt</th>
        </tr>

        <c:forEach var="s" items="${listOfStore}">
            <tr>
                <td>${s.storeID}</td>
                <td>${s.ownerUserID}</td>
                <td>${s.storeName}</td>
                <td>${s.storeAddress}</td>
                <td>${s.city}</td>
                <td>${s.storeRating}</td>
                <td>${s.openTime}</td>
                <td>${s.closeTime}</td>
                <td>${s.storePhone}</td>
                <td>${s.storeEmail}</td>
                <td>
                    <c:choose>
                        <c:when test="${s.status}">
                            <span style="color:green;">Active</span>
                        </c:when>
                        <c:otherwise>
                            <span style="color:red;">Inactive</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${s.createdAt}</td>       
            </tr>
        </c:forEach>
    </table>
</body>
</html>
