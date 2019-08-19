package game;

import javafx.scene.canvas.GraphicsContext;

public class Tunnel {
    private ColRect[] tunnelRect;
    private GraphicsContext gc;

    public Tunnel (GraphicsContext gc){
        this.gc = gc;
        this.tunnelRect = generateTunnelRects();
    }

    public ColRect[] getTunnelRect() {
        return tunnelRect;
    }

    public ColRect [] generateTunnelRects () {
        ColRect [] rects = new ColRect[2];

        rects[0] = new ColRect(0, 475,37,90);
        rects[1] = new ColRect(1075, 475,100,90);

        return rects;
    }
}
