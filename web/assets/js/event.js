//tránh lặp code khi dùng documnet.querySelector();
const $ = document.querySelector.bind(document);

Popzy.elements = [];
function Popzy(options = {}) {
    this.opt = Object.assign(
            {
                destroyOnClose: true,
                footer: false,
                cssClass: [],
                closeMethods: ["button", "overlay", "escape"],
            },
            options
            );

    if (!options.content && !options.templateId) {
        console.error("Bạn phải cung cấp một 'Content' hoặc 'templateId'.");
        return;
    }
    if (options.content && options.templateId) {
        options.templateId = null;
        console.warn("Nếu cung cấp cả 'Content' và 'templateId' thì 'templateId' sẽ bị bỏ qua.");
    }

    if (this.opt.templateId) {
        this.template = document.querySelector(`#${this.opt.templateId}`);
        if (!this.template) {
            console.error(`#${this.opt.templateId} does not exist!`);
            return;
        }
    }

    this.content = this.opt.content;
    this._allowBackdropClose = this.opt.closeMethods.includes("overlay");
    this._allowEscapeClose = this.opt.closeMethods.includes("escape");
    this._handleEscapeKey = this._handleEscapeKey.bind(this);
}

Popzy.prototype._build = function () {
    const contentNode = this.content
            ? document.createElement("div")
            : this.template.content.cloneNode(true);

    if (this.content)
        contentNode.innerHTML = this.content;

    this._backdrop = document.createElement("div");
    this._backdrop.className = "popzy-backdrop";

    const container = document.createElement("div");
    container.className = "popzy-container";
    this.opt.cssClass.forEach(cls => container.classList.add(cls));

    this._popzyContent = document.createElement("div");
    this._popzyContent.className = "popzy-content";
    this._popzyContent.append(contentNode);
    container.append(this._popzyContent);

    this._backdrop.append(container);
    document.body.append(this._backdrop);
};

Popzy.prototype.open = function () {
    Popzy.elements.push(this);
    if (!this._backdrop)
        this._build();

    setTimeout(() => this._backdrop.classList.add("show"), 0);

    if (this._allowBackdropClose) {
        this._backdrop.onclick = (e) => {
            if (e.target === this._backdrop)
                this.close();
        };
    }

    if (this._allowEscapeClose) {
        document.addEventListener("keydown", this._handleEscapeKey);
    }

    if (typeof this.opt.onOpen === "function")
        this.opt.onOpen();
};

Popzy.prototype._handleEscapeKey = function (e) {
    const lastPopzy = Popzy.elements[Popzy.elements.length - 1];
    if (e.key === "Escape" && this === lastPopzy)
        this.close();
};

Popzy.prototype.close = function () {
    Popzy.elements.pop();
    this._backdrop.classList.remove("show");
    if (this._allowEscapeClose)
        document.removeEventListener("keydown", this._handleEscapeKey);

    setTimeout(() => {
        this._backdrop.remove();
        if (typeof this.opt.onClose === "function")
            this.opt.onClose();
    }, 300);
};

//  Tạo popup event
const popzy2 = new Popzy({
    templateId: "popzy-2",
    closeMethods: ["overlay", "escape"],
    cssClass: ["event-modal"],
    destroyOnClose: true,
    onOpen: () => console.log("Popzy 2 opened"),
    onClose: () => console.log("Popzy 2 closed"),
});

// Tự động mở khi vào trang
window.addEventListener("DOMContentLoaded", () => {
    popzy2.open();
});