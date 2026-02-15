package groupone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class.getName());
    private static Scanner scanner = null;

    public Main(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        logger.info("Приложение запущено.");
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = readInt();
            logger.debug("Пользователь выбрал пункт: {}", choice);
            switch (choice) {
                case 1 -> System.out.println("Функция заполнения коллекции (в разработке)");
                case 2 -> System.out.println("Функция показа коллекции (в разработке)");
                case 3 -> System.out.println("Функция сортировки (в разработке)");
                case 4 -> System.out.println("Функция сохранения в файл (в разработке)");
                case 5 -> {
                    exit = true;
                    logger.info("Выход из приложения.");
                }
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Заполнить коллекцию");
        System.out.println("2. Показать коллекцию");
        System.out.println("3. Отсортировать коллекцию");
        System.out.println("4. Сохранить коллекцию в файл");
        System.out.println("5. Выход");
    }

    private static int readInt() {
        int value;
        while (true) {
            System.out.print("Ваш выбор: ");
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine();
                if (value >= 1 && value <= 5) {
                    return value;
                } else {
                    System.out.printf("Введите число от %d до %d.%n", 1, 5);
                }
            } else {
                System.out.println("Ошибка: введите целое число.");
                scanner.next();
            }
        }
    }
}