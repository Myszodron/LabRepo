package SpaceStation;

public class CrewMember {

    protected String name;
    protected int age;
    protected Role role;
    protected String specialization;
    protected int missionHours;

    // Constructor with parameters
    public CrewMember(String name, int age, Role role,
                      String specialization, int missionHours) {
        this.name = name;
        this.age = age;
        this.role = role;
        this.specialization = specialization;
        this.missionHours = missionHours;
    }

    public void printProfile() {
        System.out.println("Hello! Meet " + name + ", a "
                + age + "-year-old " + role + ". ");
        System.out.println("This crew member specializes in "
                + specialization + " and has completed "
                + missionHours + " mission hours.");
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Role getRole() {
        return role;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getMissionHours() {
        return missionHours;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setMissionHours(int missionHours) {
        this.missionHours = missionHours;
    }
}
