const categoryLinks = document.querySelectorAll('#categoryList a');
const storeList = document.getElementById('storeList');

categoryLinks.forEach(link => {
    link.addEventListener('click', e => {
        e.preventDefault();

        // Lấy categoryId
        const categoryId = link.dataset.id;

        // Highlight category đang chọn (optional)
        categoryLinks.forEach(l => l.classList.remove('active'));
        link.classList.add('active');

        // Gọi servlet để lấy sản phẩm theo category
        fetch(`${contextPath}/CategoryController?categoryId=${categoryId}`)

                .then(res => res.json())
                .then(products => {
                    storeList.innerHTML = "";
                    if (products.length === 0) {
                        storeList.innerHTML = "<p>Không có sản phẩm nào.</p>";
                        return;
                    }
                    products.forEach(p => {
                        storeList.innerHTML += ` 
                            <div class="store-card">
                                <img src="${contextPath}/assets/img/${p.image}" alt="${p.name}" class="store-img">
                                <h3 class="store-name">${p.name}</h3>
                                <p class="delivery-fee">$0 delivery fee</p>

                                <div class="store-info">
                                    <p><strong>Time:</strong> ${p.time}</p>
                                    <p><strong>Food Type:</strong> ${p.categoryName}</p>
                                </div>

                                <div class="store-rating">
                                    <span class="star">⭐</span> ${p.rating} 
                                    <span class="review-count">(${p.reviews})</span>
                                </div>
                            </div>`;
                    });
                })
                .catch(err => console.error("Lỗi khi load sản phẩm:", err));
    });
});
