public class ReturnConsumable {
    private Status status;
    private String outputText;
    private Item item;

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

}
