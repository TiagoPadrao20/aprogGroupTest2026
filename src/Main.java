import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int[][] moodMap = getInputFromFile();

        //aqui, chamamos o metodo para fazer print apenas do header, passamos o tamanho do 1~array, que seria o array dos dias
        printDaysHeader(moodMap[0].length);

        printMoodMapWithPeople(moodMap);
    }

    //metodo que recebemosu m ficheiro e convertemos para uma array
    private static int[][] getInputFromFile() throws FileNotFoundException {
        File file = new File("/Users/tiagopadrao/desktop/uni/prcmp/fileTest");
        Scanner sc = new Scanner(file);

        //para ignorar a primeira linha do ficheiro
        if (sc.hasNextLine()) sc.nextLine();

        String line = sc.nextLine();
        //aqui criei um array de dimensões, com o conteúdo que esta na line. (dias e quantidade de pessoas)
        String[] dimensions = line.split(" ");


        int quantityOfPeople = Integer.parseInt(dimensions[0]);
        int quantityOfDays = Integer.parseInt(dimensions[1]);

        int[][] moodMap = new int[quantityOfPeople][quantityOfDays];

        for (int row = 0; row < quantityOfPeople; row++) {
            //pega todas as linhas do ficheiro, e a cada iteração, guarda num array temporario o conteudo
            String rowLine = sc.nextLine();
            String[] tempArrayOfLines = rowLine.split(" ");
            System.out.println(tempArrayOfLines[0]);
            for (int col = 0; col < quantityOfDays; col++) {
                moodMap[row][col] = Integer.parseInt(tempArrayOfLines[col]);
            }
        }

        sc.close();
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