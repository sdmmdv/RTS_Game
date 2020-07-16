package towers;

import enemies.Enemy;

import java.util.Queue;

public class Projectile {

    public enum projectileType{
        ARCHER,ELECTRO,TERMINATOR,ADVANCEDELECTRO,ADVANCEDTERMINATOR
    }

    private double xInit;
    private double yInit;
    private double xLoc;
    private double yLoc;
    private double xDest;
    private double yDest;
    private double damage;
    private double speed;
    private Enemy targetEnemy;
    private boolean arrivedAtTarget;
    private projectileType projType;


    public Projectile(Tower shootingTower, Enemy targetEnemy, projectileType type){
        this.xInit = shootingTower.getXCor();
        this.yInit = shootingTower.getYCor();
        this.xDest = targetEnemy.getXLoc();
        this.yDest = targetEnemy.getYLoc();
        this.damage = shootingTower.getDamage();
        this.speed = shootingTower.getShootingSpeed();
        this.targetEnemy = targetEnemy;
        this.projType = type;
        arrivedAtTarget = false;

        xLoc = xInit +12*Math.cos(angleOfProjectileInRadians());
        yLoc = yInit +12*Math.sin(angleOfProjectileInRadians());

    }

    public double getxDest() {
        return xDest;
    }

    public double getyDest() {
        return yDest;
    }

    public void setxInit(double xInit) {
        this.xInit = xInit;
    }

    public void setyInit(double yInit) {
        this.yInit = yInit;
    }

    public double getxInit() {
        return xInit;
    }

    public double getyInit() {
        return yInit;
    }


    public void radiateZone(Queue<Enemy> enemyQueue){
        for(Enemy enemy : enemyQueue)
            if(enemy.getXLoc() < getXLoc() + 40 && enemy.getXLoc() > getXLoc() - 40
                    && enemy.getYLoc() < getYLoc() + 40 && enemy.getYLoc() > getYLoc() - 40) {
                enemy.takeDamage(5);
            }
    }

    public void move(){

        //projectile has hit
        if (Math.abs(xLoc - xDest)< speed/2 || Math.abs(yLoc - yDest)< speed/2){
            arrivedAtTarget = true;

            targetEnemy.takeDamage(damage);

            if(projType == projectileType.ADVANCEDELECTRO) {
                targetEnemy.setFreezeDuration(1000);
                targetEnemy.freezeEnemy();
            }


        }
        else{
            xLoc += speed*Math.cos(angleOfProjectileInRadians());
            yLoc += speed*Math.sin(angleOfProjectileInRadians());
        }
    }



    public double angleOfProjectileInRadians(){
        return Math.atan2(yDest-yInit, xDest-xInit);
    }
    public double angleOfProjectileInDegrees(){
        return (180/Math.PI)*Math.atan2(yDest-yInit, xDest-xInit);
    }

    public boolean hasArrived(){
        return arrivedAtTarget;
    }

    public double getXLoc(){
        return this.xLoc;
    }
    public double getYLoc(){
        return this.yLoc;
    }

    public void setXLoc(double xLoc) {
        this.xLoc = xLoc;
    }

    public void setYLoc(double yLoc) {
        this.yLoc = yLoc;
    }

    public double getSpeed(){
        return this.speed;
    }

    public projectileType getProjType() {
        return projType;
    }
}
