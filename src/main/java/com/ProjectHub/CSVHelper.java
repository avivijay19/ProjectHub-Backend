package com.ProjectHub;

import com.ProjectHub.entities.StudentProfile;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

    public static boolean hasCSVFormat(MultipartFile file) {
        System.out.println(file.getContentType());
        return TYPE.equals(file.getContentType())
                || Objects.equals(file.getContentType(), "application/vnd.ms-excel");
    }

    public static List<StudentProfile> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<StudentProfile> developerTutorialList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                StudentProfile developerTutorial = new StudentProfile(
                        csvRecord.get("username"),
                        csvRecord.get("password"),
                        csvRecord.get("firstname"),
                        csvRecord.get("lastname"),
                        csvRecord.get("department"),
                        csvRecord.get("emailId"),
                        csvRecord.get("personalEmail"),
                        csvRecord.get("Roles")
                );

                developerTutorialList.add(developerTutorial);
            }

            return developerTutorialList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream tutorialsToCSV(List<StudentProfile> developerTutorialList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (StudentProfile developerTutorial : developerTutorialList) {
                List<String> data = Arrays.asList(
                        developerTutorial.getUsername(),
                        developerTutorial.getPassword(),
                        developerTutorial.getFirstName(),
                        developerTutorial.getLastName(),
                        developerTutorial.getDepartment(),
                        developerTutorial.getEmailId(),
                        developerTutorial.getPersonalEmail(),
                        developerTutorial.getRoles()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}