package item;

import messages.ItemRarity;

public class Item {
    private final String longName;
    private final String shortName;
    private final ItemRarity rarity;

    public Item(String longName, String shortName,ItemRarity rarity) {
        this.longName = longName;
        this.shortName = shortName;
        this.rarity = rarity;
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getRarity() {
        return rarity.color;
    }
}
