Advanced Programming Project
Stick Hero Game
Group no-59
Team Members- Samridh Gupta(2022441) , Tanmay Singhal (2022535)

GitHub Repository: https://github.com/pecpo/APProject

   How to run:
1. Extract the project from our zip file.
2. Run the Main.java file in the Intellij IDE.
3. Now the code runs.
Note: If you are getting an error download java-fx media dependency and junit dependency.

Prerequisites:
1. JavaFX-Media-18.01
2. Junit 4

Creative Twists:
1. Background Music
2. Special Sound Effect on getting cherry
3. Lifetime cherry counter for better tracking of cherries.
4. Character Icon change on main screen.

Instructions for the game:
1. While moving on pressing Space our character gets flipped, so he can take the cherries which are spawned.
2. Hold on the left mouse button to extend the stick to the required length.
3. We have to advance further to get more score.

Design Patterns:
1. Singleton: We use the singleton design pattern to generate an instance for the player which is called using the getPlayer() function.
2. Decorator: We used the decorator design pattern within the scoreboard class. We have created a board interface for which exists a SBDecorator class.
   This SBDecorator class is extended by Black SB Decorator which updates the text style of the scoreboard to black text and bold.

Code Structure:
First the GameTest class is run which runs the junit tests for checking initial conditions of the game.
Then in main file we use launch(args) to launch our code.
First we call setup for setting up the initial elements of the game such as the player,the stick and the platforms,etc.
This method is also used whenever we successfully move ahead a platform to update the old platforms and score and create new platforms.
Next we check for input using mouse pressed and mouse released in which while the mouse is held the stick keeps expending.
Within it rest other methods which check that length of the stick is as per requirement.
We use key-pressed to flip the character during the movement on the stick.
We check for collisions with the cherry and the platform while the player is flipped and if player hits cherry a sound is
emitted and the cherry is collected.
When the platform is hit it takes the user to a game over screen.
In this game over screen there is an option to go back to main menu and another for revive provided you have atleast 2 cherries.
If the length of the stick is less than the distance between the platforms or larger than the 2nd platform's width the user
is taken to the same game over screen.
The high score and last score is serialized into files so that progress can be restored later.
A counter for lifetime cherries is also maintained which is serialized as well.
Whenever the game starts the scores are deserialized from the file to show the last score and the high score along with
the lifetime cherries.
