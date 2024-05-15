package oop;

public class InheritanceCustomer {
	private String name;
	private double creditLimit;
	private String emailAddress;

	public InheritanceCustomer(String name, double creditLimit, String emailAddress) {
		this.name = name;
		this.creditLimit = creditLimit;
		this.emailAddress = emailAddress;
	}

	public InheritanceCustomer(String name, String emailAddress) {
		this(name, 100, emailAddress);
	}

	public InheritanceCustomer() {
		this("Ronald", "ronald@gmail.com");
	}

	public String getName() {
		return name;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
}
