package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

import java.awt.*;


public class Main extends Application {

    private static double w = 1366, h = 1100;
    private Group root;
    private Scene scene;
    private static Status status = Status.GAME;
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

        root = new Group();
        scene = new Scene(root, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pac-Math");
        Canvas canvas = new Canvas(w,h);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(0, canvas);
        Menu menu = new Menu(gc, status, root);
        Game gameScreen = new Game(gc, status, root);

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
//                        boolean flag = gameScreen.collisionDetection(key); // a ideia eh colocar a linha anterior aqui dentro para ele redesenhar o pacman a cada eevento do pacman

                for (Ghost g: gameScreen.ghost) {
                    g.draw(gc);
                    g.move();
                }
                // colar a verificaçao de colisao no movimento do teclar (by shido)
                    for(ColRect r : gameScreen.getRectangles()) {
                        if (r.checkCollision(gameScreen.getPacMan()) && key.getCode() == KeyCode.UP) {
                            gc.fillText(String.valueOf("True"), 200, 50);
                            gameScreen.getPacMan().setPosY(gameScreen.getPacMan().getPosY()+2);
                        } else if (r.checkCollision(gameScreen.getPacMan()) && key.getCode() == KeyCode.DOWN){
                            gc.fillText(String.valueOf("True"), 200, 50);
                            gameScreen.getPacMan().setPosY(gameScreen.getPacMan().getPosY()-2);
                        } else if (r.checkCollision(gameScreen.getPacMan()) && key.getCode() == KeyCode.LEFT) {
                            gc.fillText(String.valueOf("True"), 200, 50);
                            gameScreen.getPacMan().setPosX(gameScreen.getPacMan().getPosX()+2);
                        } else if(r.checkCollision(gameScreen.getPacMan()) && key.getCode() == KeyCode.RIGHT){
                            gc.fillText(String.valueOf("True"), 200, 50);
                            gameScreen.getPacMan().setPosX(gameScreen.getPacMan().getPosX()-2);
                        }
                    }

                    primaryStage.setFullScreenExitHint(""); // deixa vazia a mensagem "clique esc para sair do modo tela cheia
                    primaryStage.setFullScreen(true);
                    //98.0 82.0 MOUSE_MOVED
                    //1017.0 1018.0 MOUSE_CLICKED


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
