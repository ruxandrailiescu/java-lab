package eu.airplaneexpenses.main;

import eu.airplaneexpenses.classes.Airplane;
import eu.airplaneexpenses.classes.AirplaneExpenses;

public class Main {

	public static void main(String[] args) {
		
		Airplane a1 = new Airplane(1, "INS", 203000);
		
		AirplaneExpenses e1 = new AirplaneExpenses(200.5, 3.5, 250.1, 310.12, "28/06/2024");
		AirplaneExpenses e2 = new AirplaneExpenses(400.5, 3.69, 250.1, 210.12, "29/06/2024");
		AirplaneExpenses e3 = new AirplaneExpenses(250.5, 3.5, 250.1, 310.12, "30/06/2024");
		
		try {
			a1.addExpense(e1);
			a1.addExpense(e2);
			a1.addExpense(e3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		a1.serialize("C:\\Users\\Admin\\Desktop\\CSIE\\JAVA\\java-lab\\ExamN3\\airplane_" + a1.getId() + ".bin");
		
//		Airplane a2 = Airplane.deserialize("C:\\Users\\Admin\\Desktop\\CSIE\\JAVA\\java-lab\\ExamN3\\airplane_1.bin");
//		System.out.println(a2);
		
		Airplane a2 = new Airplane(2, "INS2", 50450);
		
		AirplaneExpenses e4 = new AirplaneExpenses(100.5, 10.3, 310.23, 2333.2, "23/03/2023");
		AirplaneExpenses e5 = new AirplaneExpenses(100.5, 10.3, 310.23, 2333.2, "28/03/2023");
		
		try {
			a2.addExpense(e4);
			a2.addExpense(e5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		a2.serialize("C:\\Users\\Admin\\Desktop\\CSIE\\JAVA\\java-lab\\ExamN3\\airplane_" + a2.getId() + ".bin");
	}

}
