package Customer_Management_System;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.List;

import Exceptions.AgeNotAcceptable;
import Exceptions.CustomerNotFoundException;
import Exceptions.EmailAlreadyExistsException;
import Exceptions.InvalidPlanException;

public class ValidationRules {
	public static Customer validateAllInputs(String firstName, String lastName, String email, String password,
			String dob, String plan, List<Customer> customer) throws CustomerNotFoundException, InvalidPlanException, AgeNotAcceptable, EmailAlreadyExistsException {
		checkForDuplicate(email, customer);
		ServicePlan validPlan = parseAndValidatePlan(plan);
		LocalDate dateOfBirth = parseAndValidateAge(dob);

		return new Customer( firstName, lastName, email, password, dateOfBirth, validPlan);
		
	}
	
	public static void removeServicePlan(String plan,List<Customer> customerList) throws InvalidPlanException{
		ServicePlan newPlan=ServicePlan.valueOf(plan.toUpperCase());
		Iterator<Customer> itr=customerList.iterator();
		while(itr.hasNext())
			if(itr.next().getPlan().equals(newPlan))
				itr.remove();
				
	}
	
	
	public static void removeCustomer(String email,List<Customer> customerList) throws CustomerNotFoundException{
		Customer newEmail = new Customer(email);
		int index = customerList.indexOf(newEmail);
		if (index == -1) {
			throw new CustomerNotFoundException("Email not present");
		}System.out.println("Removed " + customerList.remove(index));
	}
	
	public static void validateEmail(String email,List<Customer> customerList) throws CustomerNotFoundException{
		Customer newEmail = new Customer(email);
		int index = customerList.indexOf(newEmail);
		if (index == -1) {
			throw new CustomerNotFoundException("Email not present");
		} System.out.println(index);
	}
	
	public static int validateEmailPass(String email,String pass,List<Customer> customerList) throws CustomerNotFoundException{
		Customer newEmail = new Customer(email);
		int index = customerList.indexOf(newEmail);
		if (index == -1) {
			throw new CustomerNotFoundException("Email not present");
		}
		if (!(pass.equals(customerList.get(index).getPassword())))
			throw new CustomerNotFoundException("Invalid Credentials");
		return index;
	}

	public static ServicePlan parseAndValidatePlan(String validPlan) throws InvalidPlanException {
		return ServicePlan.valueOf(validPlan.toUpperCase());

	}

	public static void checkForDuplicate(String newEmail, List<Customer> customer) throws EmailAlreadyExistsException {
		Customer newCustomer = new Customer(newEmail);
		if (customer.contains(newCustomer))
			throw new EmailAlreadyExistsException("Customer with email " + newEmail + " not found.");
	}

	public static LocalDate parseAndValidateAge(String dateOfBirth) throws  AgeNotAcceptable{
		LocalDate d1=LocalDate.parse(dateOfBirth);
		int age=Period.between(d1, LocalDate.now()).getYears();
		if(age<21)
			throw new AgeNotAcceptable("You are below 21");
		return d1;
	}
}
