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
            <!-- Login/SignUp Form -->
            <div class="auth">
                <div class="container auth-container">
                    <div class="auth-wrapp">
                        <h2 class="auth-title">Đăng Kí 
                            <c:if test="${param.action eq 'signUpDelivery'}">tái xế</c:if> 
                            <c:if test="${param.action eq 'signUpUser'}">Khách Hàng</c:if>
                            <c:if test="${param.action eq 'signUpStore'}">Cửa Hàng</c:if>

                            </h2>
                            <form action="${pageContext.request.contextPath}/MainController" method="POST" accept-charset="UTF-8">
                            <input type="hidden" name="action" value="${param.action}">
                            <div class="login-content">
                                <!-- Form cho User -->
                                <c:if test="${param.action ne 'signUpStore'}">
                                    <div>
                                        <input class="inputUserName" type="text" name="userName" placeholder="Tên đăng nhập" value="${u.userName}" >
                                        <span style="color:red">${error_username}</span>
                                    </div>
                                    <div>
                                        <input class="inputPassword" type="password" name="password" placeholder="mật khẩu" >
                                        <span style="color:red">${error_password}</span>
                                    </div>
                                    <div>
                                        <input class="inputFullname" type="text" name="Fullname" placeholder="Họ và tên" value="${u.userFullName}" >
                                        <span style="color:red">${error_fullName}</span>
                                    </div>
                                </c:if>

                                <div>
                                    <input class="inputEmail" type="text" name="email" placeholder="Email" value="${u.userEmail}" >
                                    <span style="color:red">${error_email}</span>
                                </div>
                                <div>
                                    <input class="inputPhone" type="text" name="Phone" placeholder="Số điện thoại" value="${u.userPhone}" >
                                    <span style="color:red">${error_phone}</span>
                                </div>
                                <!-- Form cho Delivery -->

                                <c:if test="${param.action eq 'signUpDelivery'}">
                                    <select class="inputCity" name="city">
                                        <option value="">Thành phố/Tỉnh giao hàng</option>
                                        <option value="1" ${city == '1' ? 'selected' : ''}>TP. Hồ Chí Minh</option>
                                        <option value="2" ${city == '2' ? 'selected' : ''}>Hà Nội</option>
                                        <option value="3" ${city == '3' ? 'selected' : ''}>Đà Nẵng</option>
                                        <option value="4" ${city == '4' ? 'selected' : ''}>Cần Thơ</option>
                                    </select>     
                                    <span style="color: red">${error_address}</span>
                                </c:if>

                                <!-- Form cho Store -->
                                <c:if test="${param.action eq 'signUpStore'}">
                                    <c:if test="${error_message}">
                                        <script>
                                            alert("${error_message}");
                                        </script>
                                    </c:if>
                                    <input hidden name="userId" value="${u.userID}">
                                    <input type="hidden" name="username" value="${u.userName}">
                                    <div>
                                        <input class="inputStoreName" type="text" name="storeName" placeholder="Tên cửa hàng" value="${storeName != null ? storeName : ''}">
                                        <span style="color:red">${error_storeName}</span>
                                    </div>
                                    <div>  
                                        <textarea class="store__desc" name="description"  rows="4" cols="50" placeholder="Mô tả cửa hàng">${description != null ? description : ''}</textarea>
                                    </div>

                                    <div>
                                        <label class="label_address">Địa chỉ cửa hàng</label>
                                        <div class="Form_Address">
                                            <div>
                                                <input class="inputStreet" type="text" name="street" placeholder="Số/ đường" value="${street != null ? street : param.street}" >
                                            </div>
                                            <div>
                                                <input class="inputWard" type="text" name="district" placeholder="Phường/Xã" value="${ward != null ? ward : param.ward}" >
                                            </div>

                                            <div>
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
                                        <label>Mở 24h:</label>
                                        <input type="checkbox" name="is24Hours" ${is24Hours ? 'checked' : ''}>
                                    </div>
                                    <div>
                                        <label class="label_cate">Chọn danh mục cửa hàng</label>

                                        <select class="inputCity" name="cate">
                                            <option value="" hidden selected disabled></option>
                                            <option value="1" ${cate == '1' ? 'selected' : ''}>Đồ ăn</option>
                                            <option value="2" ${cate == '2' ? 'selected' : ''}>Đồ uống</option>
                                            <option value="3" ${cate == '3' ? 'selected' : ''}>Đồ chay</option>
                                            <option value="4" ${cate == '4' ? 'selected' : ''}>Ăn vặt</option>                                          
                                            <option value="5" ${cate == '5' ? 'selected' : ''}>Đồ ngọt</option>
                                        </select>     
                                        <span style="color: red">${error_category}</span>

                                    </div>
                                    <div>
                                        <label class="label_cate">Thời gian mở cửa:</label>
                                        <input class="inputTimeOpen" type="time" name="timeOpen" placeholder="Giờ mở cửa" value="${openTime}" >
                                        <span style="color: red">${openTime}</span>

                                        <label class="label_cate">Thời gian đóng cửa:</label>
                                        <input class="inputTimeClose" type="time" name="timeClose" placeholder="Giờ đóng cửa" value="${closeTime}" >
                                        <span style="color: red">${closeTime}</span>


                                    </div>
                                    <!-- Hình ảnh -->
                                    <div>
                                        <label class="label_logo">Logo cửa hàng:</label>
                                        <input type="file" id="avatarFile" name="logo" accept="image/*" required>
                                        <input type="hidden" name="txtAvatarBase64" id="avatarBase64" value="" />
                                        <img id="avatarPreview" style="display:none; max-width:150px; margin-top:5px;" />
                                    </div>

                                    <div>
                                        <label class="label_banner">Ảnh bìa:</label>
                                        <input type="file" id="coverFile" name="coverImage" accept="image/*">
                                        <input type="hidden" name="txtCoverImageBase64" id="coverImageBase64" value="${s.coverURL}" />
                                        <img id="coverPreview" style="display:none; max-width:300px; margin-top:5px;" />
                                    </div>
                                    <h3 class="label_info__bank">Thông tin thanh toán (không bắt buộc)</h3>

                                    <div>
                                        <label class="label_info__bank" >Tên chủ tài khoản:</label>
                                        <input class="inputCity" type="text" name="bankAccountName" placeholder="VD: NGUYEN VAN A">
                                        <span style="color: red">${error_bankAccoutName}</span>

                                    </div>

                                    <div>
                                        <label class="label_info__bank" >Số tài khoản:</label>
                                        <input class="inputCity"  type="text" name="bankAccountNumber" placeholder="VD: 0123456789">
                                        <span style="color: red">${error_bankAccoutNumber}</span>

                                    </div>

                                    <div>
                                        <label class="label_info__bank">Ngân hàng:</label>
                                        <select class="inputCity"  name="bankName">
                                            <option value="" hidden selected disabled ></option>
                                            <option value="1">Vietcombank</option>
                                            <option value="2">Techcombank</option>
                                            <option value="3">BIDV</option>
                                            <option value="4">ACB</option>
                                            <option value="5">MB Bank</option>

                                        </select>
                                        <span style="color: red">${error_bankName}</span>

                                    </div>

                                </c:if>
                                <div>
                                    <button type="submit" class="btn btn-login">Đăng kí</button>
                                </div>
                            </div>
                        </form>

                        <div class="auth-div">
                            <p>Hoặc</p>
                        </div>
                        <div>
                            <a class="auth-gg" href="#">
                                <img src="${pageContext.request.contextPath}/assets/img/gg_icon.svg" alt="Google icon"/>
                                <span> Đăng kí với Google</span>
                            </a>
                        </div>
                    </div>

                </div>
            </div>       
        </main>              
        <jsp:include page="/includes/footer.jsp"/>

        <script>
            function handleFileInput(fileInputId, hiddenInputId, previewImgId) {
                const fileInput = document.getElementById(fileInputId);
                const hiddenInput = document.getElementById(hiddenInputId);
                const previewImg = document.getElementById(previewImgId);

                fileInput.addEventListener('change', function () {
                    const file = this.files[0];
                    if (!file)
                        return;

                    const reader = new FileReader();
                    reader.onload = function (e) {
                        const base64String = e.target.result; // data:image/png;base64,...
                        hiddenInput.value = base64String;

                        // Hiển thị preview
                        previewImg.src = base64String;
                        previewImg.style.display = 'block';
                    };
                    reader.readAsDataURL(file);
                });
            }

            // Áp dụng cho cả 2 file input
            handleFileInput('avatarFile', 'avatarBase64', 'avatarPreview');
            handleFileInput('coverFile', 'coverImageBase64', 'coverPreview');
        </script>
    </body>
</html>
