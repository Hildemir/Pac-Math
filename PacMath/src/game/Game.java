package game;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Game implements Drawable{
    private GraphicsContext gc;
    private Status status;
    private Image background;
    private static double w = 1100, h = 1100;
    private PacMan pacMan;
    private MazeData mazeData;



    public Game(GraphicsContext gc, Status status) {
        this.gc = gc;
        this.status = status;
        this.pacMan = new PacMan(500, 60, gc);
        images();
        //this.mazeData = new MazeData();


    }

    public void images () {
        background = new Image("/resources/mazeEmpty.png");
    }
    @Override
    public void drawing (MouseEvent mouse, KeyEvent key, Group root) {
        gc.drawImage(background, 0,0,w,h);
        pacMan.drawing(mouse, key, root);
//        Rectangle r = new Rectangle();
//        r.setX(500);
//        r.setY(500);
//        r.setHeight(100/2);
//        r.setWidth(300/2);
//        r.setFill(Color.BLUE);
//        root.getChildren().add(r);
        mazeData = new MazeData();
        for (int i = 0; i < mazeData.getMazeData().length; i++) {
            root.getChildren().add(mazeData.getMazeData()[i]);
        }



        //gc.getCanvas().getGraphicsContext2D().fillPolygon(new double[]{100, 40, 10, 40},new double[]{210, 210, 240, 240}, 4);


    }
}
