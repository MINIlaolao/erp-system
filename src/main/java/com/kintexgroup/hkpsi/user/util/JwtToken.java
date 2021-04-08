package com.kintexgroup.hkpsi.user.util;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.base.Objects;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * 生成JWT Token工具类
 *
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */
public class JwtToken extends AbstractAuthenticationToken {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        JwtToken jwtToken = (JwtToken) o;
        return Objects.equal(principal, jwtToken.principal) &&
            Objects.equal(credentials, jwtToken.credentials) &&
            Objects.equal(token, jwtToken.token);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getPrincipal(), getCredentials(), getToken());
    }

    private UserDetails principal;
    private String credentials;
    private final DecodedJWT token;

    public JwtToken(DecodedJWT token) {
        super(Collections.emptyList());
        this.token = token;
    }

    public JwtToken(UserDetails principal, DecodedJWT token,
                    Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.token = token;
    }

    @Override
    public void setDetails(Object details) {
        super.setDetails(details);
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public DecodedJWT getToken() {
        return token;
    }

}
