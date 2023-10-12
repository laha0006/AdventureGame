package item;

import game.Audio;
import messages.ItemRarity;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public abstract class Weapon extends Item {
    private int damage;
    private int durability;
    private Audio attackSound;

    public Weapon(String longName, String shortName, int damage, int durability, ItemRarity rarity){
        super(longName,shortName,rarity);
        this.damage = damage;
        this.durability = durability;
        attackSound = null;
    }

    public Weapon(String longName,String shortName, int damage, int durability,ItemRarity rarity,Audio attackSound){
        super(longName,shortName,rarity);
        this.damage = damage;
        this.durability = durability;
        this.attackSound = attackSound;
    }

    public abstract int attack();

    public void playAttackSound() {
        if(attackSound != null) {
            attackSound.playOnce();
        }
    }

    public boolean isBroken() {
        return durability <= 0;
    }

    public int getDamage() {
        return damage;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public Audio getAttackSound() {
        return attackSound;
    }
}
