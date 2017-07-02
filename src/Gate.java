import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Coded by Seong Chee Ken on 30/06/2017, 17:16.
 */
class Gate {
    private int id;
    private Lock lock = new ReentrantLock();

    Gate(int id) {
        this.id = id;
    }

    boolean dock(Aircraft aircraft) throws InterruptedException {
        if (!lock.tryLock())
            return false;
        else {
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is docking at gate " + id + ".");
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 300) * 10);
            System.out.println("Aircraft " + aircraft.getAircraftId() + " docked at gate " + id + ".");
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
        }
    }

    void undock(Aircraft aircraft) throws InterruptedException {
        System.out.println("Aircraft " + aircraft.getAircraftId() + " is undocking at gate " + id + ".");
        Thread.sleep(ThreadLocalRandom.current().nextInt(100, 300) * 10);
        System.out.println("Aircraft " + aircraft.getAircraftId() + " undocked at gate " + id + ".");
        lock.unlock();
    }
}
