package project11;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Draws the game objects to the screen
 */
public class Renderer {
    // Local Variables
    private Image groundSprite, holeSprite, jumpingShoesSprite, ninjaSprite, samuraiSprite, spawnSprite, wallSprite;
    private Image keySprite, chestSprite;
    private int tileSize;

    /**
     * Load all sprites
     * @param tileSize Size of each Tile
     */
    public Renderer(int tileSize) {
        this.tileSize = tileSize;
        loadSprites();
    }

    /**
     * Load all sprites from 'sprite' folder
     */
    private void loadSprites() {
        try {
            groundSprite = ImageIO.read(getClass().getResource("/project11/sprites/Ground.png"));
            holeSprite = ImageIO.read(getClass().getResource("/project11/sprites/Hole.png"));
            jumpingShoesSprite = ImageIO.read(getClass().getResource("/project11/sprites/JumpingShoes.png"));
            ninjaSprite = ImageIO.read(getClass().getResource("/project11/sprites/Ninja.png"));
            samuraiSprite = ImageIO.read(getClass().getResource("/project11/sprites/Samurai.png"));
            spawnSprite = ImageIO.read(getClass().getResource("/project11/sprites/spawn.png"));
            wallSprite = ImageIO.read(getClass().getResource("/project11/sprites/Wall.png"));
            keySprite = ImageIO.read(getClass().getResource("/project11/sprites/Key.png"));
            chestSprite = ImageIO.read(getClass().getResource("/project11/sprites/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws all GameObjects on call
     * @param g Graphics object
     * @param gameObjects Current game objects to be drawn
     */
    public void render(Graphics g, GameObject[][] gameObjects) {
        int xOffset = Constants.getBorderTiles() * tileSize;
        int yOffset = Constants.getDataTiles() * tileSize + Constants.getBorderTiles() * tileSize;
    
        for (int row = 0; row < gameObjects.length; row++) {
            for (int col = 0; col < gameObjects[row].length; col++) {
                GameObject obj = gameObjects[row][col];
                if (obj != null) {
                    int x = col * tileSize + xOffset;
                    int y = row * tileSize + yOffset;

                    switch (obj.getTypeId()) {
                        case 1: g.drawImage(groundSprite, x, y, tileSize, tileSize, null); break;
                        case 2: g.drawImage(holeSprite, x, y, tileSize, tileSize, null); break;
                        case 3: g.drawImage(jumpingShoesSprite, x, y, tileSize, tileSize, null); break;
                        case 4: g.drawImage(samuraiSprite, x, y, tileSize, tileSize, null); break;
                        case 5: g.drawImage(ninjaSprite, x, y, tileSize, tileSize, null); break;
                        case 6: g.drawImage(wallSprite, x, y, tileSize, tileSize, null); break;
                        case 7: g.drawImage(spawnSprite, x, y, tileSize, tileSize, null); break;
                        case 8: g.drawImage(keySprite, x, y, tileSize, tileSize, null); break;
                        case 9: g.drawImage(chestSprite, x, y, tileSize, tileSize, null); break;
                        default: break;
                    }
                }
            }
        }
    }

    /**
     * Get the wall sprite
     * @return wallSprite
     */
    public Image getWallSprite() {
        return wallSprite;
    }

    /**
     * Get the ground sprite
     * @return groundSprite
     */
    public Image getGroundSprite() {
        return groundSprite;
    }
}
