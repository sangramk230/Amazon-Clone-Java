package com.amazon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.dao.AmcDao;
import com.amazon.entity.Product;

@Service
public class AmcService {

	@Autowired
	private AmcDao amcDao;

	public Product buyProduct(String useremail, Product product) {
		Long availableId = amcDao.getAvailableIdForProduct(product.getProductid());
		if (availableId == null) {
			return null;
		}
		return amcDao.buyProduct(product);
	}

	public Product addProductToCart(String useremail, Product product) {
		Long availableId = amcDao.getAvailableIdForProduct(product.getProductid());
		if (availableId == null) {
			return null;
		}

		return amcDao.addProductToCart(product);
	}

	/*
	 * public List<Product> viewProductCart(String useremail) { return
	 * amcDao.viewProductCart(useremail); }
	 * 
	 * public Product viewProductCartById(String useremail, Integer productid) {
	 * return amcDao.viewProductCartById(useremail, productid); }
	 * 
	 * public List<Product> viewOrders(String useremail) { return
	 * amcDao.viewOrders(useremail); }
	 * 
	 * public List<Availableproduct> viewProductsByCategory(String category) {
	 * return amcDao.viewProductsByCategory(category); }
	 * 
	 * public Boolean delBuyProductsById(String useremail, Integer productid) {
	 * return amcDao.delBuyProductsById(useremail, productid); }
	 * 
	 * public Boolean delCartProductsById(String useremail, Integer productid) {
	 * return amcDao.delCartProductsById(useremail, productid); } public
	 * List<Availableproduct> allProduct() { return amcDao.allProduct(); }
	 * 
	 * public List<Availableproduct> search(String name) { return
	 * amcDao.search(name); }
	 */
}