package cci.algo;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.RunnerException;

/**
 * TODO: Check apache impl. tune bigint impl
 * 
 * @author asimov
 *
 */
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 2, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(value = 1, warmups = 1)
public class Factorial {

	private static int val = 20;

	public static void main(String[] args) throws RunnerException, IOException {
		System.out.println(getFactorial1(val));
		System.out.println(getFactorial2(val));
		System.out.println(getFactorial3(val));
		System.out.println(factorial(val));
		Main.main(args);
	}

	@Benchmark
	public void bench1IfRec() {
		getFactorial1(val);
	}

	@Benchmark
	public void bench2SwitchRec() {
		getFactorial2(val);
	}

	@Benchmark
	public void bench3SingleLoop() {
		getFactorial3(val);
	}
	
	@Benchmark
	public void bench4RealDealBigInt() {
		factorial(val);
	}

	public static long getFactorial1(int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1 | n == 2) {
			return n;
		}

		return n * getFactorial1(n - 1);
	}

	public static long getFactorial2(int n) {
		switch (n) {
		case 0:
			return 1;
		case 1:
			return 1;
		case 2:
			return 2;
		default:
			return n * getFactorial2(n - 1);
		}

	}

	public static long getFactorial3(int n) {
		long val = 1;
		for (long i = 1; i <= n; i++) {
			val *= i;
		}
		return val;

	}
	/*
	 * @see http://www.luschny.de/math/factorial/FastFactorialFunctions.htm
	 */
	static BigInteger factorial(long n) { return recfact(1, n); }
	static BigInteger recfact(long start, long n) {
	    long i;
	    if (n <= 16) { 
	    	BigInteger r = BigInteger.valueOf(start);
	        for (i = start + 1; i < start + n; i++) r = r.multiply(BigInteger.valueOf(i));
	        return r;
	    }
	    i = n / 2;
	    return recfact(start, i).multiply(recfact(start + i, n - i));
	}
	
}
