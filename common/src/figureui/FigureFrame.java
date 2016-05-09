package figureui;

import figure.Circle;
import figure.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FigureFrame extends JFrame {
    FigureCanvas canvas = new FigureCanvas();

    public FigureFrame() throws HeadlessException {
        super("Figure");

        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton start = new JButton("start");
        JButton stop = new JButton("stop");
        JButton removeButton = new JButton("Remove");
        JButton  pauseButton = new JButton("pause");
        JButton continueButton =new JButton("continue");


        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startActionPerformed(e);
            }

        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopActionPerformed(e);
            }

        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addActionPerformed(e);
            }

        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeActionPerformed(e);
            }
        });

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueActionPerformed(e);
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                pauseActionPerformed(e);
            }
        });

        controlPanel.add(addButton);
        controlPanel.add(start);
        controlPanel.add(stop);
        controlPanel.add(removeButton);
        controlPanel.add(pauseButton);
        controlPanel.add(continueButton);

        add(controlPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        setSize(450, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addActionPerformed (ActionEvent e){
        canvas.add(new Rectangle(20,30, 50, 60, canvas, Color.MAGENTA));
        canvas.add(new Circle(100,100, 30, canvas));
    }

    private void startActionPerformed(ActionEvent e){
        canvas.start();
    }

    private void removeActionPerformed (ActionEvent e){
        canvas.remove();
    }

    private void pauseActionPerformed (ActionEvent e){
        canvas.pause();
    }

    private void stopActionPerformed (ActionEvent e){
        canvas.stop();
    }


    private void continueActionPerformed (ActionEvent e){
        canvas.continueRun();
    }

    public static void main(String[] args) {
        new FigureFrame();
    }


}
