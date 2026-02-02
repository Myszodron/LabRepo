package Clinic;

class PatientVisitParser {

    public static PatientVisit parse(String rawLine) {

        String line = rawLine.replace("\"", "");
        String[] parts = line.split(",", 2);
        if (parts.length < 2) return null;

        String date = RegexUtils.find("\\d{4}-\\d{2}-\\d{2}", parts[0]);

        int age = RegexUtils.safeParseInt(
                RegexUtils.find("\\d{1,3}", parts[1])
        );
        if (age <= 0) age = 1;

        ClinicTest.Patient patient = new ClinicTest.Patient("Unknown", age, "Unknown");

        String doctorName = RegexUtils.find(
                "Doctor:\\s*[A-Za-z]+,\\s*[A-Za-z]+",
                parts[1]
        );
        doctorName = doctorName.equals("not found")
                ? "Unknown"
                : doctorName.replace("Doctor:", "").trim();

        ClinicTest.Doctor doctor = new ClinicTest.Doctor(doctorName, 1);

        //  medicine
        String medicines = RegexUtils.findWithGroup(
                "Patient takes:\\s*(.*?)(?=, Cell:|, Email:|, Doctor:|$)",
                parts[1]
        ).trim();

        //  cell
        String cell = RegexUtils.findWithGroup(
                "Cell:\\s*([+0-9-]+)",
                parts[1]
        );

        //  email
        String email = RegexUtils.find(
                "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}",
                parts[1]
        );

        return new PatientVisit(date, patient, doctor, medicines, cell, email);
    }
}
