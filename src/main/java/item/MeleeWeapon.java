package item;

import game.Audio;
import messages.ItemRarity;

public class MeleeWeapon extends Weapon {
    private int attackPower;


    public MeleeWeapon(String longName, String shortName, int damage, int durability, int attackPower,ItemRarity rarity, Audio attackSound) {
        super(longName, shortName, damage, durability,rarity,attackSound);
    }

    @Override
    public int attack() {
        setDurability(getDurability()-1);
        return getDamage() + attackPower;
    }


}