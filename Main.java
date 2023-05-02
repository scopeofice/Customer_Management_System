package Tester;

import static Customer_Management_System.ValidationRules.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
				System.out.println("7. Change plan");
				System.out.println("0. Exit");
				try {
					switch (sc.next()) {
					case "1":
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
					    ServicePlan plan=parseAndValidatePlan(sc.next());
						customerList.get(index).setRegistrationAmount(plan.getRegistrationAmount());
						System.out.println("Your plan is changed Successfully.");
						break;
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
