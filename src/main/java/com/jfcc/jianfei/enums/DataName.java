package com.jfcc.jianfei.enums;

public enum DataName {
    USER("user"),

    CONTEXT("context");

    private final String name;

    DataName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
