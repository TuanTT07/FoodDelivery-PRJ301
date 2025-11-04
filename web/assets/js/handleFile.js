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

