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
    Disc curr;


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
        moveDiscs(first.getDiscList().size(), first, middle, end);
    }



    private void moveDiscs(int number, Tower first, Tower middle, Tower end) {
        if (number == 1) {
            move(first, middle, end);
        } else {
            moveDiscs(number - 1, first, end, middle);
            move(first, middle, end);
            moveDiscs(number - 1, middle, first, end);
        }
    }

    private void move(Tower first, Tower middle, Tower end) {
        up(first, middle, end);
        update(getGraphics());
    }


    public void up(Tower first, Tower middle, Tower end) {
        curr = first.getDiscList().get(first.getDiscList().size() - 1);
        end.getDiscList().add(first.getDiscList().get(first.getDiscList().size() - 1));
        first.getDiscList().remove(first.getDiscList().get(first.getDiscList().size() - 1));
        while (curr.getY() > first.getY() - curr.getHeight()) {
            curr.move(0, -1);
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (first.getX() > end.getX()) {
            left(first, middle, end);
        } else {
            right(first, middle, end);
        }
    }


    public void right(Tower first, Tower middle, Tower end) {
        while (curr.getX() < end.getX() + ((end.getWidth() - curr.getWidth()) / 2)) {
            curr.move(1, 0);
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        down(first, middle, end);
    }


    public void down(Tower first, Tower middle, Tower end) {
        while (curr.getY() < end.getY() + end.getHeight() - Tower.horHeight - end.getDiscList().size() * (8+1)) {
            curr.move(0, 1);
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void left(Tower first, Tower middle, Tower end) {
        while (curr.getX() > end.getX() + ((end.getWidth() - curr.getWidth()) / 2)) {
            curr.move(-1, 0);
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        down(first, middle, end);
    }
}
