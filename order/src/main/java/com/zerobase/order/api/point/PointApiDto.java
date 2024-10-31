package com.zerobase.order.api.point;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public class PointApiDto {

    @Getter
    @Setter
    @ToString
    public static class PointResponse {

        private String result;
        private PointDetail data;
        private String message;
        private String errorCode;
    }

    @Getter
    @Setter
    @ToString
    public static class PointDetail {

        private String userId;
        private String point;
    }
}
