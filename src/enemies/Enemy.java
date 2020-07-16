package enemies;
import org.newdawn.slick.Image;

/**
 * General Enemy class.
 * It contains the different attributes that store the enemies' info, such as position, health or speed.
 * It contains the methods the game will need to use and control the enemies.
 */
public abstract class Enemy {

    public enum type{GOBLIN, HYPERKID, MADKNIGHT, NUTCRACKER, BIOSTRONG};
    public enum direction {LEFT, RIGHT, UP, DOWN};

    protected float XLoc;
    protected float YLoc;
    protected double health;
    protected double speed;
    protected int reward;
    protected boolean alive;
    protected boolean canMove = true;
    protected int[][] checkpoints;
    protected int locationIncrementer = 0;
    protected boolean visible = false;
    protected boolean atEndPoint = false;
    protected type	enemyType;
    protected double distanceTravelled;
    protected direction enemyDirection;
    protected boolean frozen = false;
    protected double freezeRate = 0.5;
    protected long freezeStartTime;
    protected long freezeDuration;

    /**
     * Constructor of Enemy object.
     * @param checkpoints - List of coordinates the enemy uses to follow the correct path.
     */
    public Enemy(int[][] checkpoints){
        this.checkpoints = checkpoints;
    }

    public long getFreezeDuration() {
        return freezeDuration;
    }

    public long getFreezeStartTime() {
        return freezeStartTime;
    }

    public void setFreezeStartTime(long freezeStartTime) {
        this.freezeStartTime = freezeStartTime;
    }

    public void setFreezeDuration(long freezeDuration) {
        this.freezeDuration = freezeDuration;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public void setFreezeRate(double freezeRate) {
        this.freezeRate = freezeRate;
    }

    public double getFreezeRate() {
        return freezeRate;
    }

    /**
     * An enemy is frozen when it reaches an ElectroTower's target range.
     * @return
     */
    public boolean isFrozen() {
        return frozen;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    /**
     * Makes the enemies' health decrease a damage amount.
     * If health becomes zero sets the alive and visible attributes to false.
     * @param damage
     */
    public void takeDamage(double damage) {

        this.health -= damage;
        if(health <= 0){
            alive = false;
            visible = false;
        }
    }

    /**
     * Freezes the enemy, making it move slowly.
     */
    public void freezeEnemy(){
        setFrozen(true);
        setFreezeStartTime(System.currentTimeMillis());

    }

    /**
     * Unfreezes the enemy but, makes it being frozen for one more second before it can move
     * at its normal speed.
     */
    public void releaseEnemy(){
        setFrozen(false);
        setFreezeStartTime(-10000);
    }

    public double getSpeed() {
        if(isFrozen())
           return speed * freezeRate;
        else
            return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public direction getEnemyDirection() {
        return enemyDirection;
    }

    /**
     * Tells you if an enemy is visible.
     * If an enemy is alive during the game, it should be visible.
     * @return
     */
    public boolean isVisible() {
        return visible;
    }


    public double getHealth() {
        return health;
    }


    public float getXLoc() {
        return XLoc;
    }


    public float getYLoc() {
        return YLoc;
    }


    public int getReward() {
        return reward;
    }

    /**
     * Tells you if an enemy is still alive.
     * @return
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Tells you if an enemy has reached the map's end point.
     * @return
     */
    public boolean isAtEndPoint(){
        return atEndPoint;
    }

    /**
     * Tells you if an enemy can move.
     * @return
     */
    public boolean CanMove() {
        return canMove;
    }


    public type getType(){
        return enemyType;
    }

    /**
     * Makes the enemy move.
     * First, it calls releaseEnemy method if needed.
     * Then, it searches next possible position and makes the enemy move to it with the correspondent speed.
     */
    public void move(){

        if(isFrozen() && System.currentTimeMillis() > freezeStartTime + freezeDuration){
            releaseEnemy();
        }

        if(locationIncrementer == 0)
        {
            visible = true;
        }

        try{

            if(!(XLoc>checkpoints[locationIncrementer+1][0]-this.getSpeed()&&XLoc<checkpoints[locationIncrementer+1][0]+this.getSpeed()) ){
                if(XLoc<=checkpoints[locationIncrementer+1][0]){
                    XLoc += this.getSpeed();
                    distanceTravelled+=this.getSpeed();
                    enemyDirection = direction.RIGHT;
                }
                else if(XLoc>=checkpoints[locationIncrementer+1][0])
                {
                    XLoc -= this.getSpeed();
                    distanceTravelled+=this.getSpeed();
                    enemyDirection = direction.LEFT;
                }
            }
            else if(!(YLoc>=checkpoints[locationIncrementer+1][1]-this.getSpeed()&&YLoc<=checkpoints[locationIncrementer+1][1]+this.getSpeed()) ){
                if(YLoc<=checkpoints[locationIncrementer+1][1]){
                    YLoc += this.getSpeed();
                    distanceTravelled+=this.getSpeed();
                    enemyDirection = direction.DOWN;
                }
                else if(YLoc>=checkpoints[locationIncrementer+1][1]){
                    YLoc -= this.getSpeed();
                    distanceTravelled+=this.getSpeed();
                    enemyDirection = direction.UP;
                }
            }
            else{
                locationIncrementer++;
            }


        }
        catch(IndexOutOfBoundsException e){
            visible=false;
            atEndPoint = true;
        }
    }
}
