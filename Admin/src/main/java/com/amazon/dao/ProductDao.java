package com.amazon.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazon.entity.Availableproduct;
import com.amazon.entity.Categories;
import com.amazon.entity.Product;



@Repository
public class ProductDao {
	@Autowired
	SessionFactory sessionFactory;

	public Availableproduct addProduct(Availableproduct availableproduct) {

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(availableproduct);
			transaction.commit();
		}
		return availableproduct;
	}

	public List<Availableproduct> viewProduct() {
		try (Session session = sessionFactory.openSession()) {
			Query<Availableproduct> query = session.createQuery("from Availableproduct");
			return query.list();
		}
	}

	public boolean delProduct(Long productid) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("DELETE FROM Availableproduct WHERE productid = :productid");
			query.setParameter("productid", productid);
			int rowCount = query.executeUpdate();
			session.getTransaction().commit();
			return rowCount > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Availableproduct updateProdct(Availableproduct updatedProduct) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(updatedProduct);
			session.getTransaction().commit();
			return updatedProduct;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Categories addCategories(Categories categories) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(categories);
			transaction.commit();
		}
		return categories;
	}

	public List<Categories> viewCategories() {
		try (Session session = sessionFactory.openSession()) {
			Query query = session.createQuery("FROM Categories");
			return query.list();
		}
	}

	public boolean delCategory(Long categoryid) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("DELETE FROM Categories WHERE categoryid = :categoryid");
			query.setParameter("categoryid", categoryid);
			int rowCount = query.executeUpdate();
			session.getTransaction().commit();
			return rowCount > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Product> approval() {
		try (Session session = sessionFactory.openSession()) {
			List<String> status = Arrays.asList("Approved", "Ready for dispatch", "In Progress",
					"Partially dispatched");
			Query query = session.createQuery("from Product where status IN: status");
			query.setParameter("status", status);
			return query.list();
		}
	}

	public Product actionOnApproval(Product product) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(product);
			session.getTransaction().commit();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void report(Availableproduct availableproduct) {

	}

}
