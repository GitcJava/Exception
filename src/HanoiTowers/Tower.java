package HanoiTowers;

import java.awt.*;
import java.util.ArrayList;

import figure.Figure;

public class Tower extends Figure {
    ArrayList<Disc> discList = new ArrayList<>();

    public final static int horWidth = 210;
    public final static int horHeight = 10;
    public final static int verWidth = 10;
    public final static int verHeight = 250;
    private int x;
    private int y;
    public HanoCanvas hanoCanvas;
    private Color color;

    private int xArr[];
    private int yArr[];

    public Tower(int x, int y, Color color, HanoCanvas hanoCanvas) {
        super(x, y, horWidth, verHeight + horHeight, hanoCanvas,color);
        this.x = x;
        this.y = y;
        this.color = color;
        this.hanoCanvas = hanoCanvas;

        setTowersVertices();
    }


    private void setTowersVertices(){
        xArr = new int[8];
        yArr = new int[8];

        for (int i = 0; i < 8; i++) {
            switch (i){
                case 0:
                    xArr[0] = x;
                    yArr[0] = y + verHeight;
                    break;
                case 1:
                    xArr[1] = x + (horWidth - verWidth) / 2;
                    yArr[1] = y + verHeight;
                    break;
                case 2:
                    xArr[2] = x + (horWidth - verWidth) / 2;
                    yArr[2] = y;
                    break;
                case 3:
                    xArr[3] = x + (horWidth - verWidth) / 2 + verWidth;
                    yArr[3] = y;
                    break;
                case 4:
                    xArr[4] = x + (horWidth - verWidth) / 2 + verWidth;
                    yArr[4] = y + verHeight;
                    break;
                case 5:
                    xArr[5] = x + horWidth;
                    yArr[5] = y + verHeight;
                    break;
                case 6:
                    xArr[6] = x + horWidth;
                    yArr[6] = y + verHeight + horHeight;
                    break;
                case 7:
                    xArr[7] = x;
                    yArr[7] = y + verHeight + horHeight;
                    break;
            }
        }

    }

    public Tower(int x, int y, HanoCanvas hanoCanvas) {
        super(x, y, horWidth, verHeight, hanoCanvas);
    }

    public ArrayList<Disc> getDiscList() {
        return discList;
    }

    @Override
    public void draw(Graphics g) {
        g.drawPolygon(xArr,yArr,8);
        for (Disc disc : discList) {
            disc.draw(g);
        }
    }

    @Override
    public boolean isBelong(int x, int y) {
        return  x >= xArr[0] && x <= xArr[5] && x >= xArr[7] && x <= xArr[6]
                && y >= yArr[0] && y <= yArr[7] && y >= yArr[5] && y <= yArr[6]
                ||  x >= xArr[2] && x <= xArr[3] && x >= xArr[1] && x <= xArr[4]
                && y >= yArr[2] && y <= yArr[1] && y >= yArr[3] && y <= yArr[4];
    }
}


