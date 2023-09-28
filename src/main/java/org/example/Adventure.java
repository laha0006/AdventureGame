package org.example;
public class Adventure {
    private final Map map;
    private final Player player;

    public Adventure() {
        map = new Map();
        player = new Player();
    }

    public void start() {
        map.buildWorld();
        player.setCurrentPosition(map.getStartingRoom());
    }

    public Room getStartingRoom() {
        return map.getStartingRoom();
    }

    public Room getPlayerPosition() {
        return player.getPlayerPosition();
    }

    public boolean movePlayer(String direction) {
        switch (direction) {
            case "north":
                if (player.getPlayerPosition().getNorth() != null) {
                    player.setCurrentPosition(player.getPlayerPosition().getNorth());
                    return true;
                } else return false;

            case "south":
                if (player.getPlayerPosition().getSouth() != null) {
                    player.setCurrentPosition(player.getPlayerPosition().getSouth());
                    return true;
                } else return false;

            case "east":
                if (player.getPlayerPosition().getEast() != null) {
                    player.setCurrentPosition(player.getPlayerPosition().getEast());
                    return true;
                } else return false;

            case "west":
                if (player.getPlayerPosition().getWest() != null) {
                    player.setCurrentPosition(player.getPlayerPosition().getWest());
                    return true;
                } else return false;
        }
        return false;
    }
}

