package HanoiTowers;



import java.awt.*;
import figure.FigureCanvas;

public class HanoCanvas extends FigureCanvas {

    public Tower first = new Tower(30, 170, this);
    public Tower middle = new Tower(first.getX() + Tower.horWidth + 30, 170, this);
    public Tower end = new Tower(middle.getX() + Tower.horWidth + 30, 170, this);

    private Tower selected;
    boolean isSelected;


    public HanoCanvas() {
    }

    private void select(int x, int y) {
        isSelected = false;
        if (first.isBelong(x, y)) {
            selected = first;
            isSelected = true;
        } else if (middle.isBelong(x, y)) {
            selected = middle;
            isSelected = true;
        } else if (end.isBelong(x, y)) {
            selected = end;
            isSelected = true;
        } else {
            isSelected = false;
        }
    }

    public void load(int n) {
    }

    public void paint(Graphics g) {
    }

}
