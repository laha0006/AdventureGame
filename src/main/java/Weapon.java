public abstract class Weapon extends Item {
    private int damage;
    private int durability;

    public Weapon(String longName,String shortName, int damage, int durability){
        super(longName,shortName);
        this.damage = damage;
        this.durability = durability;
    }

    public abstract int attack();

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

}
