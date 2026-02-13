package groupone.data;

public class ConsoleReader extends DataABC {
    private final Scanner scanner;

    public ConsoleReader(int count) {
        if (count <= 0) throw new RuntimeException("Ошибка входных параметров");
        this.count = count;
        this.scanner = scanner;
    }

    @Override
    public String next() {
        if (i >= count) {
            throw new RuntimeException("Все элементы уже прочитаны");
        }
        System.out.println("\nВведите данные пользователя #" + (i + 1) + ":");
        System.out.print("  Имя: ");
        String name = scanner.nextLine().trim();
        System.out.print("  Пароль: ");
        String password = scanner.nextLine().trim();
        System.out.print("  Email: ");
        String email = scanner.nextLine().trim();

        i++;
        return String.join(", ", name, password, email);
    }
}
