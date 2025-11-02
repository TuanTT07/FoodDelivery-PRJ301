
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h2> ${isUpdate? "Cập Nhật": "Thêm"} Sản Phẩm</h2>
            <c:choose>
                <c:when test="${isUpdate}">
                    <c:set var="actionValue" value="updateProduct"/>
                </c:when>
                <c:otherwise>
                    <c:set var="actionValue" value="addProduct"/>
                </c:otherwise>
            </c:choose>

            <form action="${pageContext.request.contextPath}/MainController" method="post" class="mt-4">

                <input type="text" name="action" value="${actionValue}" hidden>

                <c:if test="${isUpdate}">
                    <input type="text" name="productID" value="${product.productID}" hidden>
                </c:if>

                <input type="text" name="storeId" value="${sessionScope.store.storeID}" hidden>
                <div class="mb-3">
                    <label class="form-label">Tên sản phẩm</label>
                    <input type="text" class="form-control" name="productName" required
                           value="${isUpdate ? product.productName : txtProductName}">

                    <span>${error_productName}</span>
                </div>

                <div class="mb-3">
                    <label class="form-label">Giá sản phẩm</label>
                    <input type="number" step="0.01" class="form-control" name="productPrice" required
                           value="${isUpdate ? product.productPrice : txtProductPrice}">

                    <span>${error_productPrice}</span>

                </div>

                <div class="mb-3">
                    <label class="form-label">Mô tả</label>
                    <textarea class="form-control " name="productDesc" rows="3" cols="50" style="resize: none;" placeholder="Mô tả sản phẩm (optional)">${isUpdate ? product.productDesc : txtProductDesc}</textarea>
                </div>

                <div class="mb-3">

                    <label class="form-label">Danh mục</label>                        
                    <select class="form-select" name="categoryID" required>
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


                    <span>${txtProductCate}</span>

                </div>
                <button type="submit" class="btn btn-primary px-5">
                    <c:choose>
                        <c:when test="${isUpdate}">Cập nhật sản phẩm</c:when>
                        <c:otherwise>Thêm sản phẩm</c:otherwise>
                    </c:choose>
                </button>

            </form>

            <c:if test="${not empty error_ProductInCate}">
                <script>
                    alert("${error_ProductInCate}");
                </script>
            </c:if>
            <c:if test="${not empty error_ProductInCate}">
                <script>
                    alert("${error_ProductInCate}");
                </script>
            </c:if>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

    </body>
</html>
