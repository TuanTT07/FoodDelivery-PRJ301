<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/responsive.css"/>
        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">


        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <div class="page__container">
            <h2 class="page__title">Add Product Information</h2>

            <!-- FORM 1 -->
            <div class="product-detail">
                <div class="product-detail__header">Product Detail</div>
                <div class="product-detail__body">
                    <form id="productDetailForm" class="product-detail__form" action="${pageContext.request.contextPath}/MainController" method="post">
                        <input type="hidden" name="action" value="addProductDetail">
                        <input type="hidden" name="productID" value="${param.productID}">

                        <c:if test="${not empty error}">
                            <div class="product-detail__alert product-detail__alert--error">${error}</div>
                        </c:if>

                        <div class="product-detail__field">
                            <label class="product-detail__label">Size</label>
                            <select name="size" class="product-detail__select" required>
                                <option value="" ${empty sizeSelected ? "selected" : ""} disabled>-- Select size --</option>
                                <option value="Small" ${sizeSelected == 'Small' ? 'selected' : ''}>Small</option>
                                <option value="Medium" ${sizeSelected == 'Medium' ? 'selected' : ''}>Medium</option>
                                <option value="Large" ${sizeSelected == 'Large' ? 'selected' : ''}>Large</option>
                                <option value="Extra Large" ${sizeSelected == 'Extra Large' ? 'selected' : ''}>Extra Large</option>
                            </select>
                        </div>

                        <div class="product-detail__field">
                            <label class="product-detail__label">Combo</label>
                            <input type="text" name="combo" class="product-detail__input" placeholder="e.g. Combo 1, Combo 2"
                                   value="${comboValue != null ? comboValue : ''}">
                        </div>

                        <div class="product-detail__field">
                            <label class="product-detail__label">Extra Info</label>
                            <textarea name="extraInfo" class="product-detail__textarea" rows="3"
                                      placeholder="Any extra info...">${extraInfoValue != null ? extraInfoValue : ''}</textarea>
                        </div>

                        <button type="submit" class="product-detail__btn product-detail__btn--save">Save Product Detail</button>
                    </form>
                </div>
            </div>

            <!-- FORM 2 -->
            <div class="product-option">
                <div class="product-option__header">Product Options</div>
                <div class="product-option__body">
                    <form action="${pageContext.request.contextPath}/MainController" method="post" id="optionForm" class="product-option__form">
                        <input type="hidden" name="action" value="addProductOption">
                        <input type="hidden" name="productID" value="${param.productID}">

                        <div id="optionContainer" class="product-option__container"></div>

                        <button type="button" id="addOption" class="product-option__btn product-option__btn--add">+ Add Option</button>
                        <button type="submit" class="product-option__btn product-option__btn--save">Save All Options</button>
                    </form>
                    <c:if test="${not empty successOption}">
                        <div class="alert alert-success" role="alert">
                            ${successOption}
                        </div>
                    </c:if>

                    <c:if test="${not empty errorOption}">
                        <div class="alert alert-danger" role="alert">
                            ${errorOption}
                        </div>
                    </c:if>

                </div>


            </div>

            <!-- FORM 3 -->
            <form action="${pageContext.request.contextPath}/MainController" method="post" id="pictureForm" class="product-picture__form">
                <input type="hidden" name="action" value="addProductPicture">
                <input type="hidden" name="productID" value="${param.productID}">
                <input type="hidden" name="base64List" id="base64List">

                <div class="product-picture__field">
                    <label class="product-picture__label">Upload Pictures</label>
                    <input type="file" id="pictureInput" multiple accept="image/*" class="product-picture__input" required>
                    <div class="product-picture__note">You can select multiple pictures.</div>
                </div>

                <div id="previewContainer" class="product-picture__preview"></div>

                <button type="submit" class="product-picture__btn product-picture__btn--upload">Upload Pictures</button>
            </form>

            <div class="product-picture__footer">
                <a href="${pageContext.request.contextPath}/MainController?action=viewProduct&storeID=${param.storeID}" 
                   class="product-picture__btn product-picture__btn--back">Back to Product Page</a>
            </div>
        </div>
        <jsp:include page="/includes/footer.jsp"/>

        <script>
            const container = document.getElementById('optionContainer');
            const addBtn = document.getElementById('addOption');

            function createOptionRow() {
                const row = document.createElement('div');
                row.classList.add('product-option__row');

                const inputType = document.createElement('input');
                inputType.type = 'text';
                inputType.name = 'optionType';
                inputType.classList.add('product-option__input');
                inputType.placeholder = 'Option Type';
                inputType.required = true;

                const inputValue = document.createElement('input');
                inputValue.type = 'text';
                inputValue.name = 'optionValue';
                inputValue.classList.add('product-option__input');
                inputValue.placeholder = 'Option Value';
                inputValue.required = true;

                const inputPrice = document.createElement('input');
                inputPrice.type = 'number';
                inputPrice.name = 'extraPrice';
                inputPrice.classList.add('product-option__input');
                inputPrice.placeholder = 'Extra Price';

                const removeBtn = document.createElement('button');
                removeBtn.type = 'button';
                removeBtn.textContent = '−';
                removeBtn.classList.add('product-option__btn', 'product-option__btn--remove');
                removeBtn.addEventListener('click', () => row.remove());

                row.append(inputType, inputValue, inputPrice, removeBtn);
                return row;
            }

            container.appendChild(createOptionRow());
            addBtn.addEventListener('click', () => container.appendChild(createOptionRow()));
        </script>

        <script>
            document.addEventListener('DOMContentLoaded', () => {
                const alerts = document.querySelectorAll('.fade-message');
                alerts.forEach(alert => {
                    setTimeout(() => {
                        alert.style.transition = 'opacity 0.8s ease';
                        alert.style.opacity = '0';
                        setTimeout(() => alert.remove(), 800);
                    }, 3000);
                });
            });
        </script>


        <script src="${pageContext.request.contextPath}/assets/js/handlePictureProduct.js"></script>
    </body>
</html>
