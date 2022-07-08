package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import java.awt.Font;
import java.io.IOException;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.TrueTypeFont;

/**
 * Map menu class:
 * - Shows the Map menu.
 * - Captures the option selected with the mouse.
 * - Executes that option.
 */
public class MapMenu extends BasicGameState {
    //images
    private Image WoodlandImage;
    private Image TwilightImage;
    private Image DesertImage;
    private Image SavannahImage;
    private Image TundraImage;
    private Image BackgroundImage;


    //Constants
    public static final int BUTTON_INIT = 0;
    public static final int BUTTON_X_TOP = 275;
    public static final int BUTTON_X_BOTTOM = 75;
    public static final int BUTTON_Y = 150;
    public static final int BUTTON_BORDER_VERTICAL = 400;
    public static final int BUTTON_BORDER_HORIZONTAL = 300;
    public static final int BUTTON_TITLE_PADDING_VERTICAL = 85;
    public static final int BUTTON_TITLE_PADDING_HORIZONTAL = 35;
    public static String TOP_INFO = "Choose Level";
    public static Font TOP_font;
    public static Font BOTTOM_font;
    public static TrueTypeFont trueTypeFontTop;
    public static TrueTypeFont trueTypeFontBottom;


    //buttons clickable obj
    private Rectangle WoodlandButton;
    private Rectangle TwilightButton;
    private Rectangle DesertButton;
    private Rectangle SavannahButton;
    private Rectangle TundraButton;

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Constructor of the class.
     * @param state - game state of Map menu.
     */
    public MapMenu(int state){}

    //------------------------------------------------------------------------------------------------------------------
    // Getters, setters and draws
    /**
     * ID of the Map menu.
     * @return id
     */
    @Override
    public int getID() {
        return 2;
    }

    /**
     * Creates the images of the Map menu.
     * @throws SlickException
     */
    public void setImages() throws SlickException{
        BackgroundImage = new Image("res/images/back.jpg");
        WoodlandImage = new Image("res/images/woodland.png");
        TwilightImage = new Image("res/images/twilight.png");
        DesertImage = new Image("res/images/desert.png");
        SavannahImage = new Image("res/images/savannah.png");
        TundraImage = new Image ("res/images/tundra.png");
    }

    /**
     * Creates the buttons in their coordinates.
     */
    public void setRectangleButtons() throws SlickException{
        WoodlandButton = new Rectangle(BUTTON_X_TOP,BUTTON_Y,WoodlandImage.getWidth(),WoodlandImage.getHeight());
        TwilightButton = new Rectangle(BUTTON_X_TOP + BUTTON_BORDER_VERTICAL,BUTTON_Y,TwilightImage.getWidth(),TwilightImage.getHeight());
        DesertButton = new Rectangle(BUTTON_X_BOTTOM,BUTTON_Y + BUTTON_BORDER_HORIZONTAL,DesertImage.getWidth(),DesertImage.getHeight());
        SavannahButton = new Rectangle(BUTTON_X_BOTTOM + BUTTON_BORDER_VERTICAL,BUTTON_Y + BUTTON_BORDER_HORIZONTAL,SavannahImage.getWidth(),SavannahImage.getHeight());
        TundraButton = new Rectangle(BUTTON_X_BOTTOM + 2*BUTTON_BORDER_VERTICAL,BUTTON_Y + BUTTON_BORDER_HORIZONTAL,TundraImage.getWidth(),TundraImage.getHeight());
    }

    /**
     * Creates the notes in their coordinates.
     */
    public void setNotes() throws SlickException{
        TOP_font = new Font("Helvetica", java.awt.Font.BOLD, 40);
        BOTTOM_font = new Font("Italic", java.awt.Font.BOLD, 20);
        trueTypeFontTop = new TrueTypeFont(TOP_font, true);
        trueTypeFontBottom = new TrueTypeFont(BOTTOM_font, true);
    }

    /**
     * Draws the images of the Map menu in their coordinates.
     */
    public void drawObjects() throws SlickException {
        BackgroundImage.draw(BUTTON_INIT,BUTTON_INIT);
        WoodlandImage.draw(BUTTON_X_TOP,BUTTON_Y);
        TwilightImage.draw(BUTTON_X_TOP + BUTTON_BORDER_VERTICAL,BUTTON_Y);
        DesertImage.draw(BUTTON_X_BOTTOM,BUTTON_Y + BUTTON_BORDER_HORIZONTAL);
        SavannahImage.draw(BUTTON_X_BOTTOM + BUTTON_BORDER_VERTICAL,BUTTON_Y + BUTTON_BORDER_HORIZONTAL);
        TundraImage.draw(BUTTON_X_BOTTOM + 2*BUTTON_BORDER_VERTICAL,BUTTON_Y + BUTTON_BORDER_HORIZONTAL);
    }

    /**
     * Draws the notes of the Map menu in their coordinates.
     */
    public void drawNotes() throws SlickException{
        trueTypeFontTop.drawString(500,25, TOP_INFO, Color.black);
        trueTypeFontBottom.drawString(BUTTON_X_TOP + BUTTON_TITLE_PADDING_VERTICAL,BUTTON_Y-BUTTON_TITLE_PADDING_HORIZONTAL, "Woodlands", Color.blue);
        trueTypeFontBottom.drawString(BUTTON_X_TOP + BUTTON_BORDER_VERTICAL + BUTTON_TITLE_PADDING_VERTICAL,BUTTON_Y-BUTTON_TITLE_PADDING_HORIZONTAL, "Twilight", Color.blue);
        trueTypeFontBottom.drawString(BUTTON_X_BOTTOM + BUTTON_TITLE_PADDING_VERTICAL,BUTTON_Y + BUTTON_BORDER_HORIZONTAL - BUTTON_TITLE_PADDING_HORIZONTAL,"Desert", Color.blue);
        trueTypeFontBottom.drawString(BUTTON_X_BOTTOM + BUTTON_BORDER_VERTICAL + BUTTON_TITLE_PADDING_VERTICAL,BUTTON_Y + BUTTON_BORDER_HORIZONTAL- BUTTON_TITLE_PADDING_HORIZONTAL, "Savannah",Color.blue);
        trueTypeFontBottom.drawString(BUTTON_X_BOTTOM + 2*BUTTON_BORDER_VERTICAL + BUTTON_TITLE_PADDING_VERTICAL,BUTTON_Y + BUTTON_BORDER_HORIZONTAL- BUTTON_TITLE_PADDING_HORIZONTAL, "Tundra", Color.blue);
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the notes of Map menu.
     * Sets the images of the Map menu.
     * Sets the buttons with the menu's options.
     * @param gameContainer - container of the graphics
     * @param stateBasedGame - game object
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        setNotes();
        setImages();
        setRectangleButtons();
    }

    /**
     * Refresh the graphics.
     * @param gameContainer - container of the graphics
     * @param stateBasedGame - game object
     * @param graphics - graphics
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawObjects();
        drawNotes();
    }

    /**
     * Detects if the Mouse is Clicked.
     * If true, calls to the following method giving it the coordinates.
     * @param container - container of the graphics
     * @param sbg -  game object
     * @param i - delta time
     * @throws SlickException
     */
    @Override
    public void update(GameContainer container, StateBasedGame sbg, int i) throws SlickException {
        //listen for mouse input
        if(Mouse.isButtonDown(0)) {
            try {
                mouseClicked(Mouse.getX(), container.getHeight() - Mouse.getY(), sbg, container);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * If the coordinates send by as parameters are the same as the ones of the buttons, execute its code.
     * @param x - coordinate for width
     * @param y - coordinate for height
     * @param sbg - game object
     * @param container - container for the graphics
     * @throws SlickException
     */
    public void mouseClicked(int x, int y, StateBasedGame sbg, GameContainer container) throws SlickException, IOException {

        if(WoodlandButton.contains(x, y)){
            sbg.enterState(Game.PLAY_MENU_ID);
            PlayMenu.resetPlayMenu(1, "MapTileWoodland.png");
        }
        else if(TwilightButton.contains(x, y)){
            sbg.enterState(Game.PLAY_MENU_ID);
            PlayMenu.resetPlayMenu(2, "MapTileTwilight.png");
        }
        else if(DesertButton.contains(x, y)){
            sbg.enterState(Game.PLAY_MENU_ID);
            PlayMenu.resetPlayMenu(3, "MapTileDesert.png");
        }
        else if(SavannahButton.contains(x, y)){
            sbg.enterState(Game.PLAY_MENU_ID);
            PlayMenu.resetPlayMenu(4, "MapTileSavannah.png");
        }
        else if(TundraButton.contains(x, y)){
            sbg.enterState(Game.PLAY_MENU_ID);
            PlayMenu.resetPlayMenu(5, "MapTileTundra.png");
        }
    }
}