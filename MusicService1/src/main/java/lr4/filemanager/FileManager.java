package lr4.filemanager;
import lr4.music.Album;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileManager {
    private static final Logger logger = LoggerFactory.getLogger(FileManager.class.getName());

    // Серіалізація списку альбомів
    public static void serializeAlbums(List<Album> albums, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(albums);
            logger.info("Альбоми успішно сереалізовані.");
            logger.info("\n" + "Альбоми, збережені у файл: " + filePath);
        } catch (IOException e) {
            logger.error("\n" + "Не вдалося сереілізувати альбоми: " + e.getMessage(), e);
        }
    }

    // Десеріалізація списку альбомів
    public static List<Album> deserializeAlbums(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<Album> albums = new ArrayList<>();
                for (Object element : (List<?>) obj) {
                    if (element instanceof Album) {
                        albums.add((Album) element);
                    } else {
                        logger.warn("\n" + "Елемент не належить до типу Альбом");
                        throw new ClassCastException("Елемент не належить до типу Album");
                    }
                }
                logger.info("\n" + "Альбоми успішно десеріалізовано.");
                return albums;
            } else {
                logger.warn("\n" + "Об’єкт не має типу List");
                throw new ClassCastException("\n" + "Об’єкт не має типу List");
            }
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            logger.error("\n" + "Не вдалося десеріалізувати альбоми: " + e.getMessage(), e);
        }
        return null;
    }
}
