package lr4.menu;

import lr4.music.MusicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintAlbumsCompsCommand implements Command {
    // Поле для збереження об'єкта MusicService, який буде обробляти операції з музичними альбомами та композиціями
    private MusicService musicService;
    // Логгер для запису повідомлень у журнал
    private static final Logger logger = LoggerFactory.getLogger(PrintAlbumsCompsCommand.class);

    // Конструктор класу, який ініціалізує об'єкт MusicService
    public PrintAlbumsCompsCommand(MusicService musicService) {
        this.musicService = musicService; // Ініціалізуємо musicService
    }

    // Реалізація методу execute() інтерфейсу Command, що виконує команду
    @Override
    public void execute() {
        // Логування інформаційного повідомлення про друк альбомів і композицій
        logger.info("\n" +"Друк альбомів і композицій");
        // Викликаємо метод printAlbumsAndCompositions() з MusicService для виведення альбомів і композицій
        musicService.printAlbumsAndCompositions();
    }

    // Реалізація методу description() інтерфейсу Command, який повертає опис команди
    @Override
    public String description() {
        return "Друк альбомів і композицій"; // Опис команди
    }
}
