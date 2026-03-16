import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChampionshipManager {

    // ── Singleton infrastructure ─────────────────────────────────────────────

    /** The sole instance — lazily initialised on first access. */
    private static ChampionshipManager instance;

    /** Private constructor prevents external instantiation. */
    private ChampionshipManager() { }

    /**
     * Returns the single {@code ChampionshipManager} instance, creating it
     * on the first call.
     *
     * @return the singleton instance
     */
    public static ChampionshipManager getInstance() {
        if (instance == null) {
            instance = new ChampionshipManager();
        }
        return instance;
    }

    // ── State ────────────────────────────────────────────────────────────────

    private final List<Driver> drivers = new ArrayList<>();
    private final List<RallyRaceResult> races = new ArrayList<>();

    /** Total number of drivers currently registered. */
    private static int totalDrivers = 0;

    /** Total number of races that have been added to the championship. */
    private static int totalRaces = 0;

    // ── Mutation methods ─────────────────────────────────────────────────────

    /**
     * Registers a driver with the championship.
     *
     * @param driver the driver to add
     */
    public void registerDriver(Driver driver) {
        drivers.add(driver);
        totalDrivers++;
    }

    /**
     * Adds a completed race result to the championship record.
     *
     * @param result the race result to add
     */
    public void addRaceResult(RallyRaceResult result) {
        races.add(result);
        totalRaces++;
    }

    // ── Static query methods ─────────────────────────────────────────────────

    /**
     * Returns the list of all registered drivers sorted by championship
     * points in descending order.
     *
     * @return sorted standings list
     */
    public static List<Driver> getDriverStandings() {
        List<Driver> sorted = new ArrayList<>(getInstance().drivers);
        sorted.sort(Comparator.comparingInt(Driver::getPoints).reversed());
        return sorted;
    }

    /**
     * Returns the driver currently leading the championship (most points).
     * If two drivers are tied, the one registered first is returned.
     *
     * @return the championship leader
     */
    public static Driver getLeadingDriver() {
        return getInstance().drivers.stream()
                .max(Comparator.comparingInt(Driver::getPoints))
                .orElse(null);
    }

    /**
     * Returns the sum of all points held by all registered drivers.
     *
     * @return total championship points distributed so far
     */
    public static int getTotalChampionshipPoints() {
        return getInstance().drivers.stream()
                .mapToInt(Driver::getPoints)
                .sum();
    }

    /** @return total number of drivers currently registered */
    public static int getTotalDrivers() { return totalDrivers; }

    /** @return total number of races added to the championship */
    public static int getTotalRaces() { return totalRaces; }

    /** @return an unmodifiable view of the race results list */
    public List<RallyRaceResult> getRaces() {
        return java.util.Collections.unmodifiableList(races);
    }
}
