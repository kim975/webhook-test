package com.zerobase.user.point.controller;

import com.zerobase.user.common.response.CommonResponse;
import com.zerobase.user.point.application.PointFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointController {

    private final PointFacade pointFacade;

    @PostMapping("/point/init")
    public CommonResponse<PointDto.InitPointChargeOrderResponse> pointChargeRequest(
        Authentication authentication,
        @RequestBody PointDto.InitPointChargeOrderRequest request
    ) {

        return CommonResponse.success(
            PointDto.InitPointChargeOrderResponse.from(pointFacade.initPointChargeOrder(request.toFacadeDto(authentication.getName())))
        );
    }

    @PostMapping("/point/pay")
    public CommonResponse<PointDto.PayPointChargeResponse> pointChargeRequest(
        Authentication authentication,
        @RequestBody PointDto.PayPointChargeRequest request
    ) {
        
        return CommonResponse.success(
            PointDto.PayPointChargeResponse.from(pointFacade.addPoint(request.toFacadeDto(authentication.getName())))
        );
    }
}
