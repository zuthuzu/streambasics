import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Anton Domin on 2020-03-01
 */
@SuppressWarnings({"OptionalGetWithoutIsPresent", "SpellCheckingInspection"})
public class Main {
	static List<Integer> values = new ArrayList<>();

	public static void main(String[] args) {

		for (int i = 0; i < 50; i++) values.add((int) (Math.random() * 20 - 10));

		streamtasks1();

		arraylisttask1();

	}

	private static void streamtasks1() {
		System.out.println("Raw array: " + values.toString());

		System.out.println("Inverted array: " + Arrays.toString(getstream().map(i -> i * -1).toArray()));

		System.out.println("Average with built-in function: " + getstream().average());
		System.out.println("Manual average: " + getstream().sum() / getstream().count());

		int minvalue = getstream().min().getAsInt();

		System.out.println("[Two-part search] Minimal element is " + minvalue);

		System.out.print("[Two-part search] This element can be found at following indices: ");
		List<Integer> minvalues = IntStream.range(0, values.size()).filter(i -> values.get(i) == minvalue).boxed().collect(Collectors.toList());
		minvalues.forEach(i -> System.out.print(i + ", "));
		System.out.println("and that's it.");

		int minIdx = IntStream.range(0,values.size()).reduce((i,j) -> values.get(i) > values.get(j) ? j : i).getAsInt();
		System.out.println("[One-pass search attempt #1] Minimal value is " + values.get(minIdx) + ", found at index " + minIdx);

		class Pair {
			int index;
			int value;

			public Pair(int index, int value) {
				this.index = index;
				this.value = value;
			}
		}

		List<Pair> valuesAsPair = new ArrayList<>();
		IntStream.range(0,values.size()).forEach(i -> valuesAsPair.add(new Pair(i, values.get(i))));

		Pair minPair = valuesAsPair.stream().reduce((i, j) -> i.value > j.value ? j : i).get();
		System.out.println("[One-pass search attempt #2] Minimal value is " + minPair.value + ", found at index " + minPair.index);

		System.out.println("Total amount of 0s: " + getstream().filter(i -> i == 0).count());

		System.out.println("Total amount of positive values: " + getstream().filter(i -> i > 0).count());
	}

	private static void arraylisttask1() {
		Map<Integer, Integer> valueHits = new HashMap<>();

		getstream().boxed().forEach(i -> valueHits.put(i, valueHits.getOrDefault(i, 0) + 1));

		valueHits.keySet().stream().sorted().forEach(i -> System.out.println("Value " + i + " is encountered " + valueHits.get(i) + " times"));

	}

	private static IntStream getstream(){ return values.stream().mapToInt(i -> i); }
}
