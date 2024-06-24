package eu.ase.lab7;

public class Sum implements BinaryOperator {

	@Override
	public int operate(int operand1, int operand2) {
		return operand1 + operand2;
	}
	
}
