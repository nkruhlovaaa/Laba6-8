package lr4.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import lr4.music.Composition;
import lr4.music.Album;

public class SortByStyleCommandTest {
    Album album = new Album("Testtitle", "Test author");
    @Test
    public void testSortByStyle() {
        Composition composition = new Composition("title", "author", 1, "b");
        Composition composition2 = new Composition("title2", "author2", 2, "a");
        Composition composition3 = new Composition("title3", "author3", 3, "c");
        album.addComposition(composition);
        album.addComposition(composition2);
        album.addComposition(composition3);
        album.sortByStyle("a");
        assertEquals(composition2, album.getAlbum().get(0));    // a
        assertEquals(composition, album.getAlbum().get(1));    // b
        assertEquals(composition3, album.getAlbum().get(2));    // c
    }
}
