public class Enemy {
    private String name;
    private int enemyHealthPoints;
    private Weapon weapon;

    public Enemy(String name, int healthPoints, Weapon weapon) {
        this.name = name;
        this.enemyHealthPoints = healthPoints;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }
    public int getWeaponAttack(){
        return weapon.attack();
    }

    public boolean isAlive() {
        return enemyHealthPoints > 0;
    }

    public void loseHealth(int damage) {
        enemyHealthPoints -= damage;
    }

    public int getEnemyHealthPoints() {
        return enemyHealthPoints;
    }

    public void setEnemyHealthPoints(int enemyHealthPoints) {
        this.enemyHealthPoints = enemyHealthPoints;
    }
}
