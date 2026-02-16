package groupone;

import groupone.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class.getName());
    private static final Scanner scanner = new Scanner(System.in);

    private static List<String> dataLines = new ArrayList<>();

    private static String currentStrategy = "InsertionSort";

    public static void main(String[] args) {
        logger.info("Приложение запущено.");
        boolean exit = false;
        while (!exit) {
            printMainMenu();
            int choice = readInt("Ваш выбор: ", 8);
            logger.debug("Пользователь выбрал пункт: {}", choice);
            switch (choice) {
                case 1 -> fillCollection();
                case 2 -> showCollection();
                case 3 -> chooseSortingStrategy();
                case 4 -> sortCollection();
                case 5 -> saveToFile();
                case 6 -> countOccurrences();
                case 7 -> clearCollection();
                case 8 -> {
                    exit = true;
                    logger.info("Выход из приложения.");
                }
                default -> System.out.println("Неожиданное значение: " + choice + ". Обратитесь к разработчику.");
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
        System.out.println("1. Заполнить коллекцию");
        System.out.println("2. Показать коллекцию");
        System.out.println("3. Выбрать стратегию сортировки");
        System.out.println("4. Отсортировать коллекцию");
        System.out.println("5. Сохранить коллекцию в файл");
        System.out.println("6. Подсчитать вхождения элемента");
        System.out.println("7. Очистить коллекцию");
        System.out.println("8. Выход");
    }

    private static void fillCollection() {
        System.out.println("\n--- Заполнение коллекции ---");
        System.out.println("Выберите источник данных:");
        System.out.println("1. Ручной ввод (ConsoleReader)");
        System.out.println("2. Случайная генерация (RandomGenerator)");
        System.out.println("3. Чтение из файла (FileReader)");
        int source = readInt("Ваш выбор: ", 3);

        System.out.print("Сколько записей добавить? ");
        int count = readInt("", Integer.MAX_VALUE);

        DataABC reader = switch (source) {
            case 1 -> new ConsoleReader(count, scanner);
            case 2 -> new RandomGenerator(count);
            case 3 -> {
                System.out.print("Введите имя файла (по умолчанию input.txt): ");
                String fileName = scanner.nextLine().trim();
                if (fileName.isEmpty()) fileName = "input.txt";
                yield new FileReader(count, fileName);
            }
            default -> throw new IllegalStateException("Недопустимый источник");
        };

        List<String> newLines = readAllLines(reader);
        if (!newLines.isEmpty()) {
            if (!dataLines.isEmpty()) {
                System.out.print("Коллекция не пуста. Добавить новые записи к существующим? (1 - да, 2 - заменить): ");
                int choice = readInt("", 2);
                if (choice == 1) {
                    dataLines.addAll(newLines);
                    logger.info("Добавлено {} записей. Размер коллекции: {}", newLines.size(), dataLines.size());
                } else {
                    dataLines = newLines;
                    logger.info("Коллекция заменена. Новый размер: {}", dataLines.size());
                }
            } else {
                dataLines = newLines;
                logger.info("Коллекция заполнена {} записями.", dataLines.size());
            }
            System.out.println("Коллекция обновлена. Текущий размер: " + dataLines.size());
        } else {
            System.out.println("Не удалось добавить записи.");
            logger.warn("Не удалось прочитать данные из выбранного источника.");
        }
    }

    private static List<String> readAllLines(DataABC reader) {
        List<String> result = new ArrayList<>();
        for (String line : reader) {
            result.add(line);
        }
        return result;
    }

    private static void showCollection() {
        if (dataLines.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        System.out.println("\n=== Текущая коллекция (" + dataLines.size() + " записей) ===");
        for (int i = 0; i < dataLines.size(); i++) {
            System.out.println((i + 1) + ". " + dataLines.get(i));
        }
    }

    // ---------- Выбор стратегии сортировки (заглушка) ----------
    private static void chooseSortingStrategy() {
        System.out.println("\n--- Выбор стратегии сортировки ---");
        System.out.println("1. Обычная сортировка (вставками)");
        System.out.println("2. Сортировка только чётных значений (по числовому полю)");
        int strat = readInt("Выберите стратегию: ", 2);

        currentStrategy = (strat == 1) ? "InsertionSort" : "EvenOddSort";
        System.out.println("Стратегия '" + currentStrategy + "' выбрана (реализация сортировки будет добавлена позже).");
        logger.info("Выбрана стратегия: {}", currentStrategy);
    }

    // ---------- Сортировка (заглушка) ----------
    private static void sortCollection() {
        if (dataLines.isEmpty()) {
            System.out.println("Коллекция пуста. Нечего сортировать.");
            return;
        }
        System.out.println("\n--- Сортировка ---");
        System.out.println("Функция сортировки находится в разработке.");
        System.out.println("Выбранная стратегия: " + currentStrategy);
    }

    private static void saveToFile() {
        if (dataLines.isEmpty()) {
            System.out.println("Коллекция пуста. Нечего сохранять.");
            return;
        }
        System.out.print("Введите имя файла для сохранения (по умолчанию output.txt): ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) filename = "output.txt";
        System.out.println("Сохранение в файл '" + filename + "' будет реализовано позже.");
        logger.info("Запрос сохранения в файл {}", filename);
    }

    private static void countOccurrences() {
        if (dataLines.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        System.out.println("Введите строку для подсчёта вхождений:");
        String target = scanner.nextLine().trim();

        int count = 0;
        for (String line : dataLines) {
            if (line.equals(target)) count++;
        }
        System.out.println("Количество точных совпадений: " + count);
        logger.debug("Подсчёт вхождений для '{}': {}", target, count);
    }

    private static void clearCollection() {
        dataLines.clear();
        System.out.println("Коллекция очищена.");
        logger.info("Коллекция очищена.");
    }

    private static int readInt(String prompt, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine();
                if (value >= 1 && value <= max) {
                    return value;
                } else {
                    System.out.printf("Введите число от %d до %d.%n", 1, max);
                }
            } else {
                System.out.println("Ошибка: введите целое число.");
                scanner.next();
            }
        }
    }
}