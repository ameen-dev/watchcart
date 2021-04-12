package com.buy.watches.interfaces.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.buy.watches.exceptions.EmptyInputException;
import com.buy.watches.interfaces.CartService;


@SpringBootTest
class WatchcartServiceTests {
	
	@Test
	void contextLoads() {
	}

	@Autowired
	CartService service;
	
	@Test
	public void testSuccessOneWatch() {
		int expectedPrice = 100;
		List<String> watchList = Arrays.asList("001");
		int actualPrice = service.checkout(watchList);
	    Assertions.assertEquals(expectedPrice, actualPrice);
	}
	
	@Test
	public void testSuccessWithoutDiscount() {
		int expectedPrice = 210;
		List<String> watchList = Arrays.asList("001","002","004");
		int actualPrice = service.checkout(watchList);
	    Assertions.assertEquals(expectedPrice, actualPrice);
	}
	
	@Test
	public void testSuccessWithDiscount() {
		int expectedPrice = 460;
		List<String> watchList = Arrays.asList("001","002","003","001","001","001","004");
		int actualPrice = service.checkout(watchList);
	    Assertions.assertEquals(expectedPrice, actualPrice);
	}
	
	@Test
	public void testWithInknownITemId() {
		int expectedPrice = 460;
		List<String> watchList = Arrays.asList("001","002","003","001","001","001","004","009");
		int actualPrice = service.checkout(watchList);
	    Assertions.assertEquals(expectedPrice, actualPrice);
	}
	
	@Test
	public void testWithEmptyInput() {
	    Exception exception = assertThrows(EmptyInputException.class, () -> {
	    	service.checkout(new ArrayList<String>());
	    });
	    String expectedMessage = "Empty input. Please check the input.";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
}