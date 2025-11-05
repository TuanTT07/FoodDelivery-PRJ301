<%-- 
    Document   : cart
    Created on : Oct 22, 2025, 2:14:35 PM
    Author     : ACER
--%>

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
        <!-- FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&display=swap" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <main class="cart">
            <div class="cart__container">

                <div class="cart__header">
                    <span><input type="checkbox" /> Sản phẩm</span>
                    <span>Đơn giá</span>
                    <span>Số lượng</span>
                    <span>Số tiền</span>
                    <span>Thao tác</span>
                </div>

                <c:set var="lastStoreId" value="" />

                <c:forEach var="ci" items="${cartItems}">

                    <!-- nếu store khác với store trước đó => tạo header mới -->
                    <c:if test="${ci.productID.store.storeID != lastStoreId}">
                        <div class="cart__shop">
                            <div class="cart__shop-header">
                                <input type="checkbox" class="shop-check" data-store="${ci.productID.store.storeID}" />
                                <span class="cart__shop-name">${ci.productID.store.storeName}</span>
                            </div>
                        </div>

                        <c:set var="lastStoreId" value="${ci.productID.store.storeID}" />
                    </c:if>

                    <!-- sản phẩm thuộc store hiện tại -->
                    <div class="cart__item" data-store="${ci.productID.store.storeID}" data-product-id="${ci.productID.productID}">

                        <input type="checkbox" class="item-check"/>

                        <img src="${ci.productID.pictureURL}" alt="Ảnh món ăn" />

                        <div class="cart__item-info">
                            <p class="cart__item-name">${ci.productID.productName}</p>
                        </div>

                        <span class="cart__item-price">${ci.productID.productPrice} VNĐ</span>

                        <div class="cart__quantity">
                            <button>-</button>
                            <input type="number" value="${ci.quantity}" />
                            <button>+</button>
                        </div>

                        <span class="cart__item-total" data-item-total="${ci.productID.productPrice * ci.quantity}">
                            ${ci.productID.productPrice * ci.quantity} VNĐ
                        </span>

                        <button class="cart__remove">Xóa</button>
                    </div>

                </c:forEach>

                <div class="cart__summary" id="cartSummary">
                    <div class="cart__voucher">
                        <div class="cart__voucher-row">
                            <div class="cart__voucher-left">
                                <span>Voucher cửa hàng</span>
                            </div>
                            <a href="#">Chọn hoặc nhập mã</a>
                        </div>
                    </div>

                    <div class="cart__summary-bottom">
                        <div class="cart__summary-left">
                            <input type="checkbox" /> <span>Chọn tất cả</span>
                            <button>Xóa</button>
                        </div>
                        <div class="cart__summary-right">
                            <p>Tổng cộng: <span class="cart__total">${grandTotal} VNĐ</span></p>
                            <form id="checkoutForm" action="MainController?action=goToCheckout" method="post">
                                <input type="hidden" name="uID" value="${sessionScope.u.userID}">
                                <input type="hidden" name="selectedItems" id="selectedItems">
                                <button type="submit" class="btn-buy">Mua hàng</button>
                            </form>

                        </div>
                    </div>
                </div>
        </main>
        <jsp:include page="/includes/footer.jsp"/>

        <script>
            document.addEventListener("DOMContentLoaded", function () {

                // Helper: lấy tất cả item checkbox hiện tại
                function getItemChecks() {
                    return Array.from(document.querySelectorAll(".item-check"));
                }
                function getShopChecks() {
                    return Array.from(document.querySelectorAll(".shop-check"));
                }
                const totalDisplay = document.querySelector(".cart__total");
                const selectAll = document.querySelector(".cart__summary-left input[type='checkbox']");
                // Tính tổng các item đang được tick
                function updateTotal() {
                    let total = 0;
                    document.querySelectorAll(".item-check:checked").forEach(check => {
                        const item = check.closest(".cart__item");
                        if (!item) return;
                                const itemTotalRaw = item.querySelector(".cart__item-total")?.dataset.itemTotal || "0";
                        const itemTotal = parseFloat(itemTotalRaw) || 0;
                        total += itemTotal;
                    });
                    if (totalDisplay)
                        totalDisplay.textContent = total.toLocaleString("vi-VN") + " VNĐ";
                }

                // Cập nhật trạng thái "Chọn tất cả"
                function updateSelectAllStatus() {
                    if (!selectAll)
                        return;
                    const allItems = document.querySelectorAll(".item-check");
                    const checkedItems = document.querySelectorAll(".item-check:checked");
                    selectAll.checked = (allItems.length > 0 && allItems.length === checkedItems.length);
                }

                // Khi click shop checkbox -> chọn tất cả item trong store
                function bindShopChecks() {
                    getShopChecks().forEach(shopCheck => {
                        // remove previous handlers guard (in case of rebind) — using passive approach: addEventListener once is fine
                        shopCheck.addEventListener("change", function () {
                            const storeId = shopCheck.dataset.store;
                            if (!storeId)
                                return;
                            const itemsInStore = document.querySelectorAll(`.cart__item[data-store='${storeId}'] .item-check`);
                            itemsInStore.forEach(itemCheck => itemCheck.checked = shopCheck.checked);
                            updateTotal();
                            updateSelectAllStatus();
                        });
                    });
                }

                // Khi click item checkbox -> cập nhật trạng thái shop & tổng
                function bindItemChecks() {
                    getItemChecks().forEach(itemCheck => {
                        itemCheck.addEventListener("change", function () {
                            const item = itemCheck.closest(".cart__item");
                            if (!item)
                                return;
                            const storeId = item.dataset.store;
                            if (storeId) {
                                const itemsInStore = document.querySelectorAll(`.cart__item[data-store='${storeId}'] .item-check`);
                                const shopCheck = document.querySelector(`.shop-check[data-store='${storeId}']`);
                                if (shopCheck) {
                                    const allChecked = Array.from(itemsInStore).every(i => i.checked);
                                    shopCheck.checked = allChecked;
                                }
                            }
                            updateTotal();
                            updateSelectAllStatus();
                        });
                    });
                }

                // "Chọn tất cả"
                if (selectAll) {
                    selectAll.addEventListener("change", function () {
                        const allChecked = selectAll.checked;
                        getItemChecks().forEach(c => c.checked = allChecked);
                        getShopChecks().forEach(c => c.checked = allChecked);
                        updateTotal();
                    });
                }

                // Xử lý thay đổi số lượng (input number) và nút +/- (nếu muốn)
                function bindQuantityInputs() {
                    const quantityInputs = document.querySelectorAll(".cart__quantity input[type='number']");
                    quantityInputs.forEach(input => {
                        // đảm bảo min = 1
                        input.min = 1;
                        input.addEventListener("input", function () {
                            const item = input.closest(".cart__item");
                            if (!item) return;
                                    const priceText = item.querySelector(".cart__item-price")?.textContent || "";
                            const price = parseFloat(priceText.replace(/[^\d.-]/g, '')) || 0;
                            const qty = Math.max(1, parseInt(input.value) || 1);
                            input.value = qty;
                            const newTotal = price * qty;
                            const totalSpan = item.querySelector(".cart__item-total");
                            if (totalSpan) {
                                totalSpan.dataset.itemTotal = newTotal;
                                totalSpan.textContent = newTotal.toLocaleString("vi-VN") + " VNĐ";
                            }
                            updateTotal();
                        });
                    });
                    // Gắn sự kiện cho nút + / - nếu muốn (nếu nút có DOM như trong HTML)
                    document.querySelectorAll(".cart__quantity").forEach(qWrap => {
                        const btns = qWrap.querySelectorAll("button");
                        const input = qWrap.querySelector("input[type='number']");
                        if (!input)
                            return;
                        const minus = btns[0], plus = btns[1];
                        if (minus)
                            minus.addEventListener("click", () => {
                                input.stepDown();
                                input.dispatchEvent(new Event('input'));
                            });
                        if (plus)
                            plus.addEventListener("click", () => {
                                input.stepUp();
                                input.dispatchEvent(new Event('input'));
                            });
                    });
                }

                // Gắn toàn bộ sự kiện
                bindShopChecks();
                bindItemChecks();
                bindQuantityInputs();
                // Khởi tạo trạng thái shop (nếu tất cả item trong shop checked thì check shop)
                getShopChecks().forEach(shopCheck => {
                    const storeId = shopCheck.dataset.store;
                    if (!storeId)
                        return;
                    const itemsInStore = document.querySelectorAll(`.cart__item[data-store='${storeId}'] .item-check`);
                    shopCheck.checked = itemsInStore.length > 0 && Array.from(itemsInStore).every(i => i.checked);
                });
                updateTotal();
                updateSelectAllStatus();
                document.querySelector("#checkoutForm").addEventListener("submit", function (e) {
                    // Lấy các sản phẩm được chọn
                    const selected = Array.from(document.querySelectorAll(".item-check:checked")).map(check => {
                        const item = check.closest(".cart__item");
                        return {
                            productId: item.dataset.productId, // bạn cần thêm data-productId trong HTML
                            quantity: item.querySelector(".cart__quantity input").value
                        };
                    });


                    if (selected.length === 0) {
                        e.preventDefault();
                        alert("Vui lòng chọn ít nhất một sản phẩm trước khi mua hàng!");
                        return;
                    }
                    // Chuyển thành JSON để gửi
                    document.querySelector("#selectedItems").value = JSON.stringify(selected);
                    console.log(">>> Selected items to send:", selected);

                });
            });

        </script>


    </body>
</html>
