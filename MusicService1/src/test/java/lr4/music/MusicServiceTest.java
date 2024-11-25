package lr4.music;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MusicServiceTest {

    private MusicService musicService;
    private Album mockAlbum;
    private Composition mockComposition;

    @BeforeEach
    public void setUp() {
        musicService = new MusicService();
        mockAlbum = mock(Album.class);
        mockComposition = mock(Composition.class);
    }

    @Test
    public void testAddAlbum_ShouldAddAlbumToList() {
        // Arrange
        musicService.addAlbum(mockAlbum);
        // Act
        List<Album> albums = musicService.getAlbums();

        // Assert
        assertTrue(albums.contains(mockAlbum), "Album should be added to the albums list");
    }

    @Test
    public void testGetAlbum_WhenAlbumExists() {
        // Arrange
        when(mockAlbum.getName()).thenReturn("Test Album");
        musicService.addAlbum(mockAlbum);

        // Act
        Album retrievedAlbum = musicService.getAlbum("Test Album");

        // Assert
        assertNotNull(retrievedAlbum, "Retrieved album should not be null");
        assertEquals("Test Album", retrievedAlbum.getName(), "Album name should match");
    }

    @Test
    public void testGetAlbum_WhenAlbumDoesNotExist() {
        // Act
        Album retrievedAlbum = musicService.getAlbum("Non-Existent Album");

        // Assert
        assertNull(retrievedAlbum, "Album should be null when it doesn't exist");
    }

    @Test
    public void testAddComposition_ShouldAddCompositionToAlbum() {
        // Arrange
        List<Composition> compositions = new ArrayList<>();  // Mutable list
        when(mockAlbum.getCompositions()).thenReturn(compositions);  // Return mutable list

        // Act
        musicService.addComposition(mockAlbum, mockComposition);

        // Assert
        assertTrue(compositions.contains(mockComposition), "Composition should be added to the album");
        verify(mockAlbum, times(1)).getCompositions();
    }

    @Test
    public void testSortByStyle_ShouldSortAlbumsByStyle() {
        // Arrange
        musicService.addAlbum(mockAlbum);

        // Act
        musicService.sortByStyle("a");

        // Assert
        verify(mockAlbum, times(1)).sortByStyle("a");
    }

    @Test
    public void testGetCompsWithinBounds() {
        // Arrange
        when(mockAlbum.getName()).thenReturn("Test Album");
        when(mockAlbum.getCompsWithinBounds(100, 300)).thenReturn(List.of(mockComposition));
        musicService.addAlbum(mockAlbum);

        // Act
        musicService.getCompsWithinBounds(100, 300);

        // Assert
        verify(mockAlbum, times(1)).getCompsWithinBounds(100, 300);
    }
}
