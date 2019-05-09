package com.test.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.constants.Authority;
import com.test.domain.ApplicationUser;
import com.test.repository.ApplicationUserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	private ApplicationUserRepository applicationUserRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController(ApplicationUserRepository applicationUserRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.applicationUserRepository = applicationUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/sign-up")
	public void signUp(@RequestParam String username, @RequestParam String password) {
		ApplicationUser user = new ApplicationUser();
		user.setUsername(username);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setAuthority(Authority.ADMIN);
		applicationUserRepository.save(user);
	}

	@GetMapping("/sign-in")
	public void signIn() {
		System.out.println("Sign in");
	}

	@GetMapping("/sign-In")
	public void signIn1() {
		System.out.println("Sign in1");
	}
}