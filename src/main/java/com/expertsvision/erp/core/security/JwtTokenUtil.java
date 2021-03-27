package com.expertsvision.erp.core.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.expertsvision.erp.core.user.entity.UsersView;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
	
	private final String jwtSecret = "C*F-RaNdRgUkXp2s5v8y/A?D(GpKbPeShVmYq3t6w9z$C&E)H@McQfTjknZr4u7x!A%D*G-JaNdRgukXp2s5v8y/B?E(H+MbPeShu855-mYq3t6w9z$C&F)J@NcRfTjWnZr4";
	private final String jwtIssuer = "com.expertsvision.erp";
	
    public Integer validate(String token) {
    	return Integer.parseInt(Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(token)
        					.getBody().getSubject());
    }
    
    public String generateAccessToken(UsersView usersView) {
		return Jwts.builder()
                .setSubject(String.format("%s", usersView.getUserId()))
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000 * 14))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

}
