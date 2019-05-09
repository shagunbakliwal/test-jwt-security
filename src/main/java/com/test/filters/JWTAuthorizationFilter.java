package com.test.filters;

import static com.test.constants.SecurityConstants.HEADER_STRING;
import static com.test.constants.SecurityConstants.SECRET;
import static com.test.constants.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.test.domain.ApplicationUser;
import com.test.repository.ApplicationUserRepository;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private ApplicationUserRepository applicationUserRepository;

	public JWTAuthorizationFilter(AuthenticationManager authManager,
			ApplicationUserRepository applicationUserRepository) {
		super(authManager);
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);
		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		String username = principal.toString();
		System.out.println(username);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, "")).getSubject();
			if (user != null) {
				ApplicationUser applicationUser = applicationUserRepository.findByUsername(user);
				return new UsernamePasswordAuthenticationToken(applicationUser.getUsername(), null,
						applicationUser.getAuthorities());
			}
			return null;
		}
		return null;
	}
}