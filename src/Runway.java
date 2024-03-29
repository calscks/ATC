import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Coded by Seong Chee Ken on 22/06/2017, 23:03.
 * Classic runway. Once landed, runway is available for other aircrafts to land again. Same goes to taking off.
 * Second protocol: Assign paths to runways + third protocol: landing
 */
class Runway {
    int id;
    Lock lock = new ReentrantLock();
    boolean occupied = false;


    Runway(int id) {
        this.id = id;
    }

    boolean land(Aircraft aircraft) throws InterruptedException {
        if (!lock.tryLock()) {
            return false;
        } else {
            occupied = true;
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is switching frequency to 2000Hz.");
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is assigned path to runway " + id + "...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 150) * 5);
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is switching frequency to 3000Hz.");
            System.out.println("ATC is guiding Aircraft " + aircraft.getAircraftId() + " to land on runway " + id + "...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(150, 200) * 50);
            System.out.println("Aircraft " + aircraft.getAircraftId() + " landed on runway " + id + ".");
            lock.unlock();
            occupied = false;
            return true;
        }
    }

    boolean takeOff(Aircraft aircraft) throws InterruptedException {
        if (!lock.tryLock()) {
            return false;
        } else {
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is assigned to runway " + id + " for taking off...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 150) * 5);
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is taking off from runway " + id + "...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(150, 200) * 50);
            System.out.println("Aircraft " + aircraft.getAircraftId() + " took off from runway " + id + ".");
            lock.unlock();
            occupied = false;
            return true;
        }
    }

    boolean isOccupied() {
        return occupied;
    }
}
