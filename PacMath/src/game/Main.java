package game;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    private static double w = 1900, h = 1100;
    private Group root;
    private Scene scene;
    private static Status status = Status.MENU;
    private MouseEvent mouse;
    public static Menu menu;
    private KeyEvent key;
    private boolean keyPressed = false;
    private Timeline timeline;
    private AnimationTimer timer;
    private static MediaPlayer player;
    private static Media media;
    private int numbersEaten = 0, extra = 0;

//    private static Media media2 = new Media("file:///home/hildemir/IdeaProjects/PacMath/src/resources/pacmandeath.mp3");
//    private static MediaPlayer player2 = new MediaPlayer(media);


    public static Status getStatus() {
        return status;
    }

    public static void setStatus(Status status) {
        Main.status = status;
    }

    public int getNumbersEaten() {
        return numbersEaten;
    }

    public void setNumbersEaten(int numbersEaten) {
        this.numbersEaten = numbersEaten;
    }

    public static MediaPlayer getPlayer() {
        return player;
    }

    public static void musicaMenu(String arquivo) {
        media = new Media("file://" + arquivo);
        player = new MediaPlayer(media);
        player.setCycleCount(Timeline.INDEFINITE);            //Timeline.INDEFINITE para rodar musica infinitas vezes
        player.play();
    }

    public static void musicaGameover(String arquivo) {
            media = new Media("file://" + arquivo);
            player = new MediaPlayer(media);
            //player.setCycleCount(2);            //Timeline.INDEFINITE para rodar musica infinitas vezes

            player.stop();
            player.play();
    }

    public static void somComeNumero() {
        media = new Media("file://" + "/home/hildemir/IdeaProjects/PacMath/src/resources/pacman_eatfruit.mp3");
        player = new MediaPlayer(media);
        //player.setCycleCount(2);            //Timeline.INDEFINITE para rodar musica infinitas vezes
        player.play();

    }

    public static void playWrongSong() {
        media = new Media("file://" + "/home/hildemir/IdeaProjects/PacMath/src/resources/Wrong.mp3");
        player = new MediaPlayer(media);
        //player.setCycleCount(2);            //Timeline.INDEFINITE para rodar musica infinitas vezes
        player.play();
    }
    public static void playCorrectSong() {
        media = new Media("file://" + "/home/hildemir/IdeaProjects/PacMath/src/resources/Correct.mp3");
        player = new MediaPlayer(media);
        //player.setCycleCount(2);            //Timeline.INDEFINITE para rodar musica infinitas vezes
        player.play();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("\033[0;0;32m\033[38;2;32;34;200m"); // set caracteres do terminal para cor azul
        root = new Group();
        scene = new Scene(root, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pac-Math");
        Canvas canvas = new Canvas(w, h);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(0, canvas);
        menu = new Menu(gc, status, root);
        Game gameScreen = new Game(gc, status, root);
        GameOver gameOver = new GameOver(gc);


        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouse = mouseEvent;
                System.out.println(mouseEvent.getX() + " " + mouseEvent.getY() + " " + mouseEvent.getEventType().getName());
            }
        });

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouse = mouseEvent;
//                System.out.println(mouseEvent.getX() + " " + mouseEvent.getY() + " " + mouseEvent.getEventType().getName());
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SPACE) {
                    if (!keyPressed) {
                        key = keyEvent;
                        keyPressed = true;
                    }
                } else {
                    key = keyEvent;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                key = null;
                if (keyEvent.getCode() == KeyCode.SPACE)
                    keyPressed = false;
            }
        });


        AnimationTimer gameLoop = new AnimationTimer() {


            @Override
            public void handle(long l) {


                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                if (status == Status.MENU) {
                    primaryStage.setFullScreenExitHint(""); // deixa vazia a mensagem "clique esc para sair do modo tela cheia
                    primaryStage.setFullScreen(true);
                    menu.drawing(mouse, key, root);
                    if (mouse != null) {                //reseta o valor do mouse e assim qaundo o status for menu novamnete, ele vai ter q esperar outro clique
                        mouse = null;
                    }
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (status == Status.GAME) {

//                    ========================================RESET-GAMEOVER-SREEN=============================================
                    gameOver.setPressOneMore(0);
                    gameOver.setLove(false);
//                    =========================================================================================================
                    primaryStage.setFullScreenExitHint(""); // deixa vazia a mensagem "clique esc para sair do modo tela cheia
                    primaryStage.setFullScreen(true);
                    gameScreen.drawing(mouse, key, root);
                    menu.setArrowBlink(true);
                    if(menu.isArrowBlink()){
                        gameScreen.setCont(5);
                    } else {
                        gameScreen.setCont(0);
                    }



                    // verificaçao de colisao no movimento do teclado
                    if (key != null) {
                        for (ColRect r : gameScreen.getRectangles()) {                                                  //checa se colidiu com algum muro
                            if (r.checkCollision(gameScreen.getPacMan()) && key.getCode() == KeyCode.UP) {
                                // gc.fillText(String.valueOf("True"), 200, 50);
                                gameScreen.getPacMan().setPosY(gameScreen.getPacMan().getPosY() + 2);
                            } else if (r.checkCollision(gameScreen.getPacMan()) && key.getCode() == KeyCode.DOWN) {
                                //gc.fillText(String.valueOf("True"), 200, 50);
                                gameScreen.getPacMan().setPosY(gameScreen.getPacMan().getPosY() - 2);
                            } else if (r.checkCollision(gameScreen.getPacMan()) && key.getCode() == KeyCode.LEFT) {
                                // gc.fillText(String.valueOf("True"), 200, 50);
                                gameScreen.getPacMan().setPosX(gameScreen.getPacMan().getPosX() + 2);
                            } else if (r.checkCollision(gameScreen.getPacMan()) && key.getCode() == KeyCode.RIGHT) {
                                //gc.fillText(String.valueOf("True"), 200, 50);
                                gameScreen.getPacMan().setPosX(gameScreen.getPacMan().getPosX() - 2);
                            }
                        }

                        for (Element2D e : gameScreen.getNumMaze()) {             //checa colisao com numeros "come-los"
                            if (e.checkCollision(gameScreen.getPacMan())) {
                                if (e.getColorNumber() == Color.DEEPPINK) {
                                    e.SetIsVisible(false);
                                    somComeNumero();
                                    numbersEaten = numbersEaten + e.getNum();
//                                    extra += 250;
                                    System.out.println("num1 " + gameScreen.getExpression().getNum1());
                                    System.out.println("num2 " + gameScreen.getExpression().getNum2());
                                    System.out.println("operacao" + gameScreen.getExpression().getOpearcao());
                                    System.out.println("Expressao " + gameScreen.getExpression().getExpressao());
                                    System.out.println("resultado " + gameScreen.getExpression().getResultado());



                                }
                            }
                            e.setBuffer(numbersEaten);          //manda o valor do buffer para ser desenhado pelo drawing da gameScreen
                            if (key.getCode() == KeyCode.SPACE /*&& key.getEventType().equals(KeyEvent.KEY_PRESSED)*/) {
                                key = null;
                                if (gameScreen.getExpression().getResultado() != numbersEaten) {
                                    playWrongSong();
                                    gameScreen.getTimeBar().removeSeconds(3);               //remove 3 segundos caso a resposta esteja errada
                                    gameScreen.drawing(mouse, key, root);
                                    gameScreen.getPointsData().setPoints(-250);
                                    numbersEaten = 0;
                                    gameScreen.getPacMan().setPosX(555);
                                    gameScreen.getPacMan().setPosY(590);
                                    gameScreen.nextExpression(mouse, key, root);
                                    gameScreen.getPacMan().setMoving(true);
                                    gameScreen.ghosts[0].setMoving(true);
                                    gameScreen.ghosts[1].setMoving(true);
                                    gameScreen.ghosts[2].setMoving(true);
                                    gameScreen.ghosts[3].setMoving(true);
                                    gameScreen.setMessageVisibility(false);
                                } else {
                                    playCorrectSong();
                                    gameScreen.getTimeBar().earnSeconds(5);
                                    System.out.println("pontos ante:" + gameScreen.getPointsData().getPoints());
                                    gameScreen.getPointsData().setPoints(250);      // para os números
                                    System.out.println("Pontos depois " + gameScreen.getPointsData().getPoints());
//                                if(extra == 500) {
                                    gameScreen.getPacMan().setPosX(555);
                                    gameScreen.getPacMan().setPosY(590);
                                    gameScreen.nextExpression(mouse, key, root);
                                    gameScreen.getPacMan().setMoving(true);
                                    gameScreen.ghosts[0].setMoving(true);
                                    gameScreen.ghosts[1].setMoving(true);
                                    gameScreen.ghosts[2].setMoving(true);
                                    gameScreen.ghosts[3].setMoving(true);
                                    gameScreen.setMessageVisibility(false);
                                    numbersEaten = 0;
//                                extra = 0;
                                }
                            }
                        }
                    }
                    if (gameScreen.getTimeBar().getTime() == 60) {
                        gameOver.setPlay(true);
                    }


                } else if (status == Status.GAMEOVER) {         //manda pra tela de input e em seguida ranking
                    //====================================================RESET-GAME-SCREEN======================================================================================
                    //Desfazer o que foi feito na tela gameScreen
                    for (Element2D e : gameScreen.getNumMaze()) {             //  for each para tornar visivel numeros ja capturados
                        if (e.getColorNumber() == Color.BLACK) {
                            e.SetIsVisible(true);
                            numbersEaten = 0;                               //e zerar buffer
                            e.setBuffer(numbersEaten);
                        }
                        gameScreen.getPacMan().setPosX(555);
                        gameScreen.getPacMan().setPosY(590);
                        gameScreen.nextExpression(mouse, key, root);
                        gameScreen.getPointsData().resetPoints(0);
                        gameScreen.getTimeBar().setTime(90);                //reset timer value
                        gameScreen.setMessageVisibility(true);              //reset message visibility
                    }
                    //reset valor do buffer
//                        gameScreen.getExpression().setExpression();                 // gera nova expressao e possiveis respostas
//                        gameScreen.resetList();
                    //===========================================================================================================================================

                    gameOver.drawing(mouse, key, root);
//                    gameOver.setPlay(false);
//                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.05), evt -> space.setVisible(false, gc)),
//                                new KeyFrame(Duration.seconds( 0.1), evt -> space.setVisible(true, gc)));
//                        timeline.setCycleCount(Animation.INDEFINITE);
//                        timeline.play();
//
                    //}
                } else if (status == Status.INSTRUCTIONS) {

                } else if (status == Status.RANKING) {

                } else if (status == Status.CLOSE) {
                    primaryStage.close();
                }


            }
        };

        gameLoop.start();
        /*gameScreen.getPacMan().setPosX(gameScreen.getPacMan().getPosX() - 2);*/

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
