package com.tjnu.losca.core;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200),
    FAILED(500),
    UNAUTHORIZATION(401),
    FORBID(403);
    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }
}
