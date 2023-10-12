package item;

import messages.ItemRarity;

public class Consumable extends Item {
    private final int healthGain;
    private final int attackPower;

    public Consumable(String longName, String shortName, int healthGain, ItemRarity rarity) {
        super(longName,shortName,rarity);
        this.healthGain = healthGain;
        attackPower = 0;
    }

    public Consumable(String longName, String shortName, int healthGain,ItemRarity rarity, int attackPower) {
        super(longName,shortName,rarity);
        this.healthGain = healthGain;
        this.attackPower = attackPower;
    }

    public int getHealthGain() {
        return healthGain;
    }

    public int getAttackPower() {
        return attackPower;
    }
}
