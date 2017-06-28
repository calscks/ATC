/**
 * Coded by Seong Chee Ken on 22/06/2017, 23:03.
 */
public class Aircraft extends Thread {
    private Airspace airspace;
    private int aircraftId;

    Aircraft(Airspace airspace, int aircraftId) {
        this.airspace = airspace;
        this.aircraftId = aircraftId;
    }

    @Override
    public void run() {
        System.out.println("Aircraft " + aircraftId + " is entering the airspace.");

        if (airspace.isFull()) {
            System.out.println("Aircraft " + aircraftId + " is on hold.");
        }
        while (true) {
            if (!airspace.isFull()) {
                airspace.enter(this);
                break;
            }
        }
    }

    int getAircraftId() {
        return aircraftId;
    }
}
