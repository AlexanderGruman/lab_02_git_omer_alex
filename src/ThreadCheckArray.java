import java.util.ArrayList;

/**
 * This class implements the Runnable interface to check an array of integers
 * for subsets that sum to a given value using multiple threads.
 */
public class ThreadCheckArray implements Runnable {

    private boolean flag;
    private boolean[] winArray;
    SharedData sd;
    ArrayList<Integer> array;
    int b;

    /**
     * Constructor for the ThreadCheckArray class.
     *
     * @param sd the shared data object containing the array and the target sum.
     */
    public ThreadCheckArray(SharedData sd) {
        this.sd = sd;
        synchronized (sd) {
            array = sd.getArray();
            b = sd.getB();
        }
        winArray = new boolean[array.size()];
    }

    /**
     * A recursive function to check subsets of the array for a sum equal to b.
     *
     * @param n the current index in the array.
     * @param b the current target sum.
     */
    void rec(int n, int b) {
        synchronized (sd) {
            if (sd.getFlag()) {
                return;
            }
        }
        if (n == 1) {
            if (b == 0 || b == array.get(n - 1)) {
                flag = true;
                synchronized (sd) {
                    sd.setFlag(true);
                }
            }
            if (b == array.get(n - 1)) {
                winArray[n - 1] = true;
            }
            return;
        }

        rec(n - 1, b - array.get(n - 1));
        if (flag) {
            winArray[n - 1] = true;
        }
        synchronized (sd) {
            if (sd.getFlag()) {
                return;
            }
        }
        rec(n - 1, b);
    }

    /**
     * The run method to start the thread execution.
     */
    public void run() {
        if (array.size() != 1) {
            if (Thread.currentThread().getName().equals("thread1")) {
                rec(array.size() - 1, b - array.get(array.size() - 1));
            } else {
                rec(array.size() - 1, b);
            }
        }
        if (array.size() == 1) {
            if (b == array.get(0) && !flag) {
                winArray[0] = true;
                flag = true;
                synchronized (sd) {
                    sd.setFlag(true);
                }
            }
        }
        if (flag) {
            if (Thread.currentThread().getName().equals("thread1")) {
                winArray[array.size() - 1] = true;
            }
            synchronized (sd) {
                sd.setWinArray(winArray);
            }
        }
    }
}
