package ro.ase.acs.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import ro.ase.acs.classes.ArraySummingCallable;
import ro.ase.acs.classes.ArraySummingThread;

public class Main {

	public static void main(String[] args) {
		
		final int NB_OF_ELEMENTS = 500_000_000;
		final int NB_OF_THREADS = 4;
		int[] array = new int[NB_OF_ELEMENTS];
		
		for (int i = 0; i < NB_OF_ELEMENTS; i++) {
			array[i] = i + 1;
		}
		
		long sum = 0;
		long tstart = System.currentTimeMillis();
		
		for (int i = 0; i < NB_OF_ELEMENTS; i++) {
			sum += array[i];
		}
		
		long tstop = System.currentTimeMillis();
		System.out.printf("Sequential sum = %d computed in %d ms\r\n", sum, (tstop - tstart));
		
		sum = 0;
		tstart = System.currentTimeMillis();
		
		// cream un vector de obiecte pt a avea
		// configurabil nr de thread-uri folosite
		ArraySummingThread[] threads = new ArraySummingThread[NB_OF_THREADS];
		for (int i = 0; i < NB_OF_THREADS; i++) {
			threads[i] = new ArraySummingThread(array, 
							i * NB_OF_ELEMENTS / NB_OF_THREADS, 
							(i + 1) * NB_OF_ELEMENTS / NB_OF_THREADS);
			threads[i].start();
		}
		
		// start() si join() in for-uri diferite
		// altfel ar fi echivalentul prelucrarii secvential
		
		for (int i = 0; i < NB_OF_THREADS; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < NB_OF_THREADS; i++) {
			// nu este garantat ca atunci cand apelez getSum()
			// firele de executie au terminat calculul
			sum += threads[i].getSum();
		}
		
		tstop = System.currentTimeMillis();
		System.out.printf("Thread array sum = %d computed in %d ms\r\n", sum, (tstop - tstart));
		
		sum = 0;
		tstart = System.currentTimeMillis();
		
		ExecutorService threadPool =
				Executors.newFixedThreadPool(NB_OF_THREADS);
		
		threads = new ArraySummingThread[NB_OF_THREADS];
		for (int i = 0; i < NB_OF_THREADS; i++) {
			threads[i] = new ArraySummingThread(array,
					i * NB_OF_ELEMENTS / NB_OF_THREADS,
					(i + 1) * NB_OF_ELEMENTS / NB_OF_THREADS);
			threadPool.execute(threads[i]);
		}
		threadPool.shutdown();
		
		try {
			threadPool.awaitTermination(30, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < NB_OF_THREADS; i++) {
			sum += threads[i].getSum();
		}
		
		tstop = System.currentTimeMillis();
		System.out.printf("Threadpool sum = %d computed in %d ms\r\n", sum, (tstop - tstart));
		
		sum = 0;
		tstart = System.currentTimeMillis();
		
		ArraySummingCallable[] callables = new ArraySummingCallable[NB_OF_THREADS];
		
		List<Future<Long>> results = new ArrayList<>();
		threadPool = Executors.newFixedThreadPool(NB_OF_THREADS);
		
		for (int i = 0; i < NB_OF_THREADS; i++) {
			callables[i] = new ArraySummingCallable(array,
					i * NB_OF_ELEMENTS / NB_OF_THREADS,
					(i + 1) * NB_OF_ELEMENTS / NB_OF_THREADS);
//			try {
//				// metoda call() asteapta pana la terminarea firului
//				// de executie pt a returna un long (aceeasi discutie ca si start() + join())
//				// solutie => adaugam instanta de callable intr-un Threadpool => Future<Long>
//				sum += callables[i].call();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			results.add(threadPool.submit(callables[i]));
		}
		threadPool.shutdown();
		
		for (Future<Long> f : results) {
			try {
				sum += f.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		tstop = System.currentTimeMillis();
		System.out.printf("Callable sum = %d computed in %d ms\r\n", sum, (tstop - tstart));

	}

}
