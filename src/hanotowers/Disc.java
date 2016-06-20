package hanotowers;

import figure.Rectangle;

import java.awt.*;

public class Disc extends Rectangle {

    private Tower tower;

    public Disc(int x, int y, int width, int height, Color color, HanoCanvas hanoCanvas){
        super(x, y, width, height, hanoCanvas, color);
    }

    public Disc(int x, int y, int width, int height, HanoCanvas hanoCanvas){
        this(x, y, width, height, Color.darkGray, hanoCanvas);
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }
}
