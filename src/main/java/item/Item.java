package item;

public class Item {
    private final String longName;
    private final String shortName;

    public Item(String longName, String shortName) {
        this.longName = longName;
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }



}
