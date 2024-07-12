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

@Controller
@CrossOrigin("http://localhost:4201")
@RequestMapping("api/admin")
public class ProductController {
	@Autowired
	ProductService productService;

	@PostMapping("addProduct")
	public ResponseEntity<Availableproduct> addProduct(@RequestBody Availableproduct availableproduct) {
		return new ResponseEntity<Availableproduct>(productService.addProduct(availableproduct), HttpStatus.OK);
	}

	@GetMapping("viewProducts")
	public ResponseEntity<List<Availableproduct>> viewProduct() {
		return new ResponseEntity<List<Availableproduct>>(productService.viewProduct(), HttpStatus.OK);
	}

	@DeleteMapping("delProduct/{productid}")
	public ResponseEntity<Boolean> delProduct(@PathVariable Long productid) {
		boolean deleted = productService.delProduct(productid);

		if (deleted) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}
	}

	@PutMapping("updateProduct")
	public ResponseEntity<Availableproduct> updateProdct(@RequestBody Availableproduct updatedProduct) {
		Availableproduct product = productService.updateProdct(updatedProduct);
		return ResponseEntity.ok(product);
	}

	@PostMapping("addCategory")
	public ResponseEntity<Categories> addCategories(@RequestBody Categories categories) {
		return new ResponseEntity<>(productService.addCategories(categories), HttpStatus.OK);
	}

	@GetMapping("viewCategory")
	public ResponseEntity<List<Categories>> viewCategories() {

		return new ResponseEntity<List<Categories>>(productService.viewCategories(), HttpStatus.OK);
	}

	@DeleteMapping("delCategory/{categoryid}")
	public ResponseEntity<Boolean> delCategory(@PathVariable Long categoryid) {
		boolean deleted = productService.delCategory(categoryid);

		if (deleted) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}
	}

	@GetMapping("approval")
	public ResponseEntity<List<Product>> approval() {
		return new ResponseEntity<List<Product>>(productService.approval(), HttpStatus.OK);
	}

	@PutMapping("actionOnApproval")
	public ResponseEntity<Product> actionOnApproval(@RequestBody Product product) {
		Product prod = productService.actionOnApproval(product);
		return ResponseEntity.ok(product);
	}

	public void report(Availableproduct availableproduct) {
		productService.report(availableproduct);
	}
}
