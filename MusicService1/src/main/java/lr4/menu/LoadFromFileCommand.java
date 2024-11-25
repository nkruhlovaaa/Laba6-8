package lr4.menu;

import lr4.music.MusicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadFromFileCommand implements Command {
    // Поле для збереження об'єкта MusicService, який буде відповідати за завантаження даних
    private MusicService musicService;
    // Поле для збереження шляху до файлу
    private String filePath;
    // Поле для збереження об'єкта InputHandler для отримання вводу від користувача
    private InputHandler inputHandler;
    // Логгер для запису повідомлень у журнал
    private static final Logger logger = LoggerFactory.getLogger(LoadFromFileCommand.class);

    // Конструктор класу, який приймає об'єкти MusicService та InputHandler
    public LoadFromFileCommand(MusicService musicService, InputHandler inputHandler) {
        this.inputHandler = inputHandler; // ініціалізуємо inputHandler
        this.musicService = musicService; // ініціалізуємо musicService
    }

    // Реалізація методу execute для виконання завантаження альбомів з файлу
    @Override
    public void execute() {
        try {
            // Отримуємо шлях до файлу від користувача
            filePath = inputHandler.getString("\n" + "Введіть файл: ");
            // Логуємо інформаційне повідомлення про початок завантаження
            logger.info("\n" + "Завантаження альбомів з файлу: " + filePath);
            // Викликаємо метод завантаження альбомів з файлу в класі MusicService
            musicService.loadFromFile(filePath);
        } catch(Exception e) {
            // Якщо виникає помилка, логуємо її разом з повідомленням про помилку
            logger.error("\n" + "Не вдалося завантажити альбоми з файлу: " + filePath, e);
        }
    }

    // Реалізація методу description для повернення опису цієї команди
    @Override
    public String description() {
        return "Завантажити з файлу"; // Повертає опис для команди завантаження з файлу
    }
}
