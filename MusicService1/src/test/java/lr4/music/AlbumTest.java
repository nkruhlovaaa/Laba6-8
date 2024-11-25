package lr4.music;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlbumTest {
    private Album album;
    private Composition comp1;
    private Composition comp2;
    private Composition comp3;

    @BeforeEach
    public void setUp() {
        // Initialize an Album and some Compositions before each test
        album = new Album("Test Album", "Test Author");
        comp1 = new Composition("Song One","Author One", 120, "Pop");
        comp2 = new Composition("Song Two","Author Two", 300, "Rock");
        comp3 = new Composition("Song Three","Author Three", 180, "Jazz");
    }

    @Test
    public void testAddComposition_ShouldIncreaseTotalDuration() {
        // Arrange
        int initialDuration = album.getTotalDuration();
        
        // Act
        album.addComposition(comp1);
        album.addComposition(comp2);

        // Assert
        assertEquals(initialDuration + comp1.getDuration() + comp2.getDuration(), album.getTotalDuration());
    }

    @Test
    public void testGetCompsWithinBounds_ShouldReturnCorrectCompositions() {
        // Arrange
        album.addComposition(comp1);
        album.addComposition(comp2);
        album.addComposition(comp3);
        
        // Act
        List<Composition> result = album.getCompsWithinBounds(100, 250);

        // Assert
        assertEquals(2, result.size());
        assertEquals(comp1, result.get(0)); // Only comp1 is within bounds
    }

    @Test
    public void testSortByStyle_ShouldSortCompositionsCorrectly() {
        // Arrange
        album.addComposition(comp2); // Rock
        album.addComposition(comp3); // Jazz
        album.addComposition(comp1); // Pop
        
        // Act
        album.sortByStyle("a");

        // Assert
        List<Composition> sortedAlbum = album.getAlbum();
        assertEquals(comp3, sortedAlbum.get(0)); // Jazz should come first
        assertEquals(comp1, sortedAlbum.get(1)); // Pop should come second
        assertEquals(comp2, sortedAlbum.get(2)); // Rock should come last
    }

    @Test
    public void testGetAlbum_ShouldReturnCorrectAlbumDetails() {
        // Act
        String albumDetails = album.toString();

        // Assert
        assertEquals("Test Album - Test Author", albumDetails);
    }

    @Test
    public void testPrintAlbum_ShouldNotThrowException() {
        // Act & Assert
        assertDoesNotThrow(() -> album.printAlbum());
    }
}
