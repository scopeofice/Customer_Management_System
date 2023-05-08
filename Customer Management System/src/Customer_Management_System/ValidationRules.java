package Customer_Management_System;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import Exceptions.AgeNotAcceptable;
import Exceptions.CustomerNotFoundException;
import Exceptions.EmailAlreadyExistsException;
import Exceptions.InvalidPlanException;

public class ValidationRules {
	public static Customer validateAllInputs(String firstName, String lastName, String email, String password,
			String dob, String dateOfSub, String plan, double price, List<Customer> customer)
			throws CustomerNotFoundException, InvalidPlanException, AgeNotAcceptable, EmailAlreadyExistsException {

		checkForDuplicate(email, customer);
		LocalDate dueDateOfSub = LocalDate.parse(dateOfSub);
		ServicePlan validPlan = parseAndValidatePlan(plan);
		if (!ServicePlan.isValidPrice(validPlan, price)) {
			throw new InvalidPlanException("Price is not valid for selected plan");
		}
		LocalDate dateOfBirth = parseAndValidateAge(dob);

		return new Customer(firstName, lastName, email, password, dateOfBirth, dueDateOfSub, validPlan, price);

	}

	public static void unsubscribePlan(List<Customer> customerList) {
		System.out.println("Inside itr");
		for (Customer c : customerList) {
			int months=(Period.between(c.getDateOfSub(),LocalDate.now()).getYears())*12;
			if (months >= 3) {
				c.setRegistrationAmount(0.0);
				c.setServicePlan(null);
			}
		}
		System.out.println("Done");
//		Iterator<Customer> itr=customerList.iterator();
//			while(itr.hasNext())
//		 if (!(Period.between(LocalDate.now(), itr.next().getDateOfSub()).getMonths() < 3)) {
//			 itr.next().setServicePlan(null);
//			 itr.next().setRegistrationAmount(0.0);
//	        System.out.println("Plan unsubscribed successfully.");
//	      }
	}

	// Sort by name and price
	public static void sortNameAndPrice(List<Customer> customerList) {
		Collections.sort(customerList, new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				int retVal = o1.getFirstName().compareTo(o2.getFirstName());
				if (retVal == 0)
					return ((Double) o1.getRegistrationAmount()).compareTo(o2.getRegistrationAmount());
				return retVal;
			}
		});
	}

	// Pay for Subscription
	public static void payForSub(Double regAmount, int index, List<Customer> customerList) {
		customerList.get(index).setRegistrationAmount(regAmount);
		System.out.println("Amount Paid");
	}

	// Remove Service Plan
	public static void removeServicePlan(String plan, List<Customer> customerList) throws InvalidPlanException {
		ServicePlan newPlan = ServicePlan.valueOf(plan.toUpperCase());
		Iterator<Customer> itr = customerList.iterator();
		while (itr.hasNext())
			if (itr.next().getPlan().equals(newPlan))
				itr.remove();

	}

	// Remove Customer
	public static void removeCustomer(String email, List<Customer> customerList) throws CustomerNotFoundException {
		Customer newEmail = new Customer(email);
		int index = customerList.indexOf(newEmail);
		if (index == -1) {
			throw new CustomerNotFoundException("Email not present");
		}
		System.out.println("Removed " + customerList.remove(index));
	}

	// Validate Email
	public static void validateEmail(String email, List<Customer> customerList) throws CustomerNotFoundException {
		Customer newEmail = new Customer(email);
		int index = customerList.indexOf(newEmail);
		if (index == -1) {
			throw new CustomerNotFoundException("Email not present");
		}
		System.out.println(index);
	}

	// Validate Email and Password
	public static int validateEmailPass(String email, String pass, List<Customer> customerList)
			throws CustomerNotFoundException {
		Customer newEmail = new Customer(email);
		int index = customerList.indexOf(newEmail);
		if (index == -1) {
			throw new CustomerNotFoundException("Email not present");
		}
		if (!(pass.equals(customerList.get(index).getPassword())))
			throw new CustomerNotFoundException("Invalid Credentials");
		return index;
	}

	// Validate Service Plan
	public static ServicePlan parseAndValidatePlan(String validPlan) throws InvalidPlanException {
		return ServicePlan.valueOf(validPlan.toUpperCase());

	}

	public static void checkForDuplicate(String newEmail, List<Customer> customer) throws EmailAlreadyExistsException {
		Customer newCustomer = new Customer(newEmail);
		if (customer.contains(newCustomer))
			throw new EmailAlreadyExistsException("Customer with email " + newEmail + " not found.");
	}

	public static LocalDate parseAndValidateAge(String dateOfBirth) throws AgeNotAcceptable {
		LocalDate d1 = LocalDate.parse(dateOfBirth);
		int age = Period.between(d1, LocalDate.now()).getYears();
		if (age < 21)
			throw new AgeNotAcceptable("You are below 21");
		return d1;
	}
}
