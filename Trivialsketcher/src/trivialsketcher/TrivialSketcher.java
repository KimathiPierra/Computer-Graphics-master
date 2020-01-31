/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivialsketcher;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This is an extremely scaled-down sketching canvas; with it you
 * can only scribble thin black lines.  For simplicity the window
 * contents are never refreshed when they are uncovered.
 */

public class TrivialSketcher extends JPanel {

    /**
     * Keeps track of the last point to draw the next line from.
     */
    private ArrayList<ArrayList<Point>> trial = new ArrayList<>();

    /**
     * Constructs a panel, registering listeners for the mouse.
     */
    public TrivialSketcher() {
        // When the mouse button goes down, set the current point
        // to the location at which the mouse was pressed.
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                ArrayList<Point> lastPoint = new ArrayList<Point>();
                lastPoint.add(new Point(e.getX(), e.getY()));
                trial.add(lastPoint);
            }
        });

        // When the mouse is dragged, draw a line from the old point
        // to the new point and update the value of lastPoint to hold
        // the new current point.
      addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Graphics g = getGraphics();
                int last_NO = trial.size();
                int last_point = trial.get(last_NO-1).size();
                trial.get(last_NO-1).add(new Point(e.getX(), e.getY()));
                g.drawLine( trial.get(last_NO-1).get(last_point - 1).x,  trial.get(last_NO-1).get(last_point -1).y,
                        e.getX(), e.getY());
                g.dispose();
            }
        });
    }
       @Override
    public void paint(Graphics g) {

        for (int y = 0; y < trial.size(); y++) {

            int i = 0;
            for (int j = 1; j < trial.get(y).size(); j++) {

                g.drawLine(trial.get(y).get(i).x, trial.get(y).get(i).y,
                        trial.get(y).get(j).x, trial.get(y).get(j).y);

                i++;
            
        }
    }
    }
    /**
     * A tester method that embeds the panel in a frame so you can
     * run it as an application.
     */

    /**
     * A tester method that embeds the panel in a frame so you can
 run it as an application.
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Sketching Program");
        frame.getContentPane().add(new TrivialSketcher(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
        
    }
}