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
// Popzy 2: chỉ khởi tạo nếu tồn tại
let popzy2 = null;
if (document.querySelector("#popzy-2")) {
    popzy2 = new Popzy({
        templateId: "popzy-2",
        closeMethods: ["overlay", "escape"],
        cssClass: ["event-modal"],
        destroyOnClose: true,
        onOpen: () => console.log("Popzy 2 opened"),
        onClose: () => console.log("Popzy 2 closed")
    });
}

// Popzy 3: chỉ khởi tạo nếu tồn tại
let popzy3 = null;
if (document.querySelector("#popzy-3")) {
    popzy3 = new Popzy({
        templateId: "popzy-3",
        closeMethods: ["button", "escape", "overlay"],
        cssClass: ["event-modal"],
        destroyOnClose: false,
        onOpen: () => console.log("Popzy 3 opened"),
        onClose: () => console.log("Popzy 3 closed")
    });
}
let popzy4 = null;
if (document.querySelector("#popzy-4")) {
    popzy4 = new Popzy({
        templateId: "popzy-4",
        closeMethods: ["button", "escape", "overlay"],
        cssClass: ["event-modal"],
        destroyOnClose: false,
        onOpen: () => console.log("Popzy 4 opened"),
        onClose: () => console.log("Popzy 4 closed")
    });
}

function openPopzy3() {
    let pop = new Popzy({
        templateId: "popzy-3",
        destroyOnClose: true,
        closeMethods: ["button", "escape", "overlay"],
        cssClass: ["event-modal"]
    });
    pop.open();
}
function openPopzy4() {
    let pop = new Popzy({
        templateId: "popzy-4",
        destroyOnClose: true,
        closeMethods: ["button", "escape", "overlay"],
        cssClass: ["event-modal"]
    });
    pop.open();
}
window.addEventListener("DOMContentLoaded", function () {
    if (popzy2 && !sessionStorage.getItem("popzy2_shown")) {
        popzy2.open();
        sessionStorage.setItem("popzy2_shown", "true");
    }

    if (popzy3) {
        const addBtn = document.querySelector(".layout__card--add");
        if (addBtn) {
            addBtn.addEventListener("click", function (e) {
                e.preventDefault();
                openPopzy3();
            });
        }

        // Nút Edit
        const editBtns = document.querySelectorAll(".layout__actions--edit");
        editBtns.forEach(btn => {
            btn.addEventListener("click", function (e) {
                e.preventDefault();

                const card = e.target.closest(".layout__card"); // lấy đúng card đang bấm
                const id = card.dataset.id;
                const name = card.dataset.name;

                let pop = new Popzy({
                    templateId: "popzy-4",
                    destroyOnClose: true,
                    closeMethods: ["button", "escape", "overlay"],
                    cssClass: ["event-modal"]
                });

                pop.open();

                // sau khi open mới query được form
                const form = document.querySelector(".popzy-content form");
                form.querySelector(".cate-id").value = id;
                form.querySelector(".cate-name").value = name;
            });
        });
    }
});
