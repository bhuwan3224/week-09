import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class RallyRaceResult implements RaceResult {

    private final String raceName;
    private final String location;

   
    private final Map<Driver, Integer> results = new LinkedHashMap<>();

    /**
     * Constructs a RallyRaceResult for the given event.
     *
     * @param raceName  name of the rally (e.g. "Rally Finland")
     * @param location  venue / city (e.g. "Jyväskylä")
     */
    public RallyRaceResult(String raceName, String location) {
        this.raceName = raceName;
        this.location = location;
    }

    // ── RaceResult interface implementation ──────────────────────────────────

    /**
     * {@inheritDoc}
     * Also awards the points directly to the driver's championship total.
     */
    @Override
    public void recordResult(Driver driver, int position, int points) {
        results.put(driver, points);
        driver.addPoints(points);
    }

    /** {@inheritDoc} */
    @Override
    public int getDriverPoints(Driver driver) {
        return results.getOrDefault(driver, 0);
    }

    /** {@inheritDoc} */
    @Override
    public List<Driver> getResults() {
        return new ArrayList<>(results.keySet());
    }

    // ── Getters ─────────────────────────────────────────────────────────────

    /** @return name of this rally event */
    public String getRaceName() { return raceName; }

    /** @return location / venue of this rally event */
    public String getLocation() { return location; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Race: ").append(raceName).append(" (").append(location).append(")\n");
        int position = 1;
        for (Map.Entry<Driver, Integer> entry : results.entrySet()) {
            sb.append("  Position ").append(position++)
              .append(": ").append(entry.getKey().getName())
              .append(" - ").append(entry.getValue()).append(" points\n");
        }
        return sb.toString().trim();
    }
}
