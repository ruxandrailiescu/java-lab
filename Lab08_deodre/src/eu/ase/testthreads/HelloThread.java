package eu.ase.testthreads;

public class HelloThread extends Thread {
	
	public HelloThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println("Hello " + name);
	}
	
}
