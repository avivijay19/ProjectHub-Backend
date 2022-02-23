package com.ProjectHub;

import com.ProjectHub.documents.StudentProfile;
import com.ProjectHub.documents.TeacherProfile;
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
    static String[] HEADERs = {"Id", "Title", "Description", "Published"};

    public static boolean hasCSVFormat(MultipartFile file) {
        System.out.println(file.getContentType());
        return TYPE.equals(file.getContentType())
                || Objects.equals(file.getContentType(), "application/vnd.ms-excel");
    }

    public static List<StudentProfile> CSVToStudent(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            List<StudentProfile> studentProfiles = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                StudentProfile studentProfile = new StudentProfile(
                        csvRecord.get("username"),
                        csvRecord.get("password"),
                        csvRecord.get("firstname"),
                        csvRecord.get("lastname"),
                        csvRecord.get("department"),
                        csvRecord.get("emailId"),
                        csvRecord.get("personalEmail"),
                        csvRecord.get("Roles")
                );
                studentProfiles.add(studentProfile);
            }
            return studentProfiles;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<TeacherProfile> CSVToTeacher(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            List<TeacherProfile> teacherProfiles = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                TeacherProfile teacherProfile = new TeacherProfile(
                        csvRecord.get("employeeID"),
                        csvRecord.get("password"),
                        csvRecord.get("firstName"),
                        csvRecord.get("lastName"),
                        csvRecord.get("department"),
                        csvRecord.get("emailId"),
                        csvRecord.get("roles")
                );
                teacherProfiles.add(teacherProfile);
            }
            return teacherProfiles;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream studentToCSV(List<StudentProfile> studentProfiles) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (StudentProfile studentProfile : studentProfiles) {
                List<String> data = Arrays.asList(
                        studentProfile.getUsername(),
                        studentProfile.getPassword(),
                        studentProfile.getFirstName(),
                        studentProfile.getLastName(),
                        studentProfile.getDepartment(),
                        studentProfile.getEmailId(),
                        studentProfile.getPersonalEmail(),
                        studentProfile.getRoles()
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream teacherToCSV(List<TeacherProfile> teacherProfiles) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (TeacherProfile teacherProfile : teacherProfiles) {
                List<String> data = Arrays.asList(
                        teacherProfile.getEmployeeID(),
                        teacherProfile.getPassword(),
                        teacherProfile.getFirstName(),
                        teacherProfile.getLastName(),
                        teacherProfile.getDepartment(),
                        teacherProfile.getEmailId(),
                        teacherProfile.getRoles()
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
