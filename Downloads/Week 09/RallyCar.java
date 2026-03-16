
public abstract class RallyCar {

    private String make;
    private String model;
    private int horsepower;

    /**
     * Constructs a RallyCar with the given specifications.
     *
     * @param make       manufacturer name (e.g. "Toyota")
     * @param model      model name (e.g. "GR Yaris")
     * @param horsepower engine output in horsepower
     */
    public RallyCar(String make, String model, int horsepower) {
        this.make = make;
        this.model = model;
        this.horsepower = horsepower;
    }

    // ── Getters ─────────────────────────────────────────────────────────────

    /** @return the manufacturer name */
    public String getMake() { return make; }

    /** @return the model name */
    public String getModel() { return model; }

    /** @return engine output in horsepower */
    public int getHorsepower() { return horsepower; }

    /**
     * Calculates a performance rating specific to the surface type.
     * Subclasses must provide their own algorithm.
     *
     * @return a numeric performance rating
     */
    public abstract double calculatePerformance();

    @Override
    public String toString() {
        return make + " " + model + " (" + horsepower + " hp)";
    }
}
