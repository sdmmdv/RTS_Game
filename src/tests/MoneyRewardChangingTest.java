package tests;

import enemies.*;
import game.PlayMenu;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

/**
 * Money Reward Changing Test class
 */
public class MoneyRewardChangingTest {

    /**
     * Tests the enemies appear in the correct order, the same as in the queue.
     * Tests the enemies are being removed from the queue once they're dead.
     * Tests the player is earning money once the enemies get killed.
     */
    @Test
    public void testMoneyRewardChanging(){

        // random values for initialising the enemies with coordinates
        int[][] temp = {{50,50},{50,50}};
        // If a BioString enemy dies, it gives the player a reward of 25 money units
        Enemy bioStrong = new BioStrong(temp);
        // If a Goblin enemy dies, it gives the player a reward of 10 money units
        Enemy goblin = new Goblin(temp);
        // If a HyperKid enemy dies, it gives the player a reward of 15 money units
        Enemy hyperKid = new HyperKid(temp);
        // If a MadKnight enemy dies, it gives the player a reward of 20 money units
        Enemy madKnight = new MadKnight(temp);
        // If a NutCracker enemy dies, it gives the player a reward of 30 money units
        Enemy nutCracker = new NutCracker(temp);

        PlayMenu playMenu = new PlayMenu(0);
        Queue<Enemy> enemyQueue = new LinkedList<Enemy>();
        playMenu.setMoney(1000);
        playMenu.setActiveEnemyQueue(enemyQueue);
        assertTrue("The amount of money should be 1000", playMenu.getMoney() == 1000);


        playMenu.getActiveEnemyQueue().add(bioStrong);
        assertTrue("The first enemy in the queue should be BioStrong", playMenu.getActiveEnemyQueue().peek().getType() == Enemy.type.BIOSTRONG);
        assertTrue("BioStrong Health should be equal to 10", playMenu.getActiveEnemyQueue().peek().getHealth() == 10);
        playMenu.getActiveEnemyQueue().peek().takeDamage(10);
        assertTrue("BioStrong should be dead", !playMenu.getActiveEnemyQueue().peek().isAlive());
        playMenu.updateEnemies();
        assertTrue("The queue should be empty after killing this enemy", playMenu.getActiveEnemyQueue().isEmpty());
        assertTrue("The amount of money after getting the reward should be 1025", playMenu.getMoney() == 1025);

        playMenu.getActiveEnemyQueue().add(goblin);
        assertTrue("The second enemy in the queue should be Goblin", playMenu.getActiveEnemyQueue().peek().getType() == Enemy.type.GOBLIN);
        assertTrue("Goblin Health should be equal to 3", playMenu.getActiveEnemyQueue().peek().getHealth() == 3);
        playMenu.getActiveEnemyQueue().peek().takeDamage(3);
        assertTrue("Goblin should be dead", !playMenu.getActiveEnemyQueue().peek().isAlive());
        playMenu.updateEnemies();
        assertTrue("The queue should be empty after killing this enemy", playMenu.getActiveEnemyQueue().isEmpty());
        assertTrue("The amount of money after getting the reward should be 1035", playMenu.getMoney() == 1035);

        playMenu.getActiveEnemyQueue().add(hyperKid);
        assertTrue("The third enemy in the queue should be HyperKid", playMenu.getActiveEnemyQueue().peek().getType() == Enemy.type.HYPERKID);
        assertTrue("HyperKid Health should be equal to 3", playMenu.getActiveEnemyQueue().peek().getHealth() == 3);
        playMenu.getActiveEnemyQueue().peek().takeDamage(3);
        assertTrue("HyperKid should be dead", !playMenu.getActiveEnemyQueue().peek().isAlive());
        playMenu.updateEnemies();
        assertTrue("The queue should be empty after killing this enemy", playMenu.getActiveEnemyQueue().isEmpty());
        assertTrue("The amount of money after getting the reward should be 1050", playMenu.getMoney() == 1050);

        playMenu.getActiveEnemyQueue().add(madKnight);
        assertTrue("The fourth enemy in the queue should be MadKnight", playMenu.getActiveEnemyQueue().peek().getType() == Enemy.type.MADKNIGHT);
        assertTrue("MadKnight Health should be equal to 10", playMenu.getActiveEnemyQueue().peek().getHealth() == 10);
        playMenu.getActiveEnemyQueue().peek().takeDamage(10);
        assertTrue("MadKnight should be dead", !playMenu.getActiveEnemyQueue().peek().isAlive());
        playMenu.updateEnemies();
        assertTrue("The queue should be empty after killing this enemy", playMenu.getActiveEnemyQueue().isEmpty());
        assertTrue("The amount of money after getting the reward should be 1070", playMenu.getMoney() == 1070);

        playMenu.getActiveEnemyQueue().add(nutCracker);
        assertTrue("The fifth and last enemy in the queue should be NutCracker", playMenu.getActiveEnemyQueue().peek().getType() == Enemy.type.NUTCRACKER);
        assertTrue("NutCracker Health should be equal to 10", playMenu.getActiveEnemyQueue().peek().getHealth() == 10);
        playMenu.getActiveEnemyQueue().peek().takeDamage(10);
        assertTrue("NutCracker should be dead", !playMenu.getActiveEnemyQueue().peek().isAlive());
        playMenu.updateEnemies();
        assertTrue("The queue should be empty after killing this enemy", playMenu.getActiveEnemyQueue().isEmpty());
        assertTrue("The amount of money after getting the reward should be 1100", playMenu.getMoney() == 1100);
    }
}