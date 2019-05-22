package game;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class MenuItem extends Element{
    private String item;
    private Color color;
    private TextAlignment alignment = TextAlignment.CENTER;
    private Font fontName;
    private Text text;
    private double textSize;
    private Image buttonImage;

    // CONSTRUTOR
    public MenuItem(String item, Color color, GraphicsContext gc,
                    String fontName, double posX, double posY, double textSize) {
        super(posX,posY,gc);

        this.fontName = Font.loadFont(Main.class.getClassLoader().getResourceAsStream(fontName),textSize);
        this.text = new Text(item);
        text.setFont(this.fontName);
        // posicoes x0 x1 y0 y1 das palavras do menu para poder identificar a posicao do mouse dentro dela
        setX0(getPosX() - text.getBoundsInLocal().getWidth()/2);
        setX1(getPosX() + text.getBoundsInLocal().getWidth()/2);
        setY0(getPosY() - text.getBoundsInLocal().getHeight()/2);
        setY1(getPosY() + text.getBoundsInLocal().getHeight()/2);
        this.item = item;
        this.color = color;
        this.textSize = textSize;
    }

    public MenuItem (Image buttonImage, double posX, double posY, GraphicsContext gc) {
        super(posX, posY, gc);
        this.buttonImage = buttonImage;
        setX0(getPosX() - buttonImage.getRequestedWidth()/2);
        setX1(getPosX() + buttonImage.getRequestedWidth()/2);
        setY0(getPosY() - buttonImage.getRequestedHeight()/2);
        setY1(getPosY() + buttonImage.getRequestedHeight()/2);
    }

    public void drawing(MouseEvent mouse, KeyEvent key, Group root) {
        event(mouse,key);
        getGc().setTextAlign(alignment);
        getGc().setFont(fontName);
        getGc().setStroke(Color.rgb(59,0,121));
        getGc().setFill(color);
        getGc().fillText(item,getPosX(), getPosY());
        getGc().strokeText(item, getPosX(), getPosY());
    }

    public void drawing(MouseEvent mouse) {
        event(mouse);
        getGc().drawImage(buttonImage,getPosX(),getPosY(),buttonImage.getWidth() * 3,buttonImage.getHeight()*2);
    }

    // GETS E SETS
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
