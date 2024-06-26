package ro.ase.acs.classes;

import java.util.Random;

public class UnsyncedThread extends Thread {
	
	// firele de executie partajeaza aceeasi zona de memorie
	// atributele sunt statice => doua instante ale clasei partajeaza aceleasi variabile
	private static Random random = new Random();
	private static int a = 0;
	private static int b = 0;
	
	public UnsyncedThread(String name) {
		super(name);
	}
	
	private void method() {
		System.out.printf("%s: a = %d, b = %d\r\n", getName(), a, b);
		a++;
		try {
			// genereaza un intreg pozitiv intre 0 si 3000 <=> intre 0 si 3 secunde
			Thread.sleep(random.nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		b++;
	}

	// metoda run() nu este o metoda abstracta in Thread
	
	@Override
	public void run() {
		// codul care se executa in alt fir de executie fata de cel principal
		super.run();
		for (int i = 0; i < 3; i++) {
			method();
		}
	}	
	
}
