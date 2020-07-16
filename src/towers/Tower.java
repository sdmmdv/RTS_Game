package towers;

import enemies.Enemy;
import game.PlayMenu;

/**
 * General Tower class.
 */
public abstract class Tower {

    public enum type {
        ARCHER, ELECTRO, TERMINATOR
    }
    protected int weaponMargin = 25;
    protected int buyingCost;
    protected int refundValue;
    protected int upgradeCost;
    protected double range;
    protected double damage;
    protected double shootingSpeed;
    private double xCor;
    private double yCor;
    protected double reloadTime;
    private long lastAttackTime;
    protected type towerType;
    private Enemy targetEnemy;
    private double enemyMaxDistance = 0;
    private int level = 1;
    private int maxLevel = 3;
    protected double upgradeRate;

    /**
     * Constructor of the class.
     * @param xCor - coordinate for width
     * @param yCor - coordinate for height
     */
    public Tower(double xCor, double yCor) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.lastAttackTime = 0;
    }

    /**
     * Decides if a tower can attack again depending on its last attack and its reload time.
     * @return boolean
     */
    public boolean canAttack() {
        if ((System.currentTimeMillis() - lastAttackTime) / 1000.0 >= reloadTime) {
            lastAttackTime = System.currentTimeMillis();
            return true;
        } else
            return false;
    }

    /**
     * Upgrades the level of the tower if it's not in the maximum one and there's enough money for the upgrade.
     */
    public void upgrade(){
        if(getLevel() < getMaxLevel() && PlayMenu.getMoney() >= getUpgradeCost()){
            PlayMenu.setMoney(PlayMenu.getMoney() - getUpgradeCost());
            setRefundValue(getRefundValue() + getUpgradeCost()/2);
            setLevel(getLevel()+1);
            setRange(getRange()*getUpgradeRate());
            setDamage(getDamage()*getUpgradeRate());
            setShootingSpeed(getShootingSpeed()*getUpgradeRate());
        }
        else return;
    }

    // Getters and setters
    public int getMaxLevel() {
        return maxLevel;
    }

    public double getUpgradeRate() {
        return upgradeRate;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setShootingSpeed(double shootingSpeed) {
        this.shootingSpeed = shootingSpeed;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getShootingSpeed() {
        return shootingSpeed;
    }

    public int getBuyingCost() {
        return buyingCost;
    }

    public type getTowerType() {
        return towerType;
    }

    public double getXCor() {
        return xCor;
    }

    public double getYCor() {
        return yCor;
    }

    public void setxCor(double xCor) {
        this.xCor = xCor;
    }

    public void setyCor(double yCor) {
        this.yCor = yCor;
    }

    public type getType() {
        return this.towerType;
    }

    public int getRefundValue() {
        return refundValue;
    }

    public void setRefundValue(int refundValue) {
        this.refundValue = refundValue;
    }

    public void setTargetEnemy(Enemy enemy) {
        targetEnemy = enemy;
    }

    public Enemy getTargetEnemy() {
        return targetEnemy;
    }

    public double getEnemyMaxDistance() {
        return enemyMaxDistance;
    }

    public void setEnemyMaxDistance(double enemyMaxDistance) {
        this.enemyMaxDistance = enemyMaxDistance;
    }


    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public int getLevel() {
        return level;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public void setTimeOfLastAttack(long time) {
        lastAttackTime = time;
    }

    public int getWeaponMargin() {
        return weaponMargin;
    }

    public void setWeaponMargin(int weaponMargin) {
        this.weaponMargin = weaponMargin;
    }
}


