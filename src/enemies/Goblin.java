package enemies;
import org.newdawn.slick.Image;

/**
 * Goblin Enemy
 */
public class Goblin extends Enemy {

    public Goblin(int[][] Locations) {
        super(Locations);
        this.reward = 10;
        this.health = 3;
        this.speed = 2;
        this.XLoc = checkpoints[0][0];
        this.YLoc = checkpoints[0][1];
        this.alive = true;
        this.distanceTravelled = 0;
        this.enemyType = type.GOBLIN;

    }



}
