package messages;

import ui.Color;

public enum ItemRarity {
    COMMON(Color.WHITE),
    UNCOMMON(Color.GREEN),
    RARE(Color.BLUE_RARE),
    EPIC(Color.PURPLE_EPIC),
    LEGENDARY(Color.ORANGE);

    public final String color;

    private ItemRarity(String color) {
        this.color = color;
    }
}
