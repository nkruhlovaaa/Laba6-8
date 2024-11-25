package lr4.menu;

import lr4.music.MusicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCompsWithinBoundsCommand implements Command {
    // Поле для збереження посилання на MusicService
    private MusicService musicService;
    // Поля для збереження меж
    private int bound1;
    private int bound2;
    // Поле для InputHandler для отримання вводу від користувача
    private InputHandler inputHandler;
    // Статичний логер для запису повідомлень про виконання команди
    private static final Logger logger = LoggerFactory.getLogger(GetCompsWithinBoundsCommand.class);

    // Конструктор для ініціалізації залежностей
    public GetCompsWithinBoundsCommand(MusicService musicService, InputHandler inputHandler) {
        this.musicService = musicService;
        this.inputHandler = inputHandler;
    }

    // Реалізація методу execute для виконання команди "Отримати композиції в межах"
    @Override
    public void execute() {
        try {
            // Отримуємо мінімальну та максимальну межу через InputHandler
            bound1 = inputHandler.getInt("Мінімальна межа: ");
            bound2 = inputHandler.getInt("Максимальна межа: ");

            // Перевірка на недійсні значення меж
            if (bound1 < 0 || bound2 < 0) {
                logger.warn("\n" + "Недійсні межі"); // Логування попередження
                return; // Якщо межі невірні, припиняємо виконання
            }

            // Якщо мінімальна межа більша за максимальну, міняємо їх місцями
            if (bound1 > bound2) {
                int temp = bound1;
                bound1 = bound2;
                bound2 = temp;
            }

            // Логування інформації про початок отримання композицій в межах
            logger.info("Отримання композицій у межах");
            // Викликаємо метод сервісу для отримання композицій в заданих межах
            musicService.getCompsWithinBounds(bound1, bound2);
        } catch (Exception e) {
            // Логування помилки, якщо не вдалося отримати композиції
            logger.error("\n" + "Не вдалося отримати композиції в межах", e);
        }
    }

    // Опис команди для відображення в меню
    @Override
    public String description() {
        return "Отримайте композиції в межах"; // Повертає опис команди
    }
}
