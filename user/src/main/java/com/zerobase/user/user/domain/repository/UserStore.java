package com.zerobase.user.user.domain.repository;

import com.zerobase.user.user.domain.model.UserEntity;

public interface UserStore {

    UserEntity store(UserEntity users);
}
