package item;

public class Consumable extends Item {
    private final int healthGain;
    private final int attackPower;

    public Consumable(String longName, String shortName, int healthGain) {
        super(longName,shortName);
        this.healthGain = healthGain;
        attackPower = 0;
    }

    public Consumable(String longName, String shortName, int healthGain, int attackPower) {
        super(longName,shortName);
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
