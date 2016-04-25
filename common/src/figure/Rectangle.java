package figure;

import figureui.FigureCanvas;

import java.awt.*;

public class Rectangle extends Figure {

    public Rectangle(int x, int y, int width, int height, FigureCanvas canvas) {
        this(x, y, width, height, canvas, Color.green);
    }

    public Rectangle(int x, int y, int width, int height, FigureCanvas canvas, Color color) {
        super(x, y, width, height, canvas, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public boolean isBelong(int x, int y) {

        return x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight();
    }


}
