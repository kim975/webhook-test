package com.zerobase.report.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
public class UserInfo implements UserDetails {

    String username;
    List<UserRole> userRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles.stream()
            .map(role -> new SimpleGrantedAuthority(role.toString()))
            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return "";
    }

}
