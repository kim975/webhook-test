package com.zerobase.report.security;

import java.util.List;

public interface UserSecurity {

    String getUserUuid();

    List<UserRole> getUserRoles();

}
