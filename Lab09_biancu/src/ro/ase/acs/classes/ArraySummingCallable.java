package ro.ase.acs.classes;

import java.util.concurrent.Callable;

public class ArraySummingCallable implements Callable<Long> {

	private int[] array;
	private int startIndex;
	private int endIndex;
	
	public ArraySummingCallable(int[] array, int startIndex, int endIndex) {
		// +++ validari
		this.array = array;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	@Override
	public Long call() throws Exception {
		long sum = 0;
		for (int i = startIndex; i < endIndex; i++) {
			sum += array[i];
		}
		// transformarea din primitiva in clasa wrapper aferenta
		// se va face printr-un cast implicit
		return sum;
	}
	
}
