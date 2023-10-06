package data;

public class ReturnAttack {
        private Status status;
        private String outputText;
        private int damage;
        private boolean broken;
        private int lostEffect;

        public ReturnAttack() {}

        public ReturnAttack(Status status, String outputText, int damage) {
            this.status = status;
            this.outputText = outputText;
            this.damage = damage;
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

        public int getDamage() {
            return damage;
        }

        public void setDamage(int damage) {
            this.damage = damage;
        }

        public boolean isBroken() {
            return broken;
        }

        public void setBroken(boolean broken) {
            this.broken = broken;
        }

    public int getLostEffect() {
        return lostEffect;
    }

    public void setLostEffect(int lostEffect) {
        this.lostEffect = lostEffect;
    }
}


