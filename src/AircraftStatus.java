/**
 * Coded by Seong Chee Ken on 02/07/2017, 01:48.
 * enum class of aircraft's statuses.
 */
public enum AircraftStatus {
    NORMAL("normal"),
    MALFUNC("malfunctions"),
    FUEL_S("shortage on fuel"),
    MEDIC("medical crises");

    private String statusName;

    AircraftStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
