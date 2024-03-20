package com.hexaware.ccozyhaven.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);

	public String createToken(Map<String, Object> claims, String username, String role, Long customerId) {

		return Jwts.builder().setClaims(claims).setSubject(username)
				.claim("role", role) // Add user role claim
	            .claim("customerId", customerId) // Add customer ID claim
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

	}

	private Key getSignKey() {
		LOGGER.info("in getSignKey ");
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(String username, String role, Long customerId) {

		Map<String, Object> claims = new HashMap<>();

		return createToken(claims, username, role, customerId);//changed

	}

	private Claims extractAllClaims(String token) {
		LOGGER.info("in extractallclaims ");
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		LOGGER.info("in extract claim ");
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String extractUsername(String token) {
		LOGGER.info("in extract username ");
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		LOGGER.info("in extract expiration");
		return extractClaim(token, Claims::getExpiration);
	}

	private Boolean isTokenExpired(String token) {
		LOGGER.info("in token expired ");
		return extractExpiration(token).before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		LOGGER.info("in validate token ");
		final String username = extractUsername(token);
		LOGGER.info("inside validate token username is" + username);
		return (username.equals(userDetails.getUsername()) && (!isTokenExpired(token)));
	}

}
