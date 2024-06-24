package eu.ase.lab7;

// Functional interfaces have exactly one abstract method
// They can have default and static methods

@FunctionalInterface
public interface BinaryOperator {
	
	public int operate(int operand1, int operand2);
}
