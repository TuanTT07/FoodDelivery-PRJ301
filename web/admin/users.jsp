<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <!-- CSS -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table class="table">      
            <tr>
                <th>Avatar</th>
                <th>User_ID</th>
                <th>UserName</th>
                <th>FullName</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Role</th>
                <th>Status</th>
                <th>CreatedAt</th>
                <th>UpdatedAt</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="u" items="${listOfUsers}">
                <tr>
                    <td><img src="${u.avatarURL}" style="width: 50px"/></td>
                    <td>${u.userID}</td>
                    <td>${u.userName}</td>
                    <td>${u.userFullName}</td>
                    <td>${u.userEmail}</td>
                    <td>${u.userPhone}</td>
                    <td>${u.userAddress}</td>
                    <td>
                        <c:choose>
                            <c:when test="${u.roleID != null && u.roleID.roleName != null}">
                                ${u.roleID.roleName}
                            </c:when>
                            <c:otherwise>
                                ${u.roleID != null ? u.roleID.roleID : ''}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:choose>
                            <c:when test="${u.status}">
                                <span>Active</span>
                            </c:when>
                            <c:otherwise>
                                <span>Inactive</span>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>${u.createdAt}</td>
                    <td>${u.updatedAt}</td>
                    <td>
                        <a 
                            href="${pageContext.request.contextPath}/MainController?action=callUpdateUser&userID=${u.userID}">Update
                        </a>
                        <a 
                            href="${pageContext.request.contextPath}/MainController?action=deleteUser&userID=${u.userID}" onclick="return confirmDelete('${u.userFullName}')">Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table> 
        <!-- confirm delete script -->
        <script>
            function confirmDelete(name) {
                return confirm("Are you sure you want to delete user: " + name + "?");
            }
        </script>
    </body>
</html>
