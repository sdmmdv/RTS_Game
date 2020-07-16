package enemies;

/**
 * NutCracker Enemy
 */
public class NutCracker extends Enemy {

    public NutCracker(int[][] Locations) {
        super(Locations);
        this.reward = 30;
        this.health = 10;
        this.speed = 3;
        this.XLoc = checkpoints[0][0];
        this.YLoc = checkpoints[0][1];
        this.alive = true;
        this.distanceTravelled = 0;
        this.enemyType = type.NUTCRACKER;

    }

}
