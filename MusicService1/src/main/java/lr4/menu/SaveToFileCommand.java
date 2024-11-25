package lr4.menu;

import lr4.music.MusicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveToFileCommand implements Command {
    // Поле для збереження об'єкта MusicService, який буде обробляти операції з музичними альбомами
    private MusicService musicService;
    // Шлях до файлу, в який буде збережено дані
    private String filePath;
    // Об'єкт для обробки введення користувача
    private InputHandler inputHandler;
    // Логгер для запису повідомлень у журнал
    private static final Logger logger = LoggerFactory.getLogger(SaveToFileCommand.class);

    // Конструктор класу, який ініціалізує об'єкти MusicService та InputHandler
    public SaveToFileCommand(MusicService musicService, InputHandler inputHandler) {
        this.inputHandler = inputHandler; // Ініціалізація об'єкта InputHandler
        this.musicService = musicService; // Ініціалізація об'єкта MusicService
    }

    // Реалізація методу execute() інтерфейсу Command, який виконує команду
    @Override
    public void execute() {
        try {
            // Отримуємо шлях до файлу від користувача
            filePath = inputHandler.getString("Введіть файл: ");
            // Викликаємо метод saveToFile() з MusicService для збереження даних у файл
            musicService.saveToFile(filePath);
        } catch (Exception e) {
            // Якщо сталася помилка під час збереження, записуємо її в журнал
            logger.error("Не вдалося зберегти альбоми у файл: " + filePath, e);
        }
    }

    // Реалізація методу description() інтерфейсу Command, який повертає опис команди
    @Override
    public String description() {
        return "Зберегти альбоми у файл"; // Опис команди
    }
}
