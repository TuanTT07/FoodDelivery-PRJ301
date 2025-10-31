<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Dashboard</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/responsive.css"/>

        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">
    </head>

    <body>
        <div class="store">

            <jsp:include page="sidebar.jsp"/>

            <div class="store__content">

                <jsp:include page="headerDashboard.jsp"/>


                <!-- Dashboard Content -->
                <div class="store__dashboard">
                    <h2 class="store__dashboard-title">Dashboard Overview</h2>

                    <p class="store__dashboard-subtitle">Welcome back,${sessionScope.OwnerStoreName}</p>

                    <div class="store__stats">
                        <div class="store__card">
                            <h3 class="store__card-number">12</h3>
                            <p class="store__card-label">Order</p>
                        </div>
                        <div class="store__card">
                            <h3 class="store__card-number">8</h3>
                            <p class="store__card-label">Completed</p>
                        </div>
                        <div class="store__card">
                            <h3 class="store__card-number">2</h3>
                            <p class="store__card-label">Delivering</p>
                        </div>
                        <div class="store__card">
                            <h3 class="store__card-number">5</h3>
                            <p class="store__card-label">Favourite</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>