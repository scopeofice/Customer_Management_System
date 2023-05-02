package Customer_Management_System;

import java.time.LocalDate;

public class Customer {
	
	private int CustomerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private double registrationAmount;
    private LocalDate dob;
    private ServicePlan plan;

    private static int idCounter = 0;

    public Customer(String firstName, String lastName, String email, String password, LocalDate dob, ServicePlan plan) {
        this.CustomerId = ++idCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.registrationAmount = plan.getRegistrationAmount();
        this.dob = dob;
        this.plan = plan;
    }
    
    public Customer(String email) {
    	this.email=email;
    }


    public int getCustomerId() {
        return CustomerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getRegistrationAmount() {
        return registrationAmount;
    }

    public LocalDate getDob() {
        return dob;
    }

    public ServicePlan getPlan() {
        return plan;
    }
    public void setServicePlan(ServicePlan plan) {
        this.plan = plan;
    }

	@Override
	public String toString() {
		return "CustomerInfo [id=" + CustomerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", registrationAmount=" + registrationAmount + ", dob=" + dob + ", plan="+ plan + "]";
	}
	public void setRegistrationAmount(double price) {
   	 this.registrationAmount=price;
   }
	
	//for PK base equality testing
	@Override
	public boolean equals(Object o) {
		System.out.println("In Cust Equals");
		if(o instanceof Customer)
			return this.email.equals(((Customer)o).email);
		return false;
	}
    
}
