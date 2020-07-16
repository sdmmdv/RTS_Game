package enemies;

/**
 * MadKnight Enemy
 */
public class MadKnight extends Enemy {

    public MadKnight(int[][] Locations) {
        super(Locations);
        this.reward = 20;
        this.health = 10;
        this.speed = 2;
        this.XLoc = checkpoints[0][0];
        this.YLoc = checkpoints[0][1];
        this.alive = true;
        this.distanceTravelled = 0;
        this.enemyType = type.MADKNIGHT;

    }

}

