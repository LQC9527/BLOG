package com.jfcc.jianfei.enums;

public enum ArticleStatus {
    INIT("00"),

    DRAFT("01"),

    PULISHED("01");

    private final String status;

    ArticleStatus(String name) {
        this.status = name;
    }

    public String getStatus() {
        return status;
    }
}
