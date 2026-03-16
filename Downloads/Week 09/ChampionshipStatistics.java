import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChampionshipStatistics {

    /** Private constructor — this class should never be instantiated. */
    private ChampionshipStatistics() { }

    /**
     * Calculates the average points per driver across the entire championship.
     *
     * @param drivers list of registered drivers
     * @return average points per driver, or 0.0 if the list is empty
     */
    public static double calculateAveragePointsPerDriver(List<Driver> drivers) {
        if (drivers == null || drivers.isEmpty()) return 0.0;
        double total = drivers.stream().mapToInt(Driver::getPoints).sum();
        return total / drivers.size();
    }

    /**
     * Finds the country with the highest combined championship points.
     * If multiple countries share the top score, the first one encountered
     * (alphabetically by insertion order) is returned.
     *
     * @param drivers list of registered drivers
     * @return name of the most successful country, or "N/A" if list is empty
     */
    public static String findMostSuccessfulCountry(List<Driver> drivers) {
        if (drivers == null || drivers.isEmpty()) return "N/A";

        Map<String, Integer> countryPoints = new HashMap<>();
        for (Driver d : drivers) {
            countryPoints.merge(d.getCountry(), d.getPoints(), Integer::sum);
        }

        return countryPoints.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    /**
     * Returns the total number of races held in the championship so far.
     * Delegates to the {@link ChampionshipManager} singleton's static counter.
     *
     * @return total races held
     */
    public static int getTotalRacesHeld() {
        return ChampionshipManager.getTotalRaces();
    }
}
