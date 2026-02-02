package Clinic;

class PatientVisit {

    private final String date;
    private final ClinicTest.Patient patient;
    private final ClinicTest.Doctor doctor;
    private final String medicines;
    private final String cell;
    private final String email;

    public PatientVisit(
            String date,
            ClinicTest.Patient patient,
            ClinicTest.Doctor doctor,
            String medicines,
            String cell,
            String email
    ) {
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
        this.medicines = medicines;
        this.cell = cell;
        this.email = email;
    }

    public boolean tookDrugs() {
        return medicines != null && !medicines.equals("not found") && !medicines.isEmpty();
    }

    @Override
    public String toString() {
        return "Date: " + date + "\n" +
                "Patient age: " + patient.getAge() + "\n" +
                "Doctor: " + doctor.getName() + "\n" +
                (tookDrugs()
                        ? "Patient takes (" + medicines + ")\n"
                        : "Patient takes no medicine\n") +
                "Cell: " + (cell.equals("not found") ? "not provided" : cell) + "\n" +
                "Email: " + (email.equals("not found") ? "not provided" : email) + "\n";
    }
}
