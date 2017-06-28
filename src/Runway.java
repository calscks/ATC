import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Coded by Seong Chee Ken on 22/06/2017, 23:03.
 */
class Runway {
    private int id;
    private Lock lock = new ReentrantLock();
    private boolean occupied = false;

    Runway(int id) {
        this.id = id;
    }

    boolean land(Aircraft aircraft) throws InterruptedException {
        if (lock.tryLock()){
            occupied = true;
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is landing on runway " + id + "...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 150) * 50);
            System.out.println("Aircraft " + aircraft.getAircraftId() + " landed on runway " + id + " and docked.");
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is disembarking passengers...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 150) * 50);
            System.out.println("Passengers are disembarked from Aircraft " + aircraft.getAircraftId() + ".");
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is refilling...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(150, 200) * 50);
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is refilled.");
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is receiving passengers...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 150) * 50);
            System.out.println("Passengers have embarked on Aircraft " + aircraft.getAircraftId() + ".");
            return true;
        } else
            occupied = false;
            return false;

    }

    void takeOff(Aircraft aircraft){
        System.out.println("Aircraft " + aircraft.getAircraftId() + " is un-docked and taking off from runway " + id + "...");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 150) * 50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Aircraft " + aircraft.getAircraftId() + " took off from runway " + id + ".");
        lock.unlock();
        occupied = false;
    }

    boolean isOccupied() {
        return occupied;
    }
}
