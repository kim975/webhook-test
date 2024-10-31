package com.zerobase.order.security;

import java.util.List;

public interface UserSecurity {

    String getUserUuid();

    List<UserRole> getUserRoles();

}
