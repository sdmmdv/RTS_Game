package towers;

import game.PlayMenu;

public class Terminator extends Tower{

    public Terminator(double xCor, double yCor) {
        super(xCor, yCor);
        this.buyingCost = 200;
        this.refundValue = 100;
        this.upgradeCost = 100;
        this.range = 200;
        this.damage = 10;
        this.shootingSpeed = 10;
        this.reloadTime = 1;
        this.upgradeRate = 1.3;
        this.towerType = type.TERMINATOR;
    }

    @Override
    public void upgrade(){
        if(this.getLevel() >= 2) {
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
