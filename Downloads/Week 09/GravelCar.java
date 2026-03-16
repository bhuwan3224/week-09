
public class GravelCar extends RallyCar {

    /** Suspension travel in millimetres — key for gravel performance. */
    private double suspensionTravel;


    public GravelCar(String make, String model, int horsepower, double suspensionTravel) {
        super(make, model, horsepower);
        this.suspensionTravel = suspensionTravel;
    }

    /** @return suspension travel in millimetres */
    public double getSuspensionTravel() { return suspensionTravel; }


    @Override
    public double calculatePerformance() {
        return (getHorsepower() * 0.8) + (suspensionTravel * 1.5);
    }
}
