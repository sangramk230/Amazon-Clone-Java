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

	public Login getUserByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
			return session.createQuery("FROM Login WHERE email = :email AND role='User'", Login.class)
					.setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public boolean signUp(Login user) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public List<Login> profileUser(String email) {
		try (Session session = sessionFactory.openSession()) {
			Query<Login> query = session.createQuery("FROM Login WHERE email = :email AND role='User'", Login.class);
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
}
