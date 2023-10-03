public class ReturnConsumable {
    private ReturnStatus status;
    private String outputText;
    private Item item;

    public ReturnConsumable() {}

    public ReturnConsumable(ReturnStatus status, String outputText, Item item) {
        this.status = status;
        this.outputText = outputText;
        this.item = item;
    }

    public ReturnStatus getStatus() {
        return status;
    }

    public void setStatus(ReturnStatus status) {
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
