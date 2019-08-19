package game;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class PointsData {
    private int points, highScore;
    private Font font;
    private GraphicsContext gc;

    public  PointsData (Font font, GraphicsContext gc){
        this.font = font;
        this.gc = gc;
        this.points = 0;
        this.highScore = 1050;
    }

    public void setPoints(int p) {
        this.points = this.points + (p) ;
        if(this.points <= 0) {
            this.points = 0;
        }
    }

    public void resetPoints(int p) {
        this.points = p;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String formatPoints (int num) {
        if(num == points) {
            return String.valueOf(points);
        }
        return String.valueOf(highScore);
    }

    public void drawing(MouseEvent mouse, KeyEvent key, Group group){
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setFont(font);
        gc.setStroke(Color.ORANGE);
        gc.setFill(Color.ORANGE);        //cor alternativa  Color.rgb(59,0,121)           rgb((int)( 255 * (1 / (1 + points*0.0001))), 0, (int) (255 - 255 * (1 / (1 + points * 0.001))))
        gc.fillText("Points",1450, 250);
        gc.fillText(formatPoints(points), 1450,300);
        gc.strokeText(formatPoints(points),1450,300);


        gc.fillText("High Score",1830, 250 );
        gc.fillText(formatPoints(highScore), 1830,300);
        gc.strokeText(formatPoints(highScore),1830,300);
    }

    public int getPoints() {
        return points;
    }

    public int getHighScore() {
        return highScore;
    }
}
