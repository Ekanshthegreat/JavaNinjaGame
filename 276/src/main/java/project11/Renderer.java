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

    /**
     * Load all sprites
     */
    public Renderer() {
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
     * Draw a sprite on call
     * @param g Graphics object
     * @param image Image object
     * @param x X Coordinate
     * @param y Y Coordinate
     */
    public void drawSprite(Graphics g, Image image, int x ,int y){
        g.drawImage(image, x, y, Constants.getTileSize(), Constants.getTileSize(),null);
    }

    /**
     * Draws all GameObjects on call
     * @param g Graphics object
     * @param gameObjects Current game objects to be drawn
     */
    public void render(Graphics g, GameObject[][] gameObjects) {
        if (gameObjects == null) {
            throw new NullPointerException("GameObjects is null");
        }
        int xOffset = Constants.getBorderTiles() * Constants.getTileSize();
        int yOffset = Constants.getDataTiles() * Constants.getTileSize() + Constants.getBorderTiles() * Constants.getTileSize();
    
        for (int row = 0; row < gameObjects.length; row++) {
            for (int col = 0; col < gameObjects[row].length; col++) {
                GameObject obj = gameObjects[row][col];
                if (obj != null) {
                    int x = col * Constants.getTileSize() + xOffset;
                    int y = row * Constants.getTileSize() + yOffset;

                    switch (obj.getTypeId()) {
                        case 1: drawSprite(g, groundSprite, x, y); break;
                        case 2: drawSprite(g, holeSprite, x, y); break;
                        case 3: drawSprite(g, jumpingShoesSprite, x, y); break;
                        case 4: drawSprite(g, samuraiSprite, x, y); break;
                        case 5: drawSprite(g, ninjaSprite, x, y); break;
                        case 6: drawSprite(g, wallSprite, x, y); break;
                        case 7: drawSprite(g, spawnSprite, x, y); break;
                        case 8: drawSprite(g, keySprite, x, y); break;
                        case 9: drawSprite(g, chestSprite, x, y); break;
                        default: break;
                    }
                }
            }
        }
    }

    // Test functions

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
    /**
     * Get the hole sprite
     * @return holeSprite
     */
    public Image getHoleSprite() {
        return holeSprite;
    }
    /**
     * Get the jumping shoes sprite
     * @return jumpingShoesSprite
     */
    public Image getJumpingShoesSprite() {
        return jumpingShoesSprite;
    }
    /**
     * Get the ninja sprite
     * @return ninjaSprite
     */
    public Image getNinjaSprite() {
        return ninjaSprite;
    }
    /**
     * Get the samurai sprite
     * @return samuraiSprite
     */
    public Image getSamuraiSprite() {
        return samuraiSprite;
    }
    /**
     * Get the spawn sprite
     * @return spawnSprite
     */
    public Image getSpawnSprite() {
        return spawnSprite;
    }
    /**
     * Get the key sprite
     * @return keySprite
     */
    public Image getKeySprite() {
        return keySprite;
    }
    /**
     * Get the chest sprite
     * @return chestSprite
     */
    public Image getChestSprite() {
        return chestSprite;
    }

}
