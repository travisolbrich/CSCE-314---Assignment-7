/*
 * CSCE 314 Assignment 7
 * 
 */  

 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

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
         
                JLabel emptyLabel = new JLabel(t.toString());
                emptyLabel.setPreferredSize(new Dimension(175, 100));
                frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
         
                //Display the window.
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);

            } catch (Exception e) {
            }
        }
    }

    
}


