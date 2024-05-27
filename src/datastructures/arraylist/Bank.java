package datastructures.arraylist;

import java.util.ArrayList;

public class Bank {
	@SuppressWarnings("unused")
	private String name;
	private ArrayList<Branch> branches;

	public Bank(String name) {
		this.name = name;
		this.branches = new ArrayList<>();
	}

	public boolean addBranch(String name) {
		if (findBranch(name) != null)
			return false;

		branches.add(new Branch(name));
		return true;
	}

	public boolean addCustomer(String branchName, String customerName, double transaction) {
		Branch branch = findBranch(branchName);
		if (branch == null)
			return false;

		return branch.newCustomer(customerName, transaction);
	}

	public boolean addCustomerTransaction(String branchName, String customerName, double transaction) {
		Branch branch = findBranch(branchName);
		if (branch == null)
			return false;

		return branch.addCustomerTransaction(customerName, transaction);
	}

	private Branch findBranch(String name) {
		for (Branch branch : branches)
			if (branch.getName().equals(name))
				return branch;

		return null;
	}

	public boolean listCustomers(String branchName, boolean printTransactions) {
		Branch branch = findBranch(branchName);
		if (branch == null)
			return false;

		System.out.println("Customer details for branch " + branchName);
		int nCustomer = 1;

		for (Customer customer : branch.getCustomers()) {
			System.out.println("Customer: " + customer.getName() + "[" + nCustomer++ + "]");

			if (printTransactions) {
				System.out.println("Transactions");
				int nTransaction = 1;

				for (Double transaction : customer.getTransactions())
					System.out.println("[" + nTransaction++ + "] Amount " + transaction);
			}
		}
		return true;
	}
}
