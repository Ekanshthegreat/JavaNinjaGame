# Ninja Game

## Description
**Ninja Game** is a grid-based maze game where the player navigates through barriers, collects items, and avoids enemies. The goal is to collect all mandatory items (keys) and reach the chest to win the game. The difficulty level adjusts the enemy behavior and the maze layout.

---

## How to Build and Run

### Prerequisites
1. **Java Development Kit (JDK):**
   - Ensure you have JDK 11 or higher installed.
   - To check your JDK version:
     ```bash
     java -version
     ```
   - [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **Apache Maven:**
   - Ensure Maven is installed to manage dependencies and build the project.
   - To check your Maven version:
     ```bash
     mvn -version
     ```
   - [Install Maven](https://maven.apache.org/install.html)

---

### Steps to Build
1. Clone or download the repository:
   ```bash
   git clone <repository-url>
   cd <project-folder>
   1.  bashCopy codemvn compile
    
2.  bashCopy codemvn package
    

### Steps to Run

#### Using an IDE:

1.  Open your IDE (e.g., IntelliJ IDEA, Eclipse).
    
2.  Navigate to the src/main/java/project11/Main.java file.
    
3.  Press the **Run** button or execute the file.
    

#### Using the Packaged JAR:

1.  bashCopy codejava -jar target/.jar
    

How to Test
-----------

### Running Tests

1.  Navigate to the project directory.
    
2.  bashCopy codemvn test
    

### Testing Details

*   **Unit Tests:** Validates the core functionality of individual components (e.g., GameObject, EnemyAI).
    
*   **Integration Tests:** Tests interactions between major components like the maze, enemies, and player.
    

### Test Output

*   yamlCopy codeTests run: , Failures: 0, Errors: 0, Skipped: 0
    
*   **If any tests fail,** details will be shown in the console output.
    

Gameplay Instructions
---------------------

### Start the Game:

1.  Click the **Play** button to start.
    
2.  Select a difficulty: Easy, Medium, or Hard.
    

### Controls:

*   Use **WASD** keys to move:
    
    *   **W:** Move Up
        
    *   **A:** Move Left
        
    *   **S:** Move Down
        
    *   **D:** Move Right
        

### Objectives:

*   **Collect all keys** (mandatory items) and bonus items for additional points.
    
*   Avoid stepping into holes or colliding with enemies.
    
*   Reach the chest to win the game.
    

### Winning Condition:

1.  You must **collect all mandatory items** before reaching the chest.
    
2.  Your score must remain **above zero** to stay in the game
