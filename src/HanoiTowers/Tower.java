package HanoiTowers;

import java.awt.*;
import java.util.ArrayList;
import figure.Figure;
import figure.Rectangle;


public class Tower extends Figure {
    ArrayList<Disc> discList = new ArrayList<>();

    public final static int horWidth = 210;
    public final static int horHeight = 10;
    public final static int verWidth = 10;
    public final static int verHeight = 250;
    private int x;
    private int y;
    private Rectangle horizRect;
    private Rectangle verRect;
    public HanoCanvas hanoCanvas;

    public Tower(int x, int y, HanoCanvas hanoCanvas) {
        super(x, y, horWidth, verHeight + horHeight, hanoCanvas);
        this.x = x;
        this.y = y;
        this.hanoCanvas = hanoCanvas;
        horizRect = new Rectangle(x, y + verHeight, horWidth, horHeight, hanoCanvas);
        verRect = new Rectangle(x + (horWidth - verWidth) / 2, y, verWidth, verHeight, hanoCanvas);
    }

    public ArrayList<Disc> getDiscList() {
        return discList;
    }

    @Override
    public void draw(Graphics g) {
        horizRect.draw(g);
        verRect.draw(g);
        for (Disc disc : discList) {
            disc.draw(g);
        }
    }

    @Override
    public boolean isBelong(int x, int y) {
        return  horizRect.isBelong(x,y) || verRect.isBelong(x,y);
    }
}
