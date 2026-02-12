package groupone.data;

import java.util.Scanner;

public class consoleReader {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int readInt(String prompt) {
        System.out.print(prompt);
        while (!SCANNER.hasNextInt()) {
            System.out.println("Ошибка: введите целое число.");
            SCANNER.next();
            System.out.print(prompt);
        }
        return SCANNER.nextInt();
    }

    public static int readInt(String prompt, int min, int max) {
        int value;
        do {
            value = readInt(prompt);
            if (value < min || value > max) {
                System.out.printf("Значение должно быть от %d до %d%n", min, max);
            }
        } while (value < min || value > max);
        return value;
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        SCANNER.nextLine();
        return SCANNER.nextLine().trim();
    }

    public static void close() {
        SCANNER.close();
    }
}