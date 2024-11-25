package lr4.menu;// package lr4.menu;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import lr4.music.MusicService;

// public class CreateAlbumCommandTest {
//     private MusicService musicService;
//     private CreateAlbumCommand createAlbumCommand;

//     @Mock  
//     private InputHandler inputHandler;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this); // Initialize mocks
//         musicService = new MusicService();
//         createAlbumCommand = new CreateAlbumCommand(musicService, inputHandler);
//     }

//     @Test
//     public void testExecute() {
//         // Arrange
//         when(inputHandler.getString("Enter album name: ")).thenReturn("Test Album");
//         when(inputHandler.getString("Enter album author: ")).thenReturn("Test Author");

//         // Act
//         createAlbumCommand.execute();

//         // Assert
//         assertNotNull(musicService.getAlbum("Test Album")); // Assuming there's a method to get an album by name
//         assertEquals("Test Author", musicService.getAlbum("Test Album").getAuthor()); // Assuming Album has a getAuthor method
//     }
// }
