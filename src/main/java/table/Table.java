package table;

import java.util.ArrayList;

public class Table {
    private final String LINE = "─";
    private final String SEPERATOR = "│";
    private final String CROSS = "┼";

    private final String TOP_T = "┬";
    private final String TOP_LEFT_CORNER = "┌";
    private final String TOP_RIGHT_CORNER = "┐";

    private final String BOT_T = "┴";
    private final String BOT_LEFT_CORNER = "└";
    private final String BOT_RIGHT_CORNER = "┘";

    private final String LEFT_EDGE_SEPARATOR = "├";
    private final String RIGHT_EDGE_SEPARATOR = "┤";

    private final String WHITESPACE = " ";


    private final String header;
    private boolean center;
    private String columnsHeader;
    private String rowTemplate;
    private final ArrayList<Row> rows;
    private final ArrayList<String> columns;
    private final ArrayList<Integer> columnSizes;

    private final int size;
    private int length;

    private StringBuilder sb;

    public Table(String header, ArrayList<String> columns) {
        this.header = header;
        this.columns = columns;
        size = columns.size();
        rows = new ArrayList<>();
        columnSizes = new ArrayList<>();
        setInitColumnSizes();
        center = false;
    }

    public Table(String header, ArrayList<String> columns,boolean center) {
        this.header = header;
        this.columns = columns;
        size = columns.size();
        rows = new ArrayList<>();
        columnSizes = new ArrayList<>();
        setInitColumnSizes();
        this.center = center;
    }

    private void calcLength() {
        int sum = 0;
        if (!columnSizes.isEmpty()) {
            for (int i : columnSizes) {
                sum += i + 2;
            }
        }
        length = sum + size + 1;
    }

    private void setInitColumnSizes() {
        for (String s : columns) {
            columnSizes.add(s.length()+2);
        }
    }

    private void calcColumnSizes() {
        for (Row row : rows) {
            int count = 0;
            ArrayList<Cell> cells = row.getCells();
            for (Cell cell : cells) {
                columnSizes.set(count, Math.max(columnSizes.get(count),
                        Math.max(cell.getSize()+2, columns.get(count).length()+2)));
                count++;
            }
        }
    }

    public boolean addRow(Row row) {
        if (row.getSize() == size) {
            rows.add(row);
            return true;
        } else {
            return false;
        }
    }

    private String centerText(String text,int space) {
        StringBuilder centerText = new StringBuilder();
        int length = text.length();
        int spaces = (space - length) / 2;
        int offset = 0;
        if (length % 2 == 0 || space % 2 == 0) {
            offset = length % 2 + space % 2;
        }

        centerText.append(WHITESPACE.repeat(spaces))
                .append(text).append(WHITESPACE.repeat(spaces+offset));
        return centerText.toString();
    }

    private String createTableLine(String start, String end) {
        return start +
                LINE.repeat(this.length - 2) +
                end;
    }

    private String createTableLine(String start, String end, String indice) {
        StringBuilder line = new StringBuilder();
        int count = 0;
        line.append(start);
        for (int i : columnSizes) {
            line.append(LINE.repeat(i + 2));
            if (count != size - 1) line.append(indice);
            count++;
        }
        line.append(end);
        return line.toString();
    }

    private String createHeaderString() {
        return createTableLine(TOP_LEFT_CORNER, TOP_RIGHT_CORNER) +
                "\n" +
                SEPERATOR +
                centerText(header,(this.length-2)) + // -2 because of separators taking up one space each.
                SEPERATOR +
                "\n" +
                createTableLine(LEFT_EDGE_SEPARATOR, RIGHT_EDGE_SEPARATOR, TOP_T)+ "\n";

    }

    private String createColumnsString() {
        StringBuilder columnsString = new StringBuilder();
        int count = 0;
        columnsString.append(SEPERATOR).append(" ");
        if(center) {
            for (String s : columns) {
                columnsString.append(centerText(s, columnSizes.get(count)));
                if (count != size - 1) {
                    columnsString.append(" ").append(SEPERATOR).append(" ");
                }
                count++;
            }
            columnsString.append(" ").append(SEPERATOR).append("\n");
            columnsString.append(createTableLine(LEFT_EDGE_SEPARATOR, RIGHT_EDGE_SEPARATOR, CROSS)).append("\n");
            return columnsString.toString();
        } else {
            for (String s : columns) {
                StringBuilder template = new StringBuilder();
                template.append("%-").append(columnSizes.get(count)).append("s");
                if (count != size - 1) {
                    template.append(" ").append(SEPERATOR).append(" ");
                }
                count++;
                String tempString = String.format(template.toString(), s);
                columnsString.append(tempString);
            }
            columnsString.append(" ").append(SEPERATOR).append("\n");
            columnsString.append(createTableLine(LEFT_EDGE_SEPARATOR, RIGHT_EDGE_SEPARATOR, CROSS)).append("\n");
            return columnsString.toString();
        }
    }

    private String createTableEndLine(){
        return createTableLine(BOT_LEFT_CORNER, BOT_RIGHT_CORNER, BOT_T);
    }

    private String createRowsString() {
        StringBuilder rowsString = new StringBuilder();
        for (Row row : rows) {
            int cellCount = 0;
            ArrayList<Cell> cells = row.getCells();
            for (Cell cell : cells) {
                String type = "s";
                String prefix = "%";
                StringBuilder template = new StringBuilder();
                template.append(SEPERATOR).append(" ");
                if (cell.isINT()) {
                    type = "d";
                    prefix = "%";
                }
                if (cell.isDOUBLE()) {
                    type = ".0f";
                    prefix = "%";
                }
                if (cell.isSTRING()) {
                    type = "s";
                    prefix = "%-";
                }
                template.append(prefix).append(columnSizes.get(cellCount)).append(type);
                if (cellCount != size - 1) {
                    template.append(" ");
                } else {
                    template.append(" ").append(SEPERATOR);
                }
                if (cell.isINT()) {
                    rowsString.append(String.format(template.toString(), cell.getIntValue()));
                } else if (cell.isDOUBLE()) {
                    rowsString.append(String.format(template.toString(), cell.getDoubleValue()));
                } else if (cell.isSTRING()) {
                    rowsString.append(String.format(template.toString(), cell.getStringValue()));
                } else {
                    //TODO Make more general. overload table.Cell constructor
                    //to facilitate a new table.Cell(true,"TrueString","FalseString");
                    String humanStatus = cell.getBool() ? "YES" : "NO";
                    rowsString.append(String.format(template.toString(), humanStatus));
                }
                cellCount++;
            }
            rowsString.append("\n");
        }
        return rowsString.toString();
    }

    @Override
    public String toString() {
        calcColumnSizes();
        calcLength();

        return createHeaderString() +
                createColumnsString() +
                createRowsString() +
                createTableEndLine();
    }

    public String getTableString() {
        calcColumnSizes();
        calcLength();
        return createHeaderString() +
                createColumnsString() +
                createRowsString() +
                createTableEndLine();
    }
    public void print() {
        calcColumnSizes();
        calcLength();
        System.out.print(createHeaderString() +
                createColumnsString() +
                createRowsString() +
                createTableEndLine());

    }

}
