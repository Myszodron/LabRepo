import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

/** description */
public class PatientVisitParser {

    public static void main(String[] args) {
        // Name of the CSV file (must be in the project root directory)
        String filePath = "data (1).csv";

        // List that will store all accurately read patient visits
        List<PatientVisit> visits = new ArrayList<>();

        // Try-with-resources ensures the file is closed automatically
        try {
            // Read all lines from the CSV file at once
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // Process each line separately
            for (String line : lines) {

                // Reject empty lines
                if (line.trim().isEmpty()) {
                    System.out.println("Empty line detected and rejected");
                    continue;
                }

                // Split into date and description (only the first comma)
                String[] parts = line.split(",", 2);

                // Validate record format
                if (parts.length < 2) {
                    System.out.println("Invalid record format: " + line);
                    continue;
                }

                // Create PatientVisit object
                visits.add(new PatientVisit(parts[0].trim(), parts[1].trim()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate a final report
        ReportGenerator.generate(visits);
    }
}

/** Short description */
class PatientVisit {

    // Raw input values
    private final String rawDate;
    private final String description;

    // Parsed values
    private String date;
    private String age;
    private String phone;
    private String email;
    private String medications;
    private String doctor;

    /** Short description */
    public PatientVisit(String rawDate, String description) {
        this.rawDate = rawDate;
        this.description = description;
        parse();
    }

    /** Short description */
    private void parse() {
        // Date in format YYYY-MM-DD
        date = RegexUtils.find("\\d{4}-\\d{2}-\\d{2}", rawDate);

        // Patient age
        age = RegexUtils.find("The patient is (\\d{1,3}) years old", description);

        // Phone number (with or without country code)
        phone = RegexUtils.find("\\+?\\d{9,13}", description);

        // E-mail
        email = RegexUtils.find("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}", description);

        // Medications taken by patient
        medications = RegexUtils.find("(?i)(took|taking|reported taking) ([A-Za-z, ]+)", description);

        // Doctor name format
        doctor = RegexUtils.find("Doctor: [A-Za-z]+, [A-Za-z]+", description);
    }

    /** Short description */
    public boolean tookDrugs() {
        return !medications.equals("not found");
    }

    /** description */
    public String toString() {
        return "Date: " + date + "\n" +
                "Age: " + age + "\n" +
                "Phone: " + phone + "\n" +
                "Email: " + email + "\n" +
                "Medications: " + medications + "\n" +
                "Doctor: " + doctor + "\n";
    }
}

/** description */
class RegexUtils {

    /** description */
    public static String find(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        return matcher.find() ? matcher.group() : "not found";
    }
}

/** description */
class ReportGenerator {

    public static void generate(List<PatientVisit> visits) {
        System.out.println("Number of records: " + visits.size());

        // Count patients who took medication
        long drugUsers = visits.stream()
                .filter(PatientVisit::tookDrugs)
                .count();

        System.out.println("Patients who took drugs: " + drugUsers);
        System.out.println("\n--- VISIT DETAILS ---\n");

        // Print details of each visit
        for (PatientVisit visit : visits) {
            System.out.println(visit);
        }
    }
}
