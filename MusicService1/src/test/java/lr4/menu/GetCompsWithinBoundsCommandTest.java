package lr4.menu;

import lr4.music.MusicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetCompsWithinBoundsCommandTest {
    private MusicService musicService;
    private GetCompsWithinBoundsCommand getCompsWithinBoundsCommand;
    private InputHandler inputHandler;

    @BeforeEach
    public void setUp() {
        musicService = mock(MusicService.class);
        inputHandler = mock(InputHandler.class);
        getCompsWithinBoundsCommand = new GetCompsWithinBoundsCommand(musicService, inputHandler);
    }

    @Test
    public void testExecute_WhenBoundsProvided() {
        // Arrange
        when(inputHandler.getInt("Enter lower bound: ")).thenReturn(100);
        when(inputHandler.getInt("Enter upper bound: ")).thenReturn(500);

        // Act
        getCompsWithinBoundsCommand.execute();

        // Capture the bounds passed to getCompsWithinBounds
        ArgumentCaptor<Integer> boundCaptor1 = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> boundCaptor2 = ArgumentCaptor.forClass(Integer.class);

        // Verify that the correct method was called on musicService with the provided bounds
        verify(musicService).getCompsWithinBounds(boundCaptor1.capture(), boundCaptor2.capture());

        // Assert that the captured bounds are correct
        assertEquals(100, boundCaptor1.getValue());
        assertEquals(500, boundCaptor2.getValue());
    }

    @Test
    public void testExecute_WhenExceptionThrown() {
        // Arrange: Simulate an exception being thrown during input handling
        when(inputHandler.getInt(anyString())).thenThrow(new RuntimeException("Invalid input"));

        // Act & Assert: Ensure that execute handles the exception and doesn't throw it further
        assertDoesNotThrow(() -> getCompsWithinBoundsCommand.execute());

        // Verify that no call to getCompsWithinBounds was made since the exception occurred
        verify(musicService, never()).getCompsWithinBounds(anyInt(), anyInt());
    }
}

