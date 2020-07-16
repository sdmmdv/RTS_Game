package enemies;
import org.newdawn.slick.Image;

/**
 * HyperKid Enemy
 */
public class HyperKid extends Enemy {
    public HyperKid(int[][] Locations) {
        super(Locations);
        this.reward = 15;
        this.health = 3;
        this.speed = 4;
        this.XLoc = checkpoints[0][0];
        this.YLoc = checkpoints[0][1];
        this.alive = true;
        this.distanceTravelled = 0;
        this.enemyType = type.HYPERKID;

    }

}
