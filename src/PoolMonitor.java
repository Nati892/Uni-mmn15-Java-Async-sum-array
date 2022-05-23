import java.util.ArrayList;

public class PoolMonitor {
    private ArrayList<Integer> intPool;
    private int RunningThreads;

    public PoolMonitor(int[] arr) {
        intPool = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {//initialize the arraylist with the array
            intPool.add(arr[i]);
        }
        RunningThreads = 0;
    }

    /**
     * a synchronized function for getting a couple of numbers from the pool of numbers
     *
     * @return an array of 2 ints popped from the pool
     */
    public synchronized int[] getCouple() {
        while (intPool.size() < 2 && RunningThreads > 0) {//while note done calculating
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //if there are no more numbers to receive from any thread and the pool has only 1 number then it releases the threads
        if ((RunningThreads == 0 && intPool.size() < 2)) {
            return null;
        }

        RunningThreads++;//count the thread
        int[] returnedArr = new int[2];//pop and return the numbers
        returnedArr[0] = intPool.remove(0);
        returnedArr[1] = intPool.remove(0);
        return returnedArr;
    }

    /**
     * synchronized function
     * when a thread is done then it returns the summed numbers through this function
     * @param res - an int to add to the pool
     */
    public synchronized void giveBackResult(int res) {
        intPool.add(res);
        RunningThreads--;
        notifyAll();//release waiting threads
    }

    /**
     * synchronized function that returns the whole array's sum
     * @return int - sum of all numbers in array
     *
     */
    public synchronized int getSum() {
        while (intPool.size() != 1 || RunningThreads != 0) {//while not done calculating then wait for result
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return intPool.get(0);
    }
}


