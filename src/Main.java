import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.Scanner;

public class Main {

    static final String FILE_NAME = "/Users/tiagopadrao/Documents/myOwnProjects/NB_1252037_1230618/src/fileTest";
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(FILE_NAME);
        int[][] moodMap = getInputFromFile(file);

        printDaysHeader(moodMap[0].length);
        printMoodMapWithPeople(moodMap);
    }

    private static int[] getDimensionsOfArray(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int[] dimensionsInteger = new int[2];
        if (scanner.hasNextLine()) scanner.nextLine();

        String lineWithDimensions = scanner.nextLine();
        String[] dimensionsString = lineWithDimensions.split(" ");

        dimensionsInteger[0] = Integer.parseInt(dimensionsString[0]);
        dimensionsInteger[1] = Integer.parseInt(dimensionsString[1]);


        return dimensionsInteger;


    }

    private static int[][] getInputFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        if (scanner.hasNextLine()) scanner.nextLine();

        int[] dimensions = getDimensionsOfArray(file);
        int quantityOfPeople = dimensions[0];
        int quantityOfDays = dimensions[1];

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

    private static void calculateAverageMoodOfEachDay(int[][] moodMap, File file) throws FileNotFoundException {
        int[] dimensions = getDimensionsOfArray(file);
        int quantityOfPersons = dimensions[0];
        int quantityOfDays = dimensions[1];
        int[] mood = new int[quantityOfDays];
        int sumOfMood = 0;
        double average = 0.0;


    }
}