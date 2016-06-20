package hanotowers;


import figureui.FigureCanvas;

import java.awt.*;
import java.util.ArrayList;

public class HanoCanvas extends FigureCanvas implements Runnable {

    //    region field declaration
    private Tower firstTower;
    private Tower middleTower;
    private Tower lastTower;
    private Thread t;
    private DiscMode currentMode;
    private ModeSetter modeSetter;

    private int dX;
    private int dY;
    private int interval;

    private boolean isRunning;

    public HanoCanvas() {
        setBackground(Color.CYAN);
        init();
    }

    private void init() {
        interval = 1;
        figuresLoad();
        modeSetter = new ModeSetter();
    }

    private void figuresLoad() {
        figures = new ArrayList<>(3);
        initTowers();
        figures.add(firstTower);
        figures.add(middleTower);
        figures.add(lastTower);
    }

    private void initTowers() {
        int firstTowerX = 45;
        int towerY = 170;
        int towerWidth = 210;
        int towerHeight = 260;
        int towersInterval = 30;
        firstTower = new Tower(firstTowerX, towerY, towerWidth, towerHeight, this);
        middleTower = new Tower(firstTower.getX() + firstTower.getWidth() + towersInterval, towerY, towerWidth, towerHeight, this);
        lastTower = new Tower(middleTower.getX() + middleTower.getWidth() + towersInterval, towerY, towerWidth, towerHeight, this);
    }

    public void loadDiscs(int discsCount) {
        clear();
        int discHeight = firstTower.getFooterHeight() - interval;
        int difFromFooter = firstTower.getWidth() % discsCount;
        int conditionalUnit = (firstTower.getWidth() - difFromFooter) / discsCount;
        int conditionalStartForX = firstTower.getX() + difFromFooter / 2;
        int conditionalStartForY = firstTower.getFooterY() - firstTower.getFooterHeight();

        for (int i = 0; i < discsCount; i++) {
            firstTower.addDisc(new Disc(conditionalStartForX + (i * conditionalUnit / 2), conditionalStartForY -
                    (i * (discHeight + interval)), (discsCount - i) * conditionalUnit, discHeight, this));
        }
        repaint();
    }

    private void clear() {
        isRunning = false;
        figuresLoad();
    }

    public void start() {
        if (!isRunning) {
            t = new Thread(this);
            isRunning = true;
            t.start();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        firstTower.draw(g);
        middleTower.draw(g);
        lastTower.draw(g);
    }

    @Override
    public void run() {
        moveDiscs(firstTower.getDiscListSize(), firstTower, middleTower, lastTower);
        isRunning = false;
    }

    private void moveDiscs(int number, Tower first, Tower middle, Tower last) {
        if (number == 1) {
            move(first, last);
        } else {
            moveDiscs(number - 1, first, last, middle);
            move(first, last);
            moveDiscs(number - 1, middle, first, last);
        }
    }

    private void move(Tower from, Tower to) {
        currentMode = DiscMode.UP;
        while (isRunning && currentMode != DiscMode.DONE) {
            modeSetter.setMode(from, to);
            from.moveLastDisc(dX, dY);
            repaint();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        from.moveTo(to);
    }

    private class ModeSetter {
        boolean toLeft;
        boolean toDown;

        int modul;
        int count;

        private void setMode(Tower from, Tower to) {
            switch (currentMode) {
                case UP:
                    if (from.getLastDiscY() > from.getY() - from.getDiscsHeight()) {
                        dX = 0;
                        dY = -1;
                    } else {
                        currentMode = DiscMode.TO_NEXT;

                        setXDirection(from, to);
                    }
                    break;
                case TO_NEXT:
                    if (toLeft && from.getLastDiscX() > to.getX() + (to.getWidth() - from.getLastDiscWidth()) / 2
                            || !toLeft && from.getLastDiscX() < to.getX() + (to.getWidth() - from.getLastDiscWidth()) / 2) {
                        setXDirection(from, to);
                        setYDirection();
                    } else {
                        currentMode = DiscMode.DOWN;
                    }
                    break;
                case DOWN:
                    if (from.getLastDiscY() + from.getDiscsHeight() + interval < to.getFooterY() - interval
                            - to.getDiscListSize() * (from.getDiscsHeight() + interval)) {
                        dX = 0;
                        dY = 1;
                    } else {
                        currentMode = DiscMode.DONE;
                        count = 0;
                    }
                    break;
            }
        }

        private void setYDirection() {
            if (count < modul) {
                count++;
                dY = 0;
            } else {
                count = 0;
                dY = toDown ? 1 : -1;
            }
        }

        private void setXDirection(Tower from, Tower to) {
            int xDistance = from.getX() > to.getX() ? from.getX() - to.getX() : to.getX() - from.getX();
            int yDistance = from.getY() > to.getY() ? from.getY() - to.getY() : to.getY() - from.getY();
            int minDistance = Math.min(xDistance, yDistance);
            int maxDistance = Math.max(xDistance, yDistance);
            modul = minDistance == 0 ? maxDistance : maxDistance / minDistance;
            toDown = from.getY() > to.getY() ? false : true;

            if (from.getX() < to.getX()) {
                toLeft = false;
                dY = 0;
                dX = 1;
            } else {
                toLeft = true;
                dY = 0;
                dX = -1;
            }
        }
    }

    enum DiscMode {
        UP, TO_NEXT, DOWN, DONE;
    }

}
