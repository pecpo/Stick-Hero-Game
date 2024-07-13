# Stick Hero Game

**Group no-59**  
**Team Members:**  
- Samridh Gupta (2022441)
- Tanmay Singhal (2022535)

**GitHub Repository:** [Stick Hero Game](https://github.com/pecpo/APProject)

## How to Run

1. Extract the project from the zip file.
2. Run the `Main.java` file in the Intellij IDE.
3. The code should now run.

**Note:** If you encounter any errors, download the JavaFX Media dependency and JUnit dependency.

## Prerequisites

1. JavaFX-Media-18.01
2. Junit 4

## Creative Twists

1. Background Music
2. Special Sound Effect on getting a cherry
3. Lifetime cherry counter for better tracking of cherries
4. Character Icon change on the main screen

## Instructions for the Game

1. While moving, pressing Space will flip our character, allowing him to take the cherries that spawn.
2. Hold the left mouse button to extend the stick to the required length.
3. Advance further to get a higher score.

## Design Patterns

1. **Singleton:** We use the singleton design pattern to generate an instance for the player, which is called using the `getPlayer()` function.
2. **Decorator:** We used the decorator design pattern within the scoreboard class. We have created a `Board` interface for which there exists an `SBDecorator` class. This `SBDecorator` class is extended by `BlackSBDecorator`, which updates the text style of the scoreboard to black text and bold.

## Code Structure

1. First, the `GameTest` class is run, which runs the JUnit tests to check the initial conditions of the game.
2. In the main file, we use `launch(args)` to launch our code.
3. First, we call `setup` for setting up the initial elements of the game, such as the player, the stick, the platforms, etc. This method is also used whenever we successfully move ahead a platform to update the old platforms and score, and create new platforms.
4. Next, we check for input using `mousePressed` and `mouseReleased`. While the mouse is held, the stick keeps extending. Within it rest other methods which check that the length of the stick is as per requirement.
5. We use `keyPressed` to flip the character during the movement on the stick.
6. We check for collisions with the cherry and the platform while the player is flipped. If the player hits a cherry, a sound is emitted and the cherry is collected.
7. When the platform is hit, it takes the user to a game over screen. In this game over screen, there is an option to go back to the main menu and another for revive provided you have at least 2 cherries.
8. If the length of the stick is less than the distance between the platforms or larger than the second platform's width, the user is taken to the same game over screen.
9. The high score and last score are serialized into files so that progress can be restored later. A counter for lifetime cherries is also maintained, which is serialized as well.
10. Whenever the game starts, the scores are deserialized from the file to show the last score, the high score, and the lifetime cherries.
