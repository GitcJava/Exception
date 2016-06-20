package hanotowers;

import figure.Figure;

import java.awt.*;
import java.util.ArrayList;

public class Tower extends Figure {

//    field declaration region
    private ArrayList<Disc> discList = new ArrayList<>();

    private int footerHeight;
    private int poleWidth;
    private int poleX;
    private int footerY;

    private Disc lastDisc;

    public Tower(int x, int y, int width, int height, Color color, HanoCanvas hanoCanvas) {
        super(x, y, width, height, hanoCanvas, color);
        init();
    }

    public Tower(int x, int y, int width, int height, HanoCanvas hanoCanvas) {
        this(x, y, width, height, Color.RED, hanoCanvas);
    }

    private void init (){
        footerHeight = 10;
        poleWidth = 10;
        poleX = (getX() + (getWidth() - poleWidth) / 2);
        footerY = getY() + getHeight() - footerHeight;
    }


    public int getFooterY() {
        return footerY;
    }

    public int getFooterHeight() {
        return footerHeight;
    }

    public int getDiscListSize() {
        return discList.size();
    }

    public int getLastDiscX() {
        return discList.get(discList.size() - 1).getX();
    }

    public int getLastDiscY() {
        return discList.get(discList.size() - 1).getY();
    }

    public int getDiscsHeight() {
        return discList.get(discList.size() - 1).getHeight();
    }

    public int getLastDiscWidth() {
        return discList.get(discList.size() - 1).getWidth();
    }

    public void moveLastDisc(int dX, int dY) {
        discList.get(discList.size() - 1).move(dX, dY);
    }

    public boolean addDisc(Disc disc) {
        return discList.add(disc);
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        poleX = (getX() + (getWidth() - poleWidth) / 2);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        footerY = getY() + getHeight() - footerHeight;
    }

    public boolean moveTo(Tower to) {

        return to.addDisc(discList.remove(discList.size() - 1));
    }

    @Override
    public void move(int dX, int dY) {
        super.move(dX, dY);
        setX(getX());
        setY(getY());
        for (Figure disc : discList){
            disc.move(dX, dY);
        }
    }

    @Override
    public boolean isBelong(int x, int y) {
        for (Disc current : discList) {
            if (current.isBelong(x, y)) {
                return true;
            }
        }
        return x >= getX() && x <= getX() + getWidth() && y >= footerY && y <= footerY + footerHeight
                || x >= poleX && x <= poleX + poleWidth && y >= getY() && y <= getY() + getHeight();
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(getX(), getY() + getHeight() - footerHeight, getWidth(), footerHeight);
        g.fillRect((getX() + (getWidth() - poleWidth) / 2), getY(), poleWidth, getHeight());
        for (Disc disc : discList) {
            disc.draw(g);
        }
    }
}


