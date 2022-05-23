public class SummarizeThread extends Thread {

    private int[] currCouple;//currently handled couple
    private PoolMonitor monitor;//the monitor

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
            currCouple = monitor.getCouple(); //always asks for a couple of numbers to sum up and send back to the monitor
        }
    }
}
