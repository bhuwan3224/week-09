
public class AsphaltCar extends RallyCar {

    /** Aerodynamic downforce in Newtons — key for asphalt cornering. */
    private double downforce;

    /**
     * Constructs an AsphaltCar.
     *
     * @param make       manufacturer name
     * @param model      model name
     * @param horsepower engine output in horsepower
     * @param downforce  aerodynamic downforce in Newtons
     */
    public AsphaltCar(String make, String model, int horsepower, double downforce) {
        super(make, model, horsepower);
        this.downforce = downforce;
    }

    /** @return aerodynamic downforce in Newtons */
    public double getDownforce() { return downforce; }

    /**
     * Asphalt performance rating.
     * Formula: (horsepower * 0.9) + (downforce * 0.5)
     * Higher downforce rewards tarmac grip at speed.
     *
     * @return asphalt-specific performance rating
     */
    @Override
    public double calculatePerformance() {
        return (getHorsepower() * 0.9) + (downforce * 0.5);
    }
}
