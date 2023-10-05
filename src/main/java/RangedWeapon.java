public class RangedWeapon extends Weapon{
    private int range;

    public RangedWeapon(String longName, String shortName, int damage, int durability){
        super(longName, shortName, damage, durability);

    }

    @Override
    public int attack() {
        setDurability(getDurability()-1);
        return getDamage();
    }


}
