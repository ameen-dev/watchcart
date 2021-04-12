package com.buy.watches.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Watch {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	private String watchid;
	private String watchname;
	private int unitprice;
	private int discountquantity;
	private int discountprice;
	
	public Watch() {
		
	}

	public Watch(int id, String watchid, String watchname, int unitprice, int discountquantity, int discountprice) {
		super();
		this.id = id;
		this.watchid = watchid;
		this.watchname = watchname;
		this.unitprice = unitprice;
		this.discountquantity = discountquantity;
		this.discountprice = discountprice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWatchid() {
		return watchid;
	}

	public void setWatchid(String watchid) {
		this.watchid = watchid;
	}

	public String getWatchname() {
		return watchname;
	}

	public void setWatchname(String watchname) {
		this.watchname = watchname;
	}

	public int getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(int unitprice) {
		this.unitprice = unitprice;
	}

	public int getDiscountquantity() {
		return discountquantity;
	}

	public void setDiscountquantity(int discountquantity) {
		this.discountquantity = discountquantity;
	}

	public int getDiscountprice() {
		return discountprice;
	}

	public void setDiscountprice(int discountprice) {
		this.discountprice = discountprice;
	}

	@Override
	public String toString() {
		return "Watch [id=" + id + ", watchid=" + watchid + ", watchname=" + watchname + ", unitprice=" + unitprice
				+ ", discountquantity=" + discountquantity + ", discountprice=" + discountprice + "]";
	}
	
	
	
}
