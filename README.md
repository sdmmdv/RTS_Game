# TOWER DEFENCE - Java RTS_GAME

## About

Tower defense is a subgenre of strategy video game where the goal is to defend a player's territories or possessions by obstructing the enemy attackers, usually achieved by placing defensive structures on or along their path of attack.This typically means building a variety of different structures that serve to automatically block, impede, attack or destroy enemies. Tower defense is seen as a subgenre of real-time strategy video games, due to its real-time origins though many modern tower defense games include aspects of turn-based strategy.

## Downloads

Releases for Desktop are found on the [releases page](https://github.com/MoneiBall/RTS_Game/releases). Relase contains lwjgl OS natives (.os and .dll) and runnable .jar file. Must have JRE installed in your host machine.   

## Configuration - Intellij IDEA

1. Project compiled with jdk 1.8, so for the SDK field choose **1.8 java version "1.8.XXX"**
2. Go to the modules, make sure all the dependencies exported.
Libraries repository displayed as the **libs**, tick off and apply.
If not available export libraries manually from ```/"libs"```
3. Open <em>Edit Configuration</em>, choose main class as <em>Game.game</em>
4. Add following path to <em>VM options</em> : ```-Djava.library.path="<Full/Path/to/your/libs_folder>"```

## Screenshots

**Home**                         |  **Menu**
:-------------------------------:|:---------------------------------:
![Home](https://github.com/MoneiBall/RTS_Game/blob/master/res/screenshots/home.png)  |  ![Menu](https://github.com/MoneiBall/RTS_Game/blob/master/res/screenshots/menu.png)
**Twilight 1**                      |  **Twilight 2**
![Twilight 1](https://github.com/MoneiBall/RTS_Game/blob/master/res/screenshots/twilight.png)  |  ![Twilight 2](https://github.com/MoneiBall/RTS_Game/blob/master/res/screenshots/twilight-electro.png)
**Woodlands**                       |  **Tundra**
![Woodlands](https://github.com/MoneiBall/RTS_Game/blob/master/res/screenshots/woodlands.png)  |  ![Tundra](https://github.com/MoneiBall/RTS_Game/blob/master/res/screenshots/tundra.png)

## USE CASES
#### PLAYER. Functions:

1) Open Game: click the button to open the game and show the menu with the different levels.
2) Select Level: choose the level to play

3) Play level:
	3.1) Construct tower by selecting its type and position
		3.1.1) What happens if there’s not enough money to construct a tower
		3.1.2) What happens if the player chooses a position where a tower cannot be built
	3.2) Upgrade tower
		3.2.1)  What happens if the player doesn’t have enough money to upgrade a tower
		3.2.2) What happens if the player doesn’t have enough XP to upgrade a tower

#### SYSTEM. Functions:
	
1)  Show the level menu: when the game is opened by the player, the game should display the menu with the five different levels that can be played.

2)  Charge level: when a level is selected the game shows a different page with the corresponded scenario. It also calculates the type of enemies the level is going to have, how are they going to appear, what is its walking speed and the damage they are going to make to the tower.

3) Make the player towers shoot automatically:
	3.1)  Select how the tower is going to be upgraded
	3.2) What happens when a tower shoots at an enemy.
	3.3) What happens when a tower vanish an enemy.

4) Make the enemies appear and attack the main tower

#### CHARACTERS:

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
 
 ## References
 * Java Documentation,
 URL: https://docs.oracle.com/en/java .
* LWJGL 3 Documentation,
 URL: https://www.lwjgl.org/guide .
* SLICK2D Documentation,
 URL: https://slick.ninjacave.com/ .
* Callum May, <em>“java-tower-defense” github public repository</em>,
 URL: https://github.com/callumdmay/java-tower-defense .
