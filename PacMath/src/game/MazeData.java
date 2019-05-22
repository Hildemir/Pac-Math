package game;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MazeData {
    private Rectangle [] rectangles;

    public MazeData() {
        this.rectangles = generateMazeData();
    }

    public Rectangle [] getMazeData() {
        return this.rectangles;
    }

    public  Rectangle[] generateMazeData() {

        Rectangle[] mazeRect = new Rectangle[2];

        //Spawn
        Rectangle l1 = new Rectangle(106, 60);
        l1.setFill(Color.RED);
        l1.setTranslateX(132);
        l1.setTranslateY(115);

        Rectangle l2 = new Rectangle(140, 60);
        l2.setFill(Color.RED);
        l2.setTranslateX(316);
        l2.setTranslateY(115);

        for (int i = 0; i < mazeRect.length; i++) {
            switch (i) {
                case 0:
                    mazeRect[i] = l1;
                    break;
                case 1:
                    mazeRect[i] = l2;
                    //System.out.println("Final Added");
                    break;
            }
        }
        return mazeRect;
    }

}
