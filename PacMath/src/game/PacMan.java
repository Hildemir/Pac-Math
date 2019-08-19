package game;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class PacMan extends Element{
    private List <Image> pacmanAnimation = new ArrayList<>();
    private ImageView pac;
    private double yM, xM;
    private int ind = 0;
    private boolean middle = false;
    private int num = 0;
    private boolean moving = true;
    //private int life;
    private Image lifeIcon;
    private boolean stopChocking = false;
    private String currentDirection;

//    private double velX = 0;
//    private double velY = 0;


    public double w, h;

    public PacMan (double posX, double posY, GraphicsContext gc) {
        super(posX, posY, gc);
        imageInit();
        this.pac = new ImageView(pacmanAnimation.get(0));
        xM = ((getX1() - getX0()) / 2 + getX0());
        yM = ((getY1() - getY0()) / 2 + getY0());

        w = pacmanAnimation.get(0).getWidth()/2;
        h = pacmanAnimation.get(0).getHeight()/2;
        //this.life = 3;
    }

    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }

    //    public void setVelX(double velX) {
//        this.velX = velX;
//    }
//
//    public void setVelY(double velY) {
//        this.velY = velY;
//    }

//    public void tick () {
//        xM += velX;
//        yM+= velY;
//    }

    public void imageInit () {
        pacmanAnimation.add(new Image("/resources/pacmanLEFT.gif"));
        pacmanAnimation.add(new Image("/resources/pacmanRIGHT.gif"));
        pacmanAnimation.add(new Image ("/resources/pacmanUP.gif"));
        pacmanAnimation.add(new Image("/resources/pacmanDOWN.gif"));

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
        pac.setX(getPosX());
        pac.setY(getPosY());
//        setY1(getPosY() + pac.getImage().getHeight());
//        yM = ((getY1() - getY0())/ 2 + getY0());


        getGc().drawImage(pac.getImage(), getPosX(), getPosY(),pacmanAnimation.get(0).getWidth()/2,pacmanAnimation.get(0).getHeight()/2);
//        getGc().setStroke(Color.RED);
//        getGc().strokeRect(getPosX(), getPosY(),pacmanAnimation.get(0).getWidth()/2.5,pacmanAnimation.get(0).getHeight()/2.5);

       // mudaPacMan();
    if(isMoving()) {
        if (key != null && key.getCode() == KeyCode.RIGHT && getPosX() <= 1108) {
            setInd(1);
            if (getPosX() >= 1100) {
                setPosX(-25);
            } else {
                translatingX(2);
            }
        } else if (key != null && key.getCode() == KeyCode.LEFT && getPosX() >= -5) {
            setInd(0);
            if (getPosX() <= 0) {
                setPosX(1100);
            } else {
                translatingX(-2);
            }
        } else if (key != null && key.getCode() == KeyCode.UP && getPosY() > 45) {
            setInd(2);
            translatingY(-2);
        } else if (key != null && key.getCode() == KeyCode.DOWN && getPosY() < 990) {
            setInd(3);
            translatingY(2);
        }
    } else {
        if (key != null && key.getCode() == KeyCode.RIGHT && getPosX() <= 1108) {
            if (getPosX() >= 1100) {
                setPosX(-25);
            } else {
                translatingX(0);
            }
        } else if (key != null && key.getCode() == KeyCode.LEFT && getPosX() >= -5) {
            if (getPosX() <= 0) {
                setPosX(1100);
            } else {
                translatingX(0);
            }
        } else if (key != null && key.getCode() == KeyCode.UP && getPosY() > 45) {
            translatingY(0);
        } else if (key != null && key.getCode() == KeyCode.DOWN && getPosY() < 990) {
            translatingY(0);
        }
    }
//    for(int i = 0; i < life; i++){
//        if(i == 0){
//            getGc().drawImage(lifeIcon,1200,900,60,60);
//        } else if (i == 1){
//            getGc().drawImage(lifeIcon,1300,900,60,60);
//        } else {
//            getGc().drawImage(lifeIcon,1400,900,60,60);
//        }
        }

        //else {
//            int xxx = (int)(getPosX()) - 50;
//            if (xxx % 77 < 39 && xxx % 77 != 0)
//                translatingX(-2 );
//            else if (xxx % 77 >= 39 && xxx % 77 != 0)
//                translatingX(2 );
//        }
//    }

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

    public List<Image> getPacmanAnimation() {
        return pacmanAnimation;
    }

    public ImageView getPac() {
        return pac;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

//    public int getLife() {
//        return life;
//    }
//
//    public void decreaseLife(int life) {
//        this.life = this.life -life;
//        if(this.life < 0){
//            this.life = 0;
//        }
//    }

    public boolean getStopChocking() {
        return stopChocking;
    }

    public void setStopChocking(boolean stopChocking) {
        this.stopChocking = stopChocking;
    }

    public int getInd() {
        return ind;
    }

    public void setInd(int ind) {
        this.ind = ind;
    }
}
