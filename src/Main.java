import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static final String FILE_NAME = "fileTest";

    public static void main(String[] args) throws FileNotFoundException {
        int[][] moodMap = getInputFromFile(FILE_NAME);

        printMoodMapFormat(moodMap); //b)
        printAverageMood(moodMap);//c)
        printAverageMoodPerson(calculateAverageMoodEachPerson(moodMap));//d)
        calculateDaysWithHighestAverageMood(calculateAverageMoodOfEachDay(moodMap));//e)
        printPercentageOfHumor(calculatePecentageOfHumor(moodMap));//f)
        printPeopleWithEmotionalDisorder(moodMap); //g)
        printMoodCharts(moodMap);//h)
        printPeopleWhoNeedsTherapy(moodMap); //i)
        printSimilarHumorPerson(moodMap);//j)
    }

    //a) Tiago
    private static int[][] getInputFromKeyboard() {

        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();

        String lineWithDimensions = scanner.nextLine();
        String[] dimensions = lineWithDimensions.split(" ");

        int quantityOfPeople = Integer.parseInt(dimensions[0]);
        int quantityOfDays = Integer.parseInt(dimensions[1]);

        int[][] moodMap = new int[quantityOfPeople][quantityOfDays];


        for (int i = 0; i < quantityOfPeople; i++) {
            for (int j = 0; j < quantityOfDays; j++) {
                moodMap[i][j] = scanner.nextInt();
            }
        }

        return moodMap;
    }


    private static int[][] getInputFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        scanner.nextLine();

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
        System.out.print("day:         ");
        for (int day = 0; day < numberOfDays; day++) {
            System.out.printf("%-3d", day);
            if (day < numberOfDays - 1) System.out.print(" ");
        }
        System.out.println();
        System.out.print("-----------");
        for (int i = 0; i < numberOfDays; i++) {
            System.out.print("|---");

        }
        System.out.println("|");

    }

    //B) - Luis
    public static void printMoodMapFormat(int[][] moodMap) {
        int person = moodMap.length;
        int days = moodMap[0].length;
        System.out.println("b) Mood (level/day(person)");
        getDaysHeader(days);
        for (int p = 0; p < person; p++) {
            System.out.printf("Person #%-2d : ", p);
            for (int d = 0; d < days; d++) {
                System.out.printf("%-3d", moodMap[p][d]);
                if (d < days - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
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
        System.out.println("c) Average mood each day: ");
        getDaysHeader(averageMood.length);
        System.out.print("mood:       ");
        for (int d = 0; d < averageMood.length; d++) {
            System.out.printf("%.1f ", averageMood[d]);

        }
        System.out.println();
        System.out.println();
    }


    //E) Tiago
    private static void calculateDaysWithHighestAverageMood(double[] averageMoodArray) {

        double highestAverageMood = averageMoodArray[0];

        for (int day = 1; day < averageMoodArray.length; day++) {
            if (averageMoodArray[day] > highestAverageMood) {
                highestAverageMood = averageMoodArray[day];
            }
        }

        System.out.printf("e) Days with the highest average mood (%.1f) : " , highestAverageMood);
        for (int day = 0; day < averageMoodArray.length; day++) {
            if (averageMoodArray[day] == highestAverageMood) {
                System.out.print(day + " ");
            }
        }

        System.out.println();
        System.out.println();

    }


    //D) - Luis
    private static double[] calculateAverageMoodEachPerson(int[][] moodMap) {
        int quantityOfPeople = moodMap.length;
        int quantityOfDays = moodMap[0].length;
        double[] averageMoodPerson = new double[moodMap.length];
        for (int p = 0; p < quantityOfPeople; p++) {
            int sum = 0;
            for (int d = 0; d < quantityOfDays; d++) {
                sum += moodMap[p][d];
            }
            averageMoodPerson[p] = (double) sum / quantityOfDays;
        }
        return averageMoodPerson;
    }

    //D) - Luis
    public static void printAverageMoodPerson(double[] averageMoodPerson) {
        System.out.println("d) Average os each person's mood:");
        for (int p = 0; p < averageMoodPerson.length; p++) {
            System.out.printf("Person #%d : %.1f%n", p, averageMoodPerson[p]);
        }
        System.out.println();
    }

    //F) - Luis
    private static double[] calculatePecentageOfHumor(int[][] moodMap) {
        int quantityOfPeople = moodMap.length;
        int quantityOfDays = moodMap[0].length;
        int totalOfValues = quantityOfPeople * quantityOfDays;

        int[] countHumor = new int[6]; //talvez seja 6 em vez de 5 mas nao tenho certeza, são os valores do humor
        for (int p = 0; p < quantityOfPeople; p++) {
            for (int d = 0; d < quantityOfDays; d++) {
                int humor = moodMap[p][d];    //variavel humor corresponde a mood, só me lembrei de humor que tb da em ingles
                if (humor >= 1 && humor <= 5) {
                    countHumor[humor]++;
                }
            }
        }
        double[] percentageOfHumor = new double[6]; //aqui tambem talvez seja 6
        for (int level = 1; level <= 5; level++) {
            percentageOfHumor[level] = (countHumor[level] * 100.0) / totalOfValues;
        }
        return percentageOfHumor;
    }

    //F) - Luis
    private static void printPercentageOfHumor(double[] percentageOfHumor) {
        System.out.println("f) Percentage of mood levels: ");
        for (int level = 5; level >= 1; level--) {
            System.out.printf("Mood #%d: %.1f%%%n", level, percentageOfHumor[level]);
        }
        System.out.println();
    }

    //H) - Luis
    private static void printMoodCharts(int[][] moodMap) {
        int quantityOfPeople = moodMap.length;
        int quantityOfDays = moodMap[0].length;

        System.out.println("h) People's Mood Level Charts:");

        for (int p = 0; p < quantityOfPeople; p++) {
            System.out.printf("Person #%d:%n", p);

            int minLevel = 5;
            int maxLevel = 1;

            for (int d = 0; d < quantityOfDays; d++) {
                int mood = moodMap[p][d];
                if (mood < minLevel) minLevel = mood;
                if (mood > maxLevel) maxLevel = mood;
            }

            for (int level = maxLevel; level >= minLevel; level--) {
                System.out.printf("%4d |", level);

                for (int d = 0; d < quantityOfDays; d++) {
                    if (moodMap[p][d] == level)
                        System.out.print("*");
                    else
                        System.out.print(" ");
                }
                System.out.println();
            }

            System.out.print("Mood +");
            for (int d = 0; d < quantityOfDays; d++) {
                System.out.print("-");
            }
            System.out.println();

            System.out.print("      ");  // 4 espaços antes da escala
            for (int d = 0; d < quantityOfDays; d += 5) {
                System.out.printf("%-5d", d);
            }
            System.out.println("\n");
        }
    }

    //J) - Luis
    private static void printSimilarHumorPerson(int[][] moodMap) {
        int quantityOfPerson = moodMap.length;
        int quantityOfDays = moodMap[0].length;
        int bestPerson1 = -1;           //começam com -1 para dizer que não existe par ainda;
        int bestPerson2 = -1;
        int bestEqualDays = 0;

        for (int i = 0; i < quantityOfPerson - 1; i++) {
            for (int j = i + 1; j < quantityOfPerson; j++) {

                int equalDays = 0;
                for (int d = 0; d < quantityOfDays; d++) {
                    if (moodMap[i][d] == moodMap[j][d]) {
                        equalDays++;
                    }
                }
                if (equalDays > bestEqualDays) {
                    bestEqualDays = equalDays;
                    bestPerson1 = i;
                    bestPerson2 = j;
                } else if (equalDays == bestEqualDays && equalDays > 0) {
                    if (bestPerson1 == -1 || i < bestPerson2 || i == bestPerson1 && j < bestPerson2) {
                        bestPerson1 = i;
                        bestPerson2 = j;
                    }
                }
            }
        }
        System.out.println("j) People with the most similar moods: ");

        if (bestEqualDays == 0) {
            System.out.println("Nenhum");
        } else {
            System.out.printf("(Person #%d and Person #%d have the same mood on %d days)%n", bestPerson1, bestPerson2, bestEqualDays);
        }
        System.out.println();

    }

    //g) tiago


    private static int[] getPeopleWithEmotionalDisorder(int[][] moodMap) {

        int[] peopleWithEmotionalDisorder = new int[moodMap.length];

        for (int people = 0; people < moodMap.length; people++) {
            int consecutiveEmotionalDays = 0;
            int highConsecutiveEmotionalDays = 0;

            for (int days = 0; days < moodMap[0].length; days++) {
                if (moodMap[people][days] < 3) {
                    consecutiveEmotionalDays++;
                } else {
                    if (consecutiveEmotionalDays > highConsecutiveEmotionalDays) {
                        highConsecutiveEmotionalDays = consecutiveEmotionalDays;
                    }
                    consecutiveEmotionalDays = 0;
                }
            }
            if (consecutiveEmotionalDays > highConsecutiveEmotionalDays) {
                highConsecutiveEmotionalDays = consecutiveEmotionalDays;
            }

            peopleWithEmotionalDisorder[people] = highConsecutiveEmotionalDays;
        }

        return peopleWithEmotionalDisorder;
    }

    //g) tiago
    private static void printPeopleWithEmotionalDisorder(int[][] moodMap) {
        int[] peopleWithEmotionalDisorder = getPeopleWithEmotionalDisorder(moodMap);
        System.out.println("g) People with emotional disorders: ");

        for (int people = 0; people < peopleWithEmotionalDisorder.length; people++) {
            if (peopleWithEmotionalDisorder[people] != 1) {
                System.out.printf("Person #%d : %d consecutive days %n", people, peopleWithEmotionalDisorder[people]);
            }
        }
        System.out.println();
    }


    //i) Tiago
    private static void printPeopleWhoNeedsTherapy(int[][] moodMap) {
        int[] peopleWithEmotionalDisorder = getPeopleWithEmotionalDisorder(moodMap);

        System.out.println("i) Recommended therapy: ");

        for (int people = 0; people < peopleWithEmotionalDisorder.length; people++) {
            int consecutiveDaysWithLowMood = peopleWithEmotionalDisorder[people];

            if (consecutiveDaysWithLowMood >= 2) {
                System.out.printf(
                        "Person #%d : %s%n",
                        people,
                        (consecutiveDaysWithLowMood >= 5)
                                ? "psychological support"
                                : "listen to music"
                );
            }
        }
        System.out.println();
    }
}