package project11;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles key input for the game
 */
public class KeyHandler implements KeyListener {
    /*
     * Booleans for each key
     */
    public boolean up, down, left, right;

    /**
     * Reset input before new input
     */
    public KeyHandler() {
        resetInput();
    }

    /**
     * Sets all values to false
     */
    public void resetInput() {
        up = false;
        down = false;
        left = false;
        right = false;
    }

    /**
     * Press event
     */
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) up = true;
        if(code == KeyEvent.VK_S) down = true;
        if(code == KeyEvent.VK_A) left = true;
        if(code == KeyEvent.VK_D) right = true;
    }

    /**
     * Release event
     */
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) up = false;
        if(code == KeyEvent.VK_S) down = false;
        if(code == KeyEvent.VK_A) left = false;
        if(code == KeyEvent.VK_D) right = false;
    }

    /**
     * Not used
     */
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}
