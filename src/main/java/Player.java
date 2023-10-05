import java.util.ArrayList;

public class Player {
    private Room currentPosition;
    private ArrayList<Item> inventory;
    private int maxHealthPoints = 60;
    private int healthPoints;
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
                if (northRoom != null) {
                    currentPosition = northRoom;
                    return true;
                } else return false;

            case SOUTH:
                Room southRoom = currentPosition.getSouth();
                if (southRoom != null) {
                    currentPosition = southRoom;
                    return true;
                } else return false;

            case EAST:
                Room eastRoom = currentPosition.getEast();
                if (eastRoom != null) {
                    currentPosition = eastRoom;
                    return true;
                } else return false;

            case WEST:
                Room westRoom = currentPosition.getWest();
                if (westRoom != null) {
                    currentPosition = westRoom;
                    return true;
                } else return false;
        }
        return false;
    }

    public void setCurrentPosition(Room room) {
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
            if(weaponSlot1 != null && itemToDrop.getLongName().equals(weaponSlot1.getLongName())) {
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

    public ReturnConsumable consume(String foodName) {
        ReturnConsumable result = new ReturnConsumable();
        Item foodToEat = searchInv(foodName);
        if (foodToEat instanceof Consumable) {
            inventory.remove(foodToEat);
            healthPoints += ((Consumable) foodToEat).getHealthGain();
            healthPoints = Math.min(healthPoints,maxHealthPoints);
            result.setStatus(Status.CONSUMABLE);
            result.setOutputText(foodToEat.getLongName());
            result.setItem(foodToEat);
            return result;
        }
        if(foodToEat == null) {
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
            if(item instanceof Consumable) foodItems.add(item);
        }
        return foodItems;
    }

    public Status equip(String itemName) {
        Item itemToEquip = searchInv(itemName);
        if(itemToEquip == null) return Status.MISSING;
        if(!(itemToEquip instanceof Weapon)) return  Status.NON_EQUIPPABLE;
        if( ((Weapon) itemToEquip).isBroken()) return Status.BROKEN;
        weaponSlot1 = (Weapon) itemToEquip;
        return Status.EQUIPPABLE;

    }

    public ReturnAttack attack() {
        ReturnAttack result = new ReturnAttack();
        if(weaponSlot1 == null) {
            result.setStatus(Status.MISSING);
            return result;
        }
        if(weaponSlot1.isBroken()){
            result.setOutputText(weaponSlot1.getLongName());
            result.setStatus(Status.BROKEN);
            return result;
        }
        result.setDamage(weaponSlot1.attack());
        result.setOutputText(weaponSlot1.getShortName());
        result.setStatus(Status.SUCCESS);
        result.setBroken(weaponSlot1.isBroken());
        return result;
    }

}


