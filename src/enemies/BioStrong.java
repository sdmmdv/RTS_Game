package enemies;
/**
 * BioStrong Enemy
 */
public class BioStrong extends Enemy {

    public BioStrong(int[][] Locations) {
        super(Locations);
        this.reward = 25;
        this.health = 10;
        this.speed = 3;
        this.XLoc = checkpoints[0][0];
        this.YLoc = checkpoints[0][1];
        this.alive = true;
        this.distanceTravelled = 0;
        this.enemyType = type.BIOSTRONG;
    }
}