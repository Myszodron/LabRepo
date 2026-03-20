package SpaceStation;

public class StationTracker {

    public static void main(String[] args) {

        CrewMember[] crew = new CrewMember[10];

        crew[0] = new CrewMember("Jan", 25, Role.BOTANIST, "Researching Xenobotany", 1200);
        crew[1] = new CrewMember("Juki", 9, Role.CAT, "Emotional support", 0);
        crew[2] = new CrewMember("Kasia", 28, Role.MEDIC, "Trauma response", 600);
        crew[3] = new CrewMember("Luke", 49, Role.PILOT, "Emergency responses", 1500);
        crew[4] = new CrewMember("Eva", 40, Role.COMMANDER, "Directing operations", 3000);
        crew[5] = new CrewMember("Sarah", 37, Role.ENGINEER, "Maintaining structural integrity", 2300);
        crew[6] = new CrewMember("Nico", 55, Role.PILOT, "Rendezvous and docking:", 3450);
        crew[7] = new CrewMember("Daria", 24, Role.ENGINEER, "Resource management", 900);
        crew[8] = new CrewMember("Nina", 62, Role.MEDIC, "Preventive care", 4200);
        crew[9] = new CrewMember("Paweł", 45, Role.QUARTERMASTER, "Crew support and cargo management", 700);

        // Print crew members profiles
        for (CrewMember value : crew) {
            value.printProfile();
            System.out.println();
        }

        // Calculate crew oxygen usage
        System.out.print("Oxygen Distribution: \n");
        for (CrewMember member : crew) {
            Quartermaster.calculateOxygen(member, 2);
        }

        // Starting age/mission hours
        int totalAge = 0;
        int totalHours = 0;

        for (CrewMember crewMember : crew) {
            totalAge += crewMember.getAge();
            totalHours += crewMember.getMissionHours();
        }

        // Calculating average age/mission hours
        double averageAge = (double) totalAge / crew.length;
        double averageHours = (double) totalHours / crew.length;

        System.out.println("\n\nAverage crew age: " + averageAge);
        System.out.println("Average crew mission hours: " + averageHours);
    }
}
