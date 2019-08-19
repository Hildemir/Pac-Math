package game;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class TimeBar {
    private Font font;
    private Game game;
    private GraphicsContext gc;
    private long startTime, time = 90;

    public TimeBar(Font font, Game game, GraphicsContext gc) {
        this.font = font;
        this.game = game;
        this.gc = gc;
        startTime = System.currentTimeMillis();
    }
    public String formatTime() {
        return String.format("%02d:%02d", time/60, time%60);
    }

    public void drawing(MouseEvent mouse, KeyEvent key, Group group){
        if(System.currentTimeMillis() - startTime >= 1000){
            startTime = System.currentTimeMillis();
            removeSeconds(1);
        }
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setFont(font);
        gc.setStroke(Color.ORANGE);
        gc.setFill(Color.ORANGE);
        gc.fillText(formatTime(), 1590,120);
        gc.strokeText(formatTime(),1590,120);
    }

    public void removeSeconds(long sec) {
        time = time - sec;
        if(time < 0) {
            time = 0;
            Main.setStatus(Status.GAMEOVER);
           // time = 10;
        }
    }

    public void earnSeconds(long sec){
        time = time + sec;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
