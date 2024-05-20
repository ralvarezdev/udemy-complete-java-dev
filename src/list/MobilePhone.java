package list;

import java.util.ArrayList;

public class MobilePhone {
	@SuppressWarnings("unused")
	private String myNumber;
	private ArrayList<Contact> myContacts;

	public MobilePhone(String phoneNumber) {
		this.myNumber = phoneNumber;
		this.myContacts = new ArrayList<>();
	}

	public boolean addNewContact(Contact contact) {
		boolean isNew = findContact(contact) == -1;

		if (isNew)
			myContacts.add(contact);

		return isNew;
	}

	public boolean updateContact(Contact oldContact, Contact newContact) {
		boolean doesExist = removeContact(oldContact);
		myContacts.add(newContact);

		return doesExist;
	}

	public boolean removeContact(Contact contact) {
		boolean doesExist = findContact(contact) != -1;
		myContacts.remove(contact);

		return doesExist;
	}

	private int findContact(Contact contact) {
		return findContact(contact.getName());
	}

	private int findContact(String contactName) {
		for (Contact contact : myContacts)
			if (contact.getName() == contactName)
				return 0;

		return -1;
	}

	public Contact queryContact(String contactName) {
		for (Contact contact : myContacts)
			if (contact.getName() == contactName)
				return contact;

		return null;
	}

	public void printContacts() {
		System.out.println("Contact List:");
		int i = 1;

		for (Contact contact : myContacts)
			System.out.println("" + i++ + ". " + contact.getName() + " -> " + contact.getPhoneNumber());
	}
}
