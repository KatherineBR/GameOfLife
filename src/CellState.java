import java.util.Random;

/**
 * CellState is an enum containing enumerator states for the cells. Two states  have impacts on what they look like, and
 * the others are for the states in between evolves. Every cell is always in 1 of these 4 states.
 */

public enum CellState {
    ALIVE,
    DEAD,
    WILL_DIE,
    WILL_REVIVE;

    private static final CellState[] STATES = {ALIVE, DEAD}; //array of possible starting states
    private static final Random RANDOM = new Random(); //random object

    /**
     * WE DONT USE THIS
     * using the Random method for int 1 or 2 and using that as index to find a random state
     * @return a random state (either alive or dead)
     */
    public static CellState randomState() {
        return STATES[RANDOM.nextInt(2)];
    }
}
