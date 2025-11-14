import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Aprog Test ");
        Scanner scanner = new Scanner(System.in);

        int daysQuantity = scanner.nextInt();
        int peopleQuantity = scanner.nextInt();
        int[][] moodMap = new int[daysQuantity][peopleQuantity];
        getMoodMap(moodMap);

    }
    private static void getHeader(int[] days){
        System.out.print("day : ");
        for (int i = 0; i < days.length ; i++) {


        }
    }

    private static void getMoodMap(int[][] array) {
        System.out.println("git ");


    }
}