package messages;

import item.Consumable;
import item.Item;

public class ReturnConsumable {
    private Status status;
    private String outputText;
    private Item item;
    private int effect;

    public ReturnConsumable() {}

    public ReturnConsumable(Status status, String outputText, Item item) {
        this.status = status;
        this.outputText = outputText;
        this.item = item;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getItemHealthGain() {
        if(item instanceof Consumable consumable) {
            return consumable.getHealthGain();
        }
        return 0;
    }
    public void setEffect(int effectText) {
        this.effect = effectText;
    }

    public int getEffect() {
        return effect;
    }

}
