
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
            <h2>Thêm Sản Phẩm</h2>
            <form action="${pageContext.request.contextPath}/MainController" method="post" class="mt-4">

                <input type="text" name="action" value="addProduct" hidden>
                <input type="text" name="storeId" value="${sessionScope.store.storeID}" hidden>
                <div class="mb-3">
                    <label class="form-label">Tên sản phẩm</label>
                    <input type="text" class="form-control" name="productName" required placeholder="VD: Trà Sữa Trân Châu">
                </div>

                <div class="mb-3">
                    <label class="form-label">Giá sản phẩm</label>
                    <input type="number" step="0.01" class="form-control" name="productPrice" required placeholder="VD: 45000">
                </div>

                <div class="mb-3">
                    <label class="form-label">Mô tả</label>
                    <textarea class="form-control " name="productDesc" rows="3" cols="50" style="resize: none;" placeholder="Mô tả sản phẩm (optional)"></textarea>
                </div>

                <div class="mb-3">

                    <label class="form-label">Danh mục</label>                        
                    <select class="form-select" name="categoryID" required>

                        <c:forEach var="cate" items="${sessionScope.listOfCate}">

                            <option  value="${cate.categoryID}">${cate.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary px-5">Add Product</button>
            </form>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

    </body>
</html>
