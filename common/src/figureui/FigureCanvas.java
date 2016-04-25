package figureui;

import figure.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class FigureCanvas extends JPanel {
	private ArrayList<Figure> figures = new ArrayList<>();
//	private ArrayList figures = new ArrayList<>();

	private int mX;
	private int mY;

	private boolean isSelected;

	private figure.Rectangle border = new figure.Rectangle(10, 10, 300, 250, this, new Color(183, 185, 215));

	public int getRight(){
		return border.getX() + border.getWidth();
	}

	public int getLeft(){
		return border.getX();
	}

	public int getTop(){
		return border.getY();
	}

	public int getBottom(){
		return border.getY() + border.getHeight();
	}


	public FigureCanvas() {
		setBackground(Color.ORANGE);

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				mousePressedPerformed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				mouseDraggedPerformed(e);
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});

	}

	public void add(Figure figure) {
		if (figure != null) {
			figures.add(figure);

		}
		update(getGraphics());
	}

	public void remove() {
		if(isSelected){
			figures.remove(getSelected());
			update(getGraphics());
		}
	}

	public Figure getSelected() {
		if (isSelected) {
			int size = figures.size();
			return size > 0 ? (Figure)figures.get(size - 1) : null;
		}
		return null;
	}

	public void start() {
		if (isSelected) {
			getSelected().start();
		}
	}

	private void mouseDraggedPerformed(MouseEvent e) {
		if (isSelected) {
			getSelected().move(e.getX() - mX, e.getY() - mY);
			repaint();
		}
		mX = e.getX();
		mY = e.getY();
	}

	private void mousePressedPerformed(MouseEvent e) {
		mX = e.getX();
		mY = e.getY();
		select(mX, mY);
		update(getGraphics());

	}

	private void select(int x, int y) {
		int size = figures.size();
		isSelected = false;
		for (int i = size - 1; i >= 0; i--) {
			Figure f = (Figure)figures.get(i);
			if (f.isBelong(x, y)) {
				figures.remove(f);
				figures.add(f);
				isSelected = true;
				return;
			}
		}

	}

	public void pause(){
		if(isSelected){
			getSelected().pause();
		}

	}

	public void continueRun() {
		if(isSelected){
			getSelected().continueRun();
		}
	}

	public void stop() {
		if(isSelected){
			getSelected().stop();
		}
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	private Image buff;
	@Override
	public void paint(Graphics gr) {
		if(buff == null){
			buff = createImage(1200, 1200);
		}
		Graphics g = buff.getGraphics();
		g.clearRect(0, 0, 1000, 1000);

		border.setX(30);
		border.setY(30);
		border.setWidth(getWidth()-60);
		border.setHeight(getHeight()-60);
		border.draw(g);

		for (Figure figure : figures) {
			figure.draw(g);
		}

		gr.drawImage(buff, 0, 0, this);
	}

}
