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
        Vector<Integer> toSort = new Vector();
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

        SortThread bubble = new SortThread("bubble", (Vector<Integer>)toSort.clone());    
        SortThread insertion = new SortThread("insertion", (Vector<Integer>)toSort.clone());
        SortThread quick = new SortThread("quick", (Vector<Integer>)toSort.clone());  
        SortThread shell = new SortThread("shell", (Vector<Integer>)toSort.clone());      
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
        int delay = 10;

        SortThread(String a, Vector s) {
            algorithm = a;
            toSort = s;

            t = new Thread(this, algorithm);
            t.start(); 
        }
       
        public void run() {
            try {
                /*
                JFrame frame = new JFrame(algorithm);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
                JPanel panel = new animate(toSort);

                frame.add(panel);
         
                //Display the window.
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);

                t.sleep(2000);
                System.out.println("After Sleep" + t.toString());

                while(true){
                    t.sleep(60);
                frame.remove(panel);

                Collections.shuffle(toSort);

                panel = new animate(toSort);
                frame.add(panel);

                frame.setFocusableWindowState(false);
                frame.setVisible(true);
                frame.setFocusableWindowState(true);
                */

                JFrame frame = new JFrame(algorithm);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel panel = new animate(toSort);
                frame.add(panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);

                t.sleep(5000);

                if(algorithm.equals("insertion"))
                    insertionSort(toSort, frame);

                if(algorithm.equals("quick"))
                    quickSort(toSort, frame);

                else if(algorithm.equals("bubble"))
                    bubbleSort(toSort, frame);

                else if(algorithm.equals("shell"))
                    shellSort(toSort, frame);

            } catch (Exception e) {

            }
        }       
        
        public Vector bubbleSort(Vector<Integer> v, JFrame frame){
            Vector <Integer> bSort = v;
            
            try{
                for(int i = 0; i < bSort.size(); ++i){
                    for (int j = 1; j < bSort.size() - i; ++j){
                        int elem = j-1;
                        int nextelem = j;

                        // Part that does the graphing
                        t.sleep(delay);
                        JPanel panel = new animate(bSort);
                        frame.add(panel);
                        frame.setFocusableWindowState(false);
                        frame.setVisible(true);
                        frame.setFocusableWindowState(true);
                        //End of part that does the graphing

                        if( bSort.get(elem) > bSort.get(nextelem)){
                            int temp1 = bSort.get(elem);
                            int temp2 = bSort.get(nextelem);
                            bSort.set(elem, temp2);
                            bSort.set(nextelem, temp1);
                        }
                    }
                }
            } catch (Exception e) {

            }

            return bSort;
        }

        public Vector shellSort(Vector<Integer> v, JFrame frame){
            Vector<Integer> sSort = v;

            try{
                int inc = sSort.size() / 2;
                while (inc > 0){

                    for ( int i = inc; i < sSort.size(); ++i){
                        int j = i;
                        int temp = sSort.get(i);
                        while (j >= inc && sSort.get(j - inc) > temp){
                    // Part that does the graphing
                    t.sleep(delay);
                    JPanel panel = new animate(sSort);
                    frame.add(panel);
                    frame.setFocusableWindowState(false);
                    frame.setVisible(true);
                    frame.setFocusableWindowState(true);
                    //End of part that does the graphing
                            sSort.set(j, sSort.get(j - inc));
                            j = j - inc;
                        }
                        sSort.set(j, temp);
                    }
                    if(inc ==2) {
                        inc = 1;
                    } else {
                        inc *= (5.0/11);
                    }
                }
            } catch (Exception e) {

            }
            return sSort;
        }

        public void insertionSort(Vector<Integer> toSort, JFrame frame){        
            try{
                for (int i = 0; i < toSort.size(); i++){
                    for (int j = i; j > 0; j--){
                        
                        // Part that does the graphing
                        t.sleep(delay);
                        JPanel panel = new animate(toSort);
                        frame.add(panel);
                        frame.setFocusableWindowState(false);
                        frame.setVisible(true);
                        frame.setFocusableWindowState(true);
                        //End of part that does the graphing

                        if((int)toSort.get(j-1)>(int)toSort.get(j)){
                            int swap = (int)toSort.get(j);
                            toSort.set(j, (int)toSort.get(j-1));
                            toSort.set(j-1, swap);
                        }
                    }
                }
            } catch (Exception e) {

            }
        }

        public Vector quickSort(Vector<Integer> v, JFrame frame){
            Vector<Integer> qSort = new Vector();
            Vector<Integer> greaterThan = new Vector();
            Vector<Integer> lessThan = new Vector();
            try{
                
                int pivotIndex = (int) Math.round(v.size() / 2);
                
                for(int i = 0; i < v.size(); ++i){
                    if(i==pivotIndex){
                        i=pivotIndex;
                    } else if(v.get(i)>=v.get(pivotIndex)){
                        greaterThan.add(v.get(i));
                    } else if (v.get(i)<v.get(pivotIndex)) {
                        lessThan.add(v.get(i));
                    }
                }
                

                if(lessThan.size()!=0){
                    qSort.addAll(quickSort(lessThan, frame));
                }
                
                qSort.add(v.get(pivotIndex));
                
                if(greaterThan.size()!=0){
                    qSort.addAll(quickSort(greaterThan, frame));
                }
                // Part that does the graphing
                t.sleep(delay);
                Vector<Integer> both = new Vector<Integer>();
                both.addAll(lessThan);
                both.addAll(greaterThan);
                JPanel panel = new animate(qSort);
                frame.add(panel);
                frame.setFocusableWindowState(false);
                frame.setVisible(true);
                frame.setFocusableWindowState(true);
                //End of part that does the graphing
            
            } catch (Exception e){

            }
            return qSort;
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


