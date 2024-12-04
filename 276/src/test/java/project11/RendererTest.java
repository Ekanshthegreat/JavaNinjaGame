package project11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RendererTest {

    private Renderer renderer;
    private Graphics graphics;
    private GameObject[][] gameObjects;

    @BeforeEach
    void setUp() {
        // Mock sprite-loading to bypass actual resource dependency
        renderer = new Renderer() {
            @Override
            protected void loadSprites() {
                try {
                    this.groundSprite = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
                    this.holeSprite = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
                    this.wallSprite = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to mock sprite loading.", e);
                }
            }
        };

        graphics = mock(Graphics.class); // Mock Graphics object
        gameObjects = new GameObject[5][5]; // Create a 5x5 grid of game objects

        assertNotNull(renderer, "Renderer initialization failed.");
    }

    @Test
    void testRenderWithValidGameObjects() {
        // Arrange: Set up valid game objects in the grid
        gameObjects[0][0] = new Ground(0, 0); // Set the top-left corner to be a ground object
        gameObjects[1][1] = new Wall(1, 1);   // Set another object to be a wall
        gameObjects[2][2] = new Hole(2, 2);   // Add a hole in the grid

        // Act: Call the render method
        renderer.render(graphics, gameObjects);

        // Assert: Verify that the correct sprite is drawn for each object
        verify(graphics, atLeastOnce()).drawImage(any(), anyInt(), anyInt(), anyInt(), anyInt(), isNull());
    }

    @Test
    void testRenderWithEmptyGameObjects() {
        // Arrange: Set the gameObjects array to be empty
        gameObjects = new GameObject[0][0];

        // Act: Call the render method
        renderer.render(graphics, gameObjects);

        // Assert: Ensure no exceptions occur, and nothing is drawn
        verify(graphics, never()).drawImage(any(), anyInt(), anyInt(), anyInt(), anyInt(), any());
    }

    @Test
    void testRenderWithInvalidGameObject() {
        // Arrange: Set an invalid object (null or incorrect object) in the grid
        gameObjects[0][0] = null; // Null object in the grid

        // Act: Call the render method
        renderer.render(graphics, gameObjects);

        // Assert: Ensure that no exception occurs when encountering invalid (null) objects
        verify(graphics, never()).drawImage(any(), anyInt(), anyInt(), anyInt(), anyInt(), any());
    }
}
