/**
 * Coded by Seong Chee Ken on 22/06/2017, 23:03.
 */
public class Aircraft extends Thread {
    private Airspace airspace;
    private int aircraftId;
    private AircraftStatus status;

    Aircraft(Airspace airspace, int aircraftId) {
        this.airspace = airspace;
        this.aircraftId = aircraftId;
        status = randomise();
    }

    private AircraftStatus randomise(){
        int value = (int) (Math.random() * 20 + 1);
        if (value > 0 && value <= 17){
            return AircraftStatus.NORMAL;
        } else if (value == 18){
            return AircraftStatus.MALFUNC;
        } else if (value == 19) {
            return AircraftStatus.FUEL_S;
        } else {
            return AircraftStatus.MEDIC;
        }
    }

    @Override
    public void run() {
        try {
            airspace.enter(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    int getAircraftId() {
        return aircraftId;
    }

    AircraftStatus getStatus() {
        return status;
    }
}
