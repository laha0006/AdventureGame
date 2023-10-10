package game;

import item.Consumable;
import item.Weapon;
import ui.Color;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class Enemy {
    private String shortName;
    private String longName;
    private int enemyHealthPoints;
    private Weapon weapon;
    private Consumable consumable;

    public Enemy(String longName,String shortName, int healthPoints, Weapon weapon) {
        this.longName = longName;
        this.shortName = shortName;
        this.enemyHealthPoints = healthPoints;
        this.weapon = weapon;
    }
    public Enemy(String longName,String shortName, int healthPoints, Weapon weapon, Consumable consumable) {
        this.longName = longName;
        this.shortName = shortName;
        this.enemyHealthPoints = healthPoints;
        this.weapon = weapon;
        this.consumable=consumable;
    }

    public String getLongName() {
        return longName;
    }
    public String getShortName(){
        return shortName;
    }

    public Weapon getWeapon() {
        return weapon;
    }
    public int getWeaponAttack(){
        return weapon.attack();
    }
    public Consumable getConsumable() {
        return consumable;
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }


    public boolean isAlive() {
        return enemyHealthPoints > 0;
    }

    public void loseHealth(int damage) {
        enemyHealthPoints -= damage;
    }

    public int getEnemyHealthPoints() {
        return enemyHealthPoints;
    }

    public void playAttackSound() {
        weapon.playAttackSound();
    }

    public void setEnemyHealthPoints(int enemyHealthPoints) {
        this.enemyHealthPoints = enemyHealthPoints;
    }
}
