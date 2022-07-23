package ir.raikadev.cell;

public class Cell {
    private final int rowPosition;
    private final int columnPosition;

    private CellStatus cellStatus;

    public Cell( int rowPosition, int columnPosition, CellStatus cellStatus ) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.cellStatus = cellStatus;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus( CellStatus cellStatus ) {
        this.cellStatus = cellStatus;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public int[][] getNeighbors() {
        return new int[][]{
                {rowPosition - 1, columnPosition}
                , {rowPosition + 1, columnPosition}
                , {rowPosition, columnPosition + 1}
                , {rowPosition, columnPosition - 1}
                , {rowPosition - 1, columnPosition - 1}
                , {rowPosition + 1, columnPosition + 1}
                , {rowPosition - 1, columnPosition + 1}
                , {rowPosition + 1, columnPosition - 1}
        };
    }

    @Override
    public String toString() {
        return switch ( cellStatus ){
            case DEAD -> " ";
            case ALIVE -> "\u25A0";
        };
    }

    public enum CellStatus {
        DEAD, ALIVE
    }
}
