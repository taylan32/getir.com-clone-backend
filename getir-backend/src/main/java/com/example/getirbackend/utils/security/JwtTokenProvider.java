package com.example.getirbackend.utils.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.getirbackend.models.UserRole;
import com.example.getirbackend.repositories.RoleRepository;
import com.example.getirbackend.repositories.UserRoleRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${getir.app.secret}")
	private String APP_SECRET;

	@Value("${getir.app.expires.in}")
	private long EXPIRES_IN;

	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private RoleRepository roleRepository;

	public String generateJwtToken(Authentication authentication) {

		JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
		List<UserRole> userRoles = this.userRoleRepository.getByUserId(userDetails.getId());
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		userRoles.forEach(role -> {
			String roleName = this.roleRepository.getById(role.getId()).getName();
			authorities.add(new SimpleGrantedAuthority(roleName));
		});
		userDetails.setAuthorities(authorities);
		Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
		return Jwts.builder().setSubject(Long.toString(userDetails.getId())).setIssuedAt(new Date())
				.claim("roles", userDetails.getAuthorities()).setExpiration(expireDate)
				.setIssuer(userDetails.getEmail()).signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
	}

	Long getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
			return !isTokenExpired(token);
		} catch (SignatureException e) {
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
		Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}

}
