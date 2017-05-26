package online.smartcompute.demo.java8;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Demo1 {

	public static void main(String... args) {
		List<String> words = words();

		long count1 = words.stream().filter(s -> !s.isEmpty()).count();
		long count2 = words.stream().filter(s -> !s.isEmpty()).mapToLong(e -> 1L).sum();
		long count3 = words.stream().filter(s -> !s.isEmpty()).mapToLong(e -> 1L).reduce(0, Long::sum);
		long count4 = words.stream().parallel().filter(s -> !s.isEmpty()).mapToLong(e -> 1L).reduce(0, Long::sum);

		String js1 = words.stream().filter(s -> !s.isEmpty()).reduce("", (a, b) -> a + " " + b);
		Optional<String> js2 = words.stream().filter(s -> !s.isEmpty()).reduce((a, b) -> a + " " + b);
		String js3 = words.stream().filter(s -> !s.isEmpty()).collect(joining(" "));

		System.out.println(count1);
		System.out.println(count2);
		System.out.println(count3);
		System.out.println(count4);

		System.out.println(js1);
		System.out.println(js2);
		System.out.println(js3);

	}

	public static List<String> words() {
		String[] words = { "I", "", "am a", "java developer" };
		return Arrays.asList(words);
	}
}
