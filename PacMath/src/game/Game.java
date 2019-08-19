package game;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements Drawable{
    private GraphicsContext gc;
    private Status status;
    private Image background, died;
    private static double w = 1100, h = 1100;
    private PacMan pacMan;
    public MazeData mazeData;
   // private Rectangle [] rectangles;
    private ColRect [] rectangles;
    private ColRect [] tunnelRect;
    private TimeBar timeBar;
    private PointsData pointsData;
    private Expression expression;
    private List<Element2D> numMaze;
    private int contPacGif = 0;
    private List<Image> ghostAnimation1 = new ArrayList<>();
    private List<Image> ghostAnimation2 = new ArrayList<>();
    private List<Image> ghostAnimation3 = new ArrayList<>();
    private List<Image> ghostAnimation4 = new ArrayList<>();
    private Image borderPoints, borderExp, curvedArrow;
    private Media media;
    private MediaPlayer player;
    private  int flag = 0, cont = 5;
    private boolean messageVisibility = true;
    //private Media media = new Media();
    //private String sound = "/resources/PacmanBeginning.mp3";


    public Ghost[] ghosts;


    public List<Element2D> getNumMaze() {
        return numMaze;
    }

    public Game(GraphicsContext gc, Status status, Group root) {
        this.gc = gc;
        this.status = status;
        this.pacMan = new PacMan(555, 590, gc);
        images();
        this.mazeData = new MazeData(gc);
        this.rectangles = mazeData.generateMazeData();

        this.ghosts = new Ghost[4];
        for (int i = 0; i < 4; i++){
            if(i == 0) {
                this.ghosts[i] = new Ghost(0, gc, 85, 302, ghostAnimation1);                // CRIA GHOST1
            } else if (i == 1){
                this.ghosts[i] = new Ghost(0, gc, 1012, 216, ghostAnimation2);                // CRIA GHOST2
            } else if(i == 2){
                this.ghosts[i] = new Ghost(0, gc, 922, 898, ghostAnimation3);                // CRIA GHOST3
            } else {
                this.ghosts[i] = new Ghost(0, gc, 197, 1000, ghostAnimation4);                // CRIA GHOST4
            }
        }


        this.tunnelRect = new Tunnel(gc).getTunnelRect();
        this.timeBar = new TimeBar(Font.loadFont(Main.class.getClassLoader().getResourceAsStream("emulogic.ttf"), 40),this,gc);
        this.pointsData = new PointsData(Font.loadFont(Main.class.getClassLoader().getResourceAsStream("emulogic.ttf"), 32), gc);
        this.expression = new Expression();
        // CRIACAO DE ELEMENTOS 2D
        criarElementos2D();
        this.borderPoints = new Image("/resources/borders2.png");
        this.borderExp = new Image("/resources/borders3.png");
        this.curvedArrow = new Image("/resources/curvedArrow1.png");

    }

public void playDeathSound (){
    media = new Media("file://" + "/home/hildemir/IdeaProjects/PacMath/src/resources/pacmandeath.mp3");
    player = new MediaPlayer(media);
    //player.setCycleCount(2);            //Timeline.INDEFINITE para rodar musica infinitas vezes
    player.play();
}

    public void images () {
        background = new Image("/resources/mazeEmpty.png");
        died = new Image("/resources/pacmanDeath3.gif");
        //lista fantasma 1
        ghostAnimation1.add(new Image("/resources/ghosts/redGhostUP1.png"));
        ghostAnimation1.add(new Image("/resources/ghosts/redGhostUP2.png"));
        ghostAnimation1.add(new Image("/resources/ghosts/redGhostRIGHT1.png"));
        ghostAnimation1.add(new Image("/resources/ghosts/redGhostRIGHT2.png"));
        ghostAnimation1.add(new Image("/resources/ghosts/redGhostDOWN1.png"));
        ghostAnimation1.add(new Image("/resources/ghosts/redGhostDOWN2.png"));
        ghostAnimation1.add(new Image("/resources/ghosts/redGhostLEFT1.png"));
        ghostAnimation1.add(new Image("/resources/ghosts/redGhostLEFT2.png"));
        //lista Fantasma 2
        ghostAnimation2.add(new Image("/resources/ghosts/pinkGhostUP1.png"));
        ghostAnimation2.add(new Image("/resources/ghosts/pinkGhostUP2.png"));
        ghostAnimation2.add(new Image("/resources/ghosts/pinkGhostRIGHT1.png"));
        ghostAnimation2.add(new Image("/resources/ghosts/pinkGhostRIGHT2.png"));
        ghostAnimation2.add(new Image("/resources/ghosts/pinkGhostDOWN1.png"));
        ghostAnimation2.add(new Image("/resources/ghosts/pinkGhostDOWN2.png"));
        ghostAnimation2.add(new Image("/resources/ghosts/pinkGhostLEFT1.png"));
        ghostAnimation2.add(new Image("/resources/ghosts/pinkGhostLEFT2.png"));
        //lista Fantasma 3
        ghostAnimation3.add(new Image("/resources/ghosts/blueGhostUP1.png"));
        ghostAnimation3.add(new Image("/resources/ghosts/blueGhostUP2.png"));
        ghostAnimation3.add(new Image("/resources/ghosts/blueGhostRIGHT1.png"));
        ghostAnimation3.add(new Image("/resources/ghosts/blueGhostRIGHT2.png"));
        ghostAnimation3.add(new Image("/resources/ghosts/blueGhostDOWN1.png"));
        ghostAnimation3.add(new Image("/resources/ghosts/blueGhostDOWN2.png"));
        ghostAnimation3.add(new Image("/resources/ghosts/blueGhostLEFT1.png"));
        ghostAnimation3.add(new Image("/resources/ghosts/blueGhostLEFT2.png"));
        //lista Fantasma 4
        ghostAnimation4.add(new Image("/resources/ghosts/orangeGhostUP1.png"));
        ghostAnimation4.add(new Image("/resources/ghosts/orangeGhostUP2.png"));
        ghostAnimation4.add(new Image("/resources/ghosts/orangeGhostRIGHT1.png"));
        ghostAnimation4.add(new Image("/resources/ghosts/orangeGhostRIGHT2.png"));
        ghostAnimation4.add(new Image("/resources/ghosts/orangeGhostDOWN1.png"));
        ghostAnimation4.add(new Image("/resources/ghosts/orangeGhostDOWN2.png"));
        ghostAnimation4.add(new Image("/resources/ghosts/orangeGhostLEFT1.png"));
        ghostAnimation4.add(new Image("/resources/ghosts/orangeGhostLEFT2.png"));
    }


    @Override
    public void drawing (MouseEvent mouse, KeyEvent key, Group root) {
        gc.drawImage(background, 0,0,w,h);
       // resetList(); // reseta a lista de numeros gerados para primeira iteracao ano muda nada pois os valores ainda sao os mesmos

        for(ColRect r : this.rectangles) {      //desenha muros do maze
            r.draw(this.gc);
        }
        pointsData.drawing(mouse, key, root);
        timeBar.drawing(mouse, key, root);
        expression.draw(this.gc);
        gc.drawImage(borderPoints,1100,-255,800,900);


        desenharNumeros(mouse,key,root);        //desenha numeros e buffer

        pacMan.drawing(mouse, key, root);           //pac-man tem q ser desenhado antes do tunel e depois dos numeros
        for (ColRect rect : this.tunnelRect) {
            rect.drawTunnel(this.gc);
        }
        gc.drawImage(borderExp,1105,100,800,900);           // moldura da expressao tem que ser desenhada por cima do retangulo preto do tunel


        if(messageVisibility) {
            gc.setFill(Color.YELLOW);
            gc.fillText("This is your answer.\nPress SPACE to submit!", 1320, 800, 350);

            if (flag <= 70) {
                gc.drawImage(curvedArrow, 1350, 600, 400, 400);
                if (cont != 0) {
                    flag++;
                }
            } else {
                gc.setFill(Color.TRANSPARENT);
                flag++;
                if (flag == 110) {
                    flag = 0;
                    cont--;
                    if (cont == 0) {
                        flag = 70;      // flag ==70 && cont == 0 encerra blinker
                    }
                }
            }
//            if(key.getCode() == KeyCode.SPACE){
//                setMessageVisibility(false);
//            }
        }

//        gc.setFill(Color.BLACK);
//        gc.fillRect(1510,700,100,200);


        for (Ghost g: ghosts) {
            if(g.equals(ghosts[0])){
                g.drawing(gc, root);         // desenha fantasma 1
                g.move1();                           //movimenta fantasma 1
                if (g.checkCollision(pacMan)) {
//                    if(pacMan.getStopChocking() == false){
//                        pacMan.decreaseLife(1);
//                        //pacMan.setStopChocking(true);
//                    }
                    ghosts[0].setBuffer(0);     //usei um dos objetos fantasmas q extends a classe Element2d para zerar o buffer quando houver colisao

                    g.setMoving(false);
                    g.SetIsVisible(false);
                    ghosts[1].setMoving(false);
                    ghosts[2].setMoving(false);
                    ghosts[3].setMoving(false);
                    pacMan.setMoving(false);
                    pacMan.getPac().setImage(died);         //troca imagem do pacman para gif de morte


                    if(contPacGif < 125) {                  // tempo do gif terminar antes de desaparecer
                        getPacMan().getGc().drawImage(died, pacMan.getPosX(), getPacMan().getPosY(), pacMan.getPacmanAnimation().get(0).getWidth() / 2, pacMan.getPacmanAnimation().get(0).getHeight() / 2);
                        contPacGif++;
                        if(contPacGif == 10){               //if que somente serve para nao permitir que som seja tocado mais de uma vez enquanto contPacGif < 125
                            playDeathSound();
                        }

                    } else if(contPacGif >= 125){
                        getPacMan().setPosX(555);
                        getPacMan().setPosY(590);
                        nextExpression(mouse, key, root);
                        getPacMan().setMoving(true);
                        ghosts[0].setMoving(true);
                        ghosts[1].setMoving(true);
                        ghosts[2].setMoving(true);
                        ghosts[3].setMoving(true);
                        contPacGif = 0;
                    }
                }
            } else if(g.equals(ghosts[1])) {
                g.drawing(gc, root);         // desenha fantasma 2
                g.move2();                           //movimenta fantasma 2
                if (g.checkCollision(pacMan)) {
//                    if(pacMan.getStopChocking() == false){
//                        pacMan.decreaseLife(1);
//                       // pacMan.setStopChocking(true);
//                    }
                    ghosts[0].setBuffer(0);     //usei um dos objetos fantasmas q extends a classe Element2d para zerar o buffer quando houver colisao

                    g.setMoving(false);
                    g.SetIsVisible(false);
                    ghosts[0].setMoving(false);
                    ghosts[2].setMoving(false);
                    ghosts[3].setMoving(false);
                    pacMan.setMoving(false);
                    pacMan.getPac().setImage(died);         //troca imagem do pacman para gif de morte

                    if(contPacGif < 125) {                  // tempo do gif terminar antes de desaparecer
                        getPacMan().getGc().drawImage(died, pacMan.getPosX(), getPacMan().getPosY(), pacMan.getPacmanAnimation().get(0).getWidth() / 2, pacMan.getPacmanAnimation().get(0).getHeight() / 2);
                        contPacGif++;
                        if(contPacGif == 10){               //if que somente serve para nao permitir que som seja tocado mais de uma vez enquanto contPacGif < 125
                            playDeathSound();
                        }

                    } else if(contPacGif >= 125){
                        getPacMan().setPosX(555);
                        getPacMan().setPosY(590);
                        nextExpression(mouse, key, root);
                        getPacMan().setMoving(true);
                        ghosts[0].setMoving(true);
                        ghosts[1].setMoving(true);
                        ghosts[2].setMoving(true);
                        ghosts[3].setMoving(true);
                        contPacGif = 0;
                    }
                }
            }else if(g.equals(ghosts[2])) {
                g.drawing(gc, root);         // desenha fantasma 3
                g.move3();                           //movimenta fantasma 3
                if (g.checkCollision(pacMan)) {
//                    if(pacMan.getStopChocking() == false){
//                        pacMan.decreaseLife(1);
//                        //pacMan.setStopChocking(true);
//                    }

                    ghosts[0].setBuffer(0);     //usei um dos objetos fantasmas q extends a classe Element2d para zerar o buffer quando houver colisao

                    g.setMoving(false);
                    g.SetIsVisible(false);
                    ghosts[1].setMoving(false);
                    ghosts[0].setMoving(false);
                    ghosts[3].setMoving(false);
                    pacMan.setMoving(false);
                    pacMan.getPac().setImage(died);         //troca imagem do pacman para gif de morte


                    if(contPacGif < 125) {                  // tempo do gif terminar antes de desaparecer
                        getPacMan().getGc().drawImage(died, pacMan.getPosX(), getPacMan().getPosY(), pacMan.getPacmanAnimation().get(0).getWidth() / 2, pacMan.getPacmanAnimation().get(0).getHeight() / 2);
                        contPacGif++;
                        if(contPacGif == 10){               //if que somente serve para nao permitir que som seja tocado mais de uma vez enquanto contPacGif < 125
                            playDeathSound();
                        }

                    } else if(contPacGif >= 125){
                        getPacMan().setPosX(555);
                        getPacMan().setPosY(590);
                        nextExpression(mouse, key, root);
                        getPacMan().setMoving(true);
                        ghosts[0].setMoving(true);
                        ghosts[1].setMoving(true);
                        ghosts[2].setMoving(true);
                        ghosts[3].setMoving(true);
                        contPacGif = 0;
                    }
                }
            } else if(g.equals(ghosts[3])) {

                g.drawing(gc, root);         // desenha fantasma 3
                g.move4();                           //movimenta fantasma 3
                if (g.checkCollision(pacMan)) {
//                    if(pacMan.getStopChocking() == false){
//                        pacMan.decreaseLife(1);
//                       // pacMan.setStopChocking(true);
//                    }

                    ghosts[0].setBuffer(0);     //usei um dos objetos fantasmas q extends a classe Element2d para zerar o buffer quando houver colisao

                    g.setMoving(false);
                    g.SetIsVisible(false);
                    ghosts[1].setMoving(false);
                    ghosts[2].setMoving(false);
                    ghosts[0].setMoving(false);
                    pacMan.setMoving(false);
                    pacMan.getPac().setImage(died);         //troca imagem do pacman para gif de morte

                    if(contPacGif < 125) {                  // tempo do gif terminar antes de desaparecer
                        getPacMan().getGc().drawImage(died, pacMan.getPosX(), getPacMan().getPosY(), pacMan.getPacmanAnimation().get(0).getWidth() / 2, pacMan.getPacmanAnimation().get(0).getHeight() / 2);
                        contPacGif++;
                        if(contPacGif == 10){               //if que somente serve para nao permitir que som seja tocado mais de uma vez enquanto contPacGif < 125
                            playDeathSound();
                        }

                    } else if(contPacGif >= 125){
                        getPacMan().setPosX(555);
                        getPacMan().setPosY(590);
                        nextExpression(mouse, key, root);
                        getPacMan().setMoving(true);
                        ghosts[0].setMoving(true);
                        ghosts[1].setMoving(true);
                        ghosts[2].setMoving(true);
                        ghosts[3].setMoving(true);
                        contPacGif = 0;
                    }
                }
            }
        }



        //gc.fillText(String.valueOf("True"), 900, 50, 100);
    }

    public PacMan getPacMan (){
        return this.pacMan;
    }

    public TimeBar getTimeBar() {
        return timeBar;
    }

    public PointsData getPointsData() {
        return pointsData;
    }

    public ColRect[] getRectangles() {
        return rectangles;
    }

    public Expression getExpression() {
        return expression;
    }

    public void nextExpression(MouseEvent mouse ,KeyEvent key , Group root) {
        expression.gerarExpressao();
        expression.possiveisRespostas();
        criarElementos2D();
    }

    public void desenharNumeros(MouseEvent mouse , KeyEvent key , Group root) {
        for(Element2D e : numMaze) {              // desenha numeros
            e.drawing(mouse,key,root);
//            gc.setStroke(Color.YELLOW);
//            gc.strokeRect(e.getPosX(), e.getPosY(),e.getWidth(),e.getHeight());
            gc.setFill(Color.ORANGE);
            gc.fillText(String.valueOf(e.getBuffer()), 1575,570);
        }
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public boolean isMessageVisibility() {
        return messageVisibility;
    }

    public void setMessageVisibility(boolean messageVisibility) {
        this.messageVisibility = messageVisibility;
    }

    public void criarElementos2D() {
        numMaze = new ArrayList<>();
        for(int i = 0 ; i< 4;i++) {
                if (i == 0) {
                    numMaze.add(new Element2D(expression.getNumGerados()[i], gc, 200, 230));
                } else if (i == 1) {
                    numMaze.add(new Element2D(expression.getNumGerados()[i], gc, 980, 835));
                } else if (i == 2) {
                    numMaze.add(new Element2D(expression.getNumGerados()[i], gc, 920, 100));
                } else if (i == 3) {
                    numMaze.add(new Element2D(expression.getNumGerados()[i], gc, 310, 1035));
                }
        }
    }


}
