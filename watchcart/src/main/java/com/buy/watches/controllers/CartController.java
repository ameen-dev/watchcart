package com.buy.watches.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buy.watches.exceptions.EmptyInputException;
import com.buy.watches.interfaces.CartService;

@Controller
public class CartController {
	
	@Autowired
	CartService cartservice;

	@GetMapping("/")
	@ResponseBody
	public String welcome() {
		return "The watch inventory database is up and running. Please use 'http://localhost:8080/h2-console' in browser to view it or use 'http://localhost:8080/checkout' in postman to use 'checkout' API.";
	}
	
	@PostMapping("/checkout")
	@ResponseBody
	public ResponseEntity<Object> checkout(@RequestBody List<String> cartItems) {
		JSONObject output = new JSONObject();
		try {
			int  finalPrice = cartservice.checkout(cartItems);
			output.put("Price", finalPrice);
			return new ResponseEntity<>(output.toMap(), HttpStatus.OK);
		}catch(EmptyInputException eie) {
			output.put("Error", eie.getMessage());
			return new ResponseEntity<>(output.toMap(), HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			output.put("Error", e.getMessage());
			return new ResponseEntity<>(output.toMap(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
