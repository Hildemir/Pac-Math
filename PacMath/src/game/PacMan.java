package game;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class PacMan extends Element{
    private List <Image> pacmanAnimation = new ArrayList<>();
    private ImageView pac;
    private Image sprit1, sprite2, sprite3;
   // private double yM, xM;
    private int ind = 0;
    private boolean middle = false;
    private int num = 0;


    public PacMan (double posX, double posY, GraphicsContext gc) {
        super(posX, posY, gc);
        imageInit();
        this.pac = new ImageView(pacmanAnimation.get(0));
//        xM = ((getX1() - getX0()) / 2 + getX0());
//        yM = ((getY1() - getY0()) / 2 + getY0());
    }

    public void imageInit () {
        pacmanAnimation.add(new Image("/resources/sprint1Pacman.png"));
        pacmanAnimation.add(new Image("/resources/sprint2Pacman.png"));
        pacmanAnimation.add(new Image ("/resources/sprint3Pacman.png"));
    }

    private void mudaPacMan() {
        num++;
        if(num == 10) {
            if (!middle && ind == 0) {
                ++ind;
                middle = true;
            } else if (middle && ind == 1) {
                ++ind;
            } else if (ind == pacmanAnimation.size() - 1) {
                --ind;
                middle = false;
            } else {
                --ind;
            }
            num = 0;
        }
    }

    @Override
    public void drawing (MouseEvent mouse, KeyEvent key, Group root) {
        pac.setImage(pacmanAnimation.get(ind));
//        pac.setX(getPosX());
//        pac.setY(getPosY());
//        setY1(getPosY() + pac.getImage().getHeight());
//        yM = ((getY1() - getY0())/ 2 + getY0());


        getGc().drawImage(pac.getImage(), getPosX(), getPosY(),pacmanAnimation.get(0).getWidth()/3,pacmanAnimation.get(0).getHeight()/3);
        mudaPacMan();

        if (key != null && key.getCode() == KeyCode.RIGHT && getPosX() <= 990 ) {
            translatingX(2);
        } else if (key != null && key.getCode() == KeyCode.LEFT && getPosX() >= 70) {
            translatingX(-2);
        } else if (key != null && key.getCode() == KeyCode.UP && getPosY() > 45) {
            translatingY(-2);
        }else if (key != null && key.getCode() == KeyCode.DOWN && getPosY() < 990) {
            translatingY(2);
        }
    }

    public void translatingX (double x) {
        double novoX = getPosX() + x;
        double dif = novoX - getPosX();
        setX0(getX0() + dif);
        setX1(getX1() + dif);
        setPosX(novoX);
    }
    public void translatingY (double y) {
        double novoY = getPosY() + y;
        double dif = novoY - getPosY();
        setY0(getY0() + dif);
        setY1(getY1() + dif);
        setPosY(novoY);
    }
}
