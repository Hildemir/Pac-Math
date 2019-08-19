package game;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Ghost extends Element2D {
    private static final int DIR_LEFT = 3;
    private static final int DIR_RIGHT = 1;
    private static final int DIR_UP = 0;
    private static final int DIR_DOWN = 2;
    private int dir = 0;
//    private double posX;
//    private double posY;
    private double w;
    private double h;
    private int cont = 0;
    private boolean moving;
    private ImageView ghostImage;
    private List<Image> ghostAnimation = new ArrayList<>();
    private int ind = 0, num =0;


   // private double step = 2;

    public Ghost(int ignore,  GraphicsContext gc, double x, double y,  List<Image> ghostAnimation) {
        super(ignore, gc, x, y);
//        this.setPosX(x);
//        this.setPosY(y);
        this.ghostAnimation = ghostAnimation;
        this.w = 30;
        this.h = 30;
        //dir = new Random().nextInt(4);
        this.moving = true;
        //imageInit ();
        this.ghostImage = new ImageView(ghostAnimation.get(0));
    }

    public void drawing (GraphicsContext gc, Group root){
        ghostImage.setImage(ghostAnimation.get(ind));
        gc.drawImage(ghostImage.getImage(), getPosX(), getPosY(), w*1.5, h*1.5);
        mudaInd();

        //gc.setFill(Color.TRANSPARENT);
//        gc.setFill(colorGhost);
//        gc.fillRect(posX, posY, w, h);
    }

    public void mudaInd() {
        if(dir == DIR_UP) {
            if (num <= 20) {
                ind = 0;
                num++;
            } else {
                num++;
                ind = 1;
                if (num == 40) {
                    num = 0;
                    ind = 0;
                }

            }
        } else if(dir == DIR_RIGHT) {
            if (num <= 20) {
                ind = 2;
                num++;
            } else {
                num++;
                ind = 3;
                if (num == 40) {
                    num = 0;
                    ind = 2;
                }

            }
        } else if (dir == DIR_DOWN){
            if (num <= 20) {
                ind = 4;
                num++;
            } else {
                num++;
                ind = 5;
                if (num == 40) {
                    num = 0;
                    ind = 4;
                }

            }
        } else if (dir == DIR_LEFT){
            if (num <= 20) {
                ind = 6;
                num++;
            } else {
                num++;
                ind = 7;
                if (num == 40) {
                    num = 0;
                    ind = 6;
                }

            }
        }
    }

    public void imageInit () {
//        ghostAnimation.add(new Image("/resources/ghosts/redGhostUP1.png"));
//        ghostAnimation.add(new Image("/resources/ghosts/redGhostUP2.png"));
//        ghostAnimation.add(new Image("/resources/ghosts/redGhostRIGHT1.png"));
//        ghostAnimation.add(new Image("/resources/ghosts/redGhostRIGHT2.png"));
//        ghostAnimation.add(new Image("/resources/ghosts/redGhostDOWN1.png"));
//        ghostAnimation.add(new Image("/resources/ghosts/redGhostDOWN2.png"));
//        ghostAnimation.add(new Image("/resources/ghosts/redGhostLEFT1.png"));
//        ghostAnimation.add(new Image("/resources/ghosts/redGhostLEFT2.png"));
    }


    public void move1() {
            double vel = 1.5;     // como incrementa sempre 2 os pontos de alteracao tem q ser alcancados somando 2 constantimente
            dir = cont;
            if (dir == DIR_UP) {
               // System.out.println(getPosX());
                if(moving){
                    setPosY(getPosY() - vel);
                    if(getPosY() == 198.5){               // vel = 2        UP = 198                       RIGHT = 261              DOWN = 302                LEFT = 85
                        cont++;                           // vel = 1.5      UP = 198.5                     RIGHT = 263.5            DOWN = same               LEFT = same
                    }
                }
            } else if (dir == DIR_RIGHT) {
                if(moving){
                    setPosX(getPosX() + vel);
                    if(getPosX() == 263.5){
                        cont++;
                    }
                }
            } else if (dir == DIR_DOWN) {
                if(moving){
                    setPosY(getPosY() + vel);
                    if(getPosY() == 302){
                        cont++;
                    }
                }
            } else if (dir == DIR_LEFT) {
                if(moving){
                    setPosX(getPosX() - vel);
                    if(getPosX() == 85) {
                        cont = 0;
                    }
                }
            }
    }

    public void move2() {
        double vel = 1.5;     // como incrementa sempre 2 os pontos de alteracao tem q ser alcancados somando 2 constantimente
        dir = cont;
        if (dir == DIR_UP) {
             //System.out.println(getPosY());
            if(moving){
                setPosY(getPosY() - vel);                   // vel =2         UP = 66                    RIGHT = 998                        DOWN = 198                 LEFT = 818
                                                            // vel = 1.5      UP = same                  RIGHT = 998.5                      DOWN = same                LEFT = 818.5
                if(getPosY() == 66){
                    cont = 3;
                }
            }
        } else if (dir == DIR_RIGHT) {
            if(moving){
                setPosX(getPosX() + vel);
                if(getPosX() == 998.5){
                    cont = 0;
                }
            }
        } else if (dir == DIR_DOWN) {
            if(moving){
                setPosY(getPosY() + vel);
                if(getPosY() == 198){
                    cont = 1;
                }
            }
        } else if (dir == DIR_LEFT) {
            if(moving){
                setPosX(getPosX() - vel);
                if(getPosX() == 818.5) {
                    cont = 2;
                }
            }
        }
    }


    public void move3() {
        double vel = 1.5;     // como incrementa sempre 2 os pontos de alteracao tem q ser alcancados somando 2 constantimente
        dir = cont;
        if (dir == DIR_UP) {
            // System.out.println(getPosX());
            if(moving){
                setPosY(getPosY() - vel);               // vel =2         UP = 800/700                RIGHT = 998                         DOWN = 798/898             LEFT = 918
                                                        // vel = 1.5      UP = 799/700                RIGHT = 998.5                       DOWN = 799/898             LEFT = 919
                if(getPosY() == 799){
                    cont = 1;
                }
                if(getPosY() == 700){
                    cont = 2;
                }
            }
        } else if (dir == DIR_RIGHT) {
            if(moving){
                setPosX(getPosX() + vel);
                if(getPosX() == 998.5){
                    cont = 0;
                }
            }
        } else if (dir == DIR_DOWN) {
            if(moving){
                setPosY(getPosY() + vel);
                if(getPosY() == 799){
                    cont = 3;
                }
                if(getPosY() == 898){
                    cont = 0;
                }
            }
        } else if (dir == DIR_LEFT) {
            if(moving){
                setPosX(getPosX() - vel);
                if(getPosX() == 919) {
                    cont = 2;
                }
            }
        }
    }

    public void move4() {
        double vel = 1.5;     // como incrementa sempre 2 os pontos de alteracao tem q ser alcancados somando 2 constantimente
        dir = cont +1;                  // 0 (+1) = RIGHT
        if (dir == DIR_UP) {
            // System.out.println(getPosX());
            if (moving) {                                           // vel =2         UP = NotUsed            RIGHT = 421                         DOWN = NotUsed             LEFT = 197
                                                                    // vel = 1.5      UP = same               RIGHT = 422                         DOWN = same                LEFT = same
                setPosY(getPosY() - vel);
                if (getPosY() == 800) {
                    cont = 1;
                }
                if (getPosY() == 700) {
                    cont = 2;
                }
            }
        } else if (dir == DIR_RIGHT) {
            if (moving) {
                setPosX(getPosX() + vel);
                if (getPosX() == 422) {
                    cont = 2;           // 2 (+1) = LEFT
                }
            }
        } else if (dir == DIR_DOWN) {
            if (moving) {
                setPosY(getPosY() + vel);
                if (getPosY() == 798) {
                    cont = 3;
                }
                if (getPosY() == 898) {
                    cont = 0;
                }
            }
        } else if (dir == DIR_LEFT) {
            if (moving) {
                setPosX(getPosX() - vel);
                if (getPosX() == 197) {
                    cont = 0;               // 0 (+1) = RIGHT
                }
            }
        }
    }
    public boolean Moving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void SetIsVisible (boolean value) {
            if(value == true){
                getGc().drawImage(ghostImage.getImage(), getPosX(), getPosY(), w*1.5, h*1.5);
            } else{
                getGc().setFill(Color.BLACK);
                getGc().fillRect(getPosX()-1, getPosY()-1, (w*1.5)+1, (h*1.5)+1);


            }
    }

//    public void changeDirection() {
//        int atual = dir;
//        int novo = -1;
//        int []newDir = new int[]{DIR_DOWN, DIR_RIGHT};
//        Arrays.stream(newDir).filter(e -> e != atual);
//        while (novo != atual) {
//            novo = new Random().nextInt(4);
//        }
//        dir = novo;
//    }
}
