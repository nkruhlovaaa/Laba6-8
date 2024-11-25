package lr4.filemanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import lr4.music.MusicService;
import lr4.music.Album;
import java.io.File;
import java.util.List;

public class FileManagerTest {
    private MusicService musicService;
    private String filePath;
    
    @BeforeEach
    public void setUp() {
        musicService = new MusicService(); 
        Album album1 = new Album("Test Album1", "Test Artist1");
        Album album2 = new Album("Test Album2", "Test Artist2");
        musicService.addAlbum(album1);
        musicService.addAlbum(album2);
        filePath = "testfile.ser";
    }

    @AfterEach
    public void tearDown() {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testDataManaging() {
        FileManager.serializeAlbums(musicService.getAlbums(), filePath);
        List<Album> albums = FileManager.deserializeAlbums(filePath);
        List<Album> expectedAlbums = musicService.getAlbums();
        Assertions.assertEquals(musicService.getAlbums().size(), albums.size());
        Assertions.assertEquals(expectedAlbums.get(0).getName(), albums.get(0).getName()); 
        Assertions.assertEquals(expectedAlbums.get(1).getName(), albums.get(1).getName());
    }

    @Test
    public void testFileDoesNotExist() {
        filePath = "nonexistentfile";
        List<Album> albums = FileManager.deserializeAlbums(filePath);
        Assertions.assertNull(albums);
    }
}
