package com.zerobase.user.point.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "point_history")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private PointEntity point;

    private Long pointPaymentOrderId;

    private Long beforePoint;
    private Long afterPoint;
    private Long changePoint;

    @Enumerated(EnumType.STRING)
    private PointType pointType;

}
