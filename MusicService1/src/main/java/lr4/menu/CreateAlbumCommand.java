package lr4.menu;

import lr4.music.Album;
import lr4.music.MusicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateAlbumCommand implements Command {
    private MusicService musicService;
    private String albumName;
    private String author;
    InputHandler inputHandler;
    Logger logger = LoggerFactory.getLogger(CreateAlbumCommand.class);

    public CreateAlbumCommand(MusicService musicService, InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        this.musicService = musicService;
    }

    @Override
    public void execute() {
        try{
            albumName = inputHandler.getString("\n" + "Введіть назву альбому: ");
            author = inputHandler.getString("\n" + "Введіть автора альбому:");
            Album album = new Album(albumName, author);
            musicService.addAlbum(album);
            logger.info("\n" + "Альбом успішно створено");
        }catch(Exception e){
            logger.error("\n" + "Сталася помилка під час створення альбому", e);
        }

    }

    @Override
    public String description() {
        return "Створіть альбом";
    }
}
