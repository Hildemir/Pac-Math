package game;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Element2D implements Drawable{
    private double posX;
    private double posY;
    private double x0,x1,y0,y1;
    private double h , w;
    private int num;
    private GraphicsContext gc;
    private TextAlignment alignment = TextAlignment.CENTER;
    private Text text;
    private Color colorNumber, colorGhost;
    private int buffer;


    public Element2D(int num , GraphicsContext gc ,double posX , double posY) {
        this.num = num;
        this.gc = gc;
        this.posX = posX;
        this.posY = posY;
        this.text = new Text(posX,posY,format(num));
        this.h = text.getBoundsInParent().getHeight()*2;
        this.w = text.getBoundsInParent().getWidth()*2;
        this.colorNumber = Color.DEEPPINK;
        this.colorGhost = Color.GREEN;

    }

    public String format (int num) {
        return String.valueOf(num);
    }

    @Override
    public void drawing(MouseEvent mouse, KeyEvent key, Group group) {
            gc.setTextAlign(alignment);
            gc.setFill(colorNumber);
        gc.fillText(String.valueOf(num),posX,posY);
//            gc.setFill(Color.YELLOW);
//            gc.fillRect(posX,posY,w,h);

    }

    public void setBuffer(int buffer) {
        this.buffer = buffer;
    }
    public int getBuffer() {
        return this.buffer;
    }

    public int getNum() {
        return num;
    }

    public Color getColorNumber() {
        return colorNumber;
    }

    public void setColor(Color color) {
        this.colorNumber = color;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getWidth() {
        return w;
    }

    public void setWidth(double width) {
        this.w = width;
    }

    public double getHeight() {
        return h;
    }

    public void setHeight(double height) {
        this.h = height;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void SetIsVisible (boolean value) {
//        if( this instanceof Ghost){
//            if(value == true){
//                colorGhost = Color.GREEN;
//            } else{
//                colorGhost = Color.TRANSPARENT;
//            }
//        } else{
            if(value == false) {
                this.colorNumber = Color.BLACK;
            } else {
                this.colorNumber = Color.DEEPPINK;
            }
        //}
    }

    boolean checkCollision(PacMan element) {
        // x y
        return (element.getPosX() > this.getPosX() &&
                element.getPosY() > this.getPosY() &&
                element.getPosX()  <= this.getPosX() + this.getWidth() &&
                element.getPosY() <= this.getPosY() + this.getHeight() ) ||

                // x, y + h
                (element.getPosX() > this.getPosX()&&
                        element.getPosY() + element.h > this.getPosY() &&
                        element.getPosX()  <= this.getPosX() + this.getWidth() &&
                        element.getPosY() + element.h <= this.getPosY() + this.getHeight() ) ||

                // x + w, y
                (element.getPosX() + element.w >= this.getPosX() &&
                        element.getPosY() > this.getPosY() &&
                        element.getPosX() + element.w <= this.getPosX() + this.getWidth() &&
                        element.getPosY()  <= this.getPosY() + this.getHeight() ) ||


                // x + h, y + h
                (element.getPosX() + element.w >= this.getPosX() &&
                        element.getPosY() + element.h > this.getPosY() &&
                        element.getPosX() + element.w <= this.getPosX() + this.getWidth() &&
                        element.getPosY()  + element.h <= this.getPosY() + this.getHeight() ) ||


                //x + (w/2), y            ponto medio superior
                (element.getPosX() + (element.w/2) > this.getPosX() &&
                        element.getPosY() > this.getPosY() &&
                        element.getPosX() + (element.w/2)  <= this.getPosX() + this.getWidth() &&
                        element.getPosY() <= this.getPosY() + this.getHeight()) ||


                // x, y + (h/2)            ponto medio esquerdo
                (element.getPosX() > this.getPosX() &&
                        element.getPosY() + (element.h/2)> this.getPosY() &&
                        element.getPosX()  <= this.getPosX() + this.getWidth() &&
                        element.getPosY() + (element.h/2) <= this.getPosY() + this.getHeight()) ||


                // x + w, y +(h/2)         ponto medio direito
                (element.getPosX() + element.w <= this.getPosX() + this.getWidth() &&
                        element.getPosY() + (element.h/2) > this.getPosY() &&
                        element.getPosX() + element.w >= this.getPosX() &&
                        element.getPosY() + (element.h/2) <= this.getPosY() + this.getHeight() ) ||


                // x + (w/2), y + (h/2)              ponto medio inferior
                (element.getPosX() + (element.w/2) >= this.getPosX() &&
                        element.getPosY() + element.h > this.getPosY() &&
                        element.getPosX() + (element.w/2)<= this.getPosX() + this.getWidth() &&
                        element.getPosY()  + element.h <= this.getPosY() + this.getHeight() );
    }
}
