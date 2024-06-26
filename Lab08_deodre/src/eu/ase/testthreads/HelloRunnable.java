package eu.ase.testthreads;

public class HelloRunnable implements Runnable {

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println("Hello " + name);
	}

}
