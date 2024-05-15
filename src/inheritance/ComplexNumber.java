package oop;

public class InheritanceComplexNumber {
	private double real;
	private double imaginary;

	public InheritanceComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public double getReal() {
		return real;
	}

	public double getImaginary() {
		return imaginary;
	}

	public void add(double real, double imaginary) {
		this.real += real;
		this.imaginary += imaginary;
	}

	public void add(InheritanceComplexNumber complexNumber) {
		this.real += complexNumber.getReal();
		this.imaginary += complexNumber.getImaginary();
	}

	public void subtract(double real, double imaginary) {
		this.real -= real;
		this.imaginary -= imaginary;
	}

	public void subtract(InheritanceComplexNumber complexNumber) {
		this.real -= complexNumber.getReal();
		this.imaginary -= complexNumber.getImaginary();
	}
}
