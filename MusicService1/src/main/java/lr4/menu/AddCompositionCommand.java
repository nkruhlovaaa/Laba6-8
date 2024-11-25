package lr4.menu;

import lr4.music.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddCompositionCommand implements Command {
    private MusicService musicService;
    private Album album;
    private Composition composition;
    private InputHandler inputHandler;
    private Logger logger = LoggerFactory.getLogger(AddCompositionCommand.class);

    public AddCompositionCommand(MusicService musicService, InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        this.musicService = musicService;
    }

    public Album findAlbum(String name) {
        for (Album album : musicService.getAlbums()) {
            if (album.getName().equalsIgnoreCase(name)) {
                return album;
            }
        }
        return null; // Повертає null, якщо не знайдено
    }

    @Override
    public void execute() {
        try {

// Отримати назву альбому з вхідних даних
            String albumName = inputHandler.getString("\n" + "Введіть назву альбому:");


// Знайдіть альбом за назвою
            album = findAlbum(albumName);
            if (album == null) {
                logger.warn("\n" + "Альбом не знайдено");
                return;// Вийти, якщо альбом не знайдено
            }

            logger.info("\n" + "Додавання композиції в альбом");

            // Створення нової композиції з введенням користувача
            composition = new Composition(
                inputHandler.getString("\n" + "Введіть назву композиції: "),
                inputHandler.getString("\n" + "Введіть автора: "),
                inputHandler.getInt("\n" + "Введіть тривалість:"),
                inputHandler.getString("\n" + "Введіть стиль: ")
            ); 

            // Додати композицію в альбом за допомогою musicService
            musicService.addComposition(album, composition);

            logger.info("\n" + "Композицію успішно додано");
        } catch (Exception e) {
            logger.error("Сталася помилка під час додавання композиції до альбому", e);
        }
    }

    @Override
    public String description(){return "Додати композицію до альбому";}
}
