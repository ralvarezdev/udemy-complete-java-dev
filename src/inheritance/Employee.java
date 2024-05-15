package oop;

public class InheritanceEmployee extends InheritanceWorker {
	protected long employeeId;
	protected String hireDate;

	public InheritanceEmployee(String name, String birthDate, long employeeId, String hireDate) {
		super(name, birthDate);
		this.employeeId = employeeId;
		this.hireDate = hireDate;
	}
}
