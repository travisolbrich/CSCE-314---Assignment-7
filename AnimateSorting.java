/*
 * CSCE 314 Assignment 7
 * 
 */  

 
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import java.util.Collections;

/**
 * AnimateSorting is the master class that controls the individual sorting threads.
 * After taking the user's vector size preference, control is handed to the individual
 * threads which handle the rest of the program.
 *
 * @author  Travis Olbrich
 */
public class AnimateSorting extends JFrame{
    public static void main(String args[]) {
        Vector toSort = new Vector();
        int max = 50;

        try{
            max = Integer.valueOf(args[0]);
        } catch (Exception  e){
            System.out.println("You didn't enter a valid value, so the default vector size of 50 will be used.");
        }


        // Create and sort the vector according to user input
        for (int i = 1; i <= max; i++){
            toSort.add(i);
        }

        //Randomize the locations of the data to sort
        Collections.shuffle(toSort);

        SortThread bubble = new SortThread("bubble", toSort);
        SortThread quick = new SortThread("quick", toSort);        
    }


    /**
     * Each SortThread is the generic structure that handles each individual sorting 
     * algorithm. Each individual graphical window is created here and the sorting
     * algorithms are called from here.
     *
     * @author  Travis Olbrich
     */
    static class SortThread implements Runnable {
        Thread t;
        String algorithm;
        Vector toSort;

        SortThread(String a, Vector s) {
            algorithm = a;
            toSort = s;

            t = new Thread(this, algorithm);
            t.start(); 
        }
       
        public void run() {
            try {
                JFrame frame = new JFrame(algorithm);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         
                JPanel panel = new animate(toSort);

                frame.add(panel);
         
                //Display the window.
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);

                t.sleep(2000);

                frame.remove(panel);

                frame.setVisible(true);

            } catch (Exception e) {
            }
        }
    }

    
    static class animate extends JPanel{
        Vector toSort;

        public animate(Vector s){
            toSort = s;
            setPreferredSize(new Dimension(toSort.size()*3+5, toSort.size()*2));
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.blue);
            g2.setStroke(new BasicStroke(2));

            int maxy = toSort.size();

            for (int i = 0; i < toSort.size(); i++){
                Line2D line = new Line2D.Double(i*3+4, maxy*2, i*3+4, maxy*1.4 - (int)toSort.get(i));
                g2.draw(line);
            }
        }
    }
}


