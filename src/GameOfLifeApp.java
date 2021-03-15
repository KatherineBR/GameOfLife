import processing.core.PApplet;

/**
 * GameOfLifeApp is the main class for this program. It has the structure for everything and just calls the other
 * classes for the details. GameOfLifeApp sets up the cell array, runs draw to display, deals with mouse clicks and key
 * presses, and iterates over every non border cell to call evolve and apply rules (from Cell class). It tells the
 * program where to start and also has a getter so other classes can access the GameOfLife app.
 */
public class GameOfLifeApp extends PApplet{
    private static GameOfLifeApp app; //object is the whole class -- reference to itself so other classes can use
    private Cell[][] cells; //has-a 2d array fo cells
    public static final int CELL_SIZE = 10; //represents width and height
    private boolean evolve;
    //private boolean gameOfLifeStarted = false;
    private boolean daynight = false; //if day&night is being played
    private boolean conways = false; //if conways is being played
    private boolean isMenuGoing = false; //if menu showing

    /**
     * main is where every java program starts and tells it where to go from there. This says to start at gamOfLifeApp.
     * @param args is arguments in an array of type string that we don't use (command line parameters)
     */
    public static void main(String[] args){
        app = new GameOfLifeApp(); //instantiating new PApplet object
        app.runSketch(); //saying go
    }

    /**
     * GameOfLife constructor, declares evolve false.
     */
    public GameOfLifeApp(){
        evolve = false;
    } //constructor!

    /**
     * calls super class settings and establishes size of canvas.
     */
    @Override
    public void settings() { //setting the size
        super.settings();
        size (1000,500);
    }

    /**
     * calls super class setup, starts with menu
     */
    @Override
    public void setup() {
        super.setup();
        menuSetup();
    }

    /**
     * declares conways and daynight false, says menu is going, sets background and displays instructions
     */
    public void menuSetup(){
        conways = false;
        daynight = false;
        isMenuGoing = true;
        background(245, 229, 255);
        app.stroke(255);
        app.fill(0);
        textAlign(CENTER, CENTER);
        app.text("Welcome! \n \n Instructions: \n you can MOUSE CLICK the boxes to make your pattern (although you won't be able to if evolving). \n click the SPACEBAR to evolve. \n click ENTER to return to the menu. \n But, first, press the 'c' to setup your board with Conway's Game of Life (B2S23) or press 'd' for Day & Night (B3678/S34678). \n \n Have fun!", width/2, height/2);
    }

    /**
     * creates 2d array called cells, fills array with new cell objects for every grid location.
     */
    public void GameOfLifeSetup(){
        int history = 0;
        frameRate(5); //how frequently it calls draw?
        Rules rules;
        if (daynight) {
            rules = new MooreRules(new int[]{3,6,7,8}, new int[]{3,4,6,7,8}); //B3678S34678
        } else if (conways){
            rules = new MooreRules(new int[]{3}, new int[]{2, 3}); //B3S23
        } else { //this wont happen but resort to conways if so (for not getting an error)
            rules = new MooreRules(new int[]{3}, new int[]{2, 3}); //B3S23
        }
        cells = new Cell[height/CELL_SIZE][width/CELL_SIZE];
        for (int r = 0; r < cells.length; r ++) { //initialize all cells
            for (int c = 0; c < cells[0].length; c ++) {
                if (c == 0 || r == 0 || c == cells[0].length - 1 || r == cells.length - 1) { //border
                    Cell cell = new Cell(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, r, c, CellState.DEAD, rules, history);
                    cells[r][c] = cell;
                } else { //inside
                    Cell cell = new Cell(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, r, c, CellState.DEAD, rules, history);
                    cells[r][c] = cell;
                }
            }
        }
        //in nested loop, instantiate cell obj and insert into cells instance variable
    }

    /**
     * Continuously running. draw displays the cell objects when not showing menu and when boolean evolve is true, it calls evolve and apply rules.
     */
    @Override
    public void draw() { //controlling evolution, everytime it's called, it will go to next gen
        if (evolve){ //if the boolean is true, apply rules and evolve
            applyRules();
            evolve();
        }
        if (!isMenuGoing) {
            display(); //display cell obj
        }
    }

    /**
     * iterates through every object in 2d array cells and calls Cell's display method for them each
     */
    public void display(){
        for (int r = 0; r < cells.length; r++) {//iterate over 2d array and call each cells display
            for (int c = 0; c < cells[0].length; c++) {
                cells[r][c].display();
            }
        }
    }

    /**
     * calls super class method, establishes what row and column the cell clicked was, calls handleMouseClicked for
     * the cell in the clicked row and column.
     */
    @Override
    public void mouseClicked() { //let us make alive or dead indvl cells with a click
        super.mouseClicked(); //PApplet's mouseClicked
        int col = mouseX/CELL_SIZE;
        int row = mouseY/CELL_SIZE;
        if (!isMenuGoing) {
            cells[row][col].handleMouseClicked(); //having clicked cell obj handlemouseclicked
        }
    }

    /**
     * calls super class (PApplet) method, changes the state of boolean evolve to turn on or off cell evolution
     */
    @Override
    public void keyPressed() {
        super.keyPressed();
        if (key == 'c' && isMenuGoing && !daynight && !conways) { //start conways
            conways = true;
            isMenuGoing = false;
            GameOfLifeSetup();
        }
         if (key == 'd' && isMenuGoing && !daynight && !conways){ //start day & night
            daynight = true;
            isMenuGoing = false;
            GameOfLifeSetup();
        }
        if (key == ' ' && !isMenuGoing){ //evolve with space -- pausing and restarting cell evolution
            evolve = !evolve;
        }
        if (key == ENTER && !isMenuGoing){ //return to menu with enter
            menuSetup();
            isMenuGoing = true;
        }
    }

    /**
     * iterates over all objects in 2d array and calls Cell's apply rules for them each
     */
    private void applyRules(){
        for (int r = 1; r < cells.length - 1; r++){
            for (int c = 1; c < cells[0].length - 1; c++){
                cells[r][c].applyRules(cells);
            }
        }
    }

    /**
     * iterates over every object in 2d array cells and calls Cell's evolve for them each
     */
    private void evolve(){ //calling Cell's evolve for every cell
        for (int r = 1; r < cells.length - 1; r++) {
            for (int c = 1; c < cells[0].length - 1; c++) {
                cells[r][c].evolve();
            }
        }
    }


    /**
     * getter for gameOfLifeApp, the whole class
     * @return app, an object of PApplet established to be a reference to itself
     */
    public static GameOfLifeApp getApp(){ //how to access
        return app;
    } //getter for this app
}
