package com.sopnobazz.demo.comon.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;

/**
 * @version 1.0.0
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @Since May 28, 2021
 */
@Component
public class UserTokenRequestUtils {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(UserTokenRequestUtils.class);

    private static String JWT_SECRET = "ibcs-bof-erp";
    @SuppressWarnings("unused")
    private static int JWT_EXPIRATION_MS = 86400000;


    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public String getUserIdFromJwtToken(String token) {
        return String.valueOf(Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().get("id"));
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String token = null;
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            token = headerAuth.substring(7);
        }
        return token;
    }

    public Integer getUserIdFromRequest(HttpServletRequest request) {
        return Integer.valueOf(getUserIdFromJwtToken(getTokenFromRequest(request)));
    }
}
