<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Thông tin tài khoản</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet"/>
        <style>
            body {
                background:#f7f8fa;
            }
            .profile-card {
                background:#fff;
                border:1px solid #e5e7eb;
                border-radius:10px;
                padding:30px;
            }
            .avatar {
                width:100px;
                height:100px;
                border-radius:50%;
                object-fit:cover;
                border:3px solid #fff;
                box-shadow:0 0 0 1px #d0d5dd;
            }
            .name {
                font-size:1.6rem;
                font-weight:600;
                margin-bottom:5px;
            }
            .sub a {
                color:#1e88e5;
                text-decoration:none;
            }
            .section-title {
                font-weight:600;
                color:#6b7280;
                margin-top:25px;
                margin-bottom:10px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <div class="profile-card">
                <!-- Header -->
                <div class="d-flex align-items-center">
                    <img id="avatarPreview" class="avatar me-3"
                         src="<c:choose>
                             <c:when test='${not empty u.avatarURL}'>${u.avatarURL}</c:when>
                             <c:otherwise>${pageContext.request.contextPath}/assets/img/avatar-default.png</c:otherwise>
                         </c:choose>"
                         alt="avatar">
                    <div>
                        <div class="name" id="displayName">${fn:escapeXml(u.userFullName)}</div>
                        <div class="sub small">
                            <a id="displayEmail" href="mailto:${u.userEmail}">${fn:escapeXml(u.userEmail)}</a>
                        </div>
                    </div>
                </div>

                <!-- Account Info -->
                <div class="section-title">Thông tin tài khoản</div>
                <form action="${pageContext.request.contextPath}/MainController" method="POST"  accept-charset="UTF-8" enctype="multipart/form-data" class="row g-3">
                    <input type="hidden" name="action" value="">
                    <div class="col-md-6">
                        <label class="form-label">Username</label>
                        <input type="text" class="form-control" value="${u.userName}" readonly>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Họ và tên</label>
                        <input type="text" class="form-control" id="fullname" value="${u.userFullName}" readonly>
                    </div>

                    <div class="col-md-8">
                        <label class="form-label">Email</label>
                        <div class="input-group">
                            <input type="email" class="form-control" value="${u.userEmail}" readonly>
                            <c:if test="${not u.isVerified}">
                                <a class="btn btn-outline-primary"
                                   href="#">
                                    Xác thực email
                                </a>
                            </c:if>
                        </div>
                        <c:choose>
                            <c:when test="${u.isVerified}">
                                <div class="form-text text-success mt-1">
                                    <i class="bi bi-check2-circle"></i> Đã xác thực.
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="form-text text-danger mt-1">Vui lòng xác thực email để tiếp tục.</div>
                            </c:otherwise>
                        </c:choose>

                    </div>

                    <div class="col-md-4">
                        <label class="form-label">Số điện thoại</label>
                        <input type="text" class="form-control" value="${u.userPhone}" readonly>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Địa chỉ</label>
                        <input type="text" class="form-control" value="${u.userAddress}" readonly>
                    </div>
                    <a href="${pageContext.request.contextPath}/MainController?action=callUpdateUser&userID=${u.userID}">Update</a>
                </form>
            </div>
        </div>

        <script>
            // Hiển thị “đã xác minh”
            document.getElementById("verifyEmailBtn").addEventListener("click", function () {
                document.getElementById("emailStatus").style.display = "inline-flex";
                this.disabled = true;
            });

            // Hiển thị ảnh khi upload
            document.getElementById("avatarInput").addEventListener("change", function (e) {
                const file = e.target.files[0];
                if (!file)
                    return;
                const reader = new FileReader();
                reader.onload = ev => document.getElementById("avatarPreview").src = ev.target.result;
                reader.readAsDataURL(file);
            });

        </script>
    </body>
</html>
