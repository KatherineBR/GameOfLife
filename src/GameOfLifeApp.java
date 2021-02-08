import processing.core.PApplet;
import processing.core.PImage;

public class GameOfLifeApp extends PApplet{
    private static GameOfLifeApp app;
    private Cell[][] cells;

    public static void main(String[] args){
        PApplet.main ("GameOfLifeApp");
    }

    public static GameOfLifeApp getApp(){ //how to access
        return app;
    }

    public GameOfLifeApp(){
        app = this;
    }

    public void settings(){
        super.settings();
        size (1000,500);
    }

    public void setup(){
        super.setup();
        //instantiate cell obj
    }

    public void draw(){
        super.draw();
        //display cell obj
    }

    public void mouseClicked(){
        super.mouseClicked();
        //having cell obj handlemouseclicked
    }

    public void keyPressed(){
        super.keyPressed();
        //pausing and restarting cell evolution
    }


    //papplet extension
}
