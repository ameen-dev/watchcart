package com.buy.watches;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WatchcartApplicationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Test
	public void testRootController() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/"), HttpMethod.GET, entity,
				String.class);
		String expected = "The watch inventory database is up and running. Please use 'http://localhost:8080/h2-console' "
				+ "in browser to view it or use 'http://localhost:8080/checkout' in postman to use 'checkout' API.";
		assertEquals(expected, response.getBody());
	}

	@Test
	public void testCheckoutOneWatch() throws Exception {
		HttpEntity<List> entity = new HttpEntity<List>(Arrays.asList("001"), headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout"), HttpMethod.POST, entity,
				String.class);
		String expected = "{\"Price\":100}";
		assertEquals(expected, response.getBody());
	}

	@Test
	public void testCheckoutThreeWatches() throws Exception {
		HttpEntity<List> entity = new HttpEntity<List>(Arrays.asList("001", "002", "001"), headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout"), HttpMethod.POST, entity,
				String.class);
		String expected = "{\"Price\":280}";
		assertEquals(expected, response.getBody());
	}

	@Test
	public void testCheckoutWithoutDiscount() throws Exception {
		HttpEntity<List> entity = new HttpEntity<List>(Arrays.asList("001", "002", "003", "003", "004", "001", "004"),
				headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout"), HttpMethod.POST, entity,
				String.class);
		String expected = "{\"Price\":440}";
		assertEquals(expected, response.getBody());
	}

	@Test
	public void testCheckoutWithDiscount() throws Exception {
		HttpEntity<List> entity = new HttpEntity<List>(Arrays.asList("001", "002", "003", "001", "001", "001", "004"),
				headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout"), HttpMethod.POST, entity,
				String.class);
		String expected = "{\"Price\":460}";
		assertEquals(expected, response.getBody());
	}

	@Test
	public void testCheckoutWithUnknownKey() throws Exception {
		HttpEntity<List> entity = new HttpEntity<List>(Arrays.asList("001", "002", "003", "001", "001", "001", "004","10920"),
				headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout"), HttpMethod.POST, entity,
				String.class);
		String expected = "{\"Price\":460}";
		assertEquals(expected, response.getBody());
	}
	
	@Test
	public void testCheckoutWithException() throws Exception {
		HttpEntity<List> entity = new HttpEntity<List>(new ArrayList<String>(),headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout"), HttpMethod.POST, entity,
				String.class);
		String expected = "{\"Error\":\"Empty input. Please check the input.\"}";
		assertEquals(expected, response.getBody());
	}
}
