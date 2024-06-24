package eu.ase.lab7;

import java.util.Random;

public class MainB {

	public static void main(String[] args) {
		
		Random rand = new Random();
		BinaryOperator operator;
		
		if (rand.nextInt() % 2 == 0) {
			operator = new Sum();
		} else {
			
			// anonymous type (class)
			operator = new BinaryOperator() {
				
				@Override
				public int operate(int operand1, int operand2) {
					return operand1 - operand2;
				}
			};
		}
		
		System.out.println(operator.operate(5, 3));
		
		// anonymous object
		new Sum().operate(3, 2);
		
		operator = (o1, o2) -> o1 * o2;
		System.out.println(operator.operate(3, 5));
	}

}
