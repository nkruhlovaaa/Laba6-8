package lr4.menu;

import lr4.music.Album;
import lr4.music.Composition;
import lr4.music.MusicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import java.util.List;

public class AddCompositionCommandTest {
    private MusicService musicService;
    private InputHandler inputHandler;
    private AddCompositionCommand addCompositionCommand;

@BeforeEach
public void setUp() {
    MockitoAnnotations.openMocks(this); // Initialize mocks
    musicService = Mockito.mock(MusicService.class); // Mock MusicService
    inputHandler = Mockito.mock(InputHandler.class); // Mock InputHandler

    // Create a dummy album to return when getAlbum is called
    Album album = new Album("Test Album", "Test Artist");
    when(musicService.getAlbum("Test Album")).thenReturn(album); // Mock behavior

    addCompositionCommand = new AddCompositionCommand(musicService, inputHandler); // Inject mocks
}


@Test
public void testExecute_WhenAlbumFound() {
    // Arrange
    addCompositionCommand = new AddCompositionCommand(musicService, inputHandler);

    // Mock user input
    when(inputHandler.getString("Enter album name: ")).thenReturn("Test Album");
    when(inputHandler.getString("Enter composition title: ")).thenReturn("Test Composition");
    when(inputHandler.getString("Enter author: ")).thenReturn("Test Author");
    when(inputHandler.getInt("Enter duration: ")).thenReturn(300);
    when(inputHandler.getString("Enter style: ")).thenReturn("Pop");

    // Create a mock album and set it up to be found
    Album mockAlbum = mock(Album.class);
    when(mockAlbum.getName()).thenReturn("Test Album");

    // Mock the albums list to include the mock album
    when(musicService.getAlbums()).thenReturn(List.of(mockAlbum));

    // Act
    addCompositionCommand.execute();

    // Capture the Composition that was added
    ArgumentCaptor<Composition> compositionCaptor = ArgumentCaptor.forClass(Composition.class);

    // Verify that addComposition was called on the MusicService with the correct album
    verify(musicService).addComposition(eq(mockAlbum), compositionCaptor.capture());

    // Extract the captured Composition object
    Composition addedComposition = compositionCaptor.getValue();

    // Assert that the composition was created with the expected values
    assertNotNull(addedComposition);
    assertEquals("Test Composition", addedComposition.getTitle());
    assertEquals("Test Author", addedComposition.getAuthor());
    assertEquals(300, addedComposition.getDuration());
    assertEquals("Pop", addedComposition.getStyle());
}

    
    @Test
    public void testExecute_WhenAlbumNotFound() {
        when(inputHandler.getString("Enter album name: ")).thenReturn("Nonexistent Album");
        addCompositionCommand.execute();
        verify(musicService, never()).addComposition(any(), any()); // Ensure no composition is added
    }

    @Test
    public void testExecute_WhenExceptionOccurs() {
        when(inputHandler.getString("Enter album name: ")).thenReturn("Test Album");
        when(inputHandler.getInt("Enter duration: ")).thenThrow(new RuntimeException("Input error")); // Simulate an error

        addCompositionCommand.execute();

        verify(musicService, never()).addComposition(any(), any()); // Ensure no composition is added
    }
}
