package item;

import messages.ItemRarity;

public class RangedWeapon extends Weapon{
    private int range;

    public RangedWeapon(String longName, String shortName, int damage, int durability, ItemRarity rarity){
        super(longName, shortName, damage, durability,rarity);

    }

    @Override
    public int attack() {
        setDurability(getDurability()-1);
        return getDamage();
    }


}
