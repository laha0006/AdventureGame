package org.example;

public class Player {
    private Room currentPosition;
    //inventory

    public Player() {
    }

    public void setCurrentPosition(Room position) {
        currentPosition = position;
    }

    public Room getPlayerPosition(){
        return currentPosition;
    }

}

