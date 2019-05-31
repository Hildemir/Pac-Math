package game;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements Drawable{
    private GraphicsContext gc;
    private Status status;
    private Image background;
    private static double w = 1100, h = 1100;
    private PacMan pacMan;
    public MazeData mazeData;
   // private Rectangle [] rectangles;
    private ColRect [] rectangles;

    private final static int PACMAN_RADIUS = 20;
    private final static int WALL_RADIUS = 20;

    public Ghost[] ghost;


    public Game(GraphicsContext gc, Status status, Group root) {
        this.gc = gc;
        this.status = status;
        this.pacMan = new PacMan(800, 500, gc);
        images();
        this.mazeData = new MazeData(gc);
        this.rectangles = mazeData.generateMazeData();
        ghost = generateGhosts(10);
//        this.rectangles = mazeData.getMazeData();
//        root.getChildren().addAll(rectangles);

    }

    public void images () {
        background = new Image("/resources/mazeEmpty.png");
    }


    @Override
    public void drawing (MouseEvent mouse, KeyEvent key, Group root) {
        gc.drawImage(background, 0,0,w,h);
        pacMan.drawing(mouse, key, root);

        for(ColRect r : this.rectangles) {
            r.draw(this.gc);
        }
    }

    public PacMan getPacMan (){
        return this.pacMan;
    }

    public Ghost[] generateGhosts(int number) {
        Ghost[] ghosts = new Ghost[number];
        for (int i = 0; i < number; i++) {
            ghosts[i] = new Ghost(new Random().nextInt(900), new Random().nextInt(900));
        }

        return ghosts;
    }

//    public boolean collisionDetection( KeyEvent key) {
//
//        Bounds pacmanBounds = this.pacMan.getPac().getBoundsInParent();
//        //System.out.println(pacmanBounds);
//        for (int i = 0; i < rectangles.length; i++) {
//            Bounds mazeWall = rectangles[i].getBoundsInParent();
//            //System.out.println(mazeWall);
//            if (pacmanBounds.intersects(mazeWall)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean collisionDetection2(Rectangle r) {
        return pacMan.getX0() > r.getX() &&
                pacMan.getY0() > r.getY() &&
                pacMan.getX0() + pacMan.getX1() < r.getX() + r.getWidth() &&
                pacMan.getY0() + pacMan.getY1() < r.getY() + r.getHeight();
    }

    public ColRect[] getRectangles() {
        return rectangles;
    }

    //    public void checkCollision (Rectangle [] rectangles) {
//        System.out.println("1");
//        //for (int i = 0; i < rectangles.length; i++) {
//            System.out.println(rectangles[0].getTranslateX());
//            System.out.println(pacMan.getPosX());
//            if (calculateDistance(pacMan.getPosX(), rectangles[0].getTranslateX(), pacMan.getPosY(), rectangles[0].getTranslateY()) == 0) {
//                System.out.println("3");
//                System.out.println("CHOCOU PORRA!");
//            }
//
//    }
//
//    public double calculateDistance (double x1, double x2, double y1, double y2) {
//        return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
//    }

}
