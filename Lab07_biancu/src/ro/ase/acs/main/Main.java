package ro.ase.acs.main;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import ro.ase.acs.classes.Sum;
import ro.ase.acs.interfaces.BinaryOperator;
import ro.ase.acs.interfaces.Moveable;
import ro.ase.acs.interfaces.UnaryOperator;

public class Main {
	
	public static int transform(int x) {
		
		if (x % 2 == 0) {
			return x / 2;
		} else {
			return 2 * x;
		}
	}

	public static void main(String[] args) {
		
		Random random = new Random();
		BinaryOperator operator;
		
		// DEMONSTRATIE FUNCTIE VIRTUALA -- CONTROLAM COMPORTAMENTUL LA RUNTIME
		if (random.nextInt() % 2 == 0) {
			operator = new Sum();
		} else {
			// CLASA ANONIMA
			// adresa clasei anonime salvata in operator
			// se face upcasting (clasa anonima derivata din BinaryOperator)
			operator = new BinaryOperator() {
				
				@Override
				public double operate(double operand1, double operand2) {
					return operand1 - operand2;
				}
			};
		}
		
		//BinaryOperator operator = new Sum();
		System.out.println(operator.operate(5, 3));
		
		// OBIECT ANONIM
		new Sum().operate(3, 2);
		
		// PROGRAMARE FUNCTIONALA
		// EXPRESII LAMBDA <=> FUNCTII ANONIME
		operator = (o1, o2) -> o1 * o2;					// operator salveaza adresa functiei anonime	
		System.out.println(operator.operate(3, 5));		// apelul functiei anonime
		
		operator = (x, y) -> {
			double sum = x + y;
			return sum / 2;
		};
		
		Moveable m = () -> System.out.println("The car is moving");
		m.move();
		
		// pt functiile care au un singur parametru, parantezele nu sunt obligatorii
		//UnaryOperator op = o -> ++o;
		UnaryOperator op = (o) -> ++o;
		System.out.println(op.operate(5));
		
		List<Integer> list = Arrays.asList(3, 4, 1, 5, 1, 8, 12);
		long nb = list.stream().filter(e -> (e % 2 == 0)).count();
		System.out.println(nb);
		
		List<Integer> newList = list.stream().distinct().
									filter(e -> e < 8).sorted().collect(Collectors.toList());
		for (Integer i : newList) {
			System.out.println(i);
		}
		
		List<String> stringList = Arrays.asList("something", "something else", "a", "ab", "abc");
		String result = stringList.stream().
							filter(s -> s.length() > 2).collect(Collectors.joining(", "));
		System.out.println(result);
		
		list.stream().distinct().sorted().map(e -> e * e).forEach(e -> System.out.println(e));
		
		// REFERINTA DE METODA
		random.ints().limit(5).sorted().forEach(System.out::println);
		
		list.stream().map(Main::transform).forEach(System.out::println);
		
		// PARALLEL STREAM
		String filteredString = stringList.parallelStream().
								filter(e -> e.startsWith("a")).collect(Collectors.joining(", "));
		System.out.println(filteredString);
	}

}
