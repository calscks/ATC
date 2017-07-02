import java.util.concurrent.ThreadLocalRandom;

/**
 * Coded by Seong Chee Ken on 02/07/2017, 15:19.
 */
class EmergencyRunway extends Runway {

    EmergencyRunway(int id) {
        super(id);
    }

    @Override
    boolean land(Aircraft aircraft) throws InterruptedException {
        if (!lock.tryLock()) {
            return false;
        } else {
            occupied = true;
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is assigned to emergency runway " + id + "...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 150) * 5);
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is landing on emergency runway " + id + "...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(150, 200) * 20);
            System.out.println("Aircraft " + aircraft.getAircraftId() + " landed on emergency runway " + id + ".");
            lock.unlock();
            occupied = false;
            return true;
        }
    }
}
