package com.example.app.client;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	
	private final String SECRET_KEY="e8a0a5e9b17afe3ff5b9a18dcc324822f503fca4eda65a9e7478cfb0ffe51330";
	
	private final int EXPIRY_DAYS=10;
	public String getMailFromToken(String token)
	{
		String mail=extractEmail(token);
		return mail;
	}
	public String extractEmail(String token)
	{
		return extractClaim(token,Claims::getSubject);
	}
	
	public boolean isValid(String token,RegisteredClient client)
	{
		String mail=extractEmail(token);
		return mail.equals(client.getMail()) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token)
	{
		return extractExpiration(token).before(new Date());
	}
	
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token,Function<Claims, T> resolver)
	{
		Claims claims=extractAllClaims(token);
		return resolver.apply(claims);
	}
	
	
	private Claims extractAllClaims(String token)
	{
		return Jwts.
				parser().
				verifyWith(getSigningKey()).
				build().
				parseSignedClaims(token).
				getPayload();
	}
	
	public String generateToken(RegisteredClient client)
	{
		String token=Jwts.
				builder().
				subject(client.getMail()).
				issuedAt(new Date(System.currentTimeMillis())).
				expiration(new Date(System.currentTimeMillis()+24*EXPIRY_DAYS*60*60*1000)).
				signWith(getSigningKey()).
				compact();
		
		return token;
	}
	
	private SecretKey getSigningKey()
	{
		byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
