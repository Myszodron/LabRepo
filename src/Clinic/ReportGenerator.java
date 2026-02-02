package Clinic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

