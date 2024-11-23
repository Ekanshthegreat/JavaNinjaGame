package project11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ButtonClickTest {

    private GamePanel gamePanel;

    @BeforeEach
    void setUp() {
        GameState mockGameState = new GameState();
        KeyHandler mockKeyHandler = new KeyHandler();
        gamePanel = new GamePanel(mockGameState, mockKeyHandler);
    }

    // Play Button Tests
    @Test
    void testPlayButtonClickedInRange() {
        int x = gamePanel.getWidth() / 2; // Simulate a click within the Play button's range
        int y = (gamePanel.getHeight() / 2) + 50;
        assertTrue(gamePanel.testIsButtonClicked(x, y, Constants.PLAY_BUTTON), "Expected true for click inside the Play button.");
    }

    @Test
    void testPlayButtonClickedOutOfRange() {
        int x = 0; // Simulate a click far away from the Play button
        int y = 0;
        assertFalse(gamePanel.testIsButtonClicked(x, y, Constants.PLAY_BUTTON), "Expected false for click outside the Play button.");
    }

    @Test
    void testPlayButtonClickedNegativeX() {
        int x = -10; // Simulate a click with a negative x-coordinate
        int y = (gamePanel.getHeight() / 2) + 50;
        assertTrue(gamePanel.testIsButtonClicked(x, y, Constants.PLAY_BUTTON), "Expected false for click with negative x-coordinate.");
    }

    @Test
    void testPlayButtonClickedNegativeY() {
        int x = gamePanel.getWidth() / 2;
        int y = -10; // Simulate a click with a negative y-coordinate
        assertFalse(gamePanel.testIsButtonClicked(x, y, Constants.PLAY_BUTTON), "Expected false for click with negative y-coordinate.");
    }

    @Test
    void testPlayButtonClickedEdge() {
        int buttonWidth = 100;
        int buttonHeight = 40;
        int buttonX = (gamePanel.getWidth() - buttonWidth) / 2;
        int buttonY = (gamePanel.getHeight() - buttonHeight) / 2 + 50;

        int x = buttonX + buttonWidth; // Simulate a click on the right edge of the Play button
        int y = buttonY + buttonHeight; // Simulate a click on the bottom edge of the Play button
        assertTrue(gamePanel.testIsButtonClicked(x, y, Constants.PLAY_BUTTON), "Expected true for click on the Play button's edge.");
    }

    // Easy Button Tests
    @Test
    void testEasyButtonClickedInRange() {
        int x = (gamePanel.getWidth() / 2) - 150; // Simulate a click within the Easy button's range
        int y = gamePanel.getHeight() / 2 + 120;
        assertTrue(gamePanel.testIsButtonClicked(x, y, Constants.EASY_BUTTON), "Expected true for click inside the Easy button.");
    }

    @Test
    void testEasyButtonClickedOutOfRange() {
        int x = 0; // Simulate a click far away from the Easy button
        int y = 0;
        assertFalse(gamePanel.testIsButtonClicked(x, y, Constants.EASY_BUTTON), "Expected false for click outside the Easy button.");
    }

    @Test
    void testEasyButtonClickedNegativeX() {
        int x = -10; // Simulate a click with a negative x-coordinate
        int y = gamePanel.getHeight() / 2 + 120;
        assertFalse(gamePanel.testIsButtonClicked(x, y, Constants.EASY_BUTTON), "Expected false for click with negative x-coordinate.");
    }

    @Test
    void testEasyButtonClickedNegativeY() {
        int x = (gamePanel.getWidth() / 2) - 150;
        int y = -10; // Simulate a click with a negative y-coordinate
        assertFalse(gamePanel.testIsButtonClicked(x, y, Constants.EASY_BUTTON), "Expected false for click with negative y-coordinate.");
    }

    @Test
    void testEasyButtonClickedEdge() {
        int buttonWidth = 100;
        int buttonHeight = 40;
        int buttonX = (gamePanel.getWidth() / 2) - 150;
        int buttonY = gamePanel.getHeight() / 2 + 120;

        int x = buttonX + buttonWidth; // Simulate a click on the right edge of the Easy button
        int y = buttonY + buttonHeight; // Simulate a click on the bottom edge of the Easy button
        assertTrue(gamePanel.testIsButtonClicked(x, y, Constants.EASY_BUTTON), "Expected true for click on the Easy button's edge.");
    }

    // Medium Button Tests
    @Test
    void testMediumButtonClickedInRange() {
        int x = gamePanel.getWidth() / 2 - 50; // Simulate a click within the Medium button's range
        int y = gamePanel.getHeight() / 2 + 120;
        assertTrue(gamePanel.testIsButtonClicked(x, y, Constants.MEDIUM_BUTTON), "Expected true for click inside the Medium button.");
    }

    @Test
    void testMediumButtonClickedOutOfRange() {
        int x = 0; // Simulate a click far away from the Medium button
        int y = 0;
        assertFalse(gamePanel.testIsButtonClicked(x, y, Constants.MEDIUM_BUTTON), "Expected false for click outside the Medium button.");
    }

    @Test
    void testMediumButtonClickedNegativeX() {
        int x = -10; // Simulate a click with a negative x-coordinate
        int y = gamePanel.getHeight() / 2 + 120;
        assertTrue(gamePanel.testIsButtonClicked(x, y, Constants.MEDIUM_BUTTON), "Expected false for click with negative x-coordinate.");
    }

    @Test
    void testMediumButtonClickedNegativeY() {
        int x = gamePanel.getWidth() / 2 - 50;
        int y = -10; // Simulate a click with a negative y-coordinate
        assertFalse(gamePanel.testIsButtonClicked(x, y, Constants.MEDIUM_BUTTON), "Expected false for click with negative y-coordinate.");
    }

    @Test
    void testMediumButtonClickedEdge() {
        int buttonWidth = 100;
        int buttonHeight = 40;
        int buttonX = gamePanel.getWidth() / 2 - 50;
        int buttonY = gamePanel.getHeight() / 2 + 120;

        int x = buttonX + buttonWidth; // Simulate a click on the right edge of the Medium button
        int y = buttonY + buttonHeight; // Simulate a click on the bottom edge of the Medium button
        assertTrue(gamePanel.testIsButtonClicked(x, y, Constants.MEDIUM_BUTTON), "Expected true for click on the Medium button's edge.");
    }

    // Hard Button Tests
    @Test
    void testHardButtonClickedInRange() {
        int x = gamePanel.getWidth() / 2 + 50; // Simulate a click within the Hard button's range
        int y = gamePanel.getHeight() / 2 + 120;
        assertTrue(gamePanel.testIsButtonClicked(x, y, Constants.HARD_BUTTON), "Expected true for click inside the Hard button.");
    }

    @Test
    void testHardButtonClickedOutOfRange() {
        int x = 0; // Simulate a click far away from the Hard button
        int y = 0;
        assertFalse(gamePanel.testIsButtonClicked(x, y, Constants.HARD_BUTTON), "Expected false for click outside the Hard button.");
    }

    @Test
    void testHardButtonClickedNegativeX() {
        int x = -10; // Simulate a click with a negative x-coordinate
        int y = gamePanel.getHeight() / 2 + 120;
        assertFalse(gamePanel.testIsButtonClicked(x, y, Constants.HARD_BUTTON), "Expected false for click with negative x-coordinate.");
    }

    @Test
    void testHardButtonClickedNegativeY() {
        int x = gamePanel.getWidth() / 2 + 50;
        int y = -10; // Simulate a click with a negative y-coordinate
        assertFalse(gamePanel.testIsButtonClicked(x, y, Constants.HARD_BUTTON), "Expected false for click with negative y-coordinate.");
    }

    @Test
    void testHardButtonClickedEdge() {
        int buttonWidth = 100;
        int buttonHeight = 40;
        int buttonX = gamePanel.getWidth() / 2 + 50;
        int buttonY = gamePanel.getHeight() / 2 + 120;

        int x = buttonX + buttonWidth; // Simulate a click on the right edge of the Hard button
        int y = buttonY + buttonHeight; // Simulate a click on the bottom edge of the Hard button
        assertTrue(gamePanel.testIsButtonClicked(x, y, Constants.HARD_BUTTON), "Expected true for click on the Hard button's edge.");
    }
}
