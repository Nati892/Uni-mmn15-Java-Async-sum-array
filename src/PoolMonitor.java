import java.util.ArrayList;

public class PoolMonitor {
    private ArrayList<Integer> intPool;
    private int RunningThreads;

    public PoolMonitor(int[] arr) {
        intPool = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            intPool.add(arr[i]);
        }
        RunningThreads=0;
    }


    public synchronized int[] getCouple() {
        while (intPool.size() < 2 && RunningThreads>0)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if ((RunningThreads==0 && intPool.size()<2) ){
            return null;
        }
        RunningThreads++;
        int[] returnedArr=new int[2];
        returnedArr[0]= intPool.remove(0);
        returnedArr[1]= intPool.remove(0);
        return returnedArr;
    }


    public synchronized void giveBackResult(int res) {
        intPool.add(res);
        RunningThreads--;
        notifyAll();
    }

    public synchronized int getSum() {
        while (intPool.size() != 1 || RunningThreads!=0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return intPool.get(0);
    }
}


