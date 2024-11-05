package project11;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Draws the game objects to the screen
 */
public class Renderer {
    private Image groundSprite, holeSprite, jumpingShoesSprite, ninjaSprite, samuraiSprite, spawnSprite, wallSprite;
    private Image keySprite, chestSprite; // New images for Key and Chest

    public Renderer() {
        try {
            groundSprite = ImageIO.read(getClass().getResource("/project11/sprites/Ground.png"));
            holeSprite = ImageIO.read(getClass().getResource("/project11/sprites/Hole.png"));
            jumpingShoesSprite = ImageIO.read(getClass().getResource("/project11/sprites/JumpingShoes.png"));
            ninjaSprite = ImageIO.read(getClass().getResource("/project11/sprites/Ninja.png"));
            samuraiSprite = ImageIO.read(getClass().getResource("/project11/sprites/Samurai.png"));
            spawnSprite = ImageIO.read(getClass().getResource("/project11/sprites/spawn.png"));
            wallSprite = ImageIO.read(getClass().getResource("/project11/sprites/Wall.png"));
            keySprite = ImageIO.read(getClass().getResource("/project11/sprites/Key.png")); // Load Key image
            chestSprite = ImageIO.read(getClass().getResource("/project11/sprites/Chest.png")); // Load Chest image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g, GameObject[][] gameObjects) {
        int tileSize = GamePanel.getTileSize();
        
        // Offsets for border and data tiles
        int xOffset = GamePanel.getBorderTiles() * tileSize;
        int yOffset = GamePanel.getDataTiles() * tileSize;
    
        // Draw every GameObject in the gameObjects array
        for (int row = 0; row < gameObjects.length; row++) {
            for (int col = 0; col < gameObjects[row].length; col++) {
                GameObject obj = gameObjects[row][col];
                if (obj != null) {
                    // Apply offset
                    int x = col * tileSize + xOffset;
                    int y = row * tileSize + yOffset + GamePanel.getBorderTiles() * tileSize;

                    switch (obj.getTypeId()) {
                        case 1: g.drawImage(groundSprite, x, y, tileSize, tileSize, null); break;
                        case 2: g.drawImage(holeSprite, x, y, tileSize, tileSize, null); break;
                        case 3: g.drawImage(jumpingShoesSprite, x, y, tileSize, tileSize, null); break; // Bonus Item
                        case 4: g.drawImage(samuraiSprite, x, y, tileSize, tileSize, null); break;
                        case 5: g.drawImage(ninjaSprite, x, y, tileSize, tileSize, null); break;
                        case 6: g.drawImage(spawnSprite, x, y, tileSize, tileSize, null); break;
                        case 7: g.drawImage(wallSprite, x, y, tileSize, tileSize, null); break;
                        case 8: g.drawImage(keySprite, x, y, tileSize, tileSize, null); break; // Mandatory Item
                        case 9: g.drawImage(chestSprite, x, y, tileSize, tileSize, null); break; // Chest
                        default: break;
                    }
                }
            }
        }
    }
}
