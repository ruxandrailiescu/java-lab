package ro.ase.acs.classes;

import java.util.Random;

public class SyncedThread implements Runnable {
	
	private static Random random = new Random();
	private static int a = 0;
	private static int b = 0;
	private static Object lock = new Object();
	
	// nu avem camp mostenit pt nume
	private String name;
	
	public SyncedThread(String name) {
		this.name = name;
	}
	
	// nu putem folosi synchronized la nivelul metodei
	// pt ca avem doua instante diferite
	// blocarea trebuie sa fie la nivelul atributelor statice
	private /*synchronized*/ void method() {
		
		synchronized (lock) {
			
			System.out.printf("%s: a = %d, b = %d\r\n", name, a, b);
			a++;
			try {
				Thread.sleep(random.nextInt(3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			b++;
		}
	}
	
	// Runnable fiind interfata, metoda run() este abstracta
	// si este obligatoriu sa facem override
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			method();
		}
	}

}
