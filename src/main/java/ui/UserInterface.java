package ui;

import game.Enemy;
import item.Consumable;
import messages.*;
import game.Adventure;
import item.Item;
import ui.table.Row;
import ui.table.Table;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Adventure adventure;
    Scanner scanner;


    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the The Game currently know as game.Adventure Game");
        System.out.println("Write 'help' to show commands");
        System.out.println("You've entered:  " + adventure.getCurrentRoomName());
        System.out.println(adventure.getCurrentRoomDescription());
        while (true) {
            handleInput();
        }
    }
    public void handleInput() {
        String input = scanner.nextLine();

        String[] splitInput = input.trim().toLowerCase().split(" ");

        if (splitInput.length == 1) {
            switch (splitInput[0]) {
                case "n", "north":
                    move(Direction.NORTH);
                    break;

                case "s", "south":
                    move(Direction.SOUTH);
                    break;

                case "e", "east":
                    move(Direction.EAST);
                    break;

                case "w", "west":
                    move(Direction.WEST);
                    break;

                case "up":
                    move(Direction.UP);
                    break;

                case "down":
                    move(Direction.DOWN);
                    break;

                case "look":
                    look();
                    System.out.println("\n" + adventure.getCurrentRoomDescription());
                    break;

                case "help":
                    help();
                    break;

                case "inv", "inventory":
                    inventory();
                    break;

                case "hp":
                    System.out.println("You currently have " + adventure.getPlayerHealthPoints() +"HP");
                    break;
                case "rarity":
                    rarityChart();
                    break;

            }
        } else {
            switch(splitInput[0]) {
                case "take":
                    Item itemToTake = adventure.takeItem(splitInput[1]);
                    if(itemToTake != null) {
                        System.out.println("Added " + itemToTake.getRarity() + itemToTake.getLongName() + Color.RESET +  " to inventory.");
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
                case "consume", "eat", "inject", "sniff", "drink":
                    ReturnConsumable itemToConsume = adventure.consumeItem(splitInput[1]);
                    String color = itemToConsume.getItem().getRarity();

                    switch(itemToConsume.getStatus()) {
                        case CONSUMABLE:
                            System.out.println("Consumed " + color  + itemToConsume.getOutputText() + Color.RESET + " and gained " + itemToConsume.getItemHealthGain() + "HP");
                            System.out.println("Your current health is now " + adventure.getPlayerHealthPoints() + "/" + adventure.getPlayerMaxHealthPoints() + "HP");
                            if(itemToConsume.getEffect() != 0) {
                                System.out.println("You gained " + itemToConsume.getEffect() + " attack power.");
                            }
                            break;
                        case NON_CONSUMABLE:
                            System.out.println("You can't consume " + itemToConsume.getOutputText());
                            break;
                        case MISSING:
                            System.out.println("You don't carry " + splitInput[1]);
                            break;

                    }
                    break;
                case "equip":
                    ReturnEquip itemToEquip = adventure.equip(splitInput[1]);

                    switch (itemToEquip.getStatus()){
                        case EQUIPPABLE:
                            System.out.println("You equipped " + itemToEquip.getColor() + itemToEquip.getLongName() + Color.RESET);
                            break;
                        case NON_EQUIPPABLE:
                            System.out.println(itemToEquip.getColor() + itemToEquip.getLongName() + Color.RESET + " is not equippable ");
                            break;
                        case BROKEN:
                            System.out.println(itemToEquip.getColor() + itemToEquip.getLongName() + Color.RESET + " is broken");
                            break;
                        case MISSING:
                            System.out.println(splitInput[1] + " is not in your inventory ");
                            break;

                    }
                    break;
                case "attack":
                    ReturnAttack attack = adventure.attack(splitInput[1]);

                    switch(attack.getStatus()) {
                        case MISSING:
                            System.out.println("You flail your fists in the air awkwardly dealing no damage. ");
                            break;
                        case NO_ENEMY:
                            System.out.println("You swing your " + attack.getColor() +  attack.getOutputText() + Color.RESET  + " and hit nothing but air.");
                            break;
                        case BROKEN:
                            System.out.println(attack.getColor() +  attack.getOutputText() + Color.RESET + " is broken.");
                            break;
                        case SUCCESS:
                            System.out.println("You attack " + attack.getEnemyName() + " with " + attack.getColor() +  attack.getOutputText() + Color.RESET  + " and deal " + Color.RED_BOLD + attack.getPlayerDamage() + Color.RESET + " damage. Good job!");
                            System.out.println(attack.getEnemyName() + " has " + attack.getEnemyHealthPoints() + "HP left.");
                            System.out.println(attack.getEnemyName() + " hit you for " + Color.RED_BOLD + attack.getEnemyDamage() + Color.RESET + " damage, and you have " + adventure.getPlayerHealthPoints() + "/" + adventure.getPlayerMaxHealthPoints() + "HP");
                            if(attack.getLostEffect() != 0) {
                                System.out.println("You feel normal.");
                            }
                            if (attack.isBroken())
                                System.out.println("Your " + attack.getColor() +  attack.getOutputText() + Color.RESET  + " broke.");
                            break;
                        case ENEMY_DEAD:
                            System.out.println("You attack " + attack.getEnemyName() + " with " + attack.getColor() +  attack.getOutputText() + Color.RESET + " and deal " + Color.RED_BOLD +  attack.getPlayerDamage() + Color.RESET + " damage. Good job!");
                            System.out.println(attack.getEnemyName() + " falls over and dies.");
                            if(attack.getLostEffect() != 0) {
                                System.out.println("You feel normal.");
                            }
                            if (attack.isBroken())
                                System.out.println("Your " + attack.getColor() +  attack.getOutputText() + Color.RESET  + " broke.");
                            break;
                        case PLAYER_DEAD:
                            System.out.println("You attack " + attack.getEnemyName() + " with " + attack.getColor() +  attack.getOutputText() + Color.RESET  + " and deal " +Color.RED_BOLD +  attack.getPlayerDamage() + Color.RESET +  " damage. Good job!");
                            System.out.println(attack.getEnemyName() + " hit you for " + Color.RED_BOLD + attack.getEnemyDamage() + Color.RESET + " damage, and you die.");
                            System.exit(0);
                    }
                    break;
            }
        }
    }

    private void look() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Item(s)");
        columns.add("Foe(s)");
        Table table = new Table(adventure.getCurrentRoomName(),columns,true );
        ArrayList<Item> loot = adventure.getCurrentRoomLoot();
        ArrayList<Enemy> foes = adventure.getCurrentRoomEnemies();
        int lootSize = loot.size();
        int foeSize = foes.size();
        int count = Math.max(lootSize,foeSize);
        for (int i = 0; i < count; i++) {
            String item = "";
            String foe = "";
            String itemColor = "";
            if ( i < lootSize) {
                item = loot.get(i).getLongName();
                itemColor = loot.get(i).getRarity();
            }
            if ( i < foeSize) {
                foe = foes.get(i).getLongName();
            }
            table.addRow(new Row().addCell(item,itemColor).addCell(foe,Color.RED));

        }
        System.out.print(table.getTableString());

    }
    private void inventory() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Weapons");
        columns.add("Consumables");
        columns.add("Items");
        Table table = new Table("Inventory",columns,true );
        ArrayList<Item> weapons = adventure.getPlayerWeapons();
        ArrayList<Item> consumables = adventure.getPlayerConsumables();
        ArrayList<Item> items = adventure.getPlayerItems();
        int weaponsSize = weapons.size();
        int consumablesSize = consumables.size();
        int itemsSize = items.size();
        int count = Math.max(weaponsSize,
                Math.max(consumablesSize,itemsSize));
        for (int i = 0; i < count; i++) {
            String weapon = "", consumable = "", item = "";
            String weaponColor = "",itemColor = "",consumableColor = "";
            if ( i < weaponsSize) {
                weapon = weapons.get(i).getLongName();
                weaponColor = weapons.get(i).getRarity();
            }
            if ( i < consumablesSize) {
                consumable = consumables.get(i).getLongName();
                consumableColor = consumables.get(i).getLongName();
            }
            if (i < itemsSize) {
                item = items.get(i).getLongName();
                itemColor = items.get(i).getRarity();
            }
            table.addRow(new Row().addCell(weapon,weaponColor).addCell(consumable,consumableColor).addCell(item,itemColor));

        }
        System.out.println(table.getTableString());
    }

    private void rarityChart() {
        Table table = new Table("Item Rarity Chart",
                new ArrayList<>(List.of("Rarity")),true);
        for(ItemRarity rarity : ItemRarity.values()) {
            table.addRow(new Row().addCell(rarity.name(),rarity.color));
        }
        System.out.println(table.getTableString());
    }



    private void move(Direction direction) {
        if (adventure.movePlayer(direction)) {
            String cardinalDirection = direction.toString().toLowerCase();
            System.out.println("You went " + cardinalDirection + " to " + adventure.getCurrentRoomName());
            System.out.println(adventure.getCurrentRoomDescription());
        } else {
            System.out.println("You can't go that way.");
        }
    }

    public void help() {
        Table helpTable = new Table("Help      ",new ArrayList<>(List.of("Command","Description")),true);
        helpTable.addRow(new Row().addCell("n,s,e,w").addCell("Move in that direction"));
        helpTable.addRow(new Row().addCell("look").addCell("Search the room"));
        helpTable.addRow(new Row().addCell("inventory, inv").addCell("Show inventory"));
        helpTable.addRow(new Row().addCell("take [item]").addCell("Pick up [item]"));
        helpTable.addRow(new Row().addCell("drop [item]").addCell("Drops [item]"));
        helpTable.addRow(new Row().addCell("consume [consumable]").addCell("Gain consumable effect"));
        helpTable.addRow(new Row().addCell("hp").addCell("Show your Health Points (HP)"));
        helpTable.addRow(new Row().addCell("equip {weapon}").addCell("Equips {weapon}"));
        helpTable.addRow(new Row().addCell("attack [enemy]").addCell("Attacks [enemy]"));
        helpTable.addRow(new Row().addCell("rarity").addCell("List item rarity colors"));
        System.out.println(helpTable.getTableString());
    }
}
