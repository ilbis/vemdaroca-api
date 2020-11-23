package com.vemdaroca.vemdarocaapi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.vemdaroca.vemdarocaapi.repository.ClienteRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	@Autowired
	private ClienteRepository clienteRepository;

	// EXPIRATION_TIME = 10 dias
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "MySecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
    private static final String AUTHORITIES_KEY = "auth";
    
	static void addAuthentication(HttpServletResponse response, Authentication auth) throws IOException {
//		final String authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
//				.collect(Collectors.joining(","));
//
//		String JWT = Jwts.builder().setSubject(auth.getName()).claim("AUTHORITIES_KEY", authorities)
//				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
//
//		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        final String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        String token = Jwts.builder()
                .setSubject(auth.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME*1000))
                .compact();
        
		response.addHeader(HEADER_STRING, token);
//		response.getWriter().write("{" + HEADER_STRING + ":" + TOKEN_PREFIX + " " + token + "}");
	}

	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
//		
//        final JwtParser jwtParser = Jwts.parser().setSigningKey("SIGNING_KEY");
//
//        final Jws claimsJws = jwtParser.parseClaimsJws(token);
//
//        final Claims claims = (Claims) claimsJws.getBody();
//
//		if (token != null) {
//			// faz parse do token
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();
//
//			final Collection authorities = Arrays.stream(claims.get("AUTHORITIES_KEY").toString().split(","))
//					.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//
//			if (user != null) {
//				System.out.println(user);
//				Collection<GrantedAuthority> role = new ArrayList<>();
//				role.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//
//				return new UsernamePasswordAuthenticationToken(user, null, authorities);
//			}
//		}
//		return null;
		
        final JwtParser jwtParser = Jwts.parser().setSigningKey(SECRET);

        final Jws claimsJws = jwtParser.parseClaimsJws(token);

        final Claims claims = (Claims) claimsJws.getBody();

        final Collection authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(user, "", authorities);
	}

}
