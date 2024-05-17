package inheritance;

public class Worker {
	protected String name;
	protected String birthDate; // MM-DD-YYYY
	protected String endDate;

	public Worker(String name, String birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}

	public int getAge() {
		int currYear = 2024;
		int birthYear = Integer.parseInt(birthDate.substring(6));

		return currYear - birthYear;
	}

	public double collectPay() {
		return 100.0;
	}

	public void terminate(String endDate) {
		this.endDate = endDate;
	}
}
