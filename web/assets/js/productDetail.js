let quantity = 1;

function increase() {
  quantity++;
  document.getElementById("quantity").textContent = quantity;
  document.getElementById("quantityInput").value = quantity;
}

function decrease() {
  if (quantity > 1) {
    quantity--;
    document.getElementById("quantity").textContent = quantity;
    document.getElementById("quantityInput").value = quantity;
  }
}
