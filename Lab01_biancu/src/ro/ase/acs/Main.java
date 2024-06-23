package ro.ase.acs;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello, World!");
		
		// in C++ s-ar fi chemat constructorul implicit
		// in Java, clasele sunt de tip referential => se comporta ca un pointer
		// => valoarea lui student este null
		//Student student;
		
		// se apeleaza constructorul implicit
		Student student = new Student();
		System.out.println(student.getGrade());
		
		// 9.5 ar fi interpretat ca double => noi avem tip de data float
		Student student2 = new Student("Ionel", 9.5f);
		System.out.println(student2.getGrade());
		
		// shallow copy => ambele obiecte pointeaza spre aceeasi zona de memorie in heap
		// => solutie: clonare
		Student student3 = student2;
		student3.setName("Gigel");
		System.out.println(student2.getName());
		
		// constructorul de copiere nu este definit default ca in C++
		
		// deep copy
		Student student4 = student2.myClone();
		student4.setName("Alex");
		System.out.println(student2.getName());
		
		// tipuri de date in Java => primitive (salvate in stack) si referentiale (adresa salvata in stack, valoarea in heap)
		// String este o clasa => tip referential (initializate implicit cu null)
		
	}

}
