import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

public class QuickSorter {
	private QuickSorter() {
	}

	public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy strategy)
			throws NullPointerException {
		if (list.isEmpty() || strategy == null) {
			throw new NullPointerException("One of the arguments is invalid");
		}
		long startTime = System.nanoTime();
		if (strategy == PivotStrategy.FIRST_ELEMENT) {
			quickSort_first(list, 0, list.size() - 1);
		} else if (strategy == PivotStrategy.RANDOM_ELEMENT) {
			quickSort_random(list, 0, list.size() - 1);
		} else if (strategy == PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS) {
			quickSort_medianOfRandomThree(list, 0, list.size() - 1);
		} else if (strategy == PivotStrategy.MEDIAN_OF_THREE_ELEMENTS) {
			quickSort_medianOfThree(list, 0, list.size() - 1);
		}
		long finishTime = System.nanoTime();
		Duration elapsedTime = Duration.ofNanos(finishTime - startTime);
		return elapsedTime;
	}

	private static <E extends Comparable<E>> void quickSort_first(ArrayList<E> list, int low, int high) {
		if (low >= high) {
			return;
		}
		swap(list, low, high);
		int pivot = quickSort(list, low, high);
		quickSort_first(list, low, pivot - 1);
		quickSort_first(list, pivot + 1, high);
	}

	private static <E extends Comparable<E>> void quickSort_random(ArrayList<E> list, int low, int high) {
		if (low >= high) {
			return;
		}
		int randomPivot = rand(low, high);
		swap(list, randomPivot, high);
		int pivot = quickSort(list, low, high);
		quickSort_random(list, low, pivot - 1);
		quickSort_random(list, pivot + 1, high);
	}

	private static <E extends Comparable<E>> void quickSort_medianOfRandomThree(ArrayList<E> list, int low, int high) {
		if (low >= high) {
			return;
		}
		int randA = rand(low, high);
		int randB = rand(low, high);
		int randC = rand(low, high);
		int medianRandomPivot = median(list, randA, randB, randC);
		if (medianRandomPivot != high) {
			swap(list, medianRandomPivot, high);
		}
		int pivot = quickSort(list, low, high);
		quickSort_medianOfRandomThree(list, low, pivot - 1);
		quickSort_medianOfRandomThree(list, pivot + 1, high);
	}

	private static <E extends Comparable<E>> void quickSort_medianOfThree(ArrayList<E> list, int low, int high) {
		if (low >= high) {
			return;
		}
		int medianThreePivot = median(list, low, high, (low + high) / 2);
		if (medianThreePivot != high) {
			swap(list, medianThreePivot, high);
		}
		int pivot = quickSort(list, low, high);
		quickSort_medianOfThree(list, low, pivot - 1);
		quickSort_medianOfThree(list, pivot + 1, high);
	}

	private static int rand(int min, int max) throws IllegalArgumentException {
		if (min > max || (max - min + 1 > Integer.MAX_VALUE)) {
			throw new IllegalArgumentException("Invalid range");
		}

		return new Random().nextInt(max - min + 1) + min;
	}

	private static <E extends Comparable<E>> int median(ArrayList<E> list, int a, int b, int c) {
		if (list.get(a).compareTo(list.get(b)) > 0) {
			if (list.get(b).compareTo(list.get(c)) > 0) {
				return b;
			} else if (list.get(a).compareTo(list.get(c)) > 0) {
				return c;
			} else {
				return a;
			}
		} else {
			if (list.get(a).compareTo(list.get(c)) > 0) {
				return a;
			} else if (list.get(b).compareTo(list.get(c)) > 0) {
				return c;
			} else {
				return b;
			}
		}
	}

	private static <E extends Comparable<E>> int quickSort(ArrayList<E> list, int low, int high) {
		E pivotVal = list.get(high);
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (list.get(j).compareTo(pivotVal) <= 0) {
				i++;
				swap(list, i, j);
			}
		}
		swap(list, i + 1, high);
		return i + 1;
	}

	private static <E extends Comparable<E>> void swap(ArrayList<E> list, int i, int j) {
		E temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

	public static ArrayList<Integer> generateRandomList(int size) {
		ArrayList<Integer> list = new ArrayList<Integer>(size);
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			int num = random.nextInt();
			list.add(num);
		}
		return list;
	}

	public static enum PivotStrategy {
		FIRST_ELEMENT, RANDOM_ELEMENT, MEDIAN_OF_THREE_RANDOM_ELEMENTS, MEDIAN_OF_THREE_ELEMENTS
	}

}