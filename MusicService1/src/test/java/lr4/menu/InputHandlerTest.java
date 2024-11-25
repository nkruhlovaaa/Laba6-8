package lr4.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InputHandlerTest {

    @Test
    public void testGetString() {
        // Arrange
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("Hello, World!");
        InputHandler.setInstance(mockScanner); // Set the mock instance

        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        InputHandler inputHandler = InputHandler.getInstance();

        // Act
        String result = inputHandler.getString("Enter something: ");

        // Assert
        assertEquals("Hello, World!", result, "The input should match the mocked input.");

        // Reset standard output
        System.setOut(originalOut);
    }

    @Test
    public void testGetInt_ValidInput() {
        // Arrange
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.hasNextInt()).thenReturn(true);
        when(mockScanner.nextInt()).thenReturn(42);
        InputHandler.setInstance(mockScanner); // Set the mock instance

        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        InputHandler inputHandler = InputHandler.getInstance();

        // Act
        int result = inputHandler.getInt("Enter a number: ");

        // Assert
        assertEquals(42, result, "The output should be the integer input.");
        assertTrue(outContent.toString().contains("Enter a number: "), "Output prompt should be correct.");

        // Reset standard output
        System.setOut(originalOut);
    }

    @Test
    public void testGetInt_InvalidInput() {
        // Arrange
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.hasNextInt()).thenReturn(false).thenReturn(true);
        when(mockScanner.nextInt()).thenReturn(5);
        InputHandler.setInstance(mockScanner); // Set the mock instance

        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        InputHandler inputHandler = InputHandler.getInstance();

        // Act
        int result = inputHandler.getInt("Enter a number: ");

        // Assert
        assertEquals(5, result, "The output should be the valid integer input after invalid input.");
        assertTrue(outContent.toString().contains("Invalid input. Please enter a number."), 
                   "Should prompt for valid input after invalid entry.");

        // Reset standard output
        System.setOut(originalOut);
    }
}

