public class Consumable extends Item {
    private final int healthGain;

    public Consumable(String longName, String shortName, int healthGain) {
        super(longName,shortName);
        this.healthGain = healthGain;
    }

    public int getHealthGain() {
        return healthGain;
    }

    public int getAttackPower() {
        return attackPower;
    }
}
