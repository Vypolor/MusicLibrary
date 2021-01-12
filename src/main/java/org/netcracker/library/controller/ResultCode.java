package org.netcracker.library.controller;

public enum ResultCode {
    SUCCESS(0, "Success"),
    ITEM_EXISTS(100, "Item to add is already exists"),
    ITEM_NOT_EXISTS(200, "Place to add is not exists"),
    INVALID_PATH(404, "Invalid path"),
    INVALID_KEY(500, "Invalid key value"),
    UNEXPECTED_SAVE_ERROR(600, "Unexpected error occurred while saving");

    private final int code;
    private final String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
