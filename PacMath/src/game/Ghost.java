package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Random;

public class Ghost extends Element2D {
    private static final int DIR_LEFT = 0;
    private static final int DIR_RIGHT = 1;
    private static final int DIR_UP = 2;
    private static final int DIR_DOWN = 3;
    private int dir;
    private double posX;
    private double posY;
    private double w;
    private double h;

    private double step = 0.5;

    public Ghost() {}
    public Ghost(double x, double y) {
        this.setPosX(x);
        this.setPosY(y);
        this.setWidth(30);
        this.setHeight(30);
        dir = new Random().nextInt(4);
    }

    public void draw( GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(posX, posY, w, h);
    }

    public void move() {
        switch (dir) {
            case DIR_LEFT:
                posX -= step;
                break;
            case DIR_RIGHT:
                posX += step;
                break;
            case DIR_UP:
                posY -= step;
                break;
            case DIR_DOWN:
                posY += step;
                break;
        }
    }

    public void changeDirection() {
        int atual = dir;
        int novo = -1;
//        int []newDir = new int[]{DIR_DOWN, DIR_RIGHT};
//        Arrays.stream(newDir).filter(e -> e != atual);
        while (novo != atual) {
            novo = new Random().nextInt(4);
        }
        dir = novo;
    }
}
