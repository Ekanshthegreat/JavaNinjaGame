package project11;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * GamePanel for creating a game window, extends JPanel
 */
public class GamePanel extends JPanel {

    /**
     * Is the game started
     */
    private boolean isGameStarted = false;
    /**
     * Is the difficulty selected
     */
    private boolean difficultySelected = false;

    /*
     * GameState object
     */
    protected GameState gameState;
    /**
     * Renderer object
     */
    private Renderer renderer;

    /**
     * Constructor for GamePanel
     * @param gameState GameState object
     * @param keyHandler KeyHandler object
     */
    public GamePanel(GameState gameState, KeyHandler keyHandler) {
        if (gameState == null) {
            throw new NullPointerException("GameState is null");
        }
        if (keyHandler == null) {
            throw new NullPointerException("KeyHandler is null");
        }
        this.gameState = gameState;
        this.addKeyListener(keyHandler);
        this.renderer = new Renderer();
        int width = (Constants.getPlayColumns() + 2 * Constants.getBorderTiles()) * Constants.getTileSize();
        int height = (Constants.getPlayColumns() + 2 * Constants.getBorderTiles() + Constants.getDataTiles()) * Constants.getTileSize();
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    /**
     * Handle mouse click events
     * @param e MouseEvent object
     */
    private void handleMouseClick(MouseEvent e) {
        if (!isGameStarted) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if (isButtonClicked(mouseX, mouseY, Constants.PLAY_BUTTON)) {
                isGameStarted = true;
                repaint();
            } else if (isButtonClicked(mouseX, mouseY, Constants.EASY_BUTTON)) {
                handleDifficultySelection(0); // 0 for Easy
            } else if (isButtonClicked(mouseX, mouseY, Constants.MEDIUM_BUTTON)) {
                handleDifficultySelection(1); // 1 for Medium
            } else if (isButtonClicked(mouseX, mouseY, Constants.HARD_BUTTON)) {
                handleDifficultySelection(2); // 2 for Hard
            }
        }
    }

    /**
     * Handle difficulty selection
     * @param difficultyLevel The selected difficulty level
     */
    private void handleDifficultySelection(int difficultyLevel) {
        gameState.setDifficulty(difficultyLevel);
        difficultySelected = true;
        isGameStarted = true; // Start game after difficulty is selected
        requestFocusInWindow(); // Focus to enable key events
        repaint();
    }

    /**
     * Check if a button is clicked
     * @param x Mouse X coordinate
     * @param y Mouse Y coordinate
     * @param buttonType Type of button (e.g., Constants.PLAY_BUTTON, Constants.EASY_BUTTON)
     * @return True if the specified button is clicked, false otherwise
     */
    public boolean isButtonClicked(int x, int y, int buttonType) {
        ButtonProperties props = getButtonProperties(buttonType);
        if (props == null) return false;

        // Check if the click is within the bounds of the button
        return x >= props.x && x <= props.x + props.width && y >= props.y && y <= props.y + props.height;
    }

    /**
     * Get button properties based on button type
     * @param buttonType Type of button
     * @return ButtonProperties object containing x, y, width, and height
     */
    private ButtonProperties getButtonProperties(int buttonType) {
        int buttonX = 0;
        int buttonY = 0;
        int buttonWidth = 100;
        int buttonHeight = 40;

        switch (buttonType) {
            case Constants.PLAY_BUTTON:
                buttonX = (getWidth() - buttonWidth) / 2;
                buttonY = (getHeight() / 2) + 50;
                break;
            case Constants.EASY_BUTTON:
                buttonX = (getWidth() / 2) - 150;
                buttonY = (getHeight() / 2) + 120;
                break;
            case Constants.MEDIUM_BUTTON:
                buttonX = (getWidth() / 2) - 50;
                buttonY = (getHeight() / 2) + 120;
                break;
            case Constants.HARD_BUTTON:
                buttonX = (getWidth() / 2) + 50;
                buttonY = (getHeight() / 2) + 120;
                break;
            default:
                return null; // Invalid button type
        }

        return new ButtonProperties(buttonX, buttonY, buttonWidth, buttonHeight);
    }

    /**
     * Inner class to hold button properties
     */
    private class ButtonProperties {
        int x, y, width, height;

        public ButtonProperties(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    /**
     * Calls repaint() to update window
     */
    public void render() {
        repaint();
    }

    @Override
    /**
     * Paint the game window
     * @param g Graphics object
     */
    public void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            if (!isGameStarted || !difficultySelected) {
                drawStartScreen(g);
            } else {
                renderer.render(g, gameState.getGameObjects());
                g.setColor(Color.WHITE);
                g.drawString("Score: " + gameState.getScore(), 10, Constants.getTileSize());
                g.setColor(Color.CYAN);
                g.drawString("Items: " + gameState.getCollectedItems() + "/" + Constants.getTotalItems() + "    Bonus Item: " + gameState.getBonusItem(), 10, Constants.getTileSize() * 2);
                g.setColor(Color.GREEN);
                g.drawString("Tip: Use WASD to move, collect all keys and reach the chest to win!", 10, Constants.getTileSize() * (Constants.getPlayRows() + 2 * Constants.getBorderTiles() + Constants.getDataTiles()));
            }
        } catch (Exception e) {
            System.err.println("Error in paintComponent: " + e.getMessage());
        }
    }

    /**
     * Draw the start screen
     * @param g Graphics object
     */
    private void drawStartScreen(Graphics g) {
        int buttonWidth = 100;
        int buttonHeight = 40;
        int buttonX = (getWidth() - buttonWidth) / 2;
        int buttonY = (getHeight() - buttonHeight) / 2 + 50;
        try {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Ninja Game", getWidth() / 2 - 100, getHeight() / 2 - 40);

            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawRect(buttonX, buttonY, buttonWidth, buttonHeight);
            g.drawString("Play", buttonX + 25, buttonY + 28);

            drawButton(g, "Easy", (getWidth() / 2) - 150, getHeight() / 2 + 120);
            drawButton(g, "Medium", (getWidth() / 2) - 50, getHeight() / 2 + 120);
            drawButton(g, "Hard", (getWidth() / 2) + 50, getHeight() / 2 + 120);
        } catch (Exception e) {
            System.err.println("Error in drawStartScreen: " + e.getMessage());
        }
    }

    /**
     * Draw a button
     * @param g Graphics object
     * @param label Button label
     * @param x x-coordinate
     * @param y y-coordinate
     */
    private void drawButton(Graphics g, String label, int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative");
        }
        int buttonWidth = 100;
        int buttonHeight = 40;
        g.drawRect(x, y, buttonWidth, buttonHeight);
        g.drawString(label, x + 15, y + 28);
    }

    /**
     * Test public function for testing
     * @param mouseX Mouse X coordinate
     * @param mouseY Mouse Y coordinate
     * @param buttonType Button type
     * @return True if button is clicked, false otherwise
     */
    public boolean testIsButtonClicked(int mouseX, int mouseY, int buttonType) {
        return isButtonClicked(mouseX, mouseY, buttonType);
    }
}
