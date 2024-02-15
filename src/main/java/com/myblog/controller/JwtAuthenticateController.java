package com.myblog.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.entities.JwtRequest;
import com.myblog.entities.JwtResponse;
import com.myblog.entities.User;
import com.myblog.security.CustomUserDetailsServiceImpl;
import com.myblog.security.JwtUtil;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name ="JwtAuthenticateController" ,description = "APIs for Authentication")
public class JwtAuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/generate-token")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

		try {

			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		} catch (Exception e) {
			throw new Exception(e);
		}

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String generateToken = this.jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(generateToken));

	}

	private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("User disabled", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return (User) this.userDetailsService.loadUserByUsername(principal.getName());
	}

}
