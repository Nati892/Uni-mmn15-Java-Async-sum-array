public class SummarizeThread extends Thread {

    private int[] currCouple;
    private PoolMonitor monitor;

    public SummarizeThread(PoolMonitor monitor) {
        this.monitor = monitor;
        currCouple = new int[2];
    }

    @Override
    public void run() {
        super.run();
        currCouple = monitor.getCouple();
        while (currCouple != null) {
            monitor.giveBackResult(currCouple[0]+currCouple[1]);
            currCouple = monitor.getCouple();
        }
    }
}
