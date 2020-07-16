package tests;

import enemies.*;
import org.junit.Test;
import towers.Archer;
import towers.Electro;
import towers.Terminator;
import towers.Tower;

import java.io.IOException;
import static org.junit.Assert.*;

/**
 * Tower versus Enemy Test class
 */
public class TowerVSEnemyTest {
    /**
     * Test the three different towers against the five different enemies.
     * Test if the enemies are getting damaged and died when they should.
     * @throws IOException
     */
    @Test
    public void testTowersAndEnemies() throws IOException {

        // Archer's Damage is equal to 1
        Tower archer = new Archer(0,0);
        // BioStrong's Health is equal to 10
        Enemy bioStrong = new BioStrong(new int[2][2]);
        for(int i=0; i<10; i++){
            bioStrong.takeDamage(archer.getDamage());
        }
        assertFalse("Has BioStrong died after being attacked 10 times?", bioStrong.isAlive());

        // Goblin's Health is equal to 3
        Enemy goblin = new Goblin(new int[2][2]);
        for(int i=0; i<3; i++){
            goblin.takeDamage(archer.getDamage());
        }
        assertFalse("Has Goblin died after being attacked 3 times?", goblin.isAlive());

        // HyperKid's Health is equal to 3
        Enemy hyperKid = new HyperKid(new int[2][2]);
        for(int i=0; i<3; i++){
            hyperKid.takeDamage(archer.getDamage());
        }
        assertFalse("Has HyperKid died after being attacked 3 times?", hyperKid.isAlive());

        // MadKnight's Health is equal to 10
        Enemy madKnight = new MadKnight(new int[2][2]);
        for(int i=0; i<10; i++){
            madKnight.takeDamage(archer.getDamage());
        }
        assertFalse("Has MadKnight died after being attacked 10 times?", madKnight.isAlive());

        // NutCracker's Health is equal to 10
        Enemy nutCracker = new NutCracker(new int[2][2]);
        for(int i=0; i<10; i++){
            nutCracker.takeDamage(archer.getDamage());
        }
        assertFalse("Has NutCracker died after being attacked 10 times?", nutCracker.isAlive());



        // Electro's Damage is equal to 0.1
        Tower electro = new Electro(0,0);
        // BioStrong's Health is equal to 10
        Enemy bioStrong2 = new BioStrong(new int[2][2]);
        for(int i=0; i<101; i++){
            bioStrong2.takeDamage(electro.getDamage());
        }
        assertFalse("Has BioStrong died after being attacked 100 times?", bioStrong2.isAlive());

        // Goblin's Health is equal to 3
        Enemy goblin2 = new Goblin(new int[2][2]);
        for(int i=0; i<30; i++){
            goblin2.takeDamage(electro.getDamage());
        }
        assertFalse("Has Goblin died after being attacked 30 times?", goblin2.isAlive());

        // HyperKid's Health is equal to 3
        Enemy hyperKid2 = new HyperKid(new int[2][2]);
        for(int i=0; i<30; i++){
            hyperKid2.takeDamage(electro.getDamage());
        }
        assertFalse("Has HyperKid died after being attacked 30 times?", hyperKid2.isAlive());

        // MadKnight's Health is equal to 10
        Enemy madKnight2 = new MadKnight(new int[2][2]);
        for(int i=0; i<101; i++){
            madKnight2.takeDamage(electro.getDamage());
        }
        assertFalse("Has MadKnight died after being attacked 100 times?", madKnight2.isAlive());

        // NutCracker's Health is equal to 10
        Enemy nutCracker2 = new NutCracker(new int[2][2]);
        for(int i=0; i<101; i++){
            nutCracker2.takeDamage(electro.getDamage());
        }
        assertFalse("Has NutCracker died after being attacked 100 times?", nutCracker2.isAlive());

        // Terminator's Damage is equal to 5
        Tower terminator = new Terminator(0,0);
        // BioStrong's Health is equal to 10
        Enemy bioStrong3 = new BioStrong(new int[2][2]);
        for(int i=0; i<2; i++){
            bioStrong2.takeDamage(terminator.getDamage());
        }
        assertFalse("Has BioStrong died after being attacked 2 times?", bioStrong2.isAlive());

        // Goblin's Health is equal to 3
        Enemy goblin3 = new Goblin(new int[2][2]);
        goblin3.takeDamage(terminator.getDamage());
        assertFalse("Has Goblin died after being attacked 1 times?", goblin3.isAlive());

        // HyperKid's Health is equal to 3
        Enemy hyperKid3 = new HyperKid(new int[2][2]);
        hyperKid3.takeDamage(terminator.getDamage());
        assertFalse("Has HyperKid died after being attacked 1 times?", hyperKid3.isAlive());

        // MadKnight's Health is equal to 10
        Enemy madKnight3 = new MadKnight(new int[2][2]);
        for(int i=0; i<2; i++){
            madKnight3.takeDamage(terminator.getDamage());
        }
        assertFalse("Has MadKnight died after being attacked 2 times?", madKnight3.isAlive());

        // NutCracker's Health is equal to 10
        Enemy nutCracker3 = new NutCracker(new int[2][2]);
        for(int i=0; i<2; i++){
            nutCracker3.takeDamage(terminator.getDamage());
        }
        assertFalse("Has NutCracker died after being attacked 2 times?", nutCracker3.isAlive());
    }
}