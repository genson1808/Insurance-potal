package com.gen.com.Insurance_portal.utils;

import com.gen.com.Insurance_portal.entites.Authorities;
import com.gen.com.Insurance_portal.entites.Role;
import com.gen.com.Insurance_portal.entites.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
public class JwtUtil {
    private String SECRET_KEY = "19a1a222c2189cb6bc810a430bc5f94b";
    private final long JWT_EXPIRATION = 604800000L;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetail, Map<String, Object> claims) {
        return createToken(claims, userDetail.getUsername());
    }

    public Map<String, Object> generateClaims(User user) {
        Role role = user.getRole();

        Map<String, Object> claims = new HashMap<>();
        claims.put("surname", user.getSurname());
        claims.put("givenName", user.getGivenName());

        if (role != null) {
            claims.put("role", role.getName());
        }

        return claims;
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
