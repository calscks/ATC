import java.util.concurrent.ThreadLocalRandom;

/**
 * Coded by Seong Chee Ken on 22/06/2017, 20:45.
 */
class Airspace {
    private Runway runways[];
    private Runway emergencyRunways[];
    private Gate gates[];
    private Weather weather;

    Airspace(Runway[] runways, Runway[] emergencyRunways, Gate[] gates, Weather weather) {
        this.runways = runways;
        this.emergencyRunways = emergencyRunways;
        this.gates = gates;
        this.weather = weather;
    }

    void enter(Aircraft aircraft) throws InterruptedException {
        boolean x = false, y = false;
        int count, count2;

        if (!aircraft.getStatus().equals(AircraftStatus.NORMAL)) {
            System.out.println("Aircraft " + aircraft.getAircraftId() + " is requesting for emergency due to " +
                    aircraft.getStatus().getStatusName() + ".");
            for (count = 0; count < emergencyRunways.length; count++) {
                x = emergencyRunways[count].land(aircraft);
                if (x)
                    break;
            }
        } else {
            while (!x) {
                for (count = 0; count < runways.length; count++) {
                    x = runways[count].land(aircraft);
                    if (x)
                        break;
                }
            }
        }

        accessGates(aircraft);

        if (aircraft.getStatus().equals(AircraftStatus.MALFUNC)) {
            System.out.println("Aircraft " + aircraft.getAircraftId() + " due to " +
                    aircraft.getStatus().getStatusName() + " will be repaired and unavailable for any flights.");
            return;
        }

        while (!y) {
            if (weather.getValue() >= 1 && weather.getValue() <= 8) {
                for (count2 = 0; count2 < runways.length; count2++) {
                    y = runways[count2].takeOff(aircraft);
                    if (y)
                        break;
                }
            } else {
                System.out.println("Weather condition is bad. Taking off of Aircraft " + aircraft.getAircraftId() + " is on hold.");
                Thread.sleep(5000);
            }
        }
    }

    private void accessGates(Aircraft aircraft) throws InterruptedException {
        boolean x = false;
        int count = 0;
        while (!x) {
            for (count = 0; count < gates.length; count++) {
                x = gates[count].dock(aircraft);
                if (x)
                    break;
            }
        }
        Thread.sleep(ThreadLocalRandom.current().nextInt(50, 300) * 10);
        gates[count].undock(aircraft);
    }

    Runway[] getRunways() {
        return runways;
    }
}
