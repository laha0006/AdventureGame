package game;

import item.Weapon;
import ui.Color;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class Enemy {
    private String shortName;
    private String longName;
    private int enemyHealthPoints;
    private Weapon weapon;

    public Enemy(String longName,String shortName, int healthPoints, Weapon weapon) {
        this.longName = longName;
        this.shortName = shortName;
        this.enemyHealthPoints = healthPoints;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }
    public int getWeaponAttack(){
        return weapon.attack();
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
