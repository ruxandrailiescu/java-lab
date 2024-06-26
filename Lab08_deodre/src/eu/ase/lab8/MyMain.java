package eu.ase.lab8;

public class MyMain {

	public static void main(String[] args) {
		
		ThreadNonSync thread1 = new ThreadNonSync("thread1");
		ThreadNonSync thread2 = new ThreadNonSync("thread2");
		
		System.out.println("Non Synchronized: ");
		
		try {
			
			thread1.start();
			thread2.start();
			
			// pause the execution of the current thread until
			// the thread on which join() was called finished executing
			
			// ensures that the main thread waits for both
			// thread1 and thread2 to finish executing before it continues
			thread1.join();
			thread2.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ThreadSync thread1s = new ThreadSync("thread1");
		ThreadSync thread2s = new ThreadSync("thread2");
		
		System.out.println("Synchronized: ");
		
		thread1s.start();
		thread2s.start();
		
		try {
			
			thread1s.join();
			thread2s.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main program finished");
	}

}
