//https://github.com/Myszodron
import  java.util.ArrayList;

interface Treatable {

void receiveTreatment();
}

interface ClinicOperations {

    void addAppointment(Appointment appointment);

    void showAppointmentsForPatient(Patient patient);

    void showAppointmentsForDoctor(Doctor doctor);

    void addPrescription(Patient patient, Prescription prescription);

    void showPatientsWithMedicine(String medicineName);
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

class Treatment {

    protected String name;

    public Treatment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Medicine extends Treatment {

    public Medicine(String name) {
        super(name);
    }
}

class Prescription {

    private Doctor doctor;
    private ArrayList<Treatment> treatments = new ArrayList<>();

    public Prescription(Doctor doctor) {
        this.doctor = doctor;
    }

    public void addTreatment(Treatment t) {
        treatments.add(t);
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    public void print() {
        System.out.println("Prescribed by: " + doctor.name);
        for (Treatment t : treatments) {
            System.out.println("- " + t.getName());
        }
    }
}

class Appointment {

    private String dateTime;
    private Patient patient;
    private Doctor doctor;

    public Appointment(String dateTime, Patient patient, Doctor doctor) {
        this.dateTime = dateTime;
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void print() {
        System.out.println("Appointment at " + dateTime +
                " Patient: " + patient.name +
                " Doctor: " + doctor.name);
    }
}

class Patient extends Person implements Treatable {

    private String condition;
    private ArrayList<Prescription> prescriptions = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public Patient(String name, int age, String condition) {
        super(name, age);
        this.condition = condition;
    }

    public void receiveTreatment() {
        System.out.println("Patient with condition \"" + condition + "\" is receiving treatment.");
    }

    public void addPrescription(Prescription p) {
        prescriptions.add(p);
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addAppointment(Appointment a) {
        appointments.add(a);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
}
class Doctor extends Staff {

    private ArrayList<Appointment> appointments = new ArrayList<>();

    public Doctor(String name, int age) {
        super(name, age, "Doctor");
    }

    public void performDuties() {
       System.out.println("Doctor is treating patients and prescribing medicine");
    }

    public Prescription createPrescription() {
        return new Prescription(this);
    }
    public boolean canAddAppointment(String dateTime) {
        for (Appointment a : appointments) {
            if (a.getDateTime().equals(dateTime))
                return false;
        }
        return true;
    }

    public void addAppointment(Appointment a) {
        appointments.add(a);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
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

class ClinicSystem implements ClinicOperations {

    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();

    public void registerPatient(Patient p) {
        patients.add(p);
    }

    public void addAppointment(Appointment appointment) {

        Doctor d = appointment.getDoctor();

        if (!d.canAddAppointment(appointment.getDateTime())) {
            System.out.println("Doctor already has an appointment at this time.");
            return;
        }

        appointments.add(appointment);
        appointment.getPatient().addAppointment(appointment);
        d.addAppointment(appointment);
    }

    public void showAppointmentsForPatient(Patient patient) {
        System.out.println("Appointment for patient " + patient.name);
        for (Appointment a : patient.getAppointments()) {
            a.print();
        }
    }

    public void showAppointmentsForDoctor(Doctor doctor) {
        System.out.println("Appointments for " + doctor.name);
        for (Appointment a : doctor.getAppointments()) {
            a.print();
        }
    }

    public void addPrescription(Patient patient, Prescription prescription) {
        patient.addPrescription(prescription);
    }

    public void showPatientsWithMedicine(String medicineName) {
        System.out.println("Patients with medicine: " + medicineName);

        for (Patient p : patients) {
            for (Prescription pr : p.getPrescriptions()) {
                for (Treatment t : pr.getTreatments()) {
                    if (t instanceof Medicine && t.getName().equals(medicineName)) {
                        System.out.println(p.name + " (Prescribed by " +
                                pr.getDoctor().name + ")");


                    }
                }
            }
        }
    }
}

public class ClinicTest {

    public static void main(String[] args) {

        ClinicSystem clinic = new ClinicSystem();

        Patient p1 = new Patient("Daria", 24, "Influenza");
        Patient p2 = new Patient("Jan", 18, "Broken leg");

        Doctor d1 = new Doctor("Dr. Kowalski", 42);
        Nurse n1 = new Nurse("Hanna", 38);
        Receptionist r1 = new Receptionist("Jacek", 30);

        clinic.registerPatient(p1);
        clinic.registerPatient(p2);

        Appointment a1 = new Appointment("2026-01-12 10:00", p1, d1);
        Appointment a2 = new Appointment("2025-12-30 17:30", p2, d1);

        clinic.addAppointment(a1);
        clinic.addAppointment(a2);

        clinic.showAppointmentsForPatient(p1);
        clinic.showAppointmentsForDoctor(d1);

        Prescription pre1 = d1.createPrescription();
        pre1.addTreatment(new Medicine("Pain medication"));
        pre1.addTreatment(new Treatment("Put cast"));

        clinic.addPrescription(p2, pre1);

        clinic.showPatientsWithMedicine("Pain medication");



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
        d1.createPrescription();
        n1.checkVitals(p1);
    }
}