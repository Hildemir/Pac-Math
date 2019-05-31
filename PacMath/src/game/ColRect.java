package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ColRect { // FAZER ELE EXTENDER ELEMENT2D E AJEITAR OS BUGS GERADOS POR ISSO
    private int x;
    private int y;
    private int w;
    private int h;


    public ColRect() {}
    public  ColRect(int x, int y, int w, int h) {
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;

    }

   //gets
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    //sets


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHeight(int h) {
        this.h = h;
    }

    public void setWidth(int w) {
        this.w = w;
    }

    // melhorar porque ele so ta colidindo na parte de cima ou seja no X e Y
    boolean checkCollision(PacMan pacMan) {
        // x y
        return (pacMan.getPosX() > this.getX() &&
                pacMan.getPosY() > this.getY() &&
                pacMan.getPosX()  <= this.getX() + this.getWidth() &&
                pacMan.getPosY() <= this.getY() + this.getHeight() ) ||

                // x, y + h
                (pacMan.getPosX() > this.getX() &&
                pacMan.getPosY() + pacMan.h > this.getY() &&
                pacMan.getPosX()  <= this.getX() + this.getWidth() &&
                pacMan.getPosY() + pacMan.h <= this.getY() + this.getHeight() ) ||

                // x + w, y
                (pacMan.getPosX() + pacMan.w >= this.getX() &&
                pacMan.getPosY() > this.getY() &&
                pacMan.getPosX() + pacMan.w <= this.getX() + this.getWidth() &&
                pacMan.getPosY()  <= this.getY() + this.getHeight() ) ||


                // x + h, y + h
                (pacMan.getPosX() + pacMan.w >= this.getX() &&
                pacMan.getPosY() + pacMan.h > this.getY() &&
                pacMan.getPosX() + pacMan.w <= this.getX() + this.getWidth() &&
                pacMan.getPosY()  + pacMan.h <= this.getY() + this.getHeight() );
    }

    public void draw( GraphicsContext gc) {
//        gc.setStroke(Color.WHITE);
//        gc.strokeRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        gc.setFill(Color.TRANSPARENT);
        gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

    }
    public void drawStroke (GraphicsContext gc) {
        gc.stroke();
        gc.setStroke(Color.RED);
    }
}
