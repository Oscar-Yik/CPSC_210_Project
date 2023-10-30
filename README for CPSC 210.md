# My Personal Project

## Introduction

For this project, I want to make a manic shooter game with pixelated graphics and dynamic 
movement capabilities. I am personally a huge fan of these types of games such as *Soul Knight*,
*Realm of the Mad God*, *Vampire Survivors*, and many others. Through this class project, 
I would like to put my programming skills to the test to make something that is entertaining 
for me and hopefully other people as well. My goal for this game is to create a short 20-30 minute 
fast-paced entertaining experience for anyone who has a few minutes to spare.

## Specifications

The game will revolve around a playable character in the middle of the screen who will 
journey around a fantasy world hunting monsters. After achieving the objective, the game will reset
back to the title screen and need to be replayed. However, every replay of the game will increase 
the size of the world map, increase the difficulty of the enemies, and randomize the map entirely. 
In addition to these features, each replay will allow the character to change or upgrade their 
equipment to prepare them for the next leg of the journey. This data will be stored as a save state
and allow the user to continue their adventure when they want to play again. After the user completes
the game, they will be able to continually replay the game with the enemies increasing in quantity and 
difficulty infinitely (adding arbitrary number of "**X**" enemies to the world "**Y**"). 
Increasing the character's level will correspond to increasing the character's attributes and abilities 
in various ways. The exact implementation and detail of these features may vary as time is limited. 

## User Stories
- As a user, I want to be able to replay the game by adding an arbitrary number of enemies (**X**) to my world (**Y**)
- As a user, I want to be able to save my world state (player, enemies, world attributes)
- As a user, I want to be able to load the game to continue right where I left off previously
- As a user, I want to explore an infinitely expanding world
- As a user, I want my character to move smoothly across the screen 
- As a user, I want to feel relaxed and entertained when playing the game
- As a user, I don't want to feel bored while playing the game
- As a user, I want to move my character with the arrow keys 
- As a user, I want to view the level of my character when I play the game
- As a user, I want to be able to see my abilities when playing
- As a user, I want to be able to select which enemy my character is targeting

## Instructions for Grader
- You can generate the first required action related to adding X's to Y by
  - pressing the "Start Game" button with the mouse 
  - moving the player with the "W, A, S, D" keys on the keyboard 
  - the first action is that the all enemies follow you
- You can generate the first required action related to adding X's to Y by 
  - continuing from the first action, 
    - clicking the mouse when the player is close to the enemies
    - both the player and the enemy's health bar decreases
- You can locate my visual component by pressing the "Start Game" button with the mouse
- You can save the state of my application by
  - pressing "Start Game" with the mouse
  - pressing the "escape" key on the keyboard 
  - pressing the "Save" button with your mouse
- You can reload the state of my application by pressing the "Load" button with the mouse

You can also add an arbitrary number of enemies to the game by pressing the "+" button on the menu

## Phase 4: Task 2
Tue Aug 08 20:41:53 PDT 2023
Game paused
Tue Aug 08 20:41:58 PDT 2023
Player defeated an enemy
Tue Aug 08 20:41:58 PDT 2023
All enemies defeated, player wins!
Tue Aug 08 20:42:01 PDT 2023
Added an enemy
Tue Aug 08 20:42:13 PDT 2023
Player defeated an enemy
Tue Aug 08 20:42:16 PDT 2023
Player defeated an enemy
Tue Aug 08 20:42:16 PDT 2023
All enemies defeated, player wins!
Tue Aug 08 20:42:21 PDT 2023
Game loaded from ./data/world.json
Tue Aug 08 20:42:30 PDT 2023
Player defeated an enemy
Tue Aug 08 20:42:31 PDT 2023
Player defeated an enemy
Tue Aug 08 20:42:33 PDT 2023
Player defeated an enemy
Tue Aug 08 20:42:35 PDT 2023
Game paused
Tue Aug 08 20:42:36 PDT 2023
Game saved to ./data/world.json

## Phase 4: Task 3
In my opinion, my game is extremely unpolished and there are dozens of classes/methods/interfaces that need to be 
implemented and refactored. It is really because I ran out of time that I can't make these changes at the time of 
writing. Specifically, there needs to be a new Screen abstract class, an interface with general behaviour such as 
update and draw, the overall coupling with the playing object needs to be fixed, and many design patterns need to 
be added to clean up the code. 

I need to make a new screen abstract class because the GameOverScreen, GameWinScreen, and PauseScreen have almost 
identical behaviour which needs to be abstracted. An abstract class needs to be made because certain methods 
are the same for all three classes which need to be implemented in the new Screen class. Also, it doesn't make sense
in the context of the game to have an arbitrary screen object instantiated which makes the abstract class the 
correct choice. 

A few new interfaces need to be made so that the game can remain consistent when new features are implemented. At the
moment, adding new features is extremely painful because similar behaviour between classes is not properly 
"documented". You would have to manually check for similar behaviour when implementing a new feature which is not ideal.
Having new interfaces would simplify and organize the structure of the game immensely and serve as a powerful 
tool when scaling up the game. 

The coupling in the game is terrible because small changes in the playing class result in cascading failures in 
pretty much the entire game. In order to make the game function before the phase 3 deadline, I didn't care what objects
were being passed to what classes or what fields were created. Looking back now, I can see that some objects don't 
need to be fields in certain classes and some methods don't actually need some of their parameters. These errors are 
all a result of trying to debug the game under a time crunch. 

Finally, adding the newly learned design patterns such as the singleton pattern would make the code so much neater 
and organized. There are many classes such as the game, gamewindow, and others would benefit from using the singleton
pattern because there are only ever one of each when the game is played. Also, the observer pattern would definitely 
help in feeding information for example, from the Game to the enemyHandler.

In conclusion, these are the changes I would make to polish my code if I had more time.