package game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;



public class ChangeColor implements EventHandler<MouseEvent> {
    private Color color1;
    private Color color2;
    private MenuItem menuItem;

    public ChangeColor(Color color1, Color color2, MenuItem menuItem) {
        this.color1 = color1;
        this.color2 = color2;
        this.menuItem = menuItem;
    }
    @Override
    public void handle (MouseEvent event) {
        if(menuItem.getColor() == color1) {
            menuItem.setColor(color2);
        } else {
            menuItem.setColor(color1);
        }
    }
}
