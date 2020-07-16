package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Settings menu class:
 * - Shows the Settings menu.
 * - Captures the option selected with the mouse.
 * - Executes that option.
 */
public class SettingsMenu extends BasicGameState {

    //Button images/styles
    private Image BackgroundImage;
    private Image VolumeButtonImage;
    private Image BackButtonImage;

    //Clickable obj
    private Rectangle BackButton;
    private Rectangle VolumeButton;

    //Constants
    public static final int BUTTON_INIT = 0;
    public static final int BUTTON_X = 520;
    public static final int BUTTON_Y = 350;
    public static final int BUTTON_BORDER = 75;

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Constructor of the class.
     * @param state - game state of Settings menu.
     */
    public SettingsMenu (int state){}

    //------------------------------------------------------------------------------------------------------------------
    //Getters, setters and draws

    /**
     * ID of the Main menu.
     * @return id
     */
    @Override
    public int getID() {
        return 3;
    }

    /**
     * Creates the images of the Settings menu.
     * @throws SlickException
     */
    public void setImages() throws SlickException{
        BackgroundImage = new Image("res/images/back.jpg");
        VolumeButtonImage = new Image ("res/images/options.png");
        BackButtonImage = new Image ("res/images/quit.png");
    }

    /**
     * Creates the buttons in their coordinates.
     */
    public void setRectangleButtons(){
        BackButton = new Rectangle(BUTTON_X, BUTTON_Y + BUTTON_BORDER,BackButtonImage.getWidth(),BackButtonImage.getHeight());
    }

    /**
     * Draws the images of the Settings menu in their coordinates.
     */
    public void drawObjects(){
        BackgroundImage.draw(BUTTON_INIT, BUTTON_INIT);
        VolumeButtonImage.draw(BUTTON_X, BUTTON_Y);
        BackButtonImage.draw(BUTTON_X, BUTTON_Y + BUTTON_BORDER);

    }

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Sets the images of the Settings menu.
     * Sets the buttons with the menu's options.
     * @param container - container of the graphics
     * @param sbg - game object
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        setImages();
        setRectangleButtons();
    }

    /**
     * Refresh the graphics.
     * @param container - container of the graphics
     * @param sbg - game object
     * @param g - graphics
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawObjects();
    }

    /**
     * Detects if the Mouse is Clicked.
     * If true, calls to the following method giving it the coordinates.
     * @param container - container of the graphics
     * @param sbg -  game object
     * @param delta - time
     * @throws SlickException
     */
    @Override
    public void update(GameContainer container, StateBasedGame stateBasedGame, int i) throws SlickException {

        if(Mouse.isButtonDown(0)) {
            try {
                mouseClicked(Mouse.getX(), container.getHeight() - Mouse.getY(), stateBasedGame, container);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * If the coordinates send by as parameters are the same as the ones of the buttons, execute its code.
     * @param x - coordinate for width
     * @param y - coordinate for height
     * @param sbg - game object
     * @param gc - container for the graphics
     * @throws SlickException
     */
    public void mouseClicked(int x, int y, StateBasedGame sbg, GameContainer container) throws SlickException, InterruptedException {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(BackButton.contains(x, y)){
            sbg.enterState(Game.MAIN_MENU_ID);
        }
    }
}
