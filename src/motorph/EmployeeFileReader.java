package motorph;

import java.io.*;
import java.util.*;

public class EmployeeFileReader {
    public static List<Employee> readEmployeeData(String filePath, Integer userId) {
        List<Employee> employees = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Error: File not found.");
            return employees;
        }

        try (Scanner scanner = new Scanner(file)) {
            StringBuilder jsonContent = new StringBuilder();

            // Read entire file into a single string
            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine().trim());
            }

            // Remove outer brackets [] if present
            String content = jsonContent.toString().trim();
            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1);
            }

            // Split individual JSON objects safely
            String[] employeeEntries = content.split("\\},\\{");

            for (String entry : employeeEntries) {
                entry = entry.replace("{", "").replace("}", ""); // Clean { }
                String[] keyValues = entry.split("\",\"");

                Map<String, String> dataMap = new HashMap<>();
                for (String keyValue : keyValues) {
                    String[] pair = keyValue.split("\":\"");
                    if (pair.length == 2) {
                        String key = pair[0].trim().replace("\"", "");
                        String value = pair[1].trim().replace("\"", "");
                        dataMap.put(key, value);
                    }
                }

                // Convert Map values to Employee object
                int id = Integer.parseInt(dataMap.get("id"));
                if (userId != null && id != userId) continue; // Filter by ID if provided

                Employee employee = new Employee(
                    id, dataMap.get("lastName"), dataMap.get("firstName"), dataMap.get("birthday"), 
                    dataMap.get("address"), dataMap.get("phoneNumber"), dataMap.get("sss"), dataMap.get("philHealth"), 
                    dataMap.get("tin"), dataMap.get("pagIbig"), dataMap.get("status"), dataMap.get("position"), 
                    Double.parseDouble(dataMap.get("basicSalary")), Double.parseDouble(dataMap.get("riceAllowance")), 
                    Double.parseDouble(dataMap.get("subsidy")), Double.parseDouble(dataMap.get("phoneAllowance")), 
                    Double.parseDouble(dataMap.get("clothingAllowance")), Double.parseDouble(dataMap.get("grossSemiMonthlyRate")), 
                    Double.parseDouble(dataMap.get("hourlyRate"))
                );

                employees.add(employee);
            }

            System.out.println("File read successfully. " + employees.size() + " employees loaded.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return employees;
    }
}
