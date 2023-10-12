package messages;

public class ReturnAttack {
        private Status status;
        private String outputText;
        private int playerDamage;
        private boolean broken;
        private int lostEffect;
        private int enemyDamage;
        private int enemyHealthPoints;
        private String color;



    private String enemyName;

        //Constructor
        public ReturnAttack() {}

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

        public int getPlayerDamage() {
            return playerDamage;
        }

        public void setPlayerDamage(int playerDamage) {
            this.playerDamage = playerDamage;
        }

        public boolean isBroken() {
            return broken;
        }

        public void setBroken(boolean broken) {
            this.broken = broken;
        }
    public int getEnemyDamage() {
        return enemyDamage;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public int getEnemyHealthPoints() {
            return enemyHealthPoints;
    }

    public void setEnemyDamage(int enemyDamage) {
        this.enemyDamage = enemyDamage;
    }

    public int getLostEffect() {
        return lostEffect;
    }

    public void setLostEffect(int lostEffect) {
        this.lostEffect = lostEffect;
    }
    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public void setEnemyHealthPoints(int hp) {
            enemyHealthPoints = hp;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}


