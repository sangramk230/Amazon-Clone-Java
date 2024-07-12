package com.amazon.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazon.entity.Product;

@Repository
public class AmcDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Long getAvailableIdForProduct(Long productid) {
		try (Session session = sessionFactory.openSession()) {
			Query<Long> query = session.createQuery(
					"SELECT ap.productid FROM Availableproduct ap WHERE ap.productid = :productid", Long.class);
			query.setParameter("productid", productid);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Product buyProduct(Product product) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
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

	public Product addProductToCart(Product cart) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.save(cart);
			transaction.commit();
			return cart;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public boolean isProductInCart(String email, Long productid) { try (Session
	 * session = sessionFactory.openSession()) { Query<Long> query =
	 * session.createQuery(
	 * "SELECT COUNT(*) FROM Product WHERE email = :email AND productid = :productid"
	 * , Long.class); query.setParameter("email", email);
	 * query.setParameter("productid", productid); Long count =
	 * query.uniqueResult(); return count != null && count > 0; } catch (Exception
	 * e) { e.printStackTrace(); return false; } }
	 * 
	 * public List<Product> viewProductCart(String useremail) { try (Session session
	 * = sessionFactory.openSession()) { Query<Product> query =
	 * session.createQuery("FROM Product WHERE useremail = :useremail",
	 * Product.class); query.setParameter("useremail", useremail); return
	 * query.list(); } catch (Exception e) { e.printStackTrace(); return null; } }
	 * 
	 * public Product viewProductCartById(String useremail, Integer productid) { try
	 * (Session session = sessionFactory.openSession()) { Query<Product> query =
	 * session.createQuery(
	 * "FROM Product c WHERE c.useremail = :useremail AND c.productid = :productid",
	 * Product.class); query.setParameter("useremail", useremail);
	 * query.setParameter("productid", productid); return query.uniqueResult(); }
	 * catch (Exception e) { e.printStackTrace(); return null; } }
	 * 
	 * public List<Product> viewOrders(String useremail) { try (Session session =
	 * sessionFactory.openSession()) { Query<Product> query =
	 * session.createQuery("FROM Product p WHERE p.useremail = :useremail",
	 * Product.class); query.setParameter("useremail", useremail); return
	 * query.list(); } catch (Exception e) { e.printStackTrace(); return null; } }
	 * 
	 * public List<Availableproduct> viewProductsByCategory(String category) { try
	 * (Session session = sessionFactory.openSession()) { Query<Availableproduct>
	 * query =
	 * session.createQuery("FROM Availableproduct a WHERE a.category = :category",
	 * Availableproduct.class); query.setParameter("category", category); return
	 * query.list(); } catch (Exception e) { e.printStackTrace(); return null; } }
	 * 
	 * public Boolean delBuyProductsById(String useremail, Integer productid) {
	 * Transaction transaction = null; try (Session session =
	 * sessionFactory.openSession()) { transaction = session.beginTransaction();
	 * Query<?> query = session
	 * .createQuery("DELETE FROM Product a WHERE a.productid = :productid AND a.useremail = :useremail"
	 * ); query.setParameter("productid", productid);
	 * query.setParameter("useremail", useremail); int result =
	 * query.executeUpdate(); transaction.commit(); return result > 0; } catch
	 * (Exception e) { if (transaction != null) { transaction.rollback(); }
	 * e.printStackTrace(); return false; } }
	 * 
	 * public Boolean delCartProductsById(String useremail, Integer productid) {
	 * Transaction transaction = null; try (Session session =
	 * sessionFactory.openSession()) { transaction = session.beginTransaction();
	 * Query<?> query = session
	 * .createQuery("DELETE FROM Product a WHERE a.productid = :productid AND a.useremail = :useremail"
	 * ); query.setParameter("productid", productid);
	 * query.setParameter("useremail", useremail); int result =
	 * query.executeUpdate(); transaction.commit(); return result > 0; } catch
	 * (Exception e) { if (transaction != null) { transaction.rollback(); }
	 * e.printStackTrace(); return false; } }
	 * 
	 * public List<Availableproduct> allProduct() { try (Session session =
	 * sessionFactory.openSession()) { Query<Availableproduct> query =
	 * session.createQuery("FROM Availableproduct", Availableproduct.class); return
	 * query.list(); } catch (Exception e) { e.printStackTrace(); return null; } }
	 * 
	 * public List<Availableproduct> search(String name) { try (Session session =
	 * sessionFactory.openSession()) { Query<Availableproduct> query =
	 * session.createQuery("FROM Availableproduct WHERE name LIKE :name",
	 * Availableproduct.class); query.setParameter("name", name + "%"); return
	 * query.list(); } catch (Exception e) { e.printStackTrace(); return null; } }
	 */
}
