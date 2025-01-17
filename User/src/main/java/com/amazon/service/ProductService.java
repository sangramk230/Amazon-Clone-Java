package com.amazon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.dao.ProductDao;
import com.amazon.entity.Availableproduct;
import com.amazon.entity.Categories;
import com.amazon.entity.Product;

@Service
public class ProductService {
	@Autowired
	ProductDao productDao;

	public List<Availableproduct> allProduct() {
		return productDao.allProduct();
	}

	public Product addProductToCart(String email, Product product) {
		return productDao.addProductToCart(email, product);
	}

	public List<Product> viewProductCart(String email) {
		return productDao.viewProductCart(email);
	}

	public Boolean delCartProductsById(String email, Integer productid) {
		return productDao.delCartProductsById(email, productid);
	}

	public Product addProductToBuy(String email, Product product) {
		return productDao.addProductToBuy(email, product);
	}

	public List<Product> viewProductBuy(String email) {
		return productDao.viewProductBuy(email);
	}

	public Boolean delBuyProductsById(String email, Integer productid) {
		return productDao.delBuyProductsById(email,productid);
	}

	public List<Availableproduct> viewProductsByCategory(String category) {
		return productDao.viewProductsByCategory(category);
	}

	public List<Categories> viewCategory() {
		return productDao.viewCategory();
	}

	public List search(String name) {
		return productDao.search(name);

	}

	public Product updateCart(String email, Product product) {
		return productDao.updateCart(email, product);

	}

	public List<Product> addProductsToBuy(String email, List<Product> products) {

		return productDao.addProductsToBuy(email, products);
	}

}
