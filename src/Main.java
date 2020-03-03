import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Anton Domin on 2020-03-01
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
public class Main {
	static List<Integer> values = new ArrayList<>();

	public static void main(String[] args) {

		for (int i = 0; i < 50; i++) values.add((int) (Math.random() * 20 - 10));

		System.out.println("Average with built-in function: " + getstream().average());
		System.out.println("Manual average: " + getstream().sum() / getstream().count());

		int minvalue = getstream().min().getAsInt();

		System.out.println("[Two-part search] Minimal element is " + minvalue);

		System.out.print("[Two-part search] This element can be found at following indices: ");
		List<Integer> minvalues = IntStream.range(0, values.size()).filter(i -> values.get(i) == minvalue).boxed().collect(Collectors.toList());
		minvalues.forEach(i -> System.out.print(i + ", "));
		System.out.println("and that's it.");

		int minIdx = IntStream.range(0,values.size()).reduce((i,j) -> values.get(i) > values.get(j) ? j : i).getAsInt();
		System.out.println("[One-pass search attempt] Minimal index is " + minIdx);

		System.out.println("Total amount of 0s: " + getstream().filter(i -> i == 0).count());

		System.out.println("Total amount of positive values: " + getstream().filter(i -> i > 0).count());

		System.out.println("Raw array: " + values.toString());

		//nope, lazy operation delays getting stream until after array is cleared
		//IntStream intstream = getstream();
		//values.clear();

		System.out.println("Inverted array: " + Arrays.toString(getstream().map(i -> i * -1).toArray()));

	}

	private static IntStream getstream(){ return values.stream().mapToInt(i -> i); }
}
