<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Add Product Details</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <div class="container py-4">
            <h2 class="mb-4 text-center text-primary">Add Product Information</h2>

            <!-- FORM 1: PRODUCT DETAIL -->
            <div class="card mb-4">
                <div class="card-header bg-primary text-white">Product Detail</div>
                <div class="card-body">
                    <form id="productDetailForm" action="${pageContext.request.contextPath}/MainController" method="post">
                        <input type="hidden" name="action" value="addProductDetail">
                        <input type="hidden" name="productID" value="${param.productID}">

                        <!-- Global error (e.g. product not found or insert failed) -->
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>

                        <div class="mb-3">
                            <label class="form-label">Size</label>
                            <select name="size" class="form-select" required>
                                <option value="" ${empty sizeSelected ? "selected" : ""} disabled>-- Select size --</option>
                                <option value="Small" ${sizeSelected == 'Small' ? 'selected' : ''}>Small</option>
                                <option value="Medium" ${sizeSelected == 'Medium' ? 'selected' : ''}>Medium</option>
                                <option value="Large" ${sizeSelected == 'Large' ? 'selected' : ''}>Large</option>
                                <option value="Extra Large" ${sizeSelected == 'Extra Large' ? 'selected' : ''}>Extra Large</option>
                            </select>
                            <c:if test="${not empty error_Size}">
                                <div class="form-text text-danger">${error_Size}</div>
                            </c:if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Combo</label>
                            <input type="text" name="combo" class="form-control" placeholder="e.g. Combo 1, Combo 2"
                                   value="${comboValue != null ? comboValue : ''}">
                            <c:if test="${not empty error_Com}">
                                <div class="form-text text-danger">${error_Com}</div>
                            </c:if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Extra Info</label>
                            <textarea name="extraInfo" class="form-control" rows="3"
                                      placeholder="Any extra info...">${extraInfoValue != null ? extraInfoValue : ''}</textarea>
                            <c:if test="${not empty error_ExtraInfo}">
                                <div class="form-text text-danger">${error_ExtraInfo}</div>
                            </c:if>
                        </div>

                        <button type="submit" class="btn btn-success">Save Product Detail</button>
                    </form>

                    <!-- Success alert + hide form -->
                    <c:if test="${not empty errorDetail}">
                        <div class="alert alert-danger">${errorDetail}</div>
                    </c:if>
                    <c:if test="${not empty successDetail}">
                        <div class="alert alert-success alert-dismissible fade show mt-3" role="alert" id="successAlertDetail">
                            ${success}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>

                        <script>
                            document.addEventListener('DOMContentLoaded', () => {
                                const form = document.getElementById('productDetailForm');
                                if (form)
                                    form.style.display = 'none';

                                // auto-hide success after 3s (tùy chọn)
                                setTimeout(() => {
                                    const a = document.getElementById('successAlertDetail');
                                    if (a)
                                        a.style.display = 'none';
                                }, 3000);
                            });
                        </script>
                    </c:if>
                </div>
            </div>

            <!-- FORM 2: PRODUCT OPTION -->
            <div class="card mb-4">
                <div class="card-header bg-warning text-dark">Product Options</div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/MainController" method="post" id="optionForm">
                        <input type="hidden" name="action" value="addProductOption">
                        <input type="hidden" name="productID" value="${param.productID}">

                        <!-- Global error (tránh trùng với errorOption) -->
                        <c:if test="${not empty errorOption}">
                            <div class="alert alert-danger">${errorOption}</div>
                        </c:if>

                        <div id="optionContainer" class="mb-3"></div>

                        <button type="button" id="addOption" class="btn btn-outline-primary mb-3">+ Add Option</button>
                        <br>
                        <button type="submit" class="btn btn-warning text-dark">Save All Options</button>
                    </form>

                    <!-- Success alert + hide form -->
                    <c:if test="${not empty successOption}">
                        <div class="alert alert-success alert-dismissible fade show mt-3" role="alert" id="optionSuccessAlert">
                            ${successOption}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>

                        <script>
                            document.addEventListener('DOMContentLoaded', () => {
                                const form = document.getElementById('optionForm');
                                if (form)
                                    form.style.display = 'none';

                                setTimeout(() => {
                                    const alert = document.getElementById('optionSuccessAlert');
                                    if (alert)
                                        alert.style.display = 'none';
                                }, 3000);
                            });
                        </script>
                    </c:if>
                </div>
            </div>

            <!-- FORM 3: PRODUCT PICTURES -->
            <div class="card mb-4">
                <div class="card-header bg-info text-white">Product Pictures</div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/MainController" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="action" value="addProductPicture">
                        <input type="hidden" name="productID" value="${param.productID}">

                        <div class="mb-3">
                            <label class="form-label">Upload Pictures</label>
                            <input type="file" name="pictureFiles" class="form-control" multiple required>
                            <div class="form-text">You can select multiple pictures.</div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Main Picture</label>
                            <input type="checkbox" name="isMain" value="true"> Set as main picture
                        </div>

                        <button type="submit" class="btn btn-info text-white">Upload Pictures</button>
                    </form>
                </div>
            </div>

            <!-- Back to Product Page -->
            <div class="text-center">
                <!-- Cần phải xử lí lại -->

                <a href="${pageContext.request.contextPath}/MainController?action=viewProduct&storeID=${param.storeID}" class="btn btn-secondary">Back to Product Page</a>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                            const container = document.getElementById('optionContainer');
                            const addBtn = document.getElementById('addOption');

                            // Hàm tạo 1 dòng option mới
                            function createOptionRow() {
                                const row = document.createElement('div');
                                row.classList.add('row', 'mb-2', 'optionRow');

                                const colType = document.createElement('div');
                                colType.classList.add('col-md-4');
                                const inputType = document.createElement('input');
                                inputType.type = 'text';
                                inputType.name = 'optionType';
                                inputType.classList.add('form-control');
                                inputType.placeholder = 'Option Type (e.g. Topping)';
                                inputType.required = true;
                                colType.appendChild(inputType);

                                const colValue = document.createElement('div');
                                colValue.classList.add('col-md-4');
                                const inputValue = document.createElement('input');
                                inputValue.type = 'text';
                                inputValue.name = 'optionValue';
                                inputValue.classList.add('form-control');
                                inputValue.placeholder = 'Option Value (e.g. Cheese)';
                                inputValue.required = true;
                                colValue.appendChild(inputValue);

                                const colPrice = document.createElement('div');
                                colPrice.classList.add('col-md-3');
                                const inputPrice = document.createElement('input');
                                inputPrice.type = 'number';
                                inputPrice.step = '0.01';
                                inputPrice.name = 'extraPrice';
                                inputPrice.classList.add('form-control');
                                inputPrice.placeholder = 'Extra Price';
                                colPrice.appendChild(inputPrice);

                                const colBtn = document.createElement('div');
                                colBtn.classList.add('col-md-1', 'd-flex', 'align-items-center');
                                const removeBtn = document.createElement('button');
                                removeBtn.type = 'button';
                                removeBtn.textContent = '−';
                                removeBtn.classList.add('btn', 'btn-danger', 'btn-sm');
                                removeBtn.addEventListener('click', () => row.remove());
                                colBtn.appendChild(removeBtn);

                                // gắn tất cả vào row
                                row.appendChild(colType);
                                row.appendChild(colValue);
                                row.appendChild(colPrice);
                                row.appendChild(colBtn);

                                return row;
                            }

                            // khi load trang, có 1 dòng mặc định
                            container.appendChild(createOptionRow());

                            // khi nhấn + Add Option
                            addBtn.addEventListener('click', () => {
                                container.appendChild(createOptionRow());
                            });
        </script>
    </body>
</html>
