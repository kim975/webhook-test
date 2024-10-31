package com.zerobase.user.point.application;

import static com.zerobase.user.point.application.PointFacadeDto.*;
import static com.zerobase.user.point.service.PaymentInfo.InitPointCharge;

import com.zerobase.user.point.service.PaymentCommand;
import com.zerobase.user.point.service.PaymentInfo.PayPointCharge;
import com.zerobase.user.point.service.PaymentService;
import com.zerobase.user.point.service.PointCommand;
import com.zerobase.user.point.service.PointInfo.AddPoint;
import com.zerobase.user.point.service.PointService;
import com.zerobase.user.user.service.UserInfo.UserDetail;
import com.zerobase.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointFacade {

    private final PointService pointService;
    private final PaymentService paymentService;
    private final UserService userService;

    @Transactional
    public InitPointChargeResponse initPointChargeOrder(InitPointChargeRequest dto) {

        UserDetail userInfo = userService.getUserDetailByUuid(dto.getUserUuid());

        InitPointCharge paymentOrder = paymentService.callPaymentOrder(dto.toCommand(userInfo.getId()));

        return InitPointChargeResponse.from(paymentOrder);
    }

    @Transactional
    public PointFacadeDto.AddPointResponse addPoint(AddPointRequest dto) {

        UserDetail userDetail = userService.getUserDetailByUuid(dto.getUserUuid());

        PayPointCharge payPointCharge = paymentService.callPayPayment(
            PaymentCommand.PayPointCharge.builder()
                .pointPaymentOrderId(dto.getPointPaymentOrderId())
                .paymentMethod(dto.getPaymentMethod())
                .build()
        );

        AddPoint addPoint = pointService.addPoint(
            PointCommand.AddPoint.builder()
                .userId(userDetail.getId())
                .changePoint(payPointCharge.getPaymentAmount())
                .pointPaymentOrderId(dto.getPointPaymentOrderId())
                .build()
        );

        return PointFacadeDto.AddPointResponse.from(addPoint.getPoint());
    }
}
