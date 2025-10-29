/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model.enums;

/**
 *
 * @author vanli
 */
public enum OrderStatus {
    PENDING("Đang chờ xử lý"),
    DELIVERING("Đang giao hàng"),
    COMPLETED("Đã hoàn tất"),
    CANCELLED("Đã hủy");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus fromString(String value) {
        if (value == null) return null;
        switch (value.trim().toUpperCase()) {
            case "PENDING": return PENDING;
            case "DELIVERING": return DELIVERING;
            case "COMPLETED": return COMPLETED;
            case "CANCELLED": return CANCELLED;
            default: throw new IllegalArgumentException("Unknown OrderStatus: " + value);
        }
    }

    public String toDBValue() {
        return this.name();
    }
}
