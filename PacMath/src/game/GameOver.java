package game;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GameOver {
    private MenuItem gameover;
    private MenuItem space;
    private MenuItem loveM;
    private GraphicsContext gc;
    private String sound = "/home/hildemir/IdeaProjects/PacMath/src/resources/pacmandeath.mp3";
    private boolean play = true, love = false;
    private int flag = 0, pressOneMore = 0;
    private Image hearts, mrPacMan, mrsPacMan;


    public GameOver(GraphicsContext gc) {
        this.gc = gc;
        this.gameover = new MenuItem("Game Over", Color.RED, gc, "emulogic.ttf", 900, 550, 50, null);
        this.space =  new MenuItem("Press Space", Color.WHITE,gc, "emulogic.ttf",900, 625, 35, null);
        this.loveM = new MenuItem("I love all your pixels", Color.DEEPPINK,gc, "emulogic.ttf",900,450, 50, null);
        this.hearts = new Image("/resources/HeartLife3.gif");
        this.mrPacMan = new Image("/resources/pacmanRIGHT.gif");
        this.mrsPacMan = new Image("/resources/mrsPacman2.gif");
    }

    public void drawing(MouseEvent mouse, KeyEvent key, Group root) {
        if(love == false){
            gameover.drawing(mouse, key, root);
        } else {
            loveM.drawing(mouse, key, root);
            gc.drawImage(hearts,665,500, hearts.getWidth(), hearts.getHeight());
            gc.drawImage(mrPacMan,800,650, mrPacMan.getWidth(), mrPacMan.getHeight());
            gc.drawImage(mrsPacMan,890,650, mrsPacMan.getWidth(), mrsPacMan.getHeight());
        }

        if(flag <= 70) {
            space.drawing(mouse, key, root);

            flag++;
        } else{
            gc.setFill(Color.TRANSPARENT);
            flag++;
            if(flag == 110){
                flag = 0;
            }
        }




        if(play){
            if(love == false) {
                tocarMusica();
                setPlay(false);
            }
        }
        if(key != null && key.getCode() == KeyCode.SPACE){
            setPlay(true);
            setLove(true);
            pressOneMore++;
            space.setPosY(900);
                if(pressOneMore == 8){                      //ISSO N FAZ SENTIDO! Mas ok...
                    Main.setStatus(Status.MENU);
                    for (MenuItem item: Main.menu.getItems()) {
                        item.addToView(root);
                    }
                }

        }

        //        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3));
//        fadeTransition.setFromValue(1.0f);
//        fadeTransition.setToValue(0.3f);
//        fadeTransition.setCycleCount(2);
//        fadeTransition.setAutoReverse(true);
//        SequentialTransition sq = new SequentialTransition(fadeTransition);
//        sq.play();

    }

    public int isPressOneMore() {
        return pressOneMore;
    }

    public void setPressOneMore(int pressOneMore) {
        this.pressOneMore = pressOneMore;
    }

    public boolean getLove() {
        return love;
    }

    public void setLove(boolean love) {
        this.love = love;
    }

    public void tocarMusica(){
        Main.musicaGameover(sound);
    }

    public void setPlay(boolean play) {
        this.play = play;
    }
}
