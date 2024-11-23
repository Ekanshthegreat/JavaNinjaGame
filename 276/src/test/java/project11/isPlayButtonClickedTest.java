package project11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class isPlayButtonClickedTest {

    private GamePanel gamePanel;

    @BeforeEach
    void setUp() {
        GameState mockGameState = new GameState();
        KeyHandler mockKeyHandler = new KeyHandler();
        gamePanel = new GamePanel(mockGameState, mockKeyHandler);
    }

    @Test
    void TestPlayButtonClickedInRange() {
        int x = gamePanel.getWidth() / 2; // Simulate a click within the button's range
        int y = (gamePanel.getHeight() / 2) + 50;
        assertTrue(gamePanel.testIsPlayButtonClicked(x, y), "Expected true for click inside the play button.");
    }

    @Test
    void TestPlayButtonClickedOutOfRange() {
        int x = 0; // Simulate a click far away from the button
        int y = 0;
        assertFalse(gamePanel.testIsPlayButtonClicked(x, y), "Expected false for click outside the play button.");
    }

    @Test
    void TestPlayButtonClickedNegativeX() {
        int x = -10; // Simulate a click with a negative x-coordinate
        int y = (gamePanel.getHeight() / 2) + 50;
        assertTrue(gamePanel.testIsPlayButtonClicked(x, y), "Expected false for click with negative x-coordinate.");
    }

    @Test
    void TestPlayButtonClickedNegativeY() {
        int x = gamePanel.getWidth() / 2;
        int y = -10; // Simulate a click with a negative y-coordinate
        assertFalse(gamePanel.testIsPlayButtonClicked(x, y), "Expected false for click with negative y-coordinate.");
    }

    @Test
    void TestPlayButtonClickedEdge() {
        int buttonWidth = 100;
        int buttonHeight = 40;
        int buttonX = (gamePanel.getWidth() - buttonWidth) / 2;
        int buttonY = (gamePanel.getHeight() - buttonHeight) / 2 + 50;

        int x = buttonX + buttonWidth; // Simulate a click on the right edge of the button
        int y = buttonY + buttonHeight; // Simulate a click on the bottom edge of the button
        assertTrue(gamePanel.testIsPlayButtonClicked(x, y), "Expected true for click on the button's edge.");
    }
}
