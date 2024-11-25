package lr4.menu;
import java.util.Scanner;

public class InputHandler {
    // Статичне поле для збереження єдиного екземпляра InputHandler
    private static InputHandler instance;
    // Об'єкт для зчитування вводу з консолі
    private Scanner scanner;

    // Конструктор, який ініціалізує scanner
    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    // Статичний метод для отримання єдиного екземпляра InputHandler
    public static InputHandler getInstance() {
        if (instance == null) { // Перевіряємо, чи вже існує екземпляр
            instance = new InputHandler(new Scanner(System.in)); // Якщо не існує, створюємо новий екземпляр
        }
        return instance; // Повертаємо єдиний екземпляр
    }

    // Статичний метод для налаштування інстансу для тестування
    public static void setInstance(Scanner scanner) {
        instance = new InputHandler(scanner); // Створюємо новий екземпляр з переданим сканером
    }

    // Метод для отримання рядка з консолі з підказкою
    public String getString(String prompt) {
        System.out.print(prompt); // Виводимо підказку на консоль
        return scanner.nextLine(); // Читаємо рядок з консолі
    }

    // Метод для отримання цілого числа з консолі з підказкою
    public int getInt(String prompt) {
        System.out.print(prompt); // Виводимо підказку на консоль
        // Перевірка, чи введено ціле число
        while (!scanner.hasNextInt()) {
            System.out.println("Невірний вхід. Будь ласка, введіть номер."); // Повідомлення про помилку вводу
            scanner.next(); // Читаємо наступний токен, щоб очистити буфер вводу
        }
        int value = scanner.nextInt(); // Читаємо ціле число
        scanner.nextLine(); // Споживаємо новий рядок після введеного числа
        return value; // Повертаємо введене значення
    }
}
