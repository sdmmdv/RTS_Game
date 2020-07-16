package towers;

import game.PlayMenu;

/**
 * Electro Tower
 */

public class Electro extends Tower{

    public Electro(double xCor, double yCor) {
        super(xCor, yCor);
        this.buyingCost = 150;
        this.refundValue = 75;
        this.upgradeCost = 70;
        this.range = 120;
        this.damage = 0.1;
        this.shootingSpeed = 8;
        this.reloadTime = 0;
        this.upgradeRate = 1.2;
        this.towerType = type.ELECTRO;
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
