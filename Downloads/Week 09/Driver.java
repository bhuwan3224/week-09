
public class Driver {

    private String name;
    private String country;
    private int points;

    
    private RallyCar car;

    private static int totalDriversCreated = 0;

    // ── Constructor ──────────────────────────────────────────────────────────

    /**
     * Constructs a Driver and increments the global driver counter.
     *
     * @param name    full name of the driver
     * @param country nationality / country of the driver
     * @param car     the rally car assigned to this driver (injected)
     */
    public Driver(String name, String country, RallyCar car) {
        this.name = name;
        this.country = country;
        this.car = car;
        this.points = 0;
        totalDriversCreated++;
    }

    // ── Getters & setters ────────────────────────────────────────────────────

    /** @return the driver's full name */
    public String getName() { return name; }

    /** @return the driver's country */
    public String getCountry() { return country; }

    /** @return accumulated championship points */
    public int getPoints() { return points; }

    /** @return the car currently assigned to this driver */
    public RallyCar getCar() { return car; }

    /**
     * Swaps the car assigned to this driver (car switching between races).
     *
     * @param car the new car to assign
     */
    public void setCar(RallyCar car) { this.car = car; }

    // ── Business methods ─────────────────────────────────────────────────────

    /**
     * Adds championship points to this driver's total.
     *
     * @param pts points to add (must be non-negative)
     */
    public void addPoints(int pts) {
        if (pts > 0) this.points += pts;
    }

 
    public static int getTotalDriversCreated() { return totalDriversCreated; }

    @Override
    public String toString() {
        return name + " (" + country + "): " + points + " points";
    }
}
