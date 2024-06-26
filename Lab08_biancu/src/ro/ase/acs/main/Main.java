package ro.ase.acs.main;

import ro.ase.acs.classes.SyncedThread;
import ro.ase.acs.classes.UnsyncedThread;

public class Main {

	public static void main(String[] args) {
		
//		UnsyncedThread t1 = new UnsyncedThread("Thread 1");
//		// t1.start() fir de executie secundar => in paralel
//		t1.start();
//		
//		// t1.run() ar duce la executia in firul principal => sincrona
//		
//		UnsyncedThread t2 = new UnsyncedThread("Thread 2");
//		t2.start();
		
		SyncedThread t3 = new SyncedThread("Thread 3");
		// nu avem metoda start()
		// cream obiect anonim de tip Thread pt a folosi metoda start()
		new Thread(t3).start();
		
		SyncedThread t4 = new SyncedThread("Thread 4");
		new Thread(t4).start();
		
		// Runnable este o interfata functionala => permite folosirea expresiilor lambda
		// thread-ul o sa mearga in paralel cu celelalte thread-uri (t3 si t4 merg secvential)
		Runnable r = () -> {
			try {
				Thread.sleep(2000);
				// exceptiile aruncate in fire de executie secundare se trateaza in acele fire
				// nu afecteaza executia firului principal
				//int x = 2 / 0;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Message from another thread");
		};
		new Thread(r).start();
		
		System.out.println("Main ended");
	}

}
