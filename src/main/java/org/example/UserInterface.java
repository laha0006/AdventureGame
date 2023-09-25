package org.example;

import java.util.Scanner;

public class UserInterface {

    String direction;
    String showLoot;
    String currentRoom;


    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] splitInput = input.split(" ");

        if (splitInput.length == 1) {
            switch (splitInput[0]) {
                case "n", "north":
                    System.out.println("You went north");
                    break;
                case "s", "south":
                    System.out.println("You went south");
                    break;
                case "e", "east":
                    System.out.println("You went east");
                    break;
                case "w", "west":
                    System.out.println("You went west");
                    break;
                case "look":
                    System.out.println("You looked around and found nothing");
                    break;
            }
        }
        if (splitInput.length == 2) {
            switch(splitInput[0]) {
                case "go":
                    go(splitInput[1]);
                    break;
                case "look":
                    System.out.println("You looked " + splitInput[1]+ " and found nothing");
                    break;

            }
        }
    }

    private void go(String direction) {
        switch (direction) {
            case "n", "north":
                System.out.println("We went north");
                break;
            case "s", "south":
                System.out.println("We went south");
                break;
            case "e", "east":
                System.out.println("We went east");
                break;
            case "w", "west":
                System.out.println("We went west");
                break;

        }
    }


    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
