package com.order;

import java.util.Comparator;

import Customer_Management_System.Customer;

public class CustomerPriceDateComparator implements Comparator<Customer>{

	@Override
	public int compare(Customer o1, Customer o2) {
		int retVal=o1.getDob().compareTo(o2.getDob());
		if(retVal==0)
			if(o1.getRegistrationAmount() < o2.getRegistrationAmount())
				return -1;
			if(o1.getRegistrationAmount() == o2.getRegistrationAmount())
				return 0;
		return 1;
	}

	
	

}
