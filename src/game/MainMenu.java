package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Main menu class:
 * - Shows the Main menu.
 * - Captures the option selected with the mouse.
 * - Executes that option.
 */
public class MainMenu extends BasicGameState {

    //Button images/styles
    private Image BackgroundImage;
    private Image StartButtonImage;
    private Image SettingsButtonImage;
    private Image ExitButtonImage;
    private Image LogoImage;


    //Buttons clickable obj
    Rectangle StartButton;
    Rectangle ExitButton;
    Rectangle SettingsButton;

    //Constants
    public static final int BUTTON_INIT = 0;
    public static final int BUTTON_X = 520;
    public static final int BUTTON_Y = 350;
    public static final int BUTTON_BORDER = 75;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the class.
     * @param state - game state of Main menu.
     */
    public MainMenu(int state){}

    //------------------------------------------------------------------------------------------------------------------
    // Getters, setters and draws

    /**
     * ID of the Main menu.
     * @return id
     */
    @Override
    public int getID() {
        return 0;
    }

    /**
     * Creates the images of the Main menu.
     * @throws SlickException
     */
    public void setImages() throws SlickException{
        StartButtonImage = new Image("res/images/button_start-game.png");
        SettingsButtonImage = new Image("res/images/button_settings.png");
        BackgroundImage = new Image("res/images/back.jpg");
        LogoImage = new Image("res/images/logo.png");
        ExitButtonImage = new Image ("res/images/button_exit-game.png");
    }

    /**
     * Creates the buttons in their coordinates.
     */
    public void setRectangleButtons(){
        StartButton = new Rectangle(BUTTON_X, BUTTON_Y, StartButtonImage.getWidth(), StartButtonImage.getHeight());
        ExitButton = new Rectangle(BUTTON_X, BUTTON_Y + 2*BUTTON_BORDER, ExitButtonImage.getWidth(), ExitButtonImage.getHeight());
        SettingsButton = new Rectangle(BUTTON_X, BUTTON_Y + BUTTON_BORDER, SettingsButtonImage.getWidth(), SettingsButtonImage.getHeight());
    }

    /**
     * Draws the images of the Main menu in their coordinates.
     */
    public void drawObjects(){
        BackgroundImage.draw(BUTTON_INIT, BUTTON_INIT);
        LogoImage.draw(BUTTON_X-BUTTON_BORDER/5, BUTTON_INIT);
        StartButtonImage.draw(BUTTON_X, BUTTON_Y);
        SettingsButtonImage.draw(BUTTON_X, BUTTON_Y + BUTTON_BORDER);
        ExitButtonImage.draw(BUTTON_X, BUTTON_Y + 2*BUTTON_BORDER);
    }

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Sets the images of the Main menu.
     * Sets the buttons with the menu's options.
     * @param container - container of the graphics
     * @param sbg - game object
     * @throws SlickException
     */
    @Override
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {

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
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
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
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        if(Mouse.isButtonDown(0)){
            mouseClicked(Mouse.getX(), container.getHeight()- Mouse.getY(), sbg, container);
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
    public void mouseClicked( float x, float y, StateBasedGame sbg, GameContainer gc) throws SlickException {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(ExitButton.contains(x, y)){
            System.exit(0);
        }

        else if(StartButton.contains(x, y)){
            sbg.enterState(Game.MAP_MENU_ID);
        }
        else if(SettingsButton.contains(x, y)){
            sbg.enterState(Game.SETTINGS_MENU_ID);
        }
    }
}