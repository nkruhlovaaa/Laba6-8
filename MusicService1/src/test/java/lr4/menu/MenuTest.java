package lr4.menu;

import lr4.music.MusicService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class MenuTest {
    private MusicService musicService;
    private InputHandler inputHandler;
    private Menu menu;

    @BeforeEach
    public void setUp() {
        menu = new Menu(musicService, inputHandler);
    }

    @Test
    public void testInitializeCommands_ShouldMapAllCommands() {
        Map<Integer, Command> commandMap = menu.getCommandMap();

        assertNotNull(commandMap);
        assertEquals(9, commandMap.size());
        
        assertTrue(commandMap.containsKey(1)); // CreateAlbumCommand
        assertTrue(commandMap.containsKey(2)); // AddCompositionCommand
        assertTrue(commandMap.containsKey(3)); // SortByStyleCommand
        assertTrue(commandMap.containsKey(4)); // GetCompsWithinBoundsCommand
        assertTrue(commandMap.containsKey(5)); // SaveToFileCommand
        assertTrue(commandMap.containsKey(6)); // LoadFromFileCommand
        assertTrue(commandMap.containsKey(7)); // PrintAlbumsCommand
        assertTrue(commandMap.containsKey(8)); // PrintAlbumsCompsCommand
        assertTrue(commandMap.containsKey(9)); // ExitCommand
    }

    @Test
    public void testPrintCommands_ShouldPrintAllAvailableCommands() {
        InputStream in = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(in);

        menu.printCommands();

        assertDoesNotThrow(() -> menu.printCommands());
    }
}
