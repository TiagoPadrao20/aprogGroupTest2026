import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/tiagopadrao/desktop/uni/prcmp/fileTest");
        int[][] moodMap = getInputFromFile(file);

        printDaysHeader(moodMap[0].length);
        printMoodMapWithPeople(moodMap);
    }



    private static int[][] getInputFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        if (scanner.hasNextLine()) scanner.nextLine();

        String lineWithDimensions = scanner.nextLine();
        String[] dimensions = lineWithDimensions.split(" ");

        int quantityOfPeople = Integer.parseInt(dimensions[0]);
        int quantityOfDays = Integer.parseInt(dimensions[1]);

        int[][] moodMap = new int[quantityOfPeople][quantityOfDays];

        int people = 0;
        int days = 0;

        while (scanner.hasNext() && people < moodMap.length) {
            moodMap[people][days] = Integer.parseInt(scanner.next());
            days++;
            if (days == moodMap[people].length) {
                days = 0;
                people++;
            }
        }
        scanner.close();
        return moodMap;
    }

    private static void printDaysHeader(int numberOfDays) {
        System.out.print("day        : ");
        for (int day = 0; day < numberOfDays; day++) {
            System.out.printf("%-3d", day);
            if (day < numberOfDays - 1) System.out.print(" ");
        }
        System.out.println();
        System.out.print("-----------|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|");
        System.out.println();
    }

    private static void printMoodMapWithPeople(int[][] moodMap) {
        int people = moodMap.length;
        int days = moodMap[0].length;

        for (int p = 0; p < people; p++) {
            System.out.printf("Person#%-4d: ", p);
            for (int d = 0; d < days; d++) {
                System.out.printf("%-3d", moodMap[p][d]);
                if (d < days - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}