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

	public boolean loginAdmin(String email, String password) {
		Login admin = loginDao.getAdminByEmail(email);
		return admin != null && admin.getPassword().equals(password);
	}

	public List<Login> profileAdmin(String email) {

		return loginDao.profileAdmin(email);
	}

	public Login updateProfile(Login updatedUser) {
		return loginDao.updateProfile(updatedUser);
	}

	public List<Login> allUser() {
		return loginDao.allUser();
	}
}