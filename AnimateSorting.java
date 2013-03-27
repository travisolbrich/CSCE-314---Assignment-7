/*
 * CSCE 314 Assignment 7
 * 
 */  

/**
 * AnimateSorting is the master class that controls the individual sorting threads.
 * After taking the user's vector size preference, control is handed to the individual
 * threads which handle the rest of the program.
 *
 * @author  Travis Olbrich
 * 
 * @param   args    the first integer in args is the preferred vector size
 */
public class AnimateSorting {
    public static void main(String args[]) {
        new SortThread("bubble");
        new SortThread("quick");
        
        System.out.println("[DEBUG] Main thread exiting");
    }


    /**
     * Each SortThread is the generic structure that handles each individual sorting 
     * algorithm. Each individual graphical window is created here and the sorting
     * algorithms are called from here.
     *
     * @author  Travis Olbrich
     *
     * @param   algorithm   the name of the algorithm we would like to run
     * @param   items       the vector of integer numbers to sort
     */
    static class SortThread implements Runnable {
        Thread t;

        SortThread(String algorithm) {
            t = new Thread(this, "Sorting Thread (" + algorithm + ")");
            System.out.println("[DEBUG] Child thread: " + t);
            t.start(); 
        }
       
        public void run() {
            try {
                for(int i = 5; i > 0; i--) {
                    System.out.println("[DEBUG] Child Thread (" + t + "): " + i);
                
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("[DEBUG] Thread interrupted.");
            }
            System.out.println("[DEBUG] Exiting child thread (" + t + ").");
        }
    }
}


