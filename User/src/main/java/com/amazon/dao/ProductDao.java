package com.amazon.dao;

import java.util.ArrayList;
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
	private SessionFactory sessionFactory;

	private Long userIdCreate(String email) {
		try (Session session = sessionFactory.openSession()) {
			Query<Long> query = session.createQuery("SELECT id FROM Login WHERE email = :email", Long.class);
			query.setParameter("email", email);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Availableproduct> allProduct() {
		try (Session session = sessionFactory.openSession()) {
			Query<Availableproduct> query = session.createQuery("FROM Availableproduct", Availableproduct.class);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Product addProductToCart(String email, Product product) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			Long id = userIdCreate(email);
			product.setUserid(id);
			product.setBuyorcart("Cart");
			session.save(product);
			transaction.commit();
			return product;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> viewProductCart(String email) {
		try (Session session = sessionFactory.openSession()) {
			Long id = userIdCreate(email);
			Query<Product> query = session.createQuery("FROM Product WHERE userid = :id AND buyorcart = 'Cart'",
					Product.class);
			query.setParameter("id", id);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean delCartProductsById(String email, Integer productid) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			Long id = userIdCreate(email);
			Query<?> query = session.createQuery(
					"DELETE FROM Product WHERE userid = :id AND productid = :productid AND buyorcart = 'Cart'");
			query.setParameter("id", id);
			query.setParameter("productid", productid);
			int result = query.executeUpdate();
			transaction.commit();
			return result > 0;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	public Product addProductToBuy(String email, Product product) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			Long id = userIdCreate(email);
			product.setUserid(id);
			product.setBuyorcart("Buy");
			product.setStatus("In Progress");
			session.save(product);
			transaction.commit();
			return product;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> viewProductBuy(String email) {
		try (Session session = sessionFactory.openSession()) {
			Long id = userIdCreate(email);
			Query<Product> query = session.createQuery("FROM Product WHERE userid = :id AND buyorcart = 'Buy'",
					Product.class);
			query.setParameter("id", id);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean delBuyProductsById(String email, Integer productid) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			Long id = userIdCreate(email);
			Query<?> query = session.createQuery(
					"DELETE FROM Product WHERE userid = :id AND productid = :productid AND buyorcart = 'Buy'");
			query.setParameter("id", id);
			query.setParameter("productid", productid);
			int result = query.executeUpdate();
			transaction.commit();
			return result > 0;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	public List<Availableproduct> viewProductsByCategory(String category) {
		try (Session session = sessionFactory.openSession()) {
			Query<Availableproduct> query = session.createQuery("FROM Availableproduct WHERE category = :category",
					Availableproduct.class);
			query.setParameter("category", category);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Categories> viewCategory() {
		try (Session session = sessionFactory.openSession()) {
			Query<Categories> query = session.createQuery("FROM Categories", Categories.class);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Availableproduct> search(String name) {
		try (Session session = sessionFactory.openSession()) {
			Query<Availableproduct> query = session.createQuery("FROM Availableproduct WHERE name LIKE :name",
					Availableproduct.class);
			query.setParameter("name", name + "%");
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Product updateCart(String email, Product product) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			Long id = userIdCreate(email);
			product.setUserid(id);
			session.update(product);
			transaction.commit();
			return product;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> addProductsToBuy(String email, List<Product> products) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			Long id = userIdCreate(email);
			List<Product> savedProducts = new ArrayList<>();
			for (Product product : products) {
				product.setUserid(id);
				product.setBuyorcart("Buy");
				savedProducts.add(product);
				session.save(product);
			}
			transaction.commit();
			return savedProducts;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
}
