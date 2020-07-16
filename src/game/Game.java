package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

/**
 * Main class
 */
public class Game extends StateBasedGame {

    public static final int MAIN_MENU_ID= 0;
    public static final int PLAY_MENU_ID = 1;
    public static final int MAP_MENU_ID = 2;
    public static final int SETTINGS_MENU_ID = 3;

    /**
     * Constructor of Game object, it gives the name to the game and creates the different states it will have
     * @param name - name of the game
     * @throws IOException
     */
    public Game(String name) throws IOException{
        super(name);
        this.addState(new MainMenu(MAIN_MENU_ID));
        this.addState(new SettingsMenu(SETTINGS_MENU_ID));
        this.addState(new MapMenu(MAP_MENU_ID));
        this.addState(new PlayMenu(PLAY_MENU_ID));

    }

    /**
     * Initializes the game showing the Main menu.
     * @param gameContainer
     */
    @Override
    public void initStatesList(GameContainer gameContainer) {
        this.enterState(MAIN_MENU_ID);
    }

    /**
     * Creates the game object, giving it its name "Tower Defense".
     * Set the display mode.
     * Call start method from game.
     * @param args - arguments of the program.
     * @throws SlickException
     * @throws IOException
     */
    public static void main(String[] args) throws SlickException, IOException {
        AppGameContainer game = new AppGameContainer(new Game("TowerDefense"));
        game.setDisplayMode(1280,720,false);
        game.setShowFPS(true);
        game.start();
    }
}
