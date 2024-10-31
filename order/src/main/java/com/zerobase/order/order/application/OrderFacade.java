package com.zerobase.order.order.application;

import com.zerobase.order.api.point.PointApi;
import com.zerobase.order.api.user.UserApi;
import com.zerobase.order.api.user.UserApiDto.UserResponse;
import com.zerobase.order.api.user.UserSearchType;
import com.zerobase.order.order.service.OrderInfo;
import com.zerobase.order.order.service.OrderInfo.Main;
import com.zerobase.order.order.service.OrderService;
import com.zerobase.order.order.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final ProductService productService;
    private final UserApi userApi;
    private final PointApi pointApi;

    @Transactional
    public OrderInfo.Main registerOrder(OrderFacadeDto.RegisterOrderRequest dto) {
        // 상품 수량 변경
        productService.reduceQuantity(dto.getProductId(), dto.getOrderQuantity());

        // 주문
        UserResponse user = userApi.getUser(dto.getUserUuid(), UserSearchType.USER_UUID);
        Main order = orderService.registerOrder(dto.toCommand(user.getData().getId()));

        //포인트 차감
        pointApi.changePoint(user.getData().getId(), order.getOrderPrice());

        return order;
    }

}
