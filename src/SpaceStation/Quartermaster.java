package SpaceStation;

public class Quartermaster {



    public static void calculateOxygen(CrewMember member, int distributionPerDay) {

        int dailyOxygenUnit  = 2;

        if (member.getMissionHours() > 1000) {
            dailyOxygenUnit = 3;
        }

        double  perDistribution = (double) dailyOxygenUnit / distributionPerDay;

        System.out.print("\n" + member.getName() + " needs "
                + dailyOxygenUnit + " oxygen units daily, which means "
                + perDistribution + " units per distribution.");
    }
}