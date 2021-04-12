package com.buy.watches.interfaces.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buy.watches.exceptions.EmptyInputException;
import com.buy.watches.interfaces.CartService;
import com.buy.watches.model.Watch;
import com.buy.watches.repositories.WatchRepo;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	WatchRepo repo;

	@Override
	public int checkout(List<String> cartItems) {
		if(cartItems.size()<1) {
			throw new EmptyInputException("Empty input. Please check the input.");
		}
		List<String> distinctCartItems = cartItems.stream().distinct().collect(Collectors.toList());
		List<String> unknownItemIDs = new ArrayList<>();
		int finalPrice = 0;
		for (String key : distinctCartItems) {
			//Since we are maintaining inventory, we have to make sure that the item IDs are distinct, else we would get more than one value here
			Watch watch = repo.findByWatchid(key);
			if(watch!=null) {
				int quantity = Collections.frequency(cartItems, key);
				int unitprice = watch.getUnitprice();
				if (watch.getDiscountquantity()==0 || watch.getDiscountprice()==0 || quantity<watch.getDiscountquantity()) {
					//This means that this watch has no discount (or) the user has purchased lesser quantity that he did not qualify for discount
					int keyprice = quantity*unitprice;
					finalPrice += keyprice;
				}else {
					//This means that this watch has discount if bought beyond a certain quantity
					int noOfDiscountUnits = quantity/watch.getDiscountquantity();
					int noOfNonDiscountUnits = quantity%watch.getDiscountquantity();
					int keyprice = (noOfDiscountUnits*watch.getDiscountprice())+(noOfNonDiscountUnits*unitprice);
					finalPrice += keyprice;
				}
			}else {
				unknownItemIDs.add(key);
			}
		}
		//We could return the list of not found items to the user too. For now, I'm not sending it just to keep my response aligned with the requirement document
		System.out.println("The following items were not found in the inventory : " + unknownItemIDs);
		return finalPrice;
	}
}
