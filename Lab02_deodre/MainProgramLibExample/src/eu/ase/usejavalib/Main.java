package eu.ase.usejavalib;

import eu.ase.lab2.Student;

public class Main {

	public static void main(String[] args) {
		
		Student student = new Student(2, "John", new float[] {5.6f, 7.8f});
		
		System.out.println(student.toString());
	}

}
