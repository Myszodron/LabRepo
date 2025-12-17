//https://github.com/Myszodron
import  java.util.ArrayList;

interface Treatable {

void receiveTreatment();
}

abstract class Person {

    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void introduce() {
        System.out.println("My name is " + name + " and I'm " + age + " years old.");
    }
}

abstract class Staff extends Person {

    protected String role;

    public Staff(String name, int age, String role) {
        super(name, age);
        this.role = role;
    }

    public abstract void performDuties();
}

class Patient extends Person implements Treatable {

    private String condition;

    // Constructor initializes person data and patient condition
    public Patient(String name, int age, String condition) {
        super(name, age);
        this.condition = condition;
    }

    public void receiveTreatment() {
        System.out.println("Patient with condition \"" + condition + "\" is receiving treatment.");
    }
}
class Doctor extends Staff {

   public Doctor(String name, int age) {
        super(name, age, "Doctor");
    }

    public void performDuties() {
       System.out.println("Doctor is treating patients and prescribing medicine");
    }

    public void prescribeMedicine(String  medicine) {
       System.out.println("Doctor prescribes: " + medicine);
    }
}

class Nurse extends Staff {

    public Nurse(String name, int age) {
        super(name, age, "Nurse");
    }

    public void performDuties() {
        System.out.println("Nurse is checking patient's condition and vitals");
    }

    public void checkVitals(Patient patient) {
        System.out.println("Nurse is checking vitals of: " + patient.name);
    }
}

class Receptionist extends Staff {

    public Receptionist(String name, int age) {
        super(name, age, "Receptionist");
    }

    public void performDuties() {
        System.out.println("Receptionist is scheduling appointments");
    }
}

public class ClinicTest {

    public static void main(String[] args) {

        Patient p1 = new Patient("Daria", 24, "influenza");

        Doctor d1 = new Doctor("Dr. Kowalski", 42);
        Nurse n1 = new Nurse("Hanna", 38);
        Receptionist r1 = new Receptionist("Jacek", 30);

        System.out.println("People");
        p1.introduce();
        d1.introduce();
        n1.introduce();
        r1.introduce();
        System.out.println();

        System.out.println("Duties");
        d1.performDuties();
        n1.performDuties();
        r1.performDuties();
        System.out.println();

        System.out.println("Patient's Treatment");
        p1.receiveTreatment();
        System.out.println();

        System.out.println("Specific Job Actions");
        d1.prescribeMedicine("Antibiotics");
        n1.checkVitals(p1);
    }
}
