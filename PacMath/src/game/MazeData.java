package game;

import javafx.scene.canvas.GraphicsContext;

public class MazeData {
    //private Rectangle [] rectangles;
    private GraphicsContext gc;

    public MazeData(GraphicsContext gc) {
        this.gc = gc;
        //this.rectangles = generateMazeData();
    }

//    public Rectangle [] getMazeData() {
//        return this.rectangles;
//    }

    public ColRect[] generateMazeData() {
        return new ColRect[] {

                // PRIMEIRO QUADRANTE==================================
                new ColRect(132, 115, 106, 55), // 1
                new ColRect(317, 115, 145, 55), // 2
                new ColRect(540,30,30,140),     // 3
                new ColRect(132,251,106,30),    // 4
                new ColRect(317, 251,34,224),   // 5
                new ColRect(428,251,255,25),    // 6
                new ColRect(540,251,30,127),    // 7
                new ColRect(30,360,209,15),     // 8 passagem esquerda horizontal 1.1
                new ColRect(224,360,15,110),    // 9 passagem esquerda vertical 1.3
                new ColRect(30,460,209,15),     // 10 passagem esquerda horizontal 1.2
                new ColRect(320,348,138,30),    // 11
                new ColRect(40,25,15,340),      // 12
                new ColRect(30,25,1050,15),     // 13


                // SEGUNDO QUADRANTE==================================
                new ColRect(650, 115, 145, 55), //14
                new ColRect (872,115,106,55),   //15
                new ColRect(650,348,138,30),    //16
                new ColRect(760, 251,34,224),   //17
                new ColRect(872,251,106,27),    //18
                new ColRect(1060,25,15,340),      // 19

                new ColRect(880,360,210,15),    // 20 passagem direita horizontal 2.1
                new ColRect(872,360,15,115),    // 21 passagem direita vertical 2.3
                 new ColRect(880,460,210,15),    // 22 passagem direita horizontal 2.2




                // TERCEIRO QUADRANTE==================================

                new ColRect(595,450,86,20),   // 23
                new ColRect(665,450,20,130),  // 24
                new ColRect(430,560,254,20),  // 25
                 new ColRect(430,450,20,130), // 26
                new ColRect(430,450,86,20),   // 27

                new ColRect(880,560,210,15),  // 28 passagem direita horizontal 3.1
                new ColRect(872,560,15,115),  // 29 passagem direita vertical 3.3
                new ColRect(880,660,210,15),  // 30 passagem direita horizontal 3.2

                new ColRect(760,560,34,115),  // 31
                new ColRect(650,760,145,20),  // 32
                new ColRect(875,760,100,20),  // 33
                new ColRect(1060,670,15,390), // 34
                new ColRect(760,860,34,125),  // 35
                new ColRect(872,760,29,125),  // 36
                new ColRect(990,855,75,30),   // 37
                new ColRect(653,955,325,30),  // 38

                // QUARTO QUADRANTE==================================

                new ColRect(30,560,209,15),   // 39 passagem esquerda horizontal 4.1
                new ColRect(224,560,15,110),  // 40 passagem esquerda vertical 4.3
                new ColRect(30,660,209,15),   // 41 passagem esquerda horizontal 4.2

                new ColRect(317,560,34,115),    // 42
                new ColRect(430,660,254,15),    // 43
                new ColRect(540,660,34,120),    // 44
                new ColRect(40,670,15,390),     // 45
                new ColRect(135,760,100,20),    // 46
                new ColRect(205,760,34,125),    // 47
                new ColRect(317,760,143,20),    // 48
                new ColRect(430,860,254,25),    // 49
                new ColRect(540,860,34,125),    // 50
                new ColRect(50,855,75,30),  // 51
                new ColRect(317,860,34,125),    // 52
                new ColRect(135,955,325,30),    // 53
                new ColRect(30,1060,1050,15),     // 54
        };
    }

//    public  Rectangle[] generateMazeData() {
//
//        Rectangle[] mazeRect = new Rectangle[4];
//
//        //Spawn
//        Rectangle l1 = new Rectangle(106, 60);
//        l1.setFill(Color.RED);
//        l1.setX(132);
//        l1.setY(115);
//
//        Rectangle l2 = new Rectangle(145, 60);
//        l2.setFill(Color.RED);
//        l2.setTranslateX(316);
//        l2.setTranslateY(115);
//
//        Rectangle l3 = new Rectangle(143, 60);
//        l3.setFill(Color.RED);
//        l3.setTranslateX(650);
//        l3.setTranslateY(115);
//
//        Rectangle l4 = new Rectangle(106, 60);
//        l4.setFill(Color.RED);
//        l4.setTranslateX(872);
//        l4.setTranslateY(115);
//
//        for (int i = 0; i < mazeRect.length; i++) {
//            switch (i) {
//                case 0:
//                    mazeRect[i] = l1;
//                    break;
//                case 1:
//                    mazeRect[i] = l2;
//                    break;
//                case 2:
//                    mazeRect[i] = l3;
//                    break;
//                case 3:
//                    mazeRect[i] = l4;
//                    break;
//            }
//        }
//        return mazeRect;
//    }

}
