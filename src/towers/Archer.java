package towers;

import game.PlayMenu;

/**
 * Archer Tower
 */
public class Archer extends Tower{
    public Archer(double xCor, double yCor) {
        super(xCor, yCor);
        this.buyingCost = 100;
        this.refundValue = 50;
        this.upgradeCost = 50;
        this.range = 150;
        this.damage = 1;
        this.shootingSpeed = 4;
        this.reloadTime = 0.3;
        this.upgradeRate = 1.5;
        this.towerType = type.ARCHER;
    }

    @Override
    public void upgrade(){
        if(this.getLevel() == 2) {
            super.upgrade();
            return;
        }

        if(getLevel() < getMaxLevel() && PlayMenu.getMoney() >= getUpgradeCost()){
            PlayMenu.setMoney(PlayMenu.getMoney() - getUpgradeCost());
            setRefundValue(getRefundValue() + getUpgradeCost()/2);
            setLevel(getLevel()+1);
        }
        else
            return;
    }


}
