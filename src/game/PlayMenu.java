package game;

import map.Map;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import towers.*;
import enemies.*;

import java.awt.*;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Main menu class:
 * - Shows the map to play with all its contents.
 */
public class PlayMenu extends BasicGameState {
    private static Map gameMap;
    private static Image SandTileImage;
    private Image PathTileImage;
    private Image HeartIconImage;
    private Image WaveIconImage;
    private Image MoneyIconImage;
    private Image ArcherTowerImage;
    private Image ElectroTowerImage;
    private Image TerminatorTowerImage;
    private Image SellImage;
    private Image UpgradeImage;
    private Image ExitIconImage;
    private Image StartWaveIconImage;
    private Image TileSelectImage;
    private Image TileSellImage;
    private Image TileUpgradeImage;
    private Image ArcherProjectile;
    private Image TerminatorProjectile;
    private Image TerminatorProjectileAdvanced;
    private Image ElectroProjectile;
    private Image ElectroProjectileAdvanced;

    //buttons clickable obj
    private Rectangle ArcherTowerButton;
    private Rectangle ElectroTowerButton;
    private Rectangle TerminatorTowerButton;
    private Rectangle ExitButton;
    private Rectangle UpgradeButton;
    private Rectangle SellButton;
    private Rectangle StartWaveButton;

    // Spritesheets and animations
    private SpriteSheet GoblinSpriteSheet;
    private Animation GoblinAnimation;
    private SpriteSheet HyperKidSpriteSheet;
    private Animation HyperKidAnimation;
    private SpriteSheet NutcrackerSpriteSheet;
    private Animation NutcrackerAnimation;
    private SpriteSheet MadKnightSpriteSheet;
    private Animation MadKnightAnimation;
    private SpriteSheet BioStrongSpriteSheet;
    private Animation BioStrongAnimation;

    private static boolean waveIsInProgress;

    private static Queue<Enemy> enemyQueue = new LinkedList<Enemy>();
    private static Queue<Enemy> activeEnemyQueue = new LinkedList<Enemy>();
    private static ArrayList<Tower> towerList = new ArrayList<Tower>();
    private static ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
    private ArrayList<Projectile> pToRemove;

    static long tickCount = 0;
    private int enemySpawnDelay = 20;
    private int startWaveLevel = 0;
    private static int mouseState =-1;
    private final int startingLevel = 1;
    private final int relativePadding = 10;

    private Projectile.projectileType projType;
    private Projectile.projectileType radiationType;
    private Projectile radiation;

    //default values
    private static int money = 1000;
    private int hearts = 10;
    private final int totalWave = 10;

    private static EnemyGenerator generator;
    private static int currentLevel;

    public static Font Iconsfont;
    public static Font statusFont;
    public static TrueTypeFont trueTypeFontIcons;
    public static TrueTypeFont trueTypeFontStatus;

    ////enemies
    private static boolean gameOver = false;
    private boolean victoryStatus;


    public static boolean isWaveIsInProgress() {
        return waveIsInProgress;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private final int mouseClickDelay = 200;
    private long lastClick=(-1*mouseClickDelay);

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the class.
     * @param state - game state of Play menu.
     */
    public PlayMenu(int state){}

    //------------------------------------------------------------------------------------------------------------------
    // Getters, setters, loads and draws

    /**
     * ID of the Play menu.
     * @return id
     */
    @Override
    public int getID() {
        return 1;
    }

    /**
     * Money the player uses to buy and construct the towers.
     * @return money
     */
    public static int getMoney() {
        return money;
    }

    /**
     * Money the player uses to buy and construct the towers.
     * @param moneyParam - money
     */
    public static void setMoney(int moneyParam) {
        money = moneyParam;
    }

    /**
     * Player's HP.
     * When it comes down to zero, the game is over.
     * @param hearts
     */
    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public static void setActiveEnemyQueue(Queue<Enemy> activeEnemyQueue) {
        PlayMenu.activeEnemyQueue = activeEnemyQueue;
    }

    public static Queue<Enemy> getActiveEnemyQueue() {
        return activeEnemyQueue;
    }

    /**
     * Creates the animations of the map.
     * @throws SlickException
     */
    public void loadAnimations() throws SlickException{
        //tw -- width of tiles on the sheet
        //th -- height of tiles on the sheet
        //duration -- duration to show each frame
        GoblinSpriteSheet = new SpriteSheet("res/images/GoblinSS.png",32,26,0);
        GoblinAnimation = new Animation(GoblinSpriteSheet, 50);
        HyperKidSpriteSheet  = new SpriteSheet("res/images/eagleSS.png",41,27,0);
        HyperKidAnimation = new Animation(HyperKidSpriteSheet, 150);
        NutcrackerSpriteSheet = new SpriteSheet("res/images/nutcrackerSS.png",32,26,0);
        NutcrackerAnimation = new Animation(NutcrackerSpriteSheet, 150);
        MadKnightSpriteSheet =  new SpriteSheet("res/images/madknightSS.png",32,32,0);
        MadKnightAnimation = new Animation(MadKnightSpriteSheet, 100);
        BioStrongSpriteSheet = new SpriteSheet("res/images/biostrongSS.png",32,32,0);
        BioStrongAnimation = new Animation(BioStrongSpriteSheet, 100);
    }

    /**
     * Creates the images of the map.
     * @throws SlickException
     */
    public void loadImages() throws SlickException{
        //initialize all graphics/images from graphics folder
        PathTileImage = new Image ("res/images/PathTile.png");
        HeartIconImage = new Image("res/images/heart.png");
        MoneyIconImage = new Image("res/images/money.png");
        WaveIconImage = new Image("res/images/skull.png");
        ElectroTowerImage = new Image("res/images/electro.png");
        ArcherTowerImage = new Image("res/images/archer.png");
        TerminatorTowerImage = new Image("res/images/terminator.png");
        SellImage = new Image("res/images/sell.png");
        UpgradeImage = new Image("res/images/upgrade.png");
        StartWaveIconImage = new Image("res/images/pmWave.png");
        ExitIconImage = new Image("res/images/pmExit.png");
        TileSelectImage = new Image ("res/images/TileSelectImage.png");
        TileSellImage = new Image ("res/images/TileSellImage.png");
        TileUpgradeImage = new Image ("res/images/TileUpgradeImage.png");
        ArcherProjectile = new Image("res/images/archerProjectile.png");
        TerminatorProjectile = new Image("res/images/torpedo.png");
        TerminatorProjectileAdvanced = new Image("res/images/radiation.png");
        ElectroProjectile = new Image("res/images/electroTile.png");
        ElectroProjectileAdvanced = new Image("res/images/electroTileAdvanced.png");
    }

    /**
     * Creates the notes.
     */
    public void loadNotes(){
        Iconsfont = new Font("Helvetica", Font.PLAIN, 30);
        trueTypeFontIcons = new TrueTypeFont(Iconsfont, true);
    }

    /**
     * Creates the buttons in their coordinates.
     */
    public void setRectangleButtons(){
        ArcherTowerButton = new Rectangle(850, 25,ArcherTowerImage.getWidth(),ArcherTowerImage.getHeight());
        ElectroTowerButton = new Rectangle(850, 125,ArcherTowerImage.getWidth(),ArcherTowerImage.getHeight());
        TerminatorTowerButton = new Rectangle(850, 225,ArcherTowerImage.getWidth(),ArcherTowerImage.getHeight());
        UpgradeButton = new Rectangle(850, 325,UpgradeImage.getWidth(),UpgradeImage.getHeight());
        SellButton = new Rectangle(850, 425,SellImage.getWidth(),SellImage.getHeight());
        StartWaveButton = new Rectangle(850, 600,StartWaveIconImage.getWidth(),StartWaveIconImage.getHeight());
        ExitButton = new Rectangle(850, 660,ExitIconImage.getWidth(),ExitIconImage.getHeight());
    }

    /**
     * Draws the notes of the Map menu in their coordinates.
     */
    public void drawNotes(){
        trueTypeFontIcons.drawString(100, 660, String.valueOf(money));
        trueTypeFontIcons.drawString(400, 660, String.valueOf(hearts));
        trueTypeFontIcons.drawString(700, 660, currentLevel + " / " + totalWave);
    }

    /**
     * Draws the icons of the Map menu in their coordinates.
     */
    public void drawIcons(){
        //game icons
        MoneyIconImage.draw(20, 650);
        HeartIconImage.draw(300, 650);
        WaveIconImage.draw(580, 650);

        //tower icons
        ArcherTowerImage.draw(850, 25);
        ElectroTowerImage.draw(850, 125);
        TerminatorTowerImage.draw(850, 225);
        UpgradeImage.draw(850, 325);
        SellImage.draw(850, 425);
        StartWaveIconImage.draw(850, 600);
        ExitIconImage.draw(850, 660);
    }

    /**
     * Draws the map and its invertory.
     */
    public void drawMapAndInventory(GameContainer container, Graphics g) throws SlickException {
        for(int i=0; i < gameMap.MAP_HEIGHT; i++) {
            for(int j=0; j < gameMap.MAP_WIDTH; j++) {
                if(gameMap.getGrids()[i][j] == 0){
                    SandTileImage.draw(j*32,i*32);
                }
                else if(gameMap.getGrids()[i][j] == 1){
                    PathTileImage.draw(j*32,i*32);
                }
            }
        }

        //draw towers and icons
        //money, and hearts, and wave level
        drawIcons();
        drawNotes();

        //if mouse pointing to map
        if(mouseOnMap(Mouse.getX(),container.getHeight()-Mouse.getY())){
            Image img;
            switch(mouseState){
                case -3:
                    img = TileSellImage;
                    break;
                case -2:
                    img = TileUpgradeImage;
                    break;
                case -1:
                    img = TileSelectImage;
                    break;
                case 0:
                    img = ArcherTowerImage;
                    break;
                case 1:
                    img =  ElectroTowerImage;
                    break;
                case 2:
                    img = TerminatorTowerImage;
                    break;
                default:
                    img = null;
                    break;
            }

            img.drawCentered(Mouse.getX(), container.getHeight() - Mouse.getY());
        }
    }

    /**
     * Draws the chosen tower.
     */
    private void drawTowers( Graphics g){
        for(Tower t: towerList){

            Image img;// = ArcherTowerImage;
            switch(t.getTowerType()){
                case ARCHER:
                    img = ArcherTowerImage;
                    break;
                case ELECTRO:
                    img = ElectroTowerImage;
                    break;
                case TERMINATOR:
                    img = TerminatorTowerImage;
                    break;
                default:
                    //do nothing
                    img = null;//ArcherTowerImage;
                    break;

            }

            img.drawCentered( (float) t.getXCor() , (float) t.getYCor());
            for(int i = 0; i < t.getLevel()-1 ;i++){
                g.setColor(Color.red);
                g.fillRoundRect((float) t.getXCor() - 12 + i*15, (float) t.getYCor() +  15,10.0f,10.0f,4);
            }

        }
    }

    /**
     * Draws an enemy with the correct movement direction.
     */
    public void drawEnemy(Enemy enemy){
        int frameSet = 0;
        Animation animation;
        switch(enemy.getType()){
            case GOBLIN:
                frameSet = 0;
                animation = GoblinAnimation;
                break;
            case HYPERKID:
                frameSet = 0;
                animation = HyperKidAnimation;
                break;
            case NUTCRACKER:
                frameSet = 0;
                animation = NutcrackerAnimation;
                break;
            case MADKNIGHT:
                frameSet = 2;
                animation = MadKnightAnimation;
                break;
            case BIOSTRONG:
                frameSet = 1;
                animation = BioStrongAnimation;
                break;
            default:
                System.out.println("Wrong type animation!");
                animation = null;
        }

        if(enemy.getEnemyDirection() == Enemy.direction.RIGHT){
            animation.getCurrentFrame().setRotation(90*((3+ frameSet)%4));
            animation.getCurrentFrame().drawCentered(enemy.getXLoc(), enemy.getYLoc());
        }
        if(enemy.getEnemyDirection() == Enemy.direction.LEFT){
            animation.getCurrentFrame().setRotation(90*((1+ frameSet)%4));
            animation.getCurrentFrame().drawCentered(enemy.getXLoc(), enemy.getYLoc());

        }

        if(enemy.getEnemyDirection() == Enemy.direction.DOWN){
            animation.getCurrentFrame().setRotation(90*((0+ frameSet)%4));
            animation.getCurrentFrame().drawCentered(enemy.getXLoc(), enemy.getYLoc());
        }

        if(enemy.getEnemyDirection() == Enemy.direction.UP){
            animation.getCurrentFrame().setRotation(90*((2+ frameSet)%4));
            animation.getCurrentFrame().drawCentered(enemy.getXLoc(), enemy.getYLoc());
        }
    }

    /**
     * Draws the enemies if they are visible and still alive.
     */
    public void drawEnemies(){
        for(Enemy enemy : activeEnemyQueue)
            if(enemy.isVisible() && enemy.isAlive()) {
                drawEnemy(enemy);
            }
    }

    /**
     * Draws the projectiles the towers shoot to the enemies.
     */
    private void drawProjectiles(){
        for(Projectile project: projectileList){
            Image img;
            switch(project.getProjType()){
                case ARCHER:
                    img = ArcherProjectile;
                    break;
                case TERMINATOR:
                    img = TerminatorProjectile;
                    break;
                case ELECTRO:
                    img = ElectroProjectile;
                    break;
                case ADVANCEDELECTRO:
                    img = ElectroProjectileAdvanced;
                    break;
                case ADVANCEDTERMINATOR:
                    img = TerminatorProjectileAdvanced;
                    break;
                default:
                    img = null;
                    break;
            }
            img.setRotation( (float) project.angleOfProjectileInDegrees());
            img.drawCentered((float) project.getXLoc(),(float)project.getYLoc());
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Reset the status of the map.
     * @param level - level to play
     * @param imageName - image for the sand of the map
     * @throws IOException
     * @throws SlickException
     */
    public static void resetPlayMenu(int level, String imageName) throws IOException, SlickException{
        gameMap = new Map();
        try {
            gameMap.loadMapFromFile("res/levels/level" + level + ".txt");
            generator = new EnemyGenerator();
            generator.loadStreamFromFile("res/streams/stream" + level + ".txt");
            SandTileImage = new Image("res/images/" + imageName);
        } catch (IOException | SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reset the status of the game to its default values.
     */
    public void restartGame() {
        currentLevel = startingLevel;
        setMoney(money);
        setHearts(hearts);
        waveIsInProgress = false;
        enemyQueue = new LinkedList<Enemy>();
        activeEnemyQueue = new LinkedList<Enemy>();
        towerList =  new ArrayList<Tower>();
        gameOver = false;
        mouseState = -1;
    }

    /**
     * Creates the next attacking enemies' wave.
     */
    public void createWaveQueue(){
        generator.setFlow(gameMap.getCheckpoints(),currentLevel);
        generator.createEnemyQueue();
        generator.shuffleEnemyQueue();
        enemyQueue = generator.getEnemyQueue();
        activeEnemyQueue = new LinkedList<Enemy>();
        activeEnemyQueue.add(enemyQueue.poll());
    }

    /**
     * Adds the enemies to the next wave queue.
     */
    public void addEnemiesToQueue(){
        tickCount++;
        if(tickCount>enemySpawnDelay){
            activeEnemyQueue.add(enemyQueue.poll());
            tickCount=0;
        }
    }

    /**
     * Checks the state of the enemies, making them to move if they're still alive, deleting them if they're not.
     */
    public void updateEnemies(){
        boolean stillVisible = false;
        ArrayList<Enemy> enemiesToRemove = new ArrayList<Enemy>();
        //for each enemy list, update their movement if they are alive
        for(Enemy enemy : activeEnemyQueue){
            //only living enemies can move!
            if(enemy.isAlive())
                enemy.move();
            else{
                money += enemy.getReward();
                enemiesToRemove.add(enemy);
            }
            if(enemy.isVisible())
                stillVisible = true;
            if(enemy.isAtEndPoint()){
                hearts--;
                enemiesToRemove.add(enemy);
            }
        }

        //remove all the dead enemies that have arrived at the exit
        for(Enemy enemy : enemiesToRemove){
            activeEnemyQueue.remove(enemy);
        }


        if(!stillVisible && enemyQueue.size() == 0){
            waveIsInProgress = false;
            currentLevel++;
        }
    }

    /**
     * Reloads the towers setting their last attack time to zero.
     */
    public void reloadTowers(){
        for(Tower towers : towerList){
            towers.setTimeOfLastAttack(0);
        }
    }

    /**
     * Checks the state of the projectiles the towers shoot to the enemies, making them to keep moving
     * until they reach an enemy, or deleting them if necessary.
     */
    public void updateProjectiles(){
        //for each projectile update their locations
        pToRemove = new ArrayList<Projectile>();
        for(Projectile project: projectileList){
            if(!project.hasArrived()){
                project.move();
            }
            else {
                if(project.getProjType() == Projectile.projectileType.ADVANCEDTERMINATOR) {
                    project.radiateZone(activeEnemyQueue);
                    pToRemove.add(project);
                }
                else
                    pToRemove.add(project);
            }
        }

        for(Projectile project: pToRemove){
            projectileList.remove(project);
        }
    }

    /**
     * Gets nearest tower based on mouse click coordinates
     * Picks the tower with minimum distance
     * @param x - coordinate for width
     * @param y - coordinate for height
     * @return - selected Tower
     */
    public Tower getNearestTower(int x, int y){
        double minimumDistance = gameMap.getPixelSize() + relativePadding;
        Tower nearestTower=null;
        for(Tower tower: towerList){
            if(Math.abs(tower.getXCor()-x)+Math.abs(tower.getYCor()-y)< minimumDistance){
                nearestTower = tower;
                minimumDistance = Math.abs(tower.getXCor()-x)+Math.abs(tower.getYCor()-y);
            }

        }
        return nearestTower;
    }

    /**
     * Check if you can place a Tower in the coordinates selected with the mouse by:
     *  - Checking if it collides with any path tile
     *  - Checking if it is within the bounds of map
     *  - Checking if it collides with other tower
     * @param x - coordinate for width
     * @param y - coordinate for height
     * @return - if the coordinates are available to have a tower built there
     */
    public boolean canPlaceTowerHere(float x, float y){

        /// Check if it collides with any path tile
        for(int i=0; i< gameMap.getPathTilesPixel().length;i++){
            if(Math.abs(x - gameMap.getPathTilesPixel()[i][0]) < gameMap.getPixelSize() + relativePadding  &&
                    Math.abs(y - gameMap.getPathTilesPixel()[i][1]) < gameMap.getPixelSize() + relativePadding)
            {
                return false;
            }
        }

        /// Check if it is within the bounds of map
        if(Math.abs(x - gameMap.getMapWidth()*gameMap.getPixelSize())< gameMap.getPixelSize() ||
                Math.abs(y - gameMap.getMapHeight()*gameMap.getPixelSize()) < gameMap.getPixelSize() ||
                x < gameMap.getPixelSize() || y < gameMap.getPixelSize()){
            return false;
        }

        /// Check if it collides with other tower
        for(Tower t: towerList){
            if(Math.abs(x - t.getXCor()) < gameMap.getPixelSize()*2 - relativePadding &&
                    Math.abs(y - t.getYCor()) < gameMap.getPixelSize()*2 - relativePadding)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the enemies are in the target range of the towers.
     * If true, make the enemies become a target to that tower.
     */
    public void targetEnemies(){
        for(Tower tower : towerList){
            tower.setTargetEnemy(null);
            for(Enemy enemy: activeEnemyQueue){
                if(enemy.isAlive()&& enemy.isVisible()){
                    //calculate distance
                    double xDist = Math.abs(enemy.getXLoc() - tower.getXCor());
                    double yDist = Math.abs(enemy.getYLoc() -  tower.getYCor());
                    double dist = Math.sqrt((xDist*xDist)+(yDist*yDist));
                    if(dist<tower.getRange() && tower.getEnemyMaxDistance() < enemy.getDistanceTravelled()){
                        tower.setTargetEnemy(enemy);
                        tower.setEnemyMaxDistance(enemy.getDistanceTravelled());
                    }
                }
            }

            tower.setEnemyMaxDistance(0);
        }
    }

    /**
     * Makes the towers attack to their target enemies if they can attack them.
     */
    public void attackEnemies(){
        for(Tower tower: towerList){
            if(tower.getTargetEnemy()!= null && tower.canAttack()){
                attackEnemy(tower);
                tower.setTimeOfLastAttack(System.currentTimeMillis());
            }
        }
    }

    /**
     * Selects the different projectiles depending on the type and the level of the tower.
     * Adds the projectiles to the list.
     * @param source
     */
    public void attackEnemy(Tower source){
        switch(source.getType()){
            case ARCHER:
                projType = projType.ARCHER;
                break;
            case TERMINATOR:
                projType = projType.TERMINATOR;
                break;
            case ELECTRO:
                if(source.getLevel() == 1) {
                    projType = projType.ELECTRO;
                }
                else {
                    projType = projType.ADVANCEDELECTRO;
                }
                break;
            default:
                projType = Projectile.projectileType.ARCHER;
                System.out.println("No such tower type exists");
                break;
        }

        if(source.getType() == Tower.type.ARCHER && source.getLevel()>1) {
            Projectile projectile2 = new Projectile(source, source.getTargetEnemy(), projType);
            Projectile projectile3 = new Projectile(source, source.getTargetEnemy(), projType);
            projectile2.setyInit(projectile2.getyInit() - source.getWeaponMargin());
            projectile3.setyInit(projectile3.getyInit() + source.getWeaponMargin());
            projectileList.add(projectile2);
            projectileList.add(projectile3);
        }

        Projectile projectile = new Projectile(source, source.getTargetEnemy(),projType);
        projectileList.add(projectile);

        if(source.getType() == Tower.type.TERMINATOR && source.getLevel()>1){
            projType = projType.ADVANCEDTERMINATOR;
            radiation = new Projectile(source,source.getTargetEnemy(), projType);
            radiation.setXLoc(source.getTargetEnemy().getXLoc());
            radiation.setYLoc(source.getTargetEnemy().getYLoc());
            projectileList.add(radiation);
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the images of the map and restart the game.
     * Sets the buttons with the menu's options.
     * @param container - container of the graphics
     * @param sbg - game object
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        loadImages();
        loadAnimations();
        loadNotes();
        setRectangleButtons();
        restartGame();
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
        drawMapAndInventory(gameContainer, graphics);

        if(!isGameOver()) {
            drawTowers(graphics);
            if (waveIsInProgress) {
                drawEnemies();
                drawProjectiles();
            }
        }

        else {
            statusFont = new Font("Plain", Font.PLAIN, 60);
            trueTypeFontStatus = new TrueTypeFont(statusFont, true);
            if(!victoryStatus)
                trueTypeFontStatus.drawString(300, 250, "Game Over!", Color.black);
            else
                trueTypeFontStatus.drawString(300, 250, " Victory!", Color.black);

            trueTypeFontStatus.drawString(270, 370, "Your Record: " + (currentLevel-1), Color.black);
        }
    }

    /**
     * Detects if the game is over and the Mouse is Clicked.
     * If the game is over, deletes the unused contents.
     * If the game is NOT over, updates the animations of the enemies.
     * If the Mouse is Clicked, calls to the following method giving it the coordinates.
     * @param container - container of the graphics
     * @param sbg -  game object
     * @param delta - time
     * @throws SlickException
     */
    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        if(gameOver){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            restartGame();
            sbg.enterState(Game.MAIN_MENU_ID);
        }

        if(hearts <= 0){
            gameOver = true;
            victoryStatus = false;
        }

        else if(currentLevel > totalWave){
            gameOver = true;
            victoryStatus = true;
        }

        if(waveIsInProgress){
            if(enemyQueue.size() != 0){
                addEnemiesToQueue();
            }

            if(!isGameOver()) {
                updateProjectiles();
                updateEnemies();
                targetEnemies();
                attackEnemies();
            }
        }

        if(Mouse.isButtonDown(0)){
            mouseClicked(Mouse.getX(), container.getHeight() - Mouse.getY(), sbg, container);
        }

        if(!isGameOver()){
          GoblinAnimation.update(delta);
          HyperKidAnimation.update(delta);
          BioStrongAnimation.update(delta);
          NutcrackerAnimation.update(delta);
          MadKnightAnimation.update(delta);
        }
    }

    /**
     * Detects if the Mouse is on the Map.
     * @param x - coordinate for width
     * @param y - coordinate for height
     * @return boolean
     */
    public boolean mouseOnMap(int x, int y){
       if(x < gameMap.getMapWidth() * gameMap.getPixelSize() && y < gameMap.getMapHeight() * gameMap.getPixelSize())
       {  return true; }
       else {  return false; }
    }

    /**
     * If the coordinates send by as parameters are the same as the ones of the buttons, execute its code.
     * @param x - coordinate for width
     * @param y - coordinate for height
     * @param sbg - game object
     * @param gc - container for the graphics
     * @throws SlickException
     */
    private void mouseClicked(int x, int y, StateBasedGame sbg, GameContainer container) throws SlickException {

        //protection against multiple click registration
        if(lastClick + mouseClickDelay > System.currentTimeMillis())
            return;
        lastClick = System.currentTimeMillis();

        if(ExitButton.contains(x,y)){
            restartGame();
            sbg.enterState(Game.MAIN_MENU_ID);
        }

        //no towers selected
        if (mouseState ==- 1){

            if(StartWaveButton.contains(x,y) && !waveIsInProgress){
                waveIsInProgress = true;
                projectileList = new ArrayList<>();
                reloadTowers();
                createWaveQueue();
            }

            if(UpgradeButton.contains(x,y)){
                mouseState = -2;
             }

            if(SellButton.contains(x,y)){
                mouseState = -3;
            }

            if(ArcherTowerButton.contains(x,y)){
                mouseState = 0;
            }

            else if(ElectroTowerButton.contains(x,y)){
                mouseState = 1;
            }

            else if(TerminatorTowerButton.contains(x,y)){
                mouseState = 2;
            }

            return;
        }

        //tower selected
        else if (mouseState >=0){
            if(mouseOnMap(x,y) && canPlaceTowerHere(x,y)){
                Tower newTower;
                switch(mouseState){
                    case 0:
                        newTower = new Archer(x,y);
                        break;
                    case 1:
                        newTower= new Electro(x,y);
                        break;
                    case 2:
                        newTower= new Terminator(x,y);
                        break;
                    default: newTower = null;

                }

                if (money - newTower.getBuyingCost() >= 0){
                    money = money - newTower.getBuyingCost();
                    towerList.add(newTower);
                }
            }
        }

        // For selling towers
        else if(mouseState == -3) {
            if (mouseOnMap(x, y)) {
                Tower tower  = getNearestTower(x, y);
                if (tower != null) {
                    money = money + tower.getRefundValue();
                    towerList.remove(tower);
                }
            }
        }

        //For upgrading towers
        else if(mouseState == -2){
            if(mouseOnMap(x,y)){
                Tower tower = getNearestTower(x,y);
                if(tower != null)
                    tower.upgrade();
            }
        }

        mouseState = -1;
    }
}