package groupone.data;
import java.util.Scanner;

class ConsoleReaderTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ConsoleReader reader = new ConsoleReader(2, scanner);

        System.out.println("=== Тестирование ConsoleReader ===");
        System.out.println("Введите данные для двух пользователей (имя, пароль, email).");

        int recordNumber = 1;
        while (reader.hasNext()) {
            String line = reader.next();
            System.out.println("Получена запись #" + recordNumber + ": " + line);
            recordNumber++;
        }

        System.out.println("Тест завершён. Все записи обработаны.");

        scanner.close();
    }
}