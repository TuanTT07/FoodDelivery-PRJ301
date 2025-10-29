/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model.enums;

/**
 *
 * @author vanli
 */
public enum PaymentStatus {
    PENDING("Đang chờ thanh toán"),
    PAID("Đã thanh toán thành công"),
    FAILED("Thanh toán thất bại");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // Hàm tiện ích: chuyển từ chuỗi DB sang enum
    public static PaymentStatus fromString(String value) {
        if (value == null) return null;
        switch (value.trim().toUpperCase()) {
            case "PENDING": return PENDING;
            case "PAID": return PAID;
            case "FAILED": return FAILED;
            default: throw new IllegalArgumentException("Unknown PaymentStatus: " + value);
        }
    }

    // Dùng khi ghi xuống DB
    public String toDBValue() {
        return this.name();
    }
}
