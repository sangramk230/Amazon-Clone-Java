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
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/user")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	HttpSession httpSession;

	private boolean isUserLoggedIn() {
		httpSession = LoginController.httpSession;
		return httpSession != null && httpSession.getAttribute("loggedInUser") != null;
	}

	@GetMapping("allProduct")
	public ResponseEntity<List<Availableproduct>> allProduct() {
		return new ResponseEntity<>(productService.allProduct(), HttpStatus.OK);
	}

	@PostMapping("addToCart")
	public ResponseEntity<Product> addProductToCart(@RequestBody Product crt) {
		if (!isUserLoggedIn()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		Product result = productService.addProductToCart((String) httpSession.getAttribute("loggedInUser"), crt);
		return result != null ? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("viewCart")
	public ResponseEntity<List<Product>> viewCart() {
		if (!isUserLoggedIn()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(productService.viewProductCart((String) httpSession.getAttribute("loggedInUser")),
				HttpStatus.OK);
	}

	@DeleteMapping("delCartProductsById/{productid}")
	public ResponseEntity<Boolean> delCartProductsById(@PathVariable Integer productid) {
		if (!isUserLoggedIn()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		boolean isDeleted = productService.delCartProductsById((String) httpSession.getAttribute("loggedInUser"),
				productid);
		return ResponseEntity.ok(isDeleted);
	}

	@PostMapping("buyProduct")
	public ResponseEntity<Product> addProductToBuy(@RequestBody Product crt) {
		if (!isUserLoggedIn()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		Product result = productService.addProductToBuy((String) httpSession.getAttribute("loggedInUser"), crt);
		return result != null ? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("viewOrder")
	public ResponseEntity<List<Product>> viewBuy() {
		if (!isUserLoggedIn()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(productService.viewProductBuy((String) httpSession.getAttribute("loggedInUser")),
				HttpStatus.OK);
	}

	@DeleteMapping("delBuyProductsById/{productid}")
	public ResponseEntity<Boolean> delBuyProductsById(@PathVariable Integer productid) {
		if (!isUserLoggedIn()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		boolean isDeleted = productService.delBuyProductsById((String) httpSession.getAttribute("loggedInUser"),
				productid);
		return ResponseEntity.ok(isDeleted);
	}

	@GetMapping("viewProductsByCategory/{category}")
	public ResponseEntity<List<Availableproduct>> viewProductsByCategory(@PathVariable String category) {
		return new ResponseEntity<>(productService.viewProductsByCategory(category), HttpStatus.OK);
	}

	@GetMapping("viewCategory")
	public ResponseEntity<List<Categories>> viewCategory() {
		return new ResponseEntity<>(productService.viewCategory(), HttpStatus.OK);
	}

	@GetMapping("search/{name}")
	public ResponseEntity<List> search(@PathVariable String name) {
		return new ResponseEntity<>(productService.search(name), HttpStatus.OK);
	}

	@PutMapping("updateCart")
	public ResponseEntity<Product> updateCart(@RequestBody Product product) {
		if (!isUserLoggedIn()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(
				productService.updateCart((String) httpSession.getAttribute("loggedInUser"), product), HttpStatus.OK);
	}

	@PostMapping("buyProducts")
	public ResponseEntity<List<Product>> addProductsToBuy(@RequestBody List<Product> products) {
		if (!isUserLoggedIn()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		List<Product> result = productService.addProductsToBuy((String) httpSession.getAttribute("loggedInUser"),
				products);
		return result != null && !result.isEmpty() ? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
