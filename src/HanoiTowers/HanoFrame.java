package HanoiTowers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HanoFrame extends JFrame {

    JEditorPane text = new JEditorPane();
    HanoCanvas hanoCanvas = new HanoCanvas();
    public HanoFrame (){
        super("Hano Towers");
        JButton load = new JButton("Load");
        JButton start = new JButton("Start");
        JPanel controlPanel = new JPanel();

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadActionPerformed(e);
            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startActionPerformed(e);
            }
        });

        controlPanel.add(text);
        controlPanel.add(load);
        controlPanel.add(start);

        setSize(800,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(200,50);

        add(controlPanel, BorderLayout.NORTH);
        add(hanoCanvas,BorderLayout.CENTER);

    }
    private void loadActionPerformed(ActionEvent e){
       if(Integer.parseInt(text.getText()) > 25 || Integer.parseInt(text.getText()) < 0 ){
              JOptionPane.showMessageDialog(null,"write less than 25 or more than 0");
       }
        else { int n = Integer.parseInt(text.getText());
        hanoCanvas.load(n);
       };
    }
    private void startActionPerformed(ActionEvent e){
        hanoCanvas.start();
    }
    public static void main(String[] args) {
        new HanoFrame();
    }
}
