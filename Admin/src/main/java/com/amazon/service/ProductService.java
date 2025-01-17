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

	public Availableproduct addProduct(Availableproduct availableproduct) {
		return productDao.addProduct(availableproduct);

	}

	public List<Availableproduct> viewProduct() {
		return productDao.viewProduct();
	}

	public Boolean delProduct(Long productid) {
		return productDao.delProduct(productid);
	}

	public Availableproduct updateProdct(Availableproduct updatedProduct) {
		return productDao.updateProdct(updatedProduct);
	}

	public Categories addCategories(Categories categories) {
		return productDao.addCategories(categories);
	}

	public List<Categories> viewCategories() {
		return productDao.viewCategories();
	}

	public Boolean delCategory(Long categoryid) {
		return productDao.delCategory(categoryid);
	}

	public List<Product> approval() {
		return productDao.approval();
	}

	public Product actionOnApproval(Product product) {
		return productDao.actionOnApproval(product);
	}
	public void report(Availableproduct availableproduct) {
		productDao.report(availableproduct);
	}
}
