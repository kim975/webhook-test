package com.zerobase.user.util;

import java.util.UUID;

public class TokenGenerator {

    public static String getToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

}
