package com.clinic.team16.JWT;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JwtToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7319739615648315581L;


	@Value("${jwt.secret}")
	private String secretString;
	

	public String getEmailFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secretString).parseClaimsJws(token).getBody();
	}
	
	private boolean isTokenExpired(String token) {
		final Date expirationDate = getExpirationDateFromToken(token);
		return expirationDate.before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claimsMap = new HashMap<String, Object>();
		User a = (User)userDetails;
		return doGenerateToken(claimsMap,a.getEmail());
	}
	
	private String doGenerateToken(Map<String, Object> claims,String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 5*60*60*1000)).signWith(SignatureAlgorithm.HS512, secretString).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String email = getEmailFromToken(token);
		User a = (User)userDetails;
		return(email.equals(a.getEmail()) && !isTokenExpired(token));
	}
}
