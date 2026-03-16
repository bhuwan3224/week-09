import java.util.List;
public class RallyChampionship {

    public static void main(String[] args) {

        // ── 1. Obtain the singleton ChampionshipManager ──────────────────────
        ChampionshipManager manager = ChampionshipManager.getInstance();

        // ── 2. Create cars ───────────────────────────────────────────────────
        // Gravel cars (used in Rally Finland)
        GravelCar ogierGravel   = new GravelCar("Toyota",  "GR Yaris",     380, 220.0);
        GravelCar rovaperaGravel = new GravelCar("Toyota", "GR Yaris",     380, 215.0);
        GravelCar tanakGravel   = new GravelCar("Hyundai", "i20 N Rally1", 375, 210.0);
        GravelCar neuvilleGravel = new GravelCar("Hyundai","i20 N Rally1", 375, 205.0);

        // Asphalt cars (used in Monte Carlo)
        AsphaltCar ogierAsphalt    = new AsphaltCar("Toyota",  "GR Yaris",     380, 180.0);
        AsphaltCar rovaperaAsphalt = new AsphaltCar("Toyota",  "GR Yaris",     380, 185.0);
        AsphaltCar tanakAsphalt    = new AsphaltCar("Hyundai", "i20 N Rally1", 375, 175.0);
        AsphaltCar neuvilleAsphalt = new AsphaltCar("Hyundai", "i20 N Rally1", 375, 182.0);

        // ── 3. Create drivers (cars injected — Dependency Injection) ─────────
        Driver ogier    = new Driver("Sébastien Ogier",  "France",  ogierGravel);
        Driver rovaepra = new Driver("Kalle Rovanperä",  "Finland", rovaperaGravel);
        Driver tanak    = new Driver("Ott Tänak",         "Estonia", tanakGravel);
        Driver neuville = new Driver("Thierry Neuville",  "Belgium", neuvilleGravel);

        // ── 4. Register drivers ──────────────────────────────────────────────
        manager.registerDriver(ogier);
        manager.registerDriver(rovaepra);
        manager.registerDriver(tanak);
        manager.registerDriver(neuville);

        // ── 5. Race 1: Rally Finland (gravel) ────────────────────────────────
        RallyRaceResult finland = new RallyRaceResult("Rally Finland", "Jyväskylä");
        finland.recordResult(ogier,    1, 25);
        finland.recordResult(tanak,    2, 18);
        finland.recordResult(rovaepra, 3, 15);
        finland.recordResult(neuville, 4, 12);
        manager.addRaceResult(finland);

        // ── 6. Car switch — swap to asphalt spec before Monte Carlo ──────────
        ogier.setCar(ogierAsphalt);
        rovaepra.setCar(rovaperaAsphalt);
        tanak.setCar(tanakAsphalt);
        neuville.setCar(neuvilleAsphalt);

        // ── 7. Race 2: Monte Carlo Rally (asphalt) ───────────────────────────
        RallyRaceResult monteCarlo = new RallyRaceResult("Monte Carlo Rally", "Monaco");
        monteCarlo.recordResult(rovaepra, 1, 25);
        monteCarlo.recordResult(neuville, 2, 18);
        monteCarlo.recordResult(ogier,    3, 15);
        monteCarlo.recordResult(tanak,    4, 12);
        manager.addRaceResult(monteCarlo);

        // ── 8. Championship standings ────────────────────────────────────────
        System.out.println("===== CHAMPIONSHIP STANDINGS =====");
        List<Driver> standings = ChampionshipManager.getDriverStandings();
        for (int i = 0; i < standings.size(); i++) {
            System.out.println((i + 1) + ". " + standings.get(i));
        }

        // ── 9. Championship leader ───────────────────────────────────────────
        System.out.println("\n===== CHAMPIONSHIP LEADER =====");
        Driver leader = ChampionshipManager.getLeadingDriver();
        if (leader != null) {
            System.out.println(leader.getName() + " with " + leader.getPoints() + " points");
        }

        // ── 10. Championship statistics ──────────────────────────────────────
        System.out.println("\n===== CHAMPIONSHIP STATISTICS =====");
        System.out.println("Total Drivers: " + ChampionshipManager.getTotalDrivers());
        System.out.println("Total Races: " + ChampionshipStatistics.getTotalRacesHeld());

        double avg = ChampionshipStatistics.calculateAveragePointsPerDriver(standings);
        System.out.printf("Average Points Per Driver: %.2f%n", avg);

        String topCountry = ChampionshipStatistics.findMostSuccessfulCountry(standings);
        System.out.println("Most Successful Country: " + topCountry);
        System.out.println("Total Championship Points: " + ChampionshipManager.getTotalChampionshipPoints());

        // ── 11. Race results ─────────────────────────────────────────────────
        System.out.println("\n===== RACE RESULTS =====");
        for (RallyRaceResult race : manager.getRaces()) {
            System.out.println(race);
            System.out.println();
        }

        // ── 12. Car performance ratings ──────────────────────────────────────
        System.out.println("===== CAR PERFORMANCE RATINGS =====");
        // Use the original gravel/asphalt cars (post-switch cars are already on drivers)
        System.out.printf("Gravel Car Performance:  %.1f%n", ogierGravel.calculatePerformance());
        System.out.printf("Asphalt Car Performance: %.1f%n", ogierAsphalt.calculatePerformance());
    }
}
