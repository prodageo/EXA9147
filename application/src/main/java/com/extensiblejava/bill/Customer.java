package com.extensiblejava.bill;

import java.util.*;

public class Customer {
	private Integer custId;
	private String name;
	private List bills;

	public Customer(Integer custId, String name) {
		this.custId = custId;
		this.name = name;
	}

	public List getBills() {
		/*
		if (this.bills == null) {

		}
		*/
		return this.bills;
	}

	public String getName() { return this.name; }
}