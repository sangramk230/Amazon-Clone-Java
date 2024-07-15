package com.amazon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazon.entity.Availableproduct;
import com.amazon.entity.Categories;
import com.amazon.entity.Product;
import com.amazon.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin("http://localhost:4201")
@RequestMapping("api/admin")
public class ProductController {

	@Autowired
	private ProductService productService;


	private boolean isAdminLoggedIn() {
		HttpSession httpSession = LoginController.httpSession;
		return httpSession != null && httpSession.getAttribute("loggedInAdmin") != null;
	}

	@PostMapping("addProduct")
	public ResponseEntity<Availableproduct> addProduct(@RequestBody Availableproduct availableproduct) {
		if (isAdminLoggedIn()) {
			return new ResponseEntity<>(productService.addProduct(availableproduct), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("viewProducts")
	public ResponseEntity<List<Availableproduct>> viewProduct() {
		if (isAdminLoggedIn()) {
			return new ResponseEntity<>(productService.viewProduct(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping("delProduct/{productid}")
	public ResponseEntity<Boolean> delProduct(@PathVariable Long productid) {
		boolean deleted = productService.delProduct(productid);
		return ResponseEntity.ok(deleted);
	}

	@PutMapping("updateProduct")
	public ResponseEntity<Availableproduct> updateProduct(@RequestBody Availableproduct updatedProduct) {
		if (isAdminLoggedIn()) {
			return ResponseEntity.ok(productService.updateProdct(updatedProduct));
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("addCategory")
	public ResponseEntity<Categories> addCategories(@RequestBody Categories categories) {
		return new ResponseEntity<>(productService.addCategories(categories), HttpStatus.OK);
	}

	@GetMapping("viewCategory")
	public ResponseEntity<List<Categories>> viewCategories() {
		if (isAdminLoggedIn()) {
			return new ResponseEntity<>(productService.viewCategories(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping("delCategory/{categoryid}")
	public ResponseEntity<Boolean> delCategory(@PathVariable Long categoryid) {
		if (isAdminLoggedIn()) {
			boolean deleted = productService.delCategory(categoryid);
			return ResponseEntity.ok(deleted);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("approval")
	public ResponseEntity<List<Product>> approval() {
		if (isAdminLoggedIn()) {
			return new ResponseEntity<>(productService.approval(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("actionOnApproval")
	public ResponseEntity<Product> actionOnApproval(@RequestBody Product product) {
		if (isAdminLoggedIn()) {
			return ResponseEntity.ok(productService.actionOnApproval(product));
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	public void report(Availableproduct availableproduct) {
		productService.report(availableproduct);
	}
}
