package com.cnr.bankingapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cnr.bankingapp.entity.Token;
import com.cnr.bankingapp.entity.User;
import com.cnr.bankingapp.repository.TokenRepository;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {
	
	@Value("${bankingapp.app.secret}")
    private String secretKey;

    @Value("${bankingapp.token.expires.in}")
    private long accessTokenExpire;

    @Value("${bankingapp.refresh.token.expires.in}")
    private long refreshTokenExpire;


    private final TokenRepository tokenRepository;

    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public boolean isValid(String token, UserDetails user) {
    	try {
    		String username = extractUsername(token);

            boolean validToken = tokenRepository
                    .findByAccessToken(token)
                    .map(t -> !t.isLoggedOut())
                    .orElse(false);
            

            return (username.equals(user.getUsername())) && !isTokenExpired(token) && validToken;
    	}catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
        
    }

    public boolean isValidRefreshToken(String token, User user) {
    	
    	try {
    		String username = extractUsername(token);

            boolean validRefreshToken = tokenRepository
                    .findByRefreshToken(token)
                    .map(t -> !t.isLoggedOut())
                    .orElse(false);

            return (username.equals(user.getUsername())) && !isTokenExpired(token) && validRefreshToken;
    	}catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
        
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
    	return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    	
        
    }


    public String generateAccessToken(User user) {
        return generateToken(user, accessTokenExpire);
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, refreshTokenExpire );
    }

    private String generateToken(User user, long expireTime) {
    	Map<String, Object> claims = new HashMap<>();
        String token = Jwts
                .builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime ))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
