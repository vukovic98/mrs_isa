package com.clinic.team16.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.clinic.team16.beans.User;
import com.clinic.team16.service.UserService;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain;
@Component
public class JwtFilter extends OncePerRequestFilter {

	
	@Autowired
	private UserService userDetailService;
	
	@Autowired
	private JwtToken jwtUtility;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		
		String email = null;
		String jwt = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			
			jwt = authHeader.substring(7);
			email = jwtUtility.getEmailFromToken(jwt);
			System.out.println(email);
		}
		if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails user = this.userDetailService.loadUserByUsername(email);
			
			if(jwtUtility.validateToken(jwt, user)) {

				String a = user.getPassword();
				User uTest = new User(email, a);
				uTest.setPassword(a);
				System.out.println(uTest.getUsername());
				System.out.println(uTest.getPassword());
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(uTest, null,uTest.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
