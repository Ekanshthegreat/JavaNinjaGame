package project11;

/**
 * All constants for the game
 */
public class Constants {
    
    // Panel constants
    private static final int TILE_SIZE = 32;
    private static final int PLAY_COLUMNS = 15;
    private static final int PLAY_ROWS = 10;
    private static final int BORDER_TILES = 1;
    private static final int DATA_TILES = 2;

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

    // Damage constants
    private static int HOLE_DAMAGE = 10;
    private static int SAMURAI_DAMAGE = 10;
    
    public static int getHoleDamage() {
        return HOLE_DAMAGE;
    }
    public static void setHoleDamage(int damage) {
        HOLE_DAMAGE = damage;
    }
    public static int getSamuraiDamage() {
        return SAMURAI_DAMAGE;
    }
    public static void setSamuraiDamage(int damage) {
        SAMURAI_DAMAGE = damage;
    }

    // Game constants
    private static int TOTAL_ITEMS = 3;
    private static int ITEM_SCORE = 10;
    private static int TOTAL_BONUS_ITEMS = 1;
    private static int BONUS_SCORE = 50;
    private static int TOTAL_HOLES = 10;
    private static int GAME_DIFFICULTY = 0;
    
    public static int getTotalItems() {
        return TOTAL_ITEMS;
    }
    public static int getItemScore() {
        return ITEM_SCORE;
    }
    public static int getTotalBonusItems() {
        return TOTAL_BONUS_ITEMS;
    }
    public static int getBonusScore() {
        return BONUS_SCORE;
    }
    public static int getTotalHoles() {
        return TOTAL_HOLES;
    }
    public static int getGameDifficulty() {
        return GAME_DIFFICULTY;
    }
    

    

}
