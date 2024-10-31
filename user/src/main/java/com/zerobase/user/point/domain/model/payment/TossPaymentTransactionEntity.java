package com.zerobase.user.point.domain.model.payment;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "toss_payment_transaction")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TossPaymentTransactionEntity extends PaymentTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pointPaymentOrderId;

    private String paymentKey;
    private String orderId;
    private Long amount;

    @Enumerated(EnumType.STRING)
    private PayStatus status;

    @Override
    public String getTransactionUuid() {
        return paymentKey;
    }
}
