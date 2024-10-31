package com.zerobase.user.user.domain.repository;

import com.zerobase.user.exception.BasicErrorCode;
import com.zerobase.user.user.domain.model.UserEntity;
import com.zerobase.user.exception.BaseException;
import com.zerobase.user.exception.UserErrorCode;
import com.zerobase.user.user.domain.model.UserSearchType;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {

    private final UserRepository userRepository;


    @Override
    public boolean isExistsLoginId(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    @Override
    public boolean isExistsNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean isExistsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isExistsPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public UserEntity getUserByLoginIdAndPassword(String loginId, String password) {
        return userRepository.findByLoginIdAndPassword(loginId, password)
            .orElseThrow(() -> new BaseException(UserErrorCode.WRONG_ID_OR_PASSWORD));
    }

    @Override
    public UserEntity getUserByUserUuid(String userUuid) {
        return userRepository.findByUserUuid(userUuid)
            .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));
    }

    @Override
    public UserEntity getUserByQuery(String query, UserSearchType searchType) {
        if (UserSearchType.USER_UUID == searchType) {
            return userRepository.findByUserUuid(query)
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

        } else if (UserSearchType.USER_ID == searchType) {
            return userRepository.findById(Long.parseLong(query))
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

        } else if (UserSearchType.NICKNAME == searchType) {
            return userRepository.findByNickname(query)
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

        } else if (UserSearchType.LOGIN_ID == searchType) {
            return userRepository.findByLoginId(query)
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

        } else {
            throw new BaseException(BasicErrorCode.COMMON_SYSTEM_ERROR);
        }
    }

    @Override
    public List<UserEntity> getAllUserByQuery(List<String> query, UserSearchType searchType) {
        if (UserSearchType.USER_UUID == searchType) {
            return userRepository.findAllByUserUuidIn(query);

        } else if (UserSearchType.USER_ID == searchType) {
            return userRepository.findAllByIdIn(
                query.stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList())
            );

        } else if (UserSearchType.NICKNAME == searchType) {
            return userRepository.findAllByNicknameIn(query);

        } else if (UserSearchType.LOGIN_ID == searchType) {
            return userRepository.findAllByLoginIdIn(query);

        } else {
            throw new BaseException(BasicErrorCode.COMMON_SYSTEM_ERROR);
        }
    }
}
