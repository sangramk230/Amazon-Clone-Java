package com.amazon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.dao.LoginDao;
import com.amazon.entity.Login;

@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;

	public boolean signUp(Login user) {
		user.setRole("User");
		return loginDao.signUp(user);
	}

	public boolean loginUser(String email, String password) {
		Login user = loginDao.getUserByEmail(email);
		return user != null && user.getPassword().equals(password);
	}


	public List<Login> profileUser(String email) {

		return loginDao.profileUser(email);
	}
	public Login updateProfile(Login updatedUser) {
		return loginDao.updateProfile(updatedUser);
	}
}
