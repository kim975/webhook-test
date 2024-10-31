package com.zerobase.report.security;

import com.zerobase.report.exception.BasicErrorCode;
import com.zerobase.report.exception.JwtCustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final SecretKey secretKey;

    private static final String KEY_ROLES = "roles";

    public String getUserUuid(String token) {
        return parseClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }

        return !parseClaims(token).getExpiration().before(new Date());

    }

    public Authentication getAuthentication(String jwt) {

        Claims claims = parseClaims(jwt);

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(claims.getSubject());
        userInfo.setUserRoles(((List<String>) claims.get(KEY_ROLES)).stream()
            .map(UserRole::valueOf)
            .collect(Collectors.toList())
        );

        return new UsernamePasswordAuthenticationToken(userInfo, "", userInfo.getAuthorities());
    }

    private Claims parseClaims(String token) {

        try {
            return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        } catch (ExpiredJwtException e) {
            throw new JwtCustomException(BasicErrorCode.EXPIRED_TOKEN);
        }
    }

}
