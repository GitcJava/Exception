package HanoiTowers;



import java.awt.*;

import figure.FigureCanvas;

public class HanoCanvas extends FigureCanvas implements Runnable {

    public Tower first = new Tower(45, 170, this);
    public Tower middle = new Tower(first.getX() + Tower.horWidth + 30, 170, this);
    public Tower end = new Tower(middle.getX() + Tower.horWidth + 30, 170, this);

    private boolean isStarted = false;
    private int discsNum;
    Thread t;


    public HanoCanvas() {
        setBackground(Color.CYAN);
    }

    public void load(int discsNum) {
        Clean();
        first.addDiscs(discsNum);
        repaint();
    }


    public void start() {
        t = new Thread(this);
        t.start();
        isStarted = true;
    }



    private void select(){}

    public void paint(Graphics g) {
        super.paint(g);
        first.draw(g);
        middle.draw(g);
        end.draw(g);

    }
    private void Clean() {
        isStarted = false;
        first.getDiscList().clear();
        middle.getDiscList().clear();
        end.getDiscList().clear();
    }

    @Override
    public void run() {

    }
}
