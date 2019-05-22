package game;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Drawable{
    private GraphicsContext gc;
    private Status status;
    private Image mazeMenu, pacMan, titleMenu, orange1, orange2, orange3, orange4;
    private static double w = 1366, h = 1000;
    private List<MenuItem> items;

    public Menu(GraphicsContext gc, Status status) {
        this.gc = gc;
        this.status = status;
        items = new ArrayList<>();
        buttons();
        menuItems();
        images();

    }

    private void images() {
        mazeMenu = new Image("/resources/logoMaze.png");
        pacMan = new Image("/resources/pacmanLeft.gif");
        titleMenu = new Image ("/resources/titleMenu.png");
    }

    private void buttons() {
        orange1 = new Image("/resources/button.png");
        orange2 = new Image("/resources/button.png");
        orange3 = new Image("/resources/button.png");
        orange4 = new Image("/resources/button.png");
    }

    @Override
    public void drawing(MouseEvent mouse, KeyEvent key, Group root ){
        gc.drawImage(mazeMenu, 0,0,w,h);
        gc.drawImage(titleMenu, 135, 0);
        gc.drawImage(pacMan, 600, 280, (pacMan.getWidth()*1.2), (pacMan.getHeight()*1.2));
        /*gc.drawImage(orange1, 410,500, (orange1.getWidth()*3), (orange1.getHeight()*2));
        gc.drawImage(orange1, 410,600, (orange2.getWidth()*3), (orange2.getHeight()*2));
        gc.drawImage(orange3, 410,700, (orange3.getWidth()*3), (orange3.getHeight()*2));
        gc.drawImage(orange4, 410,800, (orange4.getWidth()*3), (orange4.getHeight()*2));*/
        int num = 0;
        for(MenuItem name : items) {
            if(num < 4) {
                name.drawing(mouse);

            } else {
                name.drawing(mouse, key, root);
            }
            num++;
        }
    }

    private void menuItems() {
        items.add(new MenuItem(orange1,410,500,gc));
        items.add(new MenuItem(orange2,410,600,gc));
        items.add(new MenuItem(orange3,410,700,gc));
        items.add(new MenuItem(orange4,410,800,gc));
        items.add(new MenuItem("Start", Color.rgb(255,216,121),gc, "emulogic.ttf",700, 595, 35));
        items.get(0).setOnMouseEntered(new ChangeColor(Color.rgb(255,216,121),Color.rgb(59,0,121), items.get(0)));
        items.get(0).setOnMouseExited(new ChangeColor(Color.rgb(255,216,121),Color.rgb(59,0,121), items.get(0)));
        items.get(4 ).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main.setStatus(Status.GAME);
            }
        });
        items.add(new MenuItem("Instructions", Color.rgb(255,216,121),gc, "emulogic.ttf",700, 695, 35));
        items.add(new MenuItem("Score", Color.rgb(255,216,121),gc, "emulogic.ttf",700, 795, 35));
        items.add(new MenuItem("Exit", Color.rgb(255,216,121),gc, "emulogic.ttf",700, 895, 35));
    }

}
