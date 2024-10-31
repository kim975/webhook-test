package com.zerobase.user.user.domain.repository;

import com.zerobase.user.user.domain.model.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByLoginId(String LoginId);

    Optional<UserEntity> findByLoginIdAndPassword(String LoginId, String password);

    Optional<UserEntity> findByUserUuid(String userUuid);

    Optional<UserEntity> findByNickname(String nickname);

    Optional<UserEntity> findByLoginId(String query);

    List<UserEntity> findAllByUserUuidIn(List<String> userUuids);

    List<UserEntity> findAllByNicknameIn(List<String> nicknames);

    List<UserEntity> findAllByIdIn(List<Long> ids);

    List<UserEntity> findAllByLoginIdIn(List<String> loginIds);
}
