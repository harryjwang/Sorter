import java.io.File;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.ArrayList;

public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		if (args.length == 4) {
			try {
				File file1 = new File(args[1]);
				File file2 = new File(args[2]);
				File file3 = new File(args[3]);
				file1.createNewFile();
				file2.createNewFile();
				file3.createNewFile();
				PrintWriter reportFile = new PrintWriter(file1);
				PrintWriter unsortedFile = new PrintWriter(file2);
				PrintWriter sortedFile = new PrintWriter(file3);
				int size = Integer.parseInt(args[0]);
				ArrayList<Integer> list = QuickSorter.generateRandomList(size);
				unsortedFile.println(list);
				unsortedFile.close();
				ArrayList<Integer> copy1 = new ArrayList<Integer>(list);
				ArrayList<Integer> copy2 = new ArrayList<Integer>(list);
				ArrayList<Integer> copy3 = new ArrayList<Integer>(list);
				ArrayList<Integer> copy4 = new ArrayList<Integer>(list);
				Duration first_element, random_element, median_of_three_elements, median_of_three_random_elements;
				first_element = QuickSorter.timedQuickSort(copy1, QuickSorter.PivotStrategy.FIRST_ELEMENT);
				random_element = QuickSorter.timedQuickSort(copy2, QuickSorter.PivotStrategy.RANDOM_ELEMENT);
				median_of_three_random_elements = QuickSorter.timedQuickSort(copy3,
						QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
				median_of_three_elements = QuickSorter.timedQuickSort(copy4,
						QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);
				sortedFile.println(copy1);
				sortedFile.close();
				reportFile.println("Array Size = " + size);
				reportFile.println("FIRST_ELEMENT : " + first_element);
				reportFile.println("RANDOM_ELEMENT : " + random_element);
				reportFile.println("MEDIAN_OF_THREE_RANDOM _ELEMENTS : " + median_of_three_random_elements);
				reportFile.println("MEDIAN_OF_THREE_ELEMENTS : " + median_of_three_elements);
				reportFile.close();
			} catch (Exception ex) {
				System.out.println("Unable to create file");
			}
		} else {
			System.out.println("Please Input Valid Arguments in Command Lines");
		}
	}
}