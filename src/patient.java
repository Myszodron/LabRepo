import javax.xml.namespace.QName;

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

abstract class Staff extends Person {

    protected String role;

    public Staff(String name, int age, String role) {
        super(name, age);
        this.role = role;
    }

    public abstract void performDuties();
}

class Doctor extends Staff {

   public Doctor(String name, int age) {
        super(name, age, "Doctor");
    }

    public void performDuties() {
       System.out.println(" Doctor is treating patients and prescribing medicine");
    }

    public void prescribeMedicine(String  medicine) {
       System.out.println("Doctor prescribes: " + medicine);
    }
}




