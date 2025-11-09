<%-- 
   Document   : register_member
   Created on : Oct 22, 2025, 2:16:07 PM
   Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reponsive.css"/>
        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&family=Roboto+Serif:ital,opsz,wght@0,8..144,100..900;1,8..144,100..900&display=swap" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>

        <main>
            <!-- Update Form -->
            <div class="auth">
                <div class="container auth-container">
                    <div class="auth-wrapp">
                        <h2 class="auth-title">Thông Tin Tài Khoản</h2>
                        <form action="${pageContext.request.contextPath}/MainController" method="POST" accept-charset="UTF-8">
                            <input type="hidden" name="action" value="updateUser">
                            <div class="login-content">
                                <div>
                                    <input class="inputUserName" type="text" name="userName" placeholder="Tên đăng nhập" value="${u.userName}" readonly>
                                    <span style="color:red">${error_username}</span>
                                </div>
                                <div>
                                    <input class="inputFullname" type="text" name="Fullname" placeholder="Họ và tên" value="${u.userFullName}" >
                                    <span style="color:red">${error_fullName}</span>
                                </div>
                                <div>
                                    <input class="inputEmail" type="text" name="email" placeholder="Email" value="${u.userEmail}" >
                                    <span style="color:red">${error_email}</span>
                                </div>
                                <div>
                                    <input class="inputPhone" type="text" name="Phone" placeholder="Số điện thoại" value="${u.userPhone}" >
                                    <span style="color:red">${error_phone}</span>
                                </div>
                                <div>
                                    <label class="label_address">
                                        Địa chỉ hiện tại
                                    </label>
                                    <div>
                                        <input class="oldAddress" type="text" name="Address" placeholder="Địa chỉ..." value="${u.userAddress}" readonly>
                                    </div>
                                </div>
                                <div>
                                    <label class="label_address">
                                        Địa chỉ mới(Nếu có)
                                    </label>
                                    <div class="Form_Address">
                                        <div>
                                            <input class="inputStreet" type="text" name="street" placeholder="Số/ đường" value="${street != null ? street : param.street}" >
                                            <input class="inputWard" type="text" name="ward" placeholder="Phường/Xã" value="${ward != null ? ward : param.ward}" >                        
                                            <select class="inputCity" name="city">
                                                <option value="">Chọn tỉnh/thành phố</option>
                                                <option value="1" ${city == '1' ? 'selected' : ''}>TP. Hồ Chí Minh</option>
                                                <option value="2" ${city == '2' ? 'selected' : ''}>Hà Nội</option>
                                                <option value="3" ${city == '3' ? 'selected' : ''}>Đà Nẵng</option>
                                                <option value="4" ${city == '4' ? 'selected' : ''}>Cần Thơ</option>
                                            </select>     
                                        </div>                                 
                                    </div>
                                    <span style="color: red">${error_address}</span>
                                </div>   
                                <div>
                                    <label >Ảnh đại diện (tùy chọn):</label>
                                    <input type="file" id="avatarFile" name="avatar" accept="image/*">
                                    <input type="hidden" name="txtAvatarUser" id="avatarBase64" value="">
                                    <img id="avatarPreview" style="display:none; max-width:150px; margin-top:5px;" />
                                </div>
                                <c:if test="${not empty sessionScope.u 
                                              and sessionScope.u.roleID != null
                                              and sessionScope.u.roleID.roleID eq 'S001'}">
                                      <!-- Role -->
                                      <div>
                                          <label>Vai trò</label>
                                          <select class="inputRole" name="roleId">
                                              <option value="">Chọn vai trò</option>
                                              <option value="S001" ${u.roleID.roleID=='S001'?'selected':''}>Admin</option>
                                              <option value="S002" ${u.roleID.roleID=='S002'?'selected':''}>Store Owner</option>
                                              <option value="S003" ${u.roleID.roleID=='S003'?'selected':''}>Driver</option>
                                              <option value="S004" ${u.roleID.roleID=='S004'?'selected':''}>Member</option>
                                          </select>
                                      </div>

                                      <!-- Status -->
                                      <div>
                                          <label>
                                              <input type="checkbox" name="status" value="1"
                                                     ${u.status ? 'checked' : ''}>
                                              Kích hoạt tài khoản (Active)
                                          </label>
                                      </div>
                                </c:if> 
                                <div>
                                    <button type="submit" class="btn btn-login">Save</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>       
        </main>              
        <jsp:include page="/includes/footer.jsp"/>
        <script src="${pageContext.request.contextPath}/assets/js/handleFile.js"></script>
    </body>
</html>
