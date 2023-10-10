package ui.table;

import java.util.ArrayList;

public class Row {
    private ArrayList<Cell> cells;

    public Row() {
        cells = new ArrayList<>();
    }

    public int getSize() {
        return cells.size();
    }
    public Row addCell(int i){
        cells.add(new Cell(i));
        return this;
    }
    public Row addCell(String s){
        cells.add(new Cell(s));
        return this;
    }

    public Row addCell(String s,String color){
        cells.add(new Cell(s,color));
        return this;
    }

    public Row addCell(double d){
        cells.add(new Cell(d));
        return this;
    }
    public Row addCell(boolean b){
        cells.add(new Cell(b));
        return this;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }
}
