package item;

import game.Audio;

public class MeleeWeapon extends Weapon {
    private int attackPower;


    public MeleeWeapon(String longName, String shortName, int damage, int durability, int attackPower) {
        super(longName, shortName, damage, durability);
    }
    public MeleeWeapon(String longName, String shortName, int damage, int durability, int attackPower, Audio attackSound) {
        super(longName, shortName, damage, durability,attackSound);
    }

    @Override
    public int attack() {
        setDurability(getDurability()-1);
        return getDamage() + attackPower;
    }


}