package lr4.menu;

import lr4.music.MusicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintAlbumsCommand implements Command {
    // Поле для збереження об'єкта MusicService, який буде обробляти операції з музичними альбомами
    private MusicService musicService;
    // Логгер для запису повідомлень у журнал
    private static final Logger logger = LoggerFactory.getLogger(PrintAlbumsCommand.class);

    // Конструктор класу, який ініціалізує об'єкт MusicService
    public PrintAlbumsCommand(MusicService musicService) {
        this.musicService = musicService; // Ініціалізуємо musicService
    }

    // Реалізація методу execute() інтерфейсу Command, що виконує команду
    @Override
    public void execute() {
        // Логування інформаційного повідомлення про друк альбомів
        logger.info("\n" + "Друк альбомів:");
        // Викликаємо метод printAlbums() з MusicService для виведення альбомів
        musicService.printAlbums();
    }

    // Реалізація методу description() інтерфейсу Command, який повертає опис команди
    @Override
    public String description() {
        return "Роздрукувати альбоми"; // Опис команди
    }
}
