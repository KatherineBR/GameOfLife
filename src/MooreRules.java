/**
 *  MooreRules determines what the cell will look like in it's next generation. It checks if cells follow the rules to be born or to stay alive.
 */

public class MooreRules extends Rules{
    private static final int NUM_NEIGHBORS = 9;
    private boolean[] birthRules;
    private boolean[] survivalRules;

    /**
     * MooreRules constructor takes in two arrays with the birth and survival rules and creates two boolean arrays
     * @param birthNeighbors an array with the number of neighbors you need to be alive for the cell to be born - in the case of B3S23, it gets sent {3} from gameOfLifeApp
     * @param survivalNeighbors an array with the number of neighbors you need to be alive for the cell to survive - in the case of B3S23, it gets sent {2,3} from gameOfLifeApp
     */

    public MooreRules(int[] birthNeighbors, int[] survivalNeighbors){
        //birth neighbors: {3} from game of life app
        //survival neighbors: {2, 3} from game of life app

        super(); //??

        //making 2 arrays of booleans the length of num neighbors and all initialized to false
        birthRules = new boolean[NUM_NEIGHBORS]; //birth rules: {false, false, false, false, false, false, false, false, false}
        survivalRules = new boolean[NUM_NEIGHBORS]; //survival rules: {false, false, false, false, false, false, false, false, false}

        //setting rule spots to true
        // birth rules has one element and then its taking that one number and using it as an index
        for (int neighbors: birthNeighbors){ //enhanced for is iterating over an array of integers -- iterating over birthNeighbors (so just the one)
            birthRules[neighbors] = true; //making the boolean at neighbors (so at the ints inside the birthNeighbors array true)
            // birthrules: {false, false, false, true, false, false, false, false, false}
        }
        for (int neighbors: survivalNeighbors){ //neighbors takes on the value of the array integers and goes through each
            survivalRules[neighbors] = true;
            //survivalrules: {false, false, true, true, false, false, false, false, false}
        }
    }

    /**
     * checks if the number of live neighbors is the same as the number needed for it to be born by checking if
     * birthrules at index live neighbors is true or not and returning that.
     * @param liveNeighbors how many neighbors are alive
     * @return boolean for if there are the right amount of alive neighbors to be born
     */
    @Override
    public boolean shouldBeBorn(int liveNeighbors) {
        return birthRules[liveNeighbors];
    }

    /**
     * checks if the number of live neighbors is the same as the number needed to survive by checking if
     * survival rules at index live neighbors is true or not and returning that.
     * @param liveNeighbors how many neighbors are alive
     * @return boolean for if there are the right amount of alive neighbors to survive
     */
    @Override
    public boolean shouldSurvive(int liveNeighbors) {
        return survivalRules[liveNeighbors];
    }
}
