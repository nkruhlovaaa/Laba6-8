package lr4.menu;

import lr4.music.MusicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PrintAlbumsCompsCommandTest {
    
    private MusicService musicService; // The mock object
    private PrintAlbumsCompsCommand printAlbumsCompsCommand; // The command to test

    @BeforeEach
    public void setUp() {
        musicService = mock(MusicService.class); // Create a mock of MusicService
        printAlbumsCompsCommand = new PrintAlbumsCompsCommand(musicService); // Inject the mock into the command
    }

    @Test
    public void testExecute_ShouldPrintAlbumsAndCompositions() {
        // Act
        printAlbumsCompsCommand.execute(); // Execute the command

        // Assert
        verify(musicService, times(1)).printAlbumsAndCompositions(); // Verify that printAlbumsAndCompositions() was called once
    }
}
