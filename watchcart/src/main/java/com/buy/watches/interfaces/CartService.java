package com.buy.watches.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CartService {

	public abstract int checkout(List<String> cartItems);
	
}
