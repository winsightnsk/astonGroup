package groupone;

import groupone.data.*;
import groupone.domain.*;
import groupone.model.User;
import groupone.model.UserABC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class.getName());
    private static final Scanner scanner = new Scanner(System.in);

    private static List<UserABC> users = new ArrayList<>();

    private static final SortInterface sortContext = new SortContext();
    private static final CustomFileWriter fileWriter = new UserFileWriter();

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
                default -> System.out.println("Неожиданное значение: " + choice);
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
        System.out.println("6. Подсчитать вхождения подстроки в именах");
        System.out.println("7. Очистить коллекцию");
        System.out.println("8. Выход");
    }

    private static void fillCollection() {
        System.out.println("\n--- Заполнение коллекции ---");
        System.out.println("Выберите источник данных:");
        System.out.println("1. Ручной ввод ");
        System.out.println("2. Случайная генерация");
        System.out.println("3. Чтение из файла");
        int source = readInt("Ваш выбор: ", 3);

        System.out.print("Сколько записей добавить? ");
        int count = readInt("", Integer.MAX_VALUE);

        DataInterface reader = switch (source) {
            case 1 -> new ConsoleReader(count, scanner);
            case 2 -> new RandomGenerator(count);
            default -> throw new IllegalStateException("Недопустимый источник");
        };

        List<UserABC> newUsers = reader.stream()
                .map(item -> new User.Builder().setLine(item).build())
                .filter(UserABC::isValid)
                .collect(Collectors.toList());

        if (!newUsers.isEmpty()) {
            if (!users.isEmpty()) {
                System.out.print("Коллекция не пуста. Добавить новые записи к существующим? (1 - да, 2 - заменить): ");
                int choice = readInt("", 2);
                if (choice == 1) {
                    users.addAll(newUsers);
                    logger.info("Добавлено {} записей. Размер коллекции: {}", newUsers.size(), users.size());
                } else {
                    users = newUsers;
                    logger.info("Коллекция заменена. Новый размер: {}", users.size());
                }
            } else {
                users = newUsers;
                logger.info("Коллекция заполнена {} записями.", users.size());
            }
            System.out.println("Коллекция обновлена. Текущий размер: " + users.size());
        } else {
            System.out.println("Не удалось добавить записи (все строки оказались невалидными).");
            logger.warn("Не удалось прочитать данные из выбранного источника.");
        }
    }

    private static void showCollection() {
        if (users.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        System.out.println("\n=== Текущая коллекция (" + users.size() + " записей) ===");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }
    }

    private static void chooseSortingStrategy() {
        System.out.println("\n--- Выбор стратегии сортировки ---");
        System.out.println("1. Обычная сортировка (по одному из полей)");
        System.out.println("2. Сортировка только чётных значений (по паролю)");
        int strat = readInt("Выберите стратегию: ", 2);

        if (strat == 1) {
            System.out.println("Выберите поле для сортировки:");
            System.out.println("1. По имени");
            System.out.println("2. По паролю (числовое поле)");
            System.out.println("3. По email");
            int field = readInt("Ваш выбор: ", 3);

            SortStrategy strategy = switch (field) {
                case 1 -> new SortByUsernameStrategy();
                case 2 -> new SortByPasswordStrategy();
                case 3 -> new SortByEmailStrategy();
                default -> throw new IllegalStateException("Unexpected value");
            };
            sortContext.setSortStrategy(strategy);
            System.out.println("Стратегия обычной сортировки установлена.");
            logger.info("Установлена стратегия обычной сортировки по полю {}", field);
        } else {
            sortContext.setSortStrategy(new SortByPasswordAltStrategy());
            System.out.println("Стратегия сортировки только чётных значений (по паролю) установлена.");
            logger.info("Установлена стратегия EvenOddSortStrategy");
        }
    }

    private static void sortCollection() {
        if (users.isEmpty()) {
            System.out.println("Коллекция пуста. Нечего сортировать.");
            return;
        }
        sortContext.sort(users);
        System.out.println("Коллекция отсортирована.");
        logger.info("Выполнена сортировка с текущей стратегией.");
    }

    private static void saveToFile() {
        if (users.isEmpty()) {
            System.out.println("Коллекция пуста. Нечего сохранять.");
            return;
        }
        System.out.print("Введите имя файла для сохранения (по умолчанию output.txt): ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) filename = "output.txt";
        fileWriter.appendWrite(users, filename);
        System.out.println("Коллекция дописана в файл " + filename);
        logger.info("Коллекция сохранена в файл {}", filename);
    }

    private static void countOccurrences() {
        if (users.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        System.out.print("Введите подстроку для поиска в именах: ");
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            System.out.println("Подстрока не может быть пустой.");
            return;
        }
        List<UserABC> abcList = new ArrayList<>(users);
        SearchInterface searcher = new SearchAsync(abcList);
        int count = searcher.matchesCount(text);
        System.out.println("Количество пользователей, в имени которых встречается \"" + text + "\": " + count);
        logger.debug("Поиск подстроки '{}' дал {} результатов.", text, count);
    }

    private static void clearCollection() {
        users.clear();
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