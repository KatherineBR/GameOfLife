/**
 * Rules defines the method applyrules but doesn't implement it. It has the logic structure, but it is used by being overridden.
 * Rules is an abstract class that contains the structure for checking if a cell should die or be revived.
 */

public abstract class Rules {
    //instance variables here
    public abstract boolean shouldBeBorn(int liveNeighbors);
    public abstract boolean shouldSurvive(int liveNeighbors);

    /**
     *  ApplyRules method is of type celllState and changes teh state of the cell depending on how many alive neighbors it has.
     *  it calls on MooreRules to check if cells follow the rules for a new state and then checks the current
     *  cell state and uses both those pieces of info to return new or same cellState.
     * @param cellState the current cellState
     * @param liveNeighbors how many neighbors are alive
     * @return the cellState a cell should be in right before evolution (will_something state or the same)
     */
    public CellState applyRules(CellState cellState, int liveNeighbors){
        if (cellState == CellState.DEAD && shouldBeBorn(liveNeighbors)){
            return CellState.WILL_REVIVE;
        } else if (cellState == CellState.ALIVE && !shouldSurvive(liveNeighbors)){
            return CellState.WILL_DIE;
        } else{
            return cellState;
        }
    }

}
