package com.example.getirbackend.services.auth;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.getirbackend.models.User;
import com.example.getirbackend.repositories.UserRepository;
import com.example.getirbackend.requests.auth.LoginRequest;
import com.example.getirbackend.requests.auth.RegisterRequest;
import com.example.getirbackend.responses.auth.LoginResponse;
import com.example.getirbackend.responses.auth.RegisterResponse;
import com.example.getirbackend.services.rules.AuthBusinessRules;
import com.example.getirbackend.utils.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository;
	private ModelMapper mapper;
	private AuthBusinessRules authBusinessRules;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public AuthServiceImpl(UserRepository userRepository, ModelMapper mapper, AuthBusinessRules authBusinessRules,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
			JwtTokenProvider jwtTokenProvider) {
		this.userRepository = userRepository;
		this.mapper = mapper;
		this.authBusinessRules = authBusinessRules;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Override
	public RegisterResponse register(RegisterRequest request) throws Exception {
		
		this.authBusinessRules.emailCanNotDuplicate(request.getEmail());
		
		request.setPassword(passwordEncoder.encode(request.getPassword()));
		User mappedUser = this.mapper.map(request, User.class);
		User createdUser =  this.userRepository.save(mappedUser);
		RegisterResponse registerResponse = this.mapper.map(createdUser, RegisterResponse.class);

		return registerResponse;
		
	}

	@Override
	public LoginResponse login(LoginRequest request) throws Exception {
		this.authBusinessRules.userShouldExist(request.getEmail());
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String accessToken = jwtTokenProvider.generateJwtToken(auth); 
		User user = this.userRepository.getByEmail(request.getEmail());
		LoginResponse loginResponse = this.mapper.map(user, LoginResponse.class);
		loginResponse.setAccessToken(accessToken);
	
		return loginResponse;
		
	}

	
}
