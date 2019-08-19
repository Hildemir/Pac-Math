package game;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Drawable{
    private GraphicsContext gc;
    private Status status;
    private Image mazeMenu, pacMan, titleMenu, orange1, orange2, orange3, orange4, orange5, orange6;
    private static double w = 1900, h = 1100;
    private List<MenuItem> items;
    private Group root;
    private Rectangle [] rectangles;
    private String sound = "/home/hildemir/IdeaProjects/PacMath/src/resources/PacManThemeREMIX.mp3";             //pacmanbeginning.mp3
    private MenuItem [] numMenu;
    private ColRect rect;
    private boolean play = true;
    private boolean arrowBlink = true;


    public Menu(GraphicsContext gc, Status status, Group root) {
        this.gc = gc;
        this.status = status;
        this.root = root;
        items = new ArrayList<>();
        buttons();
        menuItems();
        images();
        //tocarMusica();                ja eh chamada no drawing
        this.numMenu = new MenuItem[5];
        for(int i = 0; i < 5; i++) {
            if (i == 0){
                this.numMenu[0] = new MenuItem("4", Color.DEEPPINK, gc, "emulogic.ttf", 465, 545, 78, root);
            } else if (i == 1){
                this.numMenu[1] = new MenuItem("7", Color.DEEPPINK, gc, "emulogic.ttf", 158, 282, 78, root);
            } else if (i == 2){
                this.numMenu[2] = new MenuItem("5", Color.DEEPPINK, gc, "emulogic.ttf", 161, 797, 78, root);
            } else if (i == 3){
                this.numMenu[3] = new MenuItem("2", Color.DEEPPINK, gc, "emulogic.ttf", 1758, 290, 78, root);
            } else{
                this.numMenu[4] = new MenuItem("3", Color.DEEPPINK, gc, "emulogic.ttf", 1432, 873, 78, root);
            }
        }
        this.rect = new ColRect(420,455,80,138); // retangulo q esconde dots atras do numero 4

    }


    private void tocarMusica() {
        Main.musicaMenu(sound);
    }

    private void images() {
        mazeMenu = new Image("/resources/logoMaze.png");
        pacMan = new Image("/resources/pacmanLeft.gif");
        titleMenu = new Image ("/resources/titleMenu.png");
    }

    private void buttons() {
        orange1 = new Image("/resources/buttons/buttonStart.png");
        orange2 = new Image("/resources/buttons/buttonInstructions.png");
        orange3 = new Image("/resources/buttons/buttonScore.png");
        orange4 = new Image("/resources/buttons/buttonSoundOn.png");
        orange5 = new Image("/resources/buttons/buttonExit.png");
        orange6 = new Image("/resources/buttons/buttonSoundOff.png");
    }

    @Override
    public void drawing(MouseEvent mouse, KeyEvent key, Group root ){
        gc.drawImage(mazeMenu, 0,0,w,h);
        gc.drawImage(titleMenu, 405, 0);
        gc.drawImage(pacMan, 870, 280, (pacMan.getWidth()*1.2), (pacMan.getHeight()*1.2));
//        int num = 0;
//        for(MenuItem name : items) {
//            if(num < 5) {
//                name.drawing(mouse, root);
//
//            } else {
//                name.drawing(mouse, key, root);
//            }
//            num++;
//        }
        rect.drawTunnel(gc);
        for (int i =0; i < 5; i++){                 //desenha numeros no menu
            numMenu[i].drawing(mouse,key,root);
        }

        if(play){
            tocarMusica();
            setPlay(false);
        }

    }

    public List<MenuItem> getItems() {
        return items;
    }

    private void menuItems() {
        items.add(new MenuItem(orange1,710,458, gc, root));
        items.add(new MenuItem(orange2,710,558, gc, root));
        items.add(new MenuItem(orange3,710,658, gc, root));
        items.add(new MenuItem(orange6,710,758, gc, root));             //sound off
        items.add(new MenuItem(orange4,710,758, gc, root));             //sound on
        items.add(new MenuItem(orange5,710,858, gc, root));

//        items.add(new MenuItem("Start", Color.rgb(255,216,121),gc, "emulogic.ttf",970, 595, 35, root));
//        items.get(0).setOnMouseEntered(new ChangeColor(Color.rgb(255,216,121),Color.rgb(59,0,121), items.get(0)));
//        items.get(0).setOnMouseExited(new ChangeColor(Color.rgb(255,216,121),Color.rgb(59,0,121), items.get(0)));
//        items.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {                                          // duplicado e desatualizado
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                Main.setStatus(Status.GAME);
//                Main.getPlayer().stop();
//
//            }
//        });
        items.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                root.getChildren().remove(1, root.getChildren().size());
                Main.setStatus(Status.GAME);
                Main.getPlayer().stop();
                setPlay(true);
                setArrowBlink(true);


            }
        });
//        items.add(new MenuItem("Instructions", Color.rgb(255,216,121),gc, "emulogic.ttf",955, 695, 35, root));
//        items.add(new MenuItem("Score", Color.rgb(255,216,121),gc, "emulogic.ttf",970, 795, 35, root));
//        items.add(new MenuItem("Sound: On", Color.rgb(255,216,121),gc, "emulogic.ttf",970, 895, 35, root));
//        items.add(new MenuItem("Exit", Color.rgb(255,216,121),gc, "emulogic.ttf",970, 995, 35, root));
        items.get(4).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (Main.getPlayer().isMute() == false){
                    Main.getPlayer().setMute(true);
                    items.get(4).removeFromView(root);
                } else{
                    Main.getPlayer().setMute(false);
                }
            }
        });

        items.get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (Main.getPlayer().isMute() == false){
                    Main.getPlayer().setMute(true);
                } else{
                    Main.getPlayer().setMute(false);
                    items.get(4).addToView(root);
                }
            }
        });

        items.get(5).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main.setStatus(Status.CLOSE);
            }
        });
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public boolean isArrowBlink() {
        return arrowBlink;
    }

    public void setArrowBlink(boolean arrowBlink) {
        this.arrowBlink = arrowBlink;
    }
}
