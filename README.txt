Advanced Programming Project
Stick Hero Game
Group no-59
Team Members- Samridh Gupta(2022441) , Tanmay Singhal (2022535)

   How to run:
1. Extract the project from our zip file.
2. Run the Main.java file in the Intellij IDE.
3. Now the code runs.
Note: If you are getting an error download java-fx media dependency and junit dependency.

Prerequisites:
1. JavaFX-Media-18.01
2. Junit 4

Instructions for the game:
1. Please do not jitter click the mouse buttons and only tap the keyboard buttons.
2. While moving on pressing Space our character gets flipped, so he can take the cherries which are spawned.
3. Hold on the left mouse button to extend the stick to the required length.

Design Patterns:
1. Singleton: We use the singleton design pattern to generate an instance for the player which is called using the getPlayer() function.
2. Decorator:
Code Structure:
First the GameTest class is run which runs the junit tests for checking initial conditions of the game.
Then in main file we use launch(args) to launch our code.
First we call setup for setting up the initial elements of the game such as the player,the stick and the platforms,etc.
This method is also used whenever we successfully move ahead a platform to update the old platforms and score and create new platforms.
Next we check for input using mouse pressed and mouse released in which while the mouse is held the stick keeps expending.
Within it rest other methods which check that length of the stick is as per requirement.
We use keypressed to