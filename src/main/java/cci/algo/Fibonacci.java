package cci.algo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.RunnerException;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 2, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(value = 1, warmups = 1)
public class Fibonacci {

	public static void main(String[] args) throws RunnerException, IOException {
		System.out.println(fibi2(20));
		System.out.println(fibo(20));
	}

	@Benchmark
	public static final void benchFiboS() {
		fibo(40);

	}
	
	@Benchmark
	public static final void benchFiboIf() {
		fibof(40);

	}
	
	@Benchmark
	public static final void benchFibi() {
		fibi(40);

	}
	
	@Benchmark
	public static final void benchFibi2() {
		fibi2(40);

	}

	
	/**
	 * @param n
	 * @return
	 */
	private static final int fibo(int n) {
		switch (n) {
		case 0: 
			return 0;
		case 1:
			return 1;

		default:
			return fibo(n - 1) + fibo(n - 2);
		}
	}

	private static final int fibof(int n) {
		if (n < 1) {
			return n;
		}

		return fibo(n - 1) + fibo(n - 2);
	}
	
	private static final int[] fibi(int n) {
		int[] fib = new int[n];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < n; ++i) {
            fib[i] = fib[i - 2] + fib[i - 1];
        }
        return fib;
	}
	
	private static final int fibi2(int n) {
		if (n <= 1) return n;
		int n1=0,n2=1,n3;
        for (int i = 2; i <= n; i++) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
        }
        return n2;
	}
	
}
