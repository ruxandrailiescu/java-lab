package eu.ase.testthreads;

public class Main {

	public static void main(String[] args) {
		
		HelloThread helloThread = new HelloThread("1. Threads Java 1.1 -> 17");
		
		helloThread.start();
		
		HelloRunnable helloRunnable = new HelloRunnable();
		Thread tw_helloRunnable = new Thread(helloRunnable, "2. Threads Java 1.1 -> 17");
		
		tw_helloRunnable.start();
		
		// Starting with Java 7
		
		Runnable runnableTask = new Runnable() {
			
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println("Hello " + name);
			}
		};
		Thread tw_runnableTask = new Thread(runnableTask, "3. Threads Java 7 -> 17");
		
		tw_runnableTask.start();
		
		// Starting with Java 8
		
		Runnable newRunnableTask = () -> {
			String name = Thread.currentThread().getName();
			System.out.println("Hello " + name);
		};
		Thread tw_newRunnableTask = new Thread(newRunnableTask, "4. Threads Java 8 -> 17");
		
		tw_newRunnableTask.start();
	}

}
