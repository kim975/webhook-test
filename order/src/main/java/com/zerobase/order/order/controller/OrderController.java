package com.zerobase.order.order.controller;

import com.zerobase.order.common.response.CommonResponse;
import com.zerobase.order.order.application.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

    @PostMapping("/order")
    @PreAuthorize("hasRole('USER')")
    public CommonResponse<OrderDto.RegisterOrderResponse> registerOrder(
        Authentication authentication,
        @RequestBody OrderDto.RegisterOrderRequest request
    ) {
        return CommonResponse.success(
            OrderDto.RegisterOrderResponse.from(
                orderFacade.registerOrder(request.toDto(authentication.getName()))
            )
        );
    }

}
