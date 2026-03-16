
public interface RaceResult {

    /**
     * Records the result for a driver in this race.
     *
     * @param driver   the driver whose result is being recorded
     * @param position the finishing position (1 = first, etc.)
     * @param points   the championship points awarded
     */
    void recordResult(Driver driver, int position, int points);


    int getDriverPoints(Driver driver);

    java.util.List<Driver> getResults();
}
