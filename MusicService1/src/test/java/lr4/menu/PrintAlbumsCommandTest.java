package lr4.menu;

import lr4.music.MusicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PrintAlbumsCommandTest {
    
    private MusicService musicService; // The mock object
    private PrintAlbumsCommand printAlbumsCommand; // The command to test

    @BeforeEach
    public void setUp() {
        musicService = mock(MusicService.class); // Create a mock of MusicService
        printAlbumsCommand = new PrintAlbumsCommand(musicService); // Inject the mock into the command
    }

    @Test
    public void testExecute_ShouldPrintAlbums() {
        // Act
        printAlbumsCommand.execute(); // Execute the command

        // Assert
        verify(musicService, times(1)).printAlbums(); // Verify that printAlbums() was called once
    }
}
