package com.amazon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.entity.Login;
import com.amazon.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4201")
@RequestMapping("api/admin/")
public class LoginController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private LoginService loginService;

	static HttpSession httpSession;


	@GetMapping("login/{email}/{password}")
	public ResponseEntity<Boolean> loginAdmin(@PathVariable String email, @PathVariable String password) {
		httpSession = request.getSession();
		Boolean isAuthenticated = loginService.loginAdmin(email, password);
		if (isAuthenticated) {
			httpSession.setAttribute("loggedInAdmin", email);
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}
	}

	@GetMapping("logout")
	public ResponseEntity<String> logoutUser() {
		HttpSession httpSession = LoginController.httpSession;
		if (httpSession != null) {
			httpSession.setAttribute("loggedInAdmin", null);
			httpSession.invalidate();
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}


	@GetMapping("profile")
	public ResponseEntity<List<Login>> profile() {
		if (httpSession.getAttribute("loggedInAdmin") != null) {
			String email = (String) httpSession.getAttribute("loggedInAdmin");
			List<Login> admin = loginService.profileAdmin(email);
			if (admin != null) {
				return new ResponseEntity<>(admin, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("profileUpdate")
	public ResponseEntity<Login> updateProfile(@RequestBody Login updatedUser) {
		if (httpSession.getAttribute("loggedInAdmin") != null) {
		Login updatedProfile = loginService.updateProfile(updatedUser);
		return ResponseEntity.ok(updatedProfile);
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("allUsers")
	public ResponseEntity<List<Login>> allUser() {
		if (httpSession.getAttribute("loggedInAdmin") != null) {
		return new ResponseEntity<List<Login>>(loginService.allUser(), HttpStatus.OK);
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
