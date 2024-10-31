package com.zerobase.user.user.domain.repository;

import com.zerobase.user.user.domain.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {

    private final UserRepository userRepository;

    @Override
    public UserEntity store(UserEntity users) {
        return userRepository.save(users);
    }
}
