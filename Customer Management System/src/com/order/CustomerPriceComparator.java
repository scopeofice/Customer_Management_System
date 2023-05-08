package com.order;

import java.util.Comparator;

import Customer_Management_System.Customer;

public class CustomerPriceComparator implements Comparator<Customer>{

	@Override
	public int compare(Customer o1, Customer o2) {
//			if(o1.getRegistrationAmount() < o2.getRegistrationAmount())
//				return -1;
//			if(o1.getRegistrationAmount() == o2.getRegistrationAmount())
//				return 0;
//		return 1;
		return ((Double)o1.getRegistrationAmount()).compareTo(o2.getRegistrationAmount());
	}

	
	

}
