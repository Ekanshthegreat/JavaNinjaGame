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
    private static final int TILE_SIZE = 32;
    private static final int PLAY_COLUMNS = 15;
    private static final int PLAY_ROWS = 10;
    private static final int BORDER_TILES = 1;
    private static final int DATA_TILES = 2;

    private boolean isGameStarted = false;
    private boolean difficultySelected = false;

    public static int getTileSize() {
        return TILE_SIZE;
    }
    public static int getPlayColumns() {
        return PLAY_COLUMNS;
    }
    public static int getPlayRows() {
        return PLAY_ROWS;
    }
    public static int getBorderTiles() {
        return BORDER_TILES;
    }
    public static int getDataTiles() {
        return DATA_TILES;
    }

    protected GameState gameState;
    private Renderer renderer;

    public GamePanel(GameState gameState, KeyHandler keyHandler) {
        this.gameState = gameState;
        this.renderer = new Renderer(TILE_SIZE);
        int width = (PLAY_COLUMNS + 2 * BORDER_TILES) * TILE_SIZE;
        int height = (PLAY_ROWS + 2 * BORDER_TILES + DATA_TILES) * TILE_SIZE;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isGameStarted) {
                    int mouseX = e.getX();
                    int mouseY = e.getY();

                    if (isPlayButtonClicked(mouseX, mouseY)) {
                        isGameStarted = true;
                        repaint();
                    }

                    if (isEasyButtonClicked(mouseX, mouseY)) {
                        gameState.setDifficulty("easy");
                        difficultySelected = true;
                        isGameStarted = true; // Start game after difficulty is selected
                        requestFocusInWindow(); // Focus to enable key events
                        repaint();
                    } else if (isMediumButtonClicked(mouseX, mouseY)) {
                        gameState.setDifficulty("medium");
                        difficultySelected = true;
                        isGameStarted = true; // Start game after difficulty is selected
                        requestFocusInWindow(); // Focus to enable key events
                        repaint();
                    } else if (isHardButtonClicked(mouseX, mouseY)) {
                        gameState.setDifficulty("hard");
                        difficultySelected = true;
                        isGameStarted = true; // Start game after difficulty is selected
                        requestFocusInWindow(); // Focus to enable key events
                        repaint();
                    }
                }
            }
        });
    }

    private boolean isPlayButtonClicked(int mouseX, int mouseY) {
        int buttonWidth = 100;
        int buttonHeight = 40;
        int buttonX = (getWidth() - buttonWidth) / 2;
        int buttonY = (getHeight() - buttonHeight) / 2 + 50;
        return mouseX >= buttonX && mouseX <= buttonX + buttonWidth &&
               mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
    }

    private boolean isEasyButtonClicked(int mouseX, int mouseY) {
        int buttonX = (getWidth() / 2) - 150;
        int buttonY = getHeight() / 2 + 120;
        return mouseX >= buttonX && mouseX <= buttonX + 100 && mouseY >= buttonY && mouseY <= buttonY + 40;
    }

    private boolean isMediumButtonClicked(int mouseX, int mouseY) {
        int buttonX = getWidth() / 2 - 50;
        int buttonY = getHeight() / 2 + 120;
        return mouseX >= buttonX && mouseX <= buttonX + 100 && mouseY >= buttonY && mouseY <= buttonY + 40;
    }

    private boolean isHardButtonClicked(int mouseX, int mouseY) {
        int buttonX = getWidth() / 2 + 50;
        int buttonY = getHeight() / 2 + 120;
        return mouseX >= buttonX && mouseX <= buttonX + 100 && mouseY >= buttonY && mouseY <= buttonY + 40;
    }

    public void render() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isGameStarted || !difficultySelected) {
            drawStartScreen(g);
        } else {
            renderer.render(g, gameState.getGameObjects());
            g.setColor(Color.WHITE);
            g.drawString("Score: " + gameState.getScore(), 10, TILE_SIZE);
            g.setColor(Color.CYAN);
            g.drawString("Items: " + gameState.getCollectedItems() + "/" + gameState.getTotalItems() + "    Bonus Item: " + gameState.getBonusItem(), 10, TILE_SIZE * 2);
            g.setColor(Color.GREEN);
            g.drawString("Tip: Use WASD to move, collect all keys and reach the chest to win!", 10, TILE_SIZE * (PLAY_ROWS + 2 * BORDER_TILES + DATA_TILES));
        }
    }

    private void drawStartScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Ninja Game", getWidth() / 2 - 100, getHeight() / 2 - 40);

        g.setFont(new Font("Arial", Font.PLAIN, 24));
        int buttonWidth = 100;
        int buttonHeight = 40;
        int buttonX = (getWidth() - buttonWidth) / 2;
        int buttonY = (getHeight() - buttonHeight) / 2 + 50;
        g.drawRect(buttonX, buttonY, buttonWidth, buttonHeight);
        g.drawString("Play", buttonX + 25, buttonY + 28);

        drawButton(g, "Easy", (getWidth() / 2) - 150, getHeight() / 2 + 120);
        drawButton(g, "Medium", (getWidth() / 2) - 50, getHeight() / 2 + 120);
        drawButton(g, "Hard", (getWidth() / 2) + 50, getHeight() / 2 + 120);
    }

    private void drawButton(Graphics g, String label, int x, int y) {
        int buttonWidth = 100;
        int buttonHeight = 40;
        g.drawRect(x, y, buttonWidth, buttonHeight);
        g.drawString(label, x + 15, y + 28);
    }
}
