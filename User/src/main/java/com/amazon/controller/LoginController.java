package com.amazon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.entity.Login;
import com.amazon.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("api")
public class LoginController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private LoginService loginService;

	static HttpSession httpSession;

	@PostMapping("signup/user")
	public ResponseEntity<Void> signUp(@RequestBody Login user) {
		boolean isSignedUp = loginService.signUp(user);
		if (isSignedUp) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("login/user/{email}/{password}")
	public ResponseEntity<Boolean> loginUser(@PathVariable String email, @PathVariable String password) {
		httpSession = request.getSession();
		Boolean isAuthenticated = loginService.loginUser(email, password);
		if (isAuthenticated) {
			httpSession.setAttribute("loggedInUser", email);
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}
	}

	@GetMapping("logout")
	public ResponseEntity<String> logoutUser() {
		HttpSession httpSession = LoginController.httpSession;
		if (httpSession != null) {
			httpSession.setAttribute("loggedInUser", null);
			httpSession.invalidate();
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}


	@GetMapping("profile")
	public ResponseEntity<List<Login>> profile() {
		if (httpSession.getAttribute("loggedInUser") != null) {
			String email = (String) httpSession.getAttribute("loggedInUser");
			List<Login> users = loginService.profileUser(email);
			if (users != null) {
				return new ResponseEntity<>(users, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("profileUpdate")
	public ResponseEntity<Login> updateProfile(@RequestBody Login updatedUser) {
		Login updatedProfile = loginService.updateProfile(updatedUser);
		return ResponseEntity.ok(updatedProfile);
	}
}