package lab10;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

/// Entry point
/// Reads patient visit records from a CSV file
/// Performs basic input validation, and starts parsing

public class PatientVisitParser {

    /// Loads visit data from a CSV file and generates a summary report
    /// @param args command-line arguments -> not used
    public static void main(String[] args) {
        // Name of the CSV file (Must be in the project root directory)
        String filePath = "src/lab/data (1).csv";

        // List that will store all accurately read patient visits
        List<PatientVisit> visits = new ArrayList<>();
        int rejectedLines = 0;

        // Try-with-resources ensures the file is closed automatically
        try {
            // Read all lines from the CSV file at once
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // Process each line separately
            for (String line : lines) {

                // Reject empty or quoted-empty lines
                if (line.trim().isEmpty() || line.trim().equals("\"\"")) {
                    rejectedLines++;
                    continue;
                }

                // Split into date and description (only the first comma)
                String[] parts = line.split(",", 2);

                // Validate record format
                if (parts.length < 2) {
                    System.out.println("Invalid record format: " + line);
                    continue;
                }

                // Create lab.PatientVisit object
                visits.add(new PatientVisit(parts[0].trim(), parts[1].trim()));
            }

            System.out.println("Rejected empty records: " + rejectedLines);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate a final report
        ReportGenerator.generate(visits);
    }
}

/// Represents a single patient visit
/// Encapsulates input data and parsed medical information

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

    /// Creates a patient visit object and triggers data parsing
    /// @param rawDate visit date from the CSV file
    /// @param description textual visit description
    public PatientVisit(String rawDate, String description) {
        this.rawDate = rawDate;
        this.description = description;
        parse();
    }

    /// Extracts required medical data using regular expressions
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
        medications = RegexUtils.find("(?i)(took|takes|taking|reported taking)[^\\.]*", description);

        // Doctor name format
        doctor = RegexUtils.find("Doctor: [A-Za-z]+, [A-Za-z]+", description);
    }

    /// Checks whether medication information was found
    /// @return true if a patient took medication, false otherwise
    public boolean tookDrugs() {
        return !medications.equals("not found");
    }

    @Override
    public String toString() {
        return "Date: " + date + "\n" +
                "Age: " + age + "\n" +
                "Phone: " + phone + "\n" +
                "Email: " + email + "\n" +
                "Medications: " + medications + "\n" +
                "Doctor: " + doctor + "\n";
    }
}

/// Utility class providing helper methods for regular expression matching
class RegexUtils {

    /// Searches for the first occurrence of a regex pattern in the given text
    /// @param regex regular expression pattern
    /// @param text input text
    /// @return matched value or "not found" if no match exists
    public static String find(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        return matcher.find() ? matcher.group() : "not found";
    }
}

/// Generates formatted output and summary statistics based on parsed patient visit data
class ReportGenerator {

    /// Prints a summary report of all parsed visits
    /// @param visits list of patient visit records
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
