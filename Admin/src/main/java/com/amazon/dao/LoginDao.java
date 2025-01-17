package com.amazon.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazon.entity.Login;

@Repository
public class LoginDao {

    @Autowired
    private SessionFactory sessionFactory;


	public List<Login> getAdminByEmail(String email) {
		try (Session session = sessionFactory.openSession()) {
			Query<Login> query = session.createQuery("FROM Login WHERE email = : email AND role= 'Admin'", Login.class);
			query.setParameter("email", email);
					return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public List<Login> profileAdmin(String email) {
		try (Session session = sessionFactory.openSession()) {
			Query<Login> query = session.createQuery("FROM Login WHERE email = : email AND role='Admin'", Login.class);
			query.setParameter("email", email);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Login updateProfile(Login updatedUser) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(updatedUser);
			session.getTransaction().commit();
			return updatedUser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Login> allUser() {
		try (Session session = sessionFactory.openSession()) {
			Query<Login> query = session.createQuery("FROM Login where role = 'User'", Login.class);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
