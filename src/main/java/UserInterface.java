import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private final Adventure adventure;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
    }

    public void start() {
        System.out.println("Welcome to the The Game currently know as Adventure Game");
        System.out.println("Write 'help' to show commands");
        System.out.println("You've entered:  " + adventure.getCurrentRoomName());
        System.out.println(adventure.getCurrentRoomDescription());
        while (true) {
            handleInput();
        }
    }
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] splitInput = input.trim().toLowerCase().split(" ");

        if (splitInput.length == 1) {
            switch (splitInput[0]) {
                case "n", "north":
                    if (adventure.movePlayer("north")) {
                        System.out.println("You went north to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }

                case "s", "south":
                    if (adventure.movePlayer("south")) {
                        System.out.println("You went south to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    }
                case "e", "east":
                    if (adventure.movePlayer("east")) {
                        System.out.println("You went east to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }
                case "w", "west":
                    if (adventure.movePlayer("west")) {
                        System.out.println("You went west to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }
                case "look":
                    showLoot(adventure.getCurrentRoomLoot());
                    System.out.println("\n" + adventure.getCurrentRoomDescription());
                    break;

                case "help":
                    help();
                    break;
                case "inv", "inventory":
                    showInventory(adventure.getPlayerInventory());
                    break;
                case "consumables":
                    showInventory(adventure.getPlayerConsumables());

            }
        } else {
            switch(splitInput[0]) {
                case "take":
                    Item itemToTake = adventure.takeItem(splitInput[1]);
                    if(itemToTake != null) {
                        System.out.println("Added " + itemToTake.getLongName() + " to inventory.");
                    } else {
                        System.out.println(splitInput[1] +" doesn't exist");
                    }
                    break;
                case "drop":
                    Item itemToDrop = adventure.dropItem(splitInput[1]);
                    if(itemToDrop != null) {
                        System.out.println("Dropped " + itemToDrop.getLongName() + " in " + adventure.getCurrentRoomName());
                    } else {
                        System.out.println("You don't carry " + splitInput[1]);
                    }
                    break;
                case "consume", "eat":
                    Item itemToConsume = adventure.consumeItem(splitInput[1]);
                    if(itemToConsume != null) {
                        System.out.println("Consumed " + itemToConsume.getLongName() + " and gained " + ((Consumable) itemToConsume).getHealthGain() + "HP");
                        System.out.println("Your current health is now " + adventure.getPlayerHealthPoints() + " out of " + adventure.getPlayerMaxHealthPoints() + "HP");
                    } else {
                        System.out.println("You don't carry " + splitInput[1]);
                    }
                    break;

            }
        }
    }

    public void help() {
        System.out.println("You use the following commands to play the game;");
        System.out.println("To move in a direction enter 'N', 'S', 'E', 'W' to move north, south, east or west.");
        System.out.println("Enter 'look' to look around at your current location");
        System.out.println("Enter 'inv' or 'inventory' to open your inventory");
        System.out.println("Enter 'take [item]' to pick-up item");
        System.out.println("Enter 'drop [item]' to drop item");
    }


    public void showLoot(ArrayList<Item> loot) {
        if (!loot.isEmpty()) {
            System.out.println("You discover: ");
            if (loot.size() == 1) {
                System.out.print(loot.get(0).getLongName());
            } else {
                for (Item item : loot) {
                    System.out.print(item.getLongName() + ", ");
                }
            }
        } else {
            System.out.print("You discover nothing.");
        }

    }

    public void showInventory(ArrayList<Item> inventory) {
        if (!inventory.isEmpty()) {
            System.out.println("You are carrying: ");
            if (inventory.size() == 1) {
                System.out.print(inventory.get(0).getLongName());
            } else {
                for (Item item : inventory) {
                    System.out.print(item.getLongName() + ", ");
                }
            }
        } else {
            System.out.print("You carry nothing.");
        }

    }

}
