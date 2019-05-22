package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

import java.awt.*;


public class Main extends Application {

    private static double w = 1366, h = 1100;
    private Group root;
    private Scene scene;
    private static Status status = Status.MENU;
    private MouseEvent mouse;
    private KeyEvent key;

    public static Status getStatus() {
        return status;
    }

    public static void setStatus(Status status) {
        Main.status = status;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
//        primaryStage.setTitle("Hello World");
//
//        Canvas canvas = new Canvas(300,275);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        Image backGround = new Image("resources/mainImgPacMath.jpg");
//
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        root = new Group();
        scene = new Scene(root, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pac-Math");
        Canvas canvas = new Canvas(w,h);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(0, canvas);
        Menu menu = new Menu(gc, status);
        Game gameScreen = new Game(gc, status);

        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouse = mouseEvent;
                System.out.println(mouseEvent.getX()+ " "+mouseEvent.getY()+" "+mouseEvent.getEventType().getName());
            }
        });

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouse = mouseEvent;
                System.out.println(mouseEvent.getX()+ " "+mouseEvent.getY()+" "+mouseEvent.getEventType().getName());
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                key = keyEvent;
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                key = null;
            }
        });


        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
            gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

            if( status == Status.MENU) {
                menu.drawing(mouse, key, root);
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (status == Status.GAME) {
                        gameScreen.drawing(mouse, key,root);
                        primaryStage.setFullScreenExitHint(""); // deixa vazia a mensagem "clique esc para sair do modo tela cheia
                        primaryStage.setFullScreen(false);


            } else if (status == Status.GAMEOVER) {         //manda pra tela de input e em seguida ranking

            } else if (status == Status.INSTRUCTIONS) {

            } else if ( status == Status.RANKING) {

            }


                            }
        };

        gameLoop.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
