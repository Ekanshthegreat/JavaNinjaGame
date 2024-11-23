package project11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RendererTest {

    private Renderer renderer;
    private Graphics graphics;
    private GameObject[][] gameObjects;

    @BeforeEach
    void setUp() {
        renderer = new Renderer();
        graphics = mock(Graphics.class); // Mock Graphics object
        gameObjects = new GameObject[5][5]; // Create a 5x5 grid of game objects
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
        // We mock the drawSprite method to verify it's called with the correct arguments.
        verify(graphics).drawImage(eq(renderer.getGroundSprite()), anyInt(), anyInt(), eq(Constants.getTileSize()), eq(Constants.getTileSize()), isNull());
        verify(graphics).drawImage(eq(renderer.getWallSprite()), anyInt(), anyInt(), eq(Constants.getTileSize()), eq(Constants.getTileSize()), isNull());
        verify(graphics).drawImage(eq(renderer.getHoleSprite()), anyInt(), anyInt(), eq(Constants.getTileSize()), eq(Constants.getTileSize()), isNull());
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
