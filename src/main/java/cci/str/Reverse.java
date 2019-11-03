package cci.str;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.openjdk.jmh.Main;
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
public class Reverse {

	static StringBuilder str = new StringBuilder();
	static {
		IntStream.range(1, 150000).forEach(str::append);
	}
	public static void main(String[] args) throws RunnerException, IOException {
		Main.main(args);
	}
	
	@Benchmark
	public static void bench1() {
		reverse(str.toString().toCharArray());
	}
	
	@Benchmark
	public static void bench2() {
		reverseUsingXOR(str.toString().toCharArray());
	}
	
	@Benchmark
	public static void bench3() {
		str.reverse();
	}

	private static final char[] reverse(char[] x) {
		char temp;
		for (int u = 0, l = x.length - 1; u < x.length >>> 1; u++, l--) {
			temp = x[u];
			x[u] = x[l];
			x[l] = temp;
		}
		return x;
	}

	public static char[] reverseUsingXOR(char[] str) {
		int low = 0;
		int high = str.length - 1;

		while (low < high) {
			str[low] = (char) (str[low] ^ str[high]);
			str[high] = (char) (str[low] ^ str[high]);
			str[low] = (char) (str[low] ^ str[high]);
			low++;
			high--;
		}

		return str;

	}
}
