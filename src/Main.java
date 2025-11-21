import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.Scanner;

public class Main {

    static final String FILE_NAME = "/Users/tiagopadrao/Documents/myOwnProjects/NB_1252037_1230618/src/fileTest";

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(FILE_NAME);
        int[][] moodMap = getInputFromFile(file);


    }

    //a) Tiago

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

    private static void getDaysHeader(int numberOfDays) {
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


    public static void printMoodMapFormat(int[][] moodMap) {
        int person = moodMap.length;
        int days = moodMap[0].length;

        for (int i = 0; i < person; i++) {
            System.out.printf("Person #%-2d:", i);
            for (int j = 0; j < days; j++) {
                System.out.printf("%-3d", moodMap[i][j]);
                if (j < days - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    //c) - Tiago
    private static double[] calculateAverageMoodOfEachDay(int[][] moodMap) throws FileNotFoundException {
        int quantityOfPeople = moodMap.length;
        int quantityOfDays = moodMap[0].length;
        double[] averageMood = new double[moodMap[0].length];

        for (int d = 0; d < quantityOfDays; d++) {
            int sum = 0;
            for (int p = 0; p < quantityOfPeople; p++) {
                sum += moodMap[p][d];
            }
            averageMood[d] = (double) sum / quantityOfPeople;
        }
        return averageMood;
    }

    //c) - Tiago
    private static void printAverageMood(int[][] moodMap) throws FileNotFoundException {
        double[] averageMood = calculateAverageMoodOfEachDay(moodMap);
        getDaysHeader(averageMood.length);
        System.out.print("mood:       ");
        for (int d = 0; d < averageMood.length; d++) {
            System.out.printf("%.1f ", averageMood[d]);

        }
    }


}