<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Panel</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reponsive.css"/>
</head>
<body>

    <div class="admin-container">
        <!-- Sidebar -->
        <jsp:include page="sidebar.jsp"/>

        <!-- Main content -->
        <div class="admin-content">
            <div class="admin-header">
                <form class="search-form" action="MainController" method="post">
                    <input type="text" name="search" placeholder="Tìm kiếm...">
                    <button type="submit">
                        <img src="${pageContext.request.contextPath}/assets/img/search.svg" alt="search"/> 
                    </button>
                </form>
                <div class="admin-info">
                    <p>Hello, <span>Admin</span></p>
                    <img class="admin-avatar" src="${pageContext.request.contextPath}/assets/img/avatar.svg" alt="Avatar"/>
                </div>
            </div>

            <div class="admin-wrap">
                <jsp:include page="${contentPage}"/>
            </div>
        </div>
    </div>

</body>
</html>
