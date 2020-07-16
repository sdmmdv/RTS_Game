# TRIPLE S – TOWER DEFENCE
## Java RTS_Game Group Project


## General Info

Tower defense (TD) is a subgenre of strategy video game where the goal is to defend a player's territories or possessions by obstructing the enemy attackers, usually achieved by placing defensive structures on or along their path of attack.[1] This typically means building a variety of different structures that serve to automatically block, impede, attack or destroy enemies. Tower defense is seen as a subgenre of real-time strategy video games, due to its real-time origins,[2][3] though many modern tower defense games include aspects of turn-based strategy. Strategic choice and positioning of defensive elements is an essential strategy of the genre.

## Configuration / Build

Open the project in Intellij IDEA/ Eclipse

Add external libraries in ```/"libs"```

Add following path to VM options : ```-Djava.library.path="path/to/lwjgl/libs"```





## USE CASES

- Actors: player, system

(system: known as the proper game who “controls” the enemies)

PLAYER. Functions:

	1) Open Game: click the button to open the game and show the menu with the different levels.
	2) Select Level: choose the level to play
	
	3) Play level:
		3.1) Construct tower by selecting its type and position
			3.1.1) What happens if there’s not enough money to construct a tower
			3.1.2) What happens if the player chooses a position where a tower cannot be built
		3.2) Upgrade tower
			3.2.1)  What happens if the player doesn’t have enough money to upgrade a tower
			3.2.2) What happens if the player doesn’t have enough XP to upgrade a tower

SYSTEM. Functions:
	
	1)  Show the level menu: when the game is opened by the player, the game should display the menu with the five different levels that can be played.

	2)  Charge level: when a level is selected the game shows a different page with the 		corresponded scenario. It also calculates the type of enemies the level is going to have, how are they going to appear, what is its walking speed and the damage they are going to make to the tower.

	3) Make the player towers shoot automatically:
		3.1)  Select how the tower is going to be upgraded
		3.2) What happens when a tower shoots at an enemy.
		3.3) What happens when a tower vanish an enemy.
 	
	4) Make the enemies appear and attack the main tower

CHARACTERS:

- Towers: 
 1) Archer: 
    - Basic: It uses arrows for shooting the enemies (High shooting range, Medium shooting speed, Low damage)
    - Upgrade 1: It shoots 3 arrows at the same time.
    - Upgrade 2: Range, damage and shooting speed will improve.

 2) Electro:
    - Basic: It uses electric current to freeze enemies for 1-2 seconds (Low shooting range, High shooting speed, Low damage)
    - Upgrade 1: It will slow down (freeze) the enemy using additional ultraviolet magnetic thunder.
    - Upgrade 2: Range, damage and shooting speed will improve.

 3) Terminator: 
    - Basic: It destroys enemies with atomic rocket (Long shooting range, High shooting speed, High damage)
    - Upgrade 1: It will make instant radiation zone, which decreases the enemy's health gradually.
    - Upgrade 2: Range, damage and shooting speed will improve

- Enemies:
 1) Goblin: Low Durability, Medium Speed
 2) MadKnight: High Durability, Medium speed
 3) NutCracker: Medium Durability, High Speed
 4) HyperKid: Low Durability, High speed
 5) BioStrong: The Boss, High Durability, High Speed



- As a player, I want to open the game

GIVEN	The open game button

WHEN	The player clicks on the button with the mouse

THEN	The program gets opened and the menu level is shown


- As a player, I want to select a level

GIVEN	A level menu

WHEN	The player selects a level

THEN	The level gets opened allowing the player to play

 
- As a player, I want to play a level

GIVEN	A level scenario

WHEN	The player clicks on the start button

THEN	The level gets started and the game begins


- As a player, I want to construct a tower

GIVEN	A tower menu and an amount of money

WHEN	The player selects a type of tower and an allowed location

THEN	A tower of the selected type is constructed where the player asked to



GIVEN	A tower menu and an amount of money

WHEN	The player doesn’t have enough money

THEN	A message is shown in the screen to advice the player to save more money first


GIVEN	A tower menu and an amount of money

WHEN	The player tries to build a tower in a not allowed location

THEN	A message is shown in the screen to advice the player to ask for another location


- As a player, I want to upgrade a tower

GIVEN	A built tower, XP and money

WHEN	The player gets enough XP and money

THEN	The player can upgrade the attack and the damage of a tower


GIVEN	A built tower, XP and money

WHEN	The player doesn’t have enough XP

THEN	A message is shown in the screen to advice the player that he has to kill more enemies to be allowed to earn more XP


GIVEN	A built tower, XP and money

WHEN	The player doesn’t have enough money

THEN	A message is shown in the screen to advice the player that he has to earn more money to upgrade the tower


- As the system, I want to show the level menu

GIVEN	An open game button

WHEN	The player clicks that button

THEN	The level menu is displayed in the screen


- As the system, I want to charge a level

GIVEN	A level menu

WHEN	The player selects the level he wants to play

THEN	The system displays the scenario for the selected level, decides the type of enemies that are going to appear in that level and calculates how often are they going to appear, their walking speed and the damage they can make to the main tower.


- As the system, I want to make the player’s towers shoot automatically

GIVEN	The player asks to the system to build a tower

WHEN	The tower is already built

THEN	The system makes it shoot at the enemies who are in the tower’s rank according to the tower’s shooting frequency


GIVEN	A built tower

WHEN	The tower shoots to an enemy

THEN	The HP of the enemies decreases, and the player gains money


GIVEN	A built tower

WHEN	The tower vanish an enemy

THEN	The enemy disappears and the XP of the player increases


- As the system, I want to make the enemies appear and attack the main tower

GIVEN	The scenario of a level

WHEN	The level gets started

THEN	The enemies start to appear and walk to get to the main tower and attack it
