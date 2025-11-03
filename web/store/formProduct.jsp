<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/responsive.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/pagesCss/food-form.css"/>

        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>

        <div class="food-form__container">
            <h2 class="food-form__title">${isUpdate? "Cập Nhật": "Thêm"} Món Ăn</h2>

            <c:choose>
                <c:when test="${isUpdate}">
                    <c:set var="actionValue" value="updateProduct"/>
                </c:when>
                <c:otherwise>
                    <c:set var="actionValue" value="addProduct"/>
                </c:otherwise>
            </c:choose>

            <form action="${pageContext.request.contextPath}/MainController" method="post" class="food-form__form mt-4">

                <input type="text" name="action" value="${actionValue}" hidden>

                <c:if test="${isUpdate}">
                    <input type="text" name="productID" value="${product.productID}" hidden>
                </c:if>

                <input type="text" name="storeId" value="${sessionScope.store.storeID}" hidden>

                <div class="food-form__group">
                    <label class="food-form__label form-label">Tên món ăn</label>
                    <input type="text" class="food-form__input form-control" name="productName" required
                           value="${isUpdate ? product.productName : txtProductName}">
                    <span class="food-form__error">${error_productName}</span>
                </div>

                <div class="food-form__group">
                    <label class="food-form__label form-label">Giá món ăn</label>
                    <input type="text" class="food-form__input form-control" name="productPrice" required
                           value="${isUpdate ? product.productPrice : txtProductPrice}">
                    <span class="food-form__error">${error_productPrice}</span>
                </div>

                <div class="food-form__group">
                    <label class="food-form__label form-label">Mô tả</label>
                    <textarea class="food-form__textarea form-control" name="productDesc" rows="3" cols="50" style="resize: none;" placeholder="Mô tả món ăn (optional)">${isUpdate ? product.productDesc : txtProductDesc}</textarea>
                </div>

                <div class="food-form__group">
                    <label class="food-form__label form-label">Danh mục</label>                        
                    <select class="food-form__select form-select" name="categoryID" required>
                        <option value="">Chọn danh mục</option>

                        <c:forEach var="cate" items="${sessionScope.listOfCate}">
                            <c:if test="${cate.isActive}">
                                <option value="${cate.categoryID}"
                                        ${isUpdate && product.categoryID.categoryID == cate.categoryID ? "selected" : ""}>
                                    ${cate.categoryName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <span class="food-form__error">${txtProductCate}</span>
                </div>

                <button type="submit" class="food-form__button">
                    <c:choose>
                        <c:when test="${isUpdate}">Cập nhật món ăn</c:when>
                        <c:otherwise>Thêm món ăn</c:otherwise>
                    </c:choose>
                </button>

            </form>

            <c:if test="${not empty error_ProductInCate}">
                <script>
                    alert("${error_ProductInCate}");
                </script>
            </c:if>
        </div>

        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
