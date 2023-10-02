package table;

public class Cell {
    private int intValue;
    private String stringValue;
    private double doubleValue;
    private boolean bool;
    private final boolean INT;
    private final boolean STRING;
    private final boolean DOUBLE;
    private final int size;

    public Cell(int intValue) {
        this.intValue = intValue;
        INT = true;
        STRING = false;
        DOUBLE = false;
        size = (int) (Math.log10(intValue) + 1);
    }

    public Cell(String stringValue) {
        this.stringValue = stringValue;
        INT = false;
        STRING = true;
        DOUBLE = false;
        size = stringValue.length();
    }

    public Cell(boolean bool) {
        this.bool = bool;
        INT = false;
        STRING = false;
        DOUBLE = false;
        size = 4;
    }

    public Cell(double doubleValue) {
        this.doubleValue = doubleValue;
        INT = false;
        STRING = false;
        DOUBLE = true;
        size = (int) (Math.log10(doubleValue) + 1);

    }
    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public int getSize(){
        return size;
    }
    public boolean getBool() {
        return bool;
    }

    public boolean isINT() {
        return INT;
    }

    public boolean isSTRING() {
        return STRING;
    }

    public boolean isDOUBLE() {
        return DOUBLE;
    }
}
