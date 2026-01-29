package Clinic;

import Clinic.ClinicTest.Patient;
import Clinic.ClinicTest.Doctor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReportGenerator {

    public static void main(String[] args) {

        String filePath = "src/Clinic/data (1).csv";
        List<PatientVisit> visits = new ArrayList<>();
        int rejectedLines = 0;

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {

                if (line.trim().isEmpty() || line.trim().equals("\"\"")) {
                    rejectedLines++;
                    continue;
                }

                PatientVisit visit = PatientVisitParser.parse(line);
                if (visit != null) {
                    visits.add(visit);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Rejected empty records: " + rejectedLines);
        generate(visits);
    }

    public static void generate(List<PatientVisit> visits) {

        System.out.println("Number of records: " + visits.size());

        long takingMeds = visits.stream()
                .filter(PatientVisit::tookDrugs)
                .count();

        System.out.println("Patients who take medication: " + takingMeds);
        System.out.println("\n--- VISIT DETAILS ---\n");

        visits.forEach(System.out::println);
    }
}

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

        Patient patient = new Patient("Unknown", age, "Unknown");

        String doctorName = RegexUtils.find(
                "Doctor:\\s*[A-Za-z]+,\\s*[A-Za-z]+",
                parts[1]
        );
        doctorName = doctorName.equals("not found")
                ? "Unknown"
                : doctorName.replace("Doctor:", "").trim();

        Doctor doctor = new Doctor(doctorName, 1);

        // ✅ medicine
        String medicines = RegexUtils.findWithGroup(
                "Patient takes:\\s*(.*?)(?=, Cell:|, Email:|, Doctor:|$)",
                parts[1]
        ).trim();

        // ✅ cell
        String cell = RegexUtils.findWithGroup(
                "Cell:\\s*([+0-9-]+)",
                parts[1]
        );

        // ✅ email
        String email = RegexUtils.find(
                "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}",
                parts[1]
        );

        return new PatientVisit(date, patient, doctor, medicines, cell, email);
    }
}

class PatientVisit {

    private final String date;
    private final Patient patient;
    private final Doctor doctor;
    private final String medicines;
    private final String cell;
    private final String email;

    public PatientVisit(
            String date,
            Patient patient,
            Doctor doctor,
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

class RegexUtils {

    public static String find(String regex, String text) {
        Matcher m = Pattern.compile(regex).matcher(text);
        return m.find() ? m.group() : "not found";
    }

    public static String findWithGroup(String regex, String text) {
        Matcher m = Pattern.compile(regex).matcher(text);
        if (!m.find()) return "not found";
        return m.groupCount() >= 1 ? m.group(1) : m.group();
    }

    public static int safeParseInt(String text) {
        if (text == null || text.equals("not found")) return 0;
        return Integer.parseInt(text);
    }
}
