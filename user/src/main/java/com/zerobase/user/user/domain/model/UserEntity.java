package com.zerobase.user.user.domain.model;

import com.zerobase.user.common.model.BaseEntity;
import com.zerobase.user.converter.AES256Converter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "user")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String loginId;

    private String password;

    private String name;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    @Convert(converter = AES256Converter.class)
    private String email;

    @Column(unique = true, nullable = false)
    @Convert(converter = AES256Converter.class)
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String userUuid;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<UserRole> userRoles = new ArrayList<>();

    private LocalDateTime leaveDateTime;
}
