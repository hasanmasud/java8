package online.smartcompute.demo.java8;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Demo2 {
	public static void main(String... args) {
		// System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
		// "0");

		Map<String, List<Integer>> m = IntStream.range(0, 128).parallel().boxed()
				.collect(groupingBy(i -> Thread.currentThread().getName()));

		m.forEach((k, v) -> System.out.printf("%s -> {size=%s} %s \n", k, v.size(), v));

	}

}
