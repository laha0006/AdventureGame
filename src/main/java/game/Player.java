package game;

import messages.Direction;
import messages.ReturnAttack;
import messages.ReturnConsumable;
import messages.Status;
import item.Consumable;
import item.Item;
import item.Weapon;

import java.util.ArrayList;

public class Player {
    private Room currentPosition;
    private Room previousPosition;
    private ArrayList<Item> inventory;
    private int maxHealthPoints;
    private int healthPoints;
    private int attackPower;
    private Weapon weaponSlot1;
    private Weapon weaponSlot2;
    //inventory

    public Player() {
        inventory = new ArrayList<Item>();
        maxHealthPoints = 60;
        healthPoints = 50;
    }

    public boolean movePlayer(Direction direction) {
        switch (direction) {
            case NORTH:
                Room northRoom = currentPosition.getNorth();
                return moveToRoom(northRoom);

            case SOUTH:
                Room southRoom = currentPosition.getSouth();
                return moveToRoom(southRoom);

            case EAST:
                Room eastRoom = currentPosition.getEast();
                return moveToRoom(eastRoom);

            case WEST:
                Room westRoom = currentPosition.getWest();
                return moveToRoom(westRoom);

            case UP:
                Room upRoom = currentPosition.getUp();
                return moveToRoom(upRoom);

            case DOWN:
                Room downRoom = currentPosition.getDown();
                return moveToRoom(downRoom);
        }
        return false;
    }

    private boolean moveToRoom(Room room) {
        if (room != null) {
            setCurrentPosition(room);
            updateAmbientSound();
            return true;
        } else return false;
    }

    public void setCurrentPosition(Room room) {
        if (currentPosition != null) {
            previousPosition = currentPosition;
        } else {
            previousPosition = room;
        }
        currentPosition = room;
    }

    public Room getPlayerPosition() {
        return currentPosition;
    }

    public Item takeItem(String itemName) {
        Item itemToTake = currentPosition.searchRoom(itemName);
        if (itemToTake != null) {
            currentPosition.removeItem(itemToTake);
            inventory.add(itemToTake);
            return itemToTake;
        }
        return null;
    }

    public Item dropItem(String itemName) {
        Item itemToDrop = searchInv(itemName);
        if (itemToDrop != null) {
            inventory.remove(itemToDrop);
            currentPosition.addItem(itemToDrop);
            if (weaponSlot1 != null && itemToDrop.getLongName().equals(weaponSlot1.getLongName())) {
                weaponSlot1 = null;
            }
            return itemToDrop;
        }
        return null;
    }

    public Item searchInv(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public String getCurrentRoomDescription() {
        return currentPosition.getDescription();
    }

    public String getCurrentRoomName() {
        return currentPosition.getName();
    }

    public ArrayList<Item> getCurrentRoomLoot() {
        return currentPosition.getLoot();
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }


    public Weapon getWeaponSlot1() {
        return weaponSlot1;
    }

    public ReturnConsumable consume(String foodName) {
        ReturnConsumable result = new ReturnConsumable();
        Item foodToEat = searchInv(foodName);
        if (foodToEat instanceof Consumable) {
            inventory.remove(foodToEat);
            int tempHealthPoints = healthPoints + ((Consumable) foodToEat).getHealthGain();
            healthPoints = Math.min(tempHealthPoints, maxHealthPoints);
            if (((Consumable) foodToEat).getAttackPower() != 0) {
                attackPower += (((Consumable) foodToEat).getAttackPower());
                result.setEffect(((Consumable) foodToEat).getAttackPower());
            }
            result.setStatus(Status.CONSUMABLE);
            result.setOutputText(foodToEat.getLongName());
            result.setItem(foodToEat);
            return result;
        }
        if (foodToEat == null) {
            result.setStatus(Status.MISSING);
            result.setOutputText(foodName);
            result.setItem(null);
            return result;
        }
        result.setStatus(Status.NON_CONSUMABLE);
        result.setOutputText(foodToEat.getLongName());
        result.setItem(foodToEat);
        return result;
    }

    public ArrayList<Item> getConsumables() {
        ArrayList<Item> foodItems = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof Consumable) foodItems.add(item);
        }
        return foodItems;
    }
    public ArrayList<Item> getWeapons() {
        ArrayList<Item> weapons = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof Weapon) weapons.add(item);
        }
        return weapons;
    }
    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof Weapon || item instanceof  Consumable) items.remove(item);
           else items.add(item);
        }
        return items;
    }



    public Status equip(String itemName) {
        Item itemToEquip = searchInv(itemName);
        if (itemToEquip == null) return Status.MISSING;
        if (!(itemToEquip instanceof Weapon)) return Status.NON_EQUIPPABLE;
        if (((Weapon) itemToEquip).isBroken()) return Status.BROKEN;
        weaponSlot1 = (Weapon) itemToEquip;
        return Status.EQUIPPABLE;

    }

    public ReturnAttack attack(String enemyName) {
        ReturnAttack result = new ReturnAttack();
        Enemy currentEnemy = currentPosition.findEnemy(enemyName);
        if (weaponSlot1 == null) {
            result.setStatus(Status.MISSING);
            return result;
        }
        if (weaponSlot1.isBroken()) {
            result.setOutputText(weaponSlot1.getLongName());
            result.setStatus(Status.BROKEN);
            return result;
        }
        if (currentEnemy == null) {
            result.setStatus(Status.NO_ENEMY);
            result.setOutputText(weaponSlot1.getLongName());
            return result;
        }
        int attackDamage = weaponSlot1.attack() + attackPower;
        int enemyDamage = currentEnemy.getWeaponAttack();

        result.setPlayerDamage(attackDamage);
        result.setOutputText(weaponSlot1.getShortName());
        result.setEnemyDamage(enemyDamage);
        result.setEnemyName(currentEnemy.getShortName());
        result.setBroken(weaponSlot1.isBroken());
        result.setLostEffect(attackPower);
        attackPower = 0;

        currentEnemy.loseHealth(attackDamage); // player deals damage to enemy
        weaponSlot1.playAttackSound();
        result.setEnemyHealthPoints(currentEnemy.getEnemyHealthPoints());
        if (!currentEnemy.isAlive()) {
            currentPosition.addItem(currentEnemy.getWeapon());
            if(currentEnemy.getConsumable()!=null) {
                currentPosition.addItem(currentEnemy.getConsumable());
            }
            currentPosition.removeEnemy(currentEnemy);
            result.setStatus(Status.ENEMY_DEAD);
            return result;
        }
        healthPoints -= enemyDamage; // enemy deals damage to player
        if (healthPoints < 1) {
            result.setStatus(Status.PLAYER_DEAD);
            return result;
        }
        result.setStatus(Status.SUCCESS);
        return result;
    }

    public ArrayList<Enemy> getEnemiesInCurrentRoom() {
        return currentPosition.getEnemies();
    }


    private void updateAmbientSound() {
        if (previousPosition.getAmbientSound() != null) {
            previousPosition.stopAmbient();
        }
        if (currentPosition.getAmbientSound() != null) {
            currentPosition.playAmbient();
        }
    }

}


