import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Coded by Seong Chee Ken on 02/07/2017, 02:09.
 * Consumer runnable which starts aircraft threads from queue.
 */
public class Consumer implements Runnable {
    private BlockingQueue<Aircraft> queue;

    Consumer(BlockingQueue<Aircraft> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            try {
                Aircraft aircraft = queue.take();
                count++;
                aircraft.start();
                Thread.sleep(ThreadLocalRandom.current().nextInt(1500, 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 99)
                break; //not gonna use if queue.isEmpty(), because consuming CAN be faster than producing
        }
    }
}
