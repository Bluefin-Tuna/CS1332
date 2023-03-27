import java.util.Comparator;

public class Test {
    public static void main(String[] args) {

        Integer[] integers = new Integer[8];
        integers[0] = 5;
        integers[1] = 4;
        integers[2] = 2;
        integers[3] = 3;
        integers[4] = 6;
        integers[5] = 1;
        integers[6] = 0;
        integers[7] = 7;
        Comparator<Integer> comp = (o1, o2) -> o1 - o2;

        Sorting.cocktailSort(integers, comp);

        Sorting.print(integers);
    }
}
