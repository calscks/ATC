import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Coded by Seong Chee Ken on 02/07/2017, 02:09.
 * Consumer runnable which starts aircraft threads from queue. First protocol: holding and landing assignment
 */
public class Consumer implements Runnable {
    private BlockingQueue<Aircraft> queue;
    private Airspace airspace;
    private BlockingQueue<Aircraft> holder;

    Consumer(BlockingQueue<Aircraft> queue, Airspace airspace) {
        this.queue = queue;
        this.airspace = airspace;
    }

    @Override
    public void run() {
        holder = new LinkedBlockingQueue<>(); //another blocking queue to hold aircrafts
        holdStarter();
        int count = 0;
        while (true) {
            try {
                Aircraft aircraft = queue.take();
                count++;
                System.out.println("Aircraft " + aircraft.getAircraftId() + " is entering the airspace and switching frequency to 1000Hz.");
                if (airspace.runwayFull() && aircraft.getStatus().equals(AircraftStatus.NORMAL)) {
                    System.out.println("Aircraft " + aircraft.getAircraftId() + " is assigned to hold in the airspace.");
                    holder.put(aircraft); //put into holding queue if runway's unavailable
                } else {
                    aircraft.start(); //just start if runway's available
                }
                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (queue.isEmpty())
                break;
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void holdStarter(){
        Runnable launcher = () -> { //pls use java 8 to compile
            while (true) { //runs indefinitely...
                if (!airspace.runwayFull()) {
                    try {
                        holder.take().start(); //FIFO order, who holds first is the first one to go
                        Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(launcher).start();
        //hopefully it'll run just fine. Haven't figured out how to actually stop this launcher thread lol.
    }
}
