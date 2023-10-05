public class MeleeWeapon extends Weapon {
    private int attackPower;
    public MeleeWeapon(String longName, String shortName, int damage, int durability, int attackPower) {
        super(longName, shortName, damage, durability);
    }

    @Override
    public int attack() {
        setDurability(getDurability()-1);
        return getDamage() + attackPower;
    }


}