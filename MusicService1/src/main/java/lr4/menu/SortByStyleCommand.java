package lr4.menu;

import lr4.music.MusicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortByStyleCommand implements Command {
    // Поле для збереження об'єкта MusicService, який забезпечує функціональність для роботи з альбомами
    private MusicService musicService;
    // Об'єкт для обробки введення користувача
    private InputHandler inputHandler;
    // Логгер для запису повідомлень у журнал
    private static final Logger logger = LoggerFactory.getLogger(SortByStyleCommand.class);

    // Конструктор класу, який ініціалізує об'єкти MusicService та InputHandler
    public SortByStyleCommand(MusicService musicService) {
        this.musicService = musicService; // Ініціалізація об'єкта MusicService
        this.inputHandler = InputHandler.getInstance(); // Отримання єдиного екземпляра InputHandler
    }

    // Реалізація методу execute() інтерфейсу Command, який виконує команду сортування альбомів
    @Override
    public void execute() {
        try {
            // Виводимо користувачу інструкції щодо вибору сортування
            logger.info("\n" + "За зростанням - a | За спаданням - d");
            // Отримуємо вибір користувача (a або d)
            String order = inputHandler.getString("\n" + "Введіть вибір: ");
            // Викликаємо метод сортування за стилем, передаючи вибір користувача
            musicService.sortByStyle(order);
            // Якщо сортування пройшло успішно, записуємо відповідне повідомлення в журнал
            logger.info("Альбоми відсортовані за стилем.");
        } catch (Exception e) {
            // Якщо сталася помилка, записуємо її в журнал
            logger.error("Не вдалося відсортувати альбоми за стилем\n", e);
        }
    }

    // Реалізація методу description() інтерфейсу Command, який повертає опис команди
    @Override
    public String description() {
        return "Сортування альбомів за стилем."; // Опис команди
    }
}
