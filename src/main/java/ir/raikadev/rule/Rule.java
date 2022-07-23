package ir.raikadev.rule;

import ir.raikadev.cell.Cell;

public class Rule {

  public void detectCellStatus(Cell cell, Cell[][] world) {
    int sumOfLiveNeighbors = computeSumOfLiveNeighbors(cell, world);
    setCellStatus(cell, world, sumOfLiveNeighbors);
  }

  private int computeSumOfLiveNeighbors(Cell cell, Cell[][] world) {

    int sumOfLiveOfNeighbors = 0;

    int[][] neighbors = cell.getNeighbors();

    final int worldHeight = world.length;
    final int worldWith = world[0].length;

    for (int[] neighbor : neighbors) {

      int neighborRowPosition = neighbor[0];
      int neighborColumnPosition = neighbor[1];

      if (!(isVisibleNeighborPosition(
          neighborRowPosition, neighborColumnPosition, worldWith, worldHeight))) {
        neighborRowPosition = fixPosition(neighborRowPosition, worldHeight);
        neighborColumnPosition = fixPosition(neighborColumnPosition, worldWith);
      }

      Cell neighborCell = world[neighborRowPosition][neighborColumnPosition];

      if (neighborCell.getCellStatus() == Cell.CellStatus.ALIVE) sumOfLiveOfNeighbors++;
    }

    return sumOfLiveOfNeighbors;
  }

  private int fixPosition(int cellPosition, int worldBound) {
    if (cellPosition >= worldBound) return cellPosition - worldBound;
    else if (cellPosition < 0) return cellPosition + worldBound;
    else return cellPosition;
  }

  private boolean isVisibleNeighborPosition(
      int neighborRowPosition, int neighborColumnPosition, int worldWith, int worldHeight) {
    return (neighborRowPosition < worldHeight && neighborRowPosition >= 0)
        && (neighborColumnPosition < worldWith && neighborColumnPosition >= 0);
  }

  private void setCellStatus(Cell cell, Cell[][] world, int sumOfLiveNeighbors) {
    Cell.CellStatus cellStatus =
        world[cell.getRowPosition()][cell.getColumnPosition()].getCellStatus();

    if (cellStatus == Cell.CellStatus.ALIVE && (sumOfLiveNeighbors > 3 || sumOfLiveNeighbors < 2))
      cell.setCellStatus(Cell.CellStatus.DEAD);
    else if (cellStatus == Cell.CellStatus.DEAD && sumOfLiveNeighbors == 3)
      cell.setCellStatus(Cell.CellStatus.ALIVE);
    else cell.setCellStatus(cellStatus);
  }
}
