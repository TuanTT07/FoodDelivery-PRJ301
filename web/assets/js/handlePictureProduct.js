document.getElementById("pictureInput").addEventListener("change", async function (e) {
    const files = Array.from(e.target.files);
    const previewContainer = document.getElementById("previewContainer");
    const base64Array = [];

    previewContainer.innerHTML = "";

    // Đọc toàn bộ ảnh, giữ đúng thứ tự
    const base64List = await Promise.all(files.map(file => {
        return new Promise((resolve) => {
            const reader = new FileReader();
            reader.onload = evt => resolve(evt.target.result);
            reader.readAsDataURL(file);
        });
    }));

    base64List.forEach((base64, index) => {
        base64Array.push(base64);

        const wrapper = document.createElement("div");
        wrapper.classList.add("text-center", "m-2");
        wrapper.style.position = "relative";

        const img = document.createElement("img");
        img.src = base64;
        img.classList.add("img-thumbnail");
        img.style.width = "100px";
        img.style.height = "100px";
        wrapper.appendChild(img);

        const radio = document.createElement("input");
        radio.type = "radio";
        radio.name = "mainIndex";
        radio.value = index;
        radio.classList.add("form-check-input", "mt-2");
        wrapper.appendChild(document.createElement("br"));
        wrapper.appendChild(radio);
        wrapper.appendChild(document.createTextNode(" Main"));

        previewContainer.appendChild(wrapper);
    });

    // Gán danh sách base64
    document.getElementById("base64List").value = JSON.stringify(base64Array);
});
