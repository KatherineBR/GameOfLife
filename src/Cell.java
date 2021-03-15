import java.util.ArrayList;

/**
 * This class does everything specific to the cell your discussing, meaning it gets used for each cell.
 * when a grid location is clicked, the state becomes alive, meaning it becomes black. Cell class also is getting the
 * cell information to send to rules' apply rules.
 */


public class Cell {
    private final int x;
    private final int y;
    private final int size;
    private CellState cellState;
    private final int row;
    private final int column;
    private Rules rules;
    private int history;

    /**
     * the cell constructor initializes all params to variables.
     * @param _x coordinate x
     * @param _y coordinate y
     * @param _size the cell size
     * @param _row row in grid for this specific cell
     * @param _column column in grid for this specific cell
     * @param _cellstate what state it's in (alive, dead, etc)
     * @param _rules an object of rules that we use to access Rule methods
     */

    public Cell (int _x, int _y, int _size, int _row, int _column, CellState _cellstate, Rules _rules, int _history){
        x = _x;
        y = _y;
        size = _size;
        row = _row;
        column = _column;
        cellState = _cellstate;
        rules = _rules;
        history = _history;
    }

    /**
     * display continuously runs, making all the grid cells (rectangles of size CELL_SIZE on both dimensions). It also
     * decides the fill and stroke color when alive versus when dead.
     */
    public void display(){
        GameOfLifeApp app = GameOfLifeApp.getApp();
        if (this.cellState == CellState.ALIVE){
            app.fill(20 + 10*history, 0, 20 + 10*history);
            app.stroke(255);
        } else if (this.cellState == CellState.DEAD){
            app.fill(255);
            app.stroke(0);
        }
        app.rect(x, y, size, size); //we use app because rect is a method of PApplet so we need an object of PApplet to access
    }

    /**
     * handleMouseCLicked is called when the mouse is clicked, switching the cellState from alive to dead or vice versa.
     */
    public void handleMouseClicked(){
        if (cellState == CellState.ALIVE) {
            cellState = CellState.DEAD;
        } else if (cellState == CellState.DEAD){
            cellState = CellState.ALIVE;
        }
        display();
    }

    /**
     * applyRules takes the information from countLiveNeighbors and param cells[] and sends it to rules' applyRules.
     * @param cells an array of cells, given the cells array created in GameOfLifeApp
     */
    public void applyRules(Cell[][] cells){
        int live = countLiveNeighbors(cells); //how is it supposed to know what cell you want the count for?
        cellState = rules.applyRules(cells[row][column].cellState, live);
    } //check if should be alive or dead

    /**
     * evolve lets the cells with cellStates will_-- become the new state!
     */
    public void evolve(){ //put it to next state
        if (cellState == CellState.WILL_REVIVE){
             cellState = CellState.ALIVE;
        } else if (cellState == CellState.WILL_DIE){
            cellState = CellState.DEAD;
        } else if (cellState == CellState.ALIVE){
            history++;
        }
    }

    /**
     * countLiveNeighbors counts how many of the cells 8 surrounding cells are alive.
     * @param cells an array of cells, given through applyRules, the array created in GameOfLifeApp
     * @return counter, the number of alive neighbors
     */
    private int countLiveNeighbors(Cell[][] cells){
        int counter = 0;
        //if (column != 0 || row != 0 || column != cells[row].length - 1|| row != cells.length - 1) {
          for (int z = -1; z < 2; z++) {
              for (int y = -1; y < 2; y++) {
                  if (cells[row + z][column + y].cellState == CellState.ALIVE || cells[row + z][column + y].cellState == CellState.WILL_DIE) {
                      counter++;
                   }
              }
           }

            if (cellState == CellState.ALIVE){
                counter--;
            }
        //}
        return counter;
    }

}
