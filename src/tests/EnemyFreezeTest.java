package tests;

import enemies.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Enemy Freeze Test class
 */
public class EnemyFreezeTest {
    /**
     * Test if the different enemies get freeze, slowing their speed during the right amount of time.
     * @throws IOException
     */
    @Test
    public void testEnemyFreeze() throws IOException {

        Enemy bioStrong = new BioStrong(new int[2][2]);
        bioStrong.setFreezeRate(0.5);
        bioStrong.setSpeed(4);
        bioStrong.freezeEnemy();
        assertTrue("The BioStrong's speed should be 2 after freezing", bioStrong.getSpeed() == 2);

        Enemy goblin = new Goblin(new int[2][2]);
        goblin.setFreezeRate(0.25);
        goblin.setSpeed(8);
        goblin.freezeEnemy();
        assertTrue("The Goblin's speed should be 2 after freezing", goblin.getSpeed() == 2);

        Enemy hyperKid = new HyperKid(new int[2][2]);
        hyperKid.setFreezeRate(0.25);
        hyperKid.setSpeed(16);
        hyperKid.freezeEnemy();
        assertTrue("The HyperKid's speed should be 4 after freezing", hyperKid.getSpeed() == 4);

        Enemy madKnight = new MadKnight(new int[2][2]);
        madKnight.setFreezeRate(0.5);
        madKnight.setSpeed(2);
        madKnight.freezeEnemy();
        assertTrue("The MadKnight's speed should be 1 after freezing", madKnight.getSpeed() == 1);

        Enemy nutCracker = new NutCracker(new int[2][2]);
        nutCracker.setFreezeRate(0.1);
        nutCracker.setSpeed(10);
        nutCracker.freezeEnemy();
        assertTrue("The NutCracker's speed should be 1 after freezing", nutCracker.getSpeed() == 1);
    }
}