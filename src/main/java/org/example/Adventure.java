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
}
