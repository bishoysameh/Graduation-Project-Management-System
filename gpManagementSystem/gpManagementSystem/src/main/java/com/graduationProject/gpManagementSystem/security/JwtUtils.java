package com.graduationProject.gpManagementSystem.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.model.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {

    private final static String SECRET_KEY = "680985b33fccdb28ed249302d4cf185a45c2c249fe0f54db51c05e6831bf3c60";

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, User userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("id", userDetails.getId())
                .claim("role", userDetails.getRole()) // Assuming UserDetails is an instance of your User class
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}



// import io.jsonwebtoken.*;
// import io.jsonwebtoken.io.Decoders;
// import io.jsonwebtoken.security.Keys;
// import io.jsonwebtoken.security.SignatureException;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Component;

// import java.security.Key;
// import java.util.Date;
// @Slf4j
// @Component
// public class JwtUtils {

//     @Value("${app.jwt.secret}")
//     private String jwtSecret;

//     @Value("${app.jwt.expiration.ms}")
//     private int jwtExpirationMs;

//     public String generateToken(Authentication authentication){
//         if(authentication == null){
//             throw new IllegalArgumentException("authentication cannot be null");
//         }
//         if(!(authentication.getPrincipal() instanceof CustomerDetailsImpl)){
//             throw new IllegalArgumentException("authentication principal must be of type CustomerDetailsImpl");
//         }
//         if(!authentication.isAuthenticated()){
//             throw new IllegalArgumentException("authentication must be authenticated");
//         }
//         CustomerDetailsImpl customerDetails = (CustomerDetailsImpl) authentication.getPrincipal();
//         return Jwts.builder()
//                 .setSubject(customerDetails.getUsername())
//                 .claim("id", customerDetails.getId())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//                 .signWith(key(), SignatureAlgorithm.HS256)

//                 .compact();
//     }
//     public String getEmailFromJwtToken(String jwt){
//         return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(jwt).getBody().getSubject();
//     }

//     public Boolean validateJwtToken(String jwt){
//         try{
//             Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(jwt);
//             Claims claims = Jwts.parser()
//                     .setSigningKey(key())
//                     .parseClaimsJws(jwt)
//                     .getBody();

//             if (claims.getExpiration().before(new Date())) {
//                 throw new ExpiredJwtException(Jwts.header(), claims, "The session expired");
//             }
//             return true;
//         }
//         catch (MalformedJwtException e){
//            log.error("Invalid jwt {}", e.getMessage());
//         }
//         catch (ExpiredJwtException e){
//             log.error("expired jwt {}", e.getMessage());
//         }
//         catch (UnsupportedJwtException e){
//             log.error("unsupported jwt {}", e.getMessage());
//         }
//         catch (IllegalArgumentException e){
//             log.error("jwt Claim string is empty {}", e.getMessage());
//         }
//         catch (SignatureException e){
//             log.error("jwt signature does not match locally computed signature {}", e.getMessage());
//         }
//         return false;
//     }
//     private Key key(){return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));}
// }
