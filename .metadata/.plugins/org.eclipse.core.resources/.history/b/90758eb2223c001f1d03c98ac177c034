package com.amazon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.entity.Product;
import com.amazon.service.AmcService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/product")
public class AmcController {

	@Autowired
	private AmcService amcService;

	@Autowired
	private HttpServletRequest request;

	@PostMapping("buyProduct")
	public ResponseEntity<Product> buyProduct(@RequestBody Product product) {
		HttpSession httpSession = LoginController.httpSession;
		if (httpSession == null || httpSession.getAttribute("loggedInUser") == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			String useremail = (String) httpSession.getAttribute("loggedInUser");
			Product result = amcService.buyProduct(useremail, product);
			if (result != null) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@PostMapping("addToCart")
	public ResponseEntity<Product> addProductToCart(@RequestBody Product Product) {
		HttpSession httpSession = LoginController.httpSession;
		if (httpSession == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			String useremail = (String) httpSession.getAttribute("loggedInUser");

			Product result = amcService.addProductToCart(useremail, Product);
			if (result != null) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/*
	 * @GetMapping("viewProductsByCategory/{category}") public
	 * ResponseEntity<List<Availableproduct>> viewProductsByCategory(@PathVariable
	 * String category) { List<Availableproduct> products =
	 * amcService.viewProductsByCategory(category); return new
	 * ResponseEntity<>(products, HttpStatus.OK); }
	 * 
	 * @DeleteMapping("delBuyProductsById/{productid}") public
	 * ResponseEntity<Boolean> delBuyProductsById(@PathVariable Integer productid) {
	 * HttpSession httpSession = LoginController.httpSession; if (httpSession !=
	 * null) { String useremail = (String) httpSession.getAttribute("loggedInUser");
	 * boolean isDeleted = amcService.delBuyProductsById(useremail, productid); if
	 * (isDeleted) { return new ResponseEntity<>(true, HttpStatus.OK); } } return
	 * new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	 * 
	 * }
	 * 
	 * @DeleteMapping("delCartProductsById/{productid}") public
	 * ResponseEntity<Boolean> delCartProductsById(@PathVariable Integer productid)
	 * { HttpSession httpSession = LoginController.httpSession; if (httpSession !=
	 * null) { String useremail = (String) httpSession.getAttribute("loggedInUser");
	 * boolean isDeleted = amcService.delCartProductsById(useremail, productid); if
	 * (isDeleted) { return new ResponseEntity<>(true, HttpStatus.OK); } } return
	 * new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	 * 
	 * }
	 * 
	 * @GetMapping("allProduct") public ResponseEntity<List<Availableproduct>>
	 * allProduct() { return new ResponseEntity<>(amcService.allProduct(),
	 * HttpStatus.OK); }
	 * 
	 * @GetMapping("viewCart") public ResponseEntity<List<Product>> viewCart() {
	 * HttpSession httpSession = LoginController.httpSession; List<Product> products
	 * = amcService.viewProductCart((String)
	 * httpSession.getAttribute("loggedInUser")); if (products != null &&
	 * !products.isEmpty()) { return new ResponseEntity<>(products, HttpStatus.OK);
	 * } else { return new ResponseEntity<>(HttpStatus.NO_CONTENT); } }
	 * 
	 * @GetMapping("viewProductCartById/{productid}") public Product
	 * viewProductCartById(@PathVariable Integer productid) { HttpSession
	 * httpSession = LoginController.httpSession; if (httpSession != null) { return
	 * amcService.viewProductCartById((String)
	 * httpSession.getAttribute("loggedInUser"), productid); } return null; }
	 * 
	 * @GetMapping("viewOrder") public ResponseEntity<List<Product>> viewOrders() {
	 * HttpSession httpSession = LoginController.httpSession; List<Product> products
	 * = amcService.viewOrders((String) httpSession.getAttribute("loggedInUser"));
	 * if (products != null && !products.isEmpty()) { return new
	 * ResponseEntity<>(products, HttpStatus.OK); } else { return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } }
	 * 
	 * @GetMapping("search/{name}") public ResponseEntity<List<Availableproduct>>
	 * search(@PathVariable String name) { List<Availableproduct> products =
	 * amcService.search(name); System.out.println(products); if (products != null
	 * && !products.isEmpty()) { return new ResponseEntity<>(products,
	 * HttpStatus.FOUND); } else { return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 */
}
