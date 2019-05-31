package game;

public class Element2D {
    private double posX;
    private double posY;
    private double width;
    private double height;

    public Element2D() {}

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    boolean checkCollision(Element2D element) {
        // x y
        return (element.getPosX() > this.getPosX() &&
                element.getPosY() > this.getPosY() &&
                element.getPosX()  <= this.getPosX() + this.getWidth() &&
                element.getPosY() <= this.getPosY() + this.getHeight() ) ||

                // x, y + h
                (element.getPosX() > this.getPosX() &&
                        element.getPosY() + element.getHeight() > this.getPosY() &&
                        element.getPosX()  <= this.getPosX() + this.getWidth() &&
                        element.getPosY() + element.getHeight() <= this.getPosY() + this.getHeight() ) ||

                // x + w, y
                (element.getPosX() + element.getWidth() >= this.getPosX() &&
                        element.getPosY() > this.getPosY() &&
                        element.getPosX() + element.getWidth() <= this.getPosX() + this.getWidth() &&
                        element.getPosY()  <= this.getPosY() + this.getHeight() ) ||


                // x + h, y + h
                (element.getPosX() + element.getWidth() >= this.getPosX() &&
                        element.getPosY() + element.getHeight() > this.getPosY() &&
                        element.getPosX() + element.getWidth() <= this.getPosX() + this.getWidth() &&
                        element.getPosY()  + element.getHeight() <= this.getPosY() + this.getHeight() );
    }
}
