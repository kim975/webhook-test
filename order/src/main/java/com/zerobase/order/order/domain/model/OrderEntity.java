package com.zerobase.order.order.domain.model;

import com.zerobase.order.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "orders")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProductEntity product;
    private Long userId;

    @Column(unique = true)
    private String orderToken;
    private String deliveryAddress;
    private String receiverName;
    private String receiverPhoneNumber;
    private int orderPrice;
    private int orderQuantity;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    public OrderTransactionEntity toOrderTransactionEntity() {
        return OrderTransactionEntity.builder()
            .orderId(id)
            .productId(product.getId())
            .userId(userId)
            .orderToken(orderToken)
            .orderPrice(orderPrice)
            .orderQuantity(orderQuantity)
            .orderType(orderType)
            .build();
    }

}
