package lr4;

import lr4.music.MusicService;
import lr4.menu.Menu;
import lr4.menu.InputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    // Статичне поле для отримання екземпляра InputHandler
    private static InputHandler inputHandler = InputHandler.getInstance();
    // Логгер для запису повідомлень у журнал
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    // Головний метод програми, точка входу
    public static void main(String[] args) {
        Main main = new Main(); // Створюємо екземпляр класу Main
        main.startApp(); // Запускаємо програму
    }

    // Метод для запуску основної логіки програми
    public void startApp() {
        try {
            // Записуємо в журнал, що програма запущена
            logger.info("\n" + "Програма запущена");

            // Створюємо екземпляр MusicService для обробки музичних даних
            MusicService musicService = new MusicService();
            // Створюємо меню, передаючи залежності
            Menu menu = new Menu(musicService, inputHandler);
            // Запускаємо метод run для відображення меню та обробки команд
            menu.run();

            // Якщо програма працює без помилок, записуємо повідомлення про успішне завершення
            logger.info("Застосування успішно завершено");
        } catch (Exception e) {
            // Якщо виникає помилка, записуємо її в журнал
            logger.error("Не вдалося запустити програму", e);
        }
    }
}
