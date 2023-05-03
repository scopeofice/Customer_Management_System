package Tester;

import static Customer_Management_System.ValidationRules.parseAndValidatePlan;
import static Customer_Management_System.ValidationRules.removeCustomer;
import static Customer_Management_System.ValidationRules.validateAllInputs;
import static Customer_Management_System.ValidationRules.validateEmail;
import static Customer_Management_System.ValidationRules.validateEmailPass;
import static Customer_Management_System.ValidationRules.removeServicePlan;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.order.CustomerPriceComparator;
import com.order.CustomerPriceDateComparator;

import Customer_Management_System.Customer;
import Customer_Management_System.ServicePlan;

public class CustomerManagmentSystem {
	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			List<Customer> customerList = new ArrayList<Customer>();
			boolean exit = false;
			while (!exit) {
				System.out.println("Welcome to the Customer Management System!");
				System.out.println("Please select an option:");
				System.out.println("1. Sign up");
				System.out.println("2. Sign in");
				System.out.println("3. Change Password");
				System.out.println("4. View all customers");
				System.out.println("5. Search for a customer by email");
				System.out.println("6. Remove a customer by email");
				System.out.println("7. Unsubscribe");
				System.out.println("8. Change plan");
				System.out.println("9. Delete plan");
				System.out.println("10. Sort by email");
				System.out.println("11. Sort by price");
				System.out.println("12. Sort by	date and price");
				System.out.println("13. Sort by name and price");

				System.out.println("0. Exit");
				try {
					switch (sc.next()) {
					case "1":
						System.out.println("----Service Plans----");
						for(ServicePlan p:ServicePlan.values())
						{
							System.out.println(p);
						}
						System.out.println("---------------------");
						System.out.println("Enter Details : firstName, lastName, email, password, dob(yr-mon-day), plan");
						Customer validCustomer = validateAllInputs(sc.next(), sc.next(), sc.next(), sc.next(),
								sc.next(), sc.next(), customerList);
						customerList.add(validCustomer);
						break;
					case "2":
						System.out.println("Enter email and password");
						int index=validateEmailPass(sc.next(),sc.next(),customerList);
						System.out.println("You are logedin");
						System.out.println(customerList.get(index));
						break;
						
					case "3":
						System.out.println("Enter email and old password");
						index=validateEmailPass(sc.next(),sc.next(),customerList);
						System.out.println("Enter new password");
						customerList.get(index).setPassword(sc.next());
						System.out.println("Done");
					    break;
					case "4":
						System.out.println("Customer List");
						for (Customer c : customerList)
							System.out.println(c);
						break;
					case "5":
						System.out.println("Enter email");
						validateEmail(sc.next(),customerList);
						break;
					case "6":
						System.out.println("Enter email to delete customer");
						removeCustomer(sc.next(),customerList);
						break;
						
					case "7":
					    System.out.println("Enter email and password");
					    index= validateEmailPass(sc.next(),sc.next(),customerList);
					    customerList.get(index).setServicePlan(null);
						customerList.get(index).setRegistrationAmount(0.0);
						System.out.println("Successfully unsubscribed from the plan.");
						break;
					case "8":
					    System.out.println("Enter email and password");
					    index= validateEmailPass(sc.next(),sc.next(),customerList);
					    System.out.println("Enter new plan");
					    ServicePlan plan=parseAndValidatePlan(sc.next());
						customerList.get(index).setRegistrationAmount(plan.getRegistrationAmount());
						System.out.println("Your plan is changed Successfully.");
						break;
					case "9":
					    System.out.println("Enter new plan");
					    removeServicePlan(sc.next(),customerList);
						System.out.println("Removed Successfully.");
						break;
					case "10":
						System.out.println("Sorted by email");
						Collections.sort(customerList);
						break;
					case "11":
						System.out.println("Sorted by Price");
						Collections.sort(customerList,new CustomerPriceComparator());
						break;
					case "12":
						System.out.println("Sorted by Date and Price");
						Collections.sort(customerList,new CustomerPriceDateComparator());
						break;
					case "13":
						System.out.println("Sorted by First name and price");
						Collections.sort(customerList, new Comparator<Customer>(){

							@Override
							public int compare(Customer o1, Customer o2) {
							int retVal=o1.getFirstName().compareTo(o2.getFirstName());
							if(retVal==0)
								if(retVal==0)
									if(o1.getRegistrationAmount() < o2.getRegistrationAmount())
										return -1;
									if(o1.getRegistrationAmount() == o2.getRegistrationAmount())
										return 0;
								return 1;
							}
							
						});
					case "0":
						exit = true;
						break;
					default: System.out.println("Invalid Option!");
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
