package motorph;

import java.util.*;

public class MotorPH {
    private static final TreeMap<Double, Double> sssTable = new TreeMap<>();

    static {
        // Populating SSS contribution table
        sssTable.put(3249.99, 135.00);
        sssTable.put(3749.99, 157.50);
        sssTable.put(4249.99, 180.00);
        sssTable.put(4749.99, 202.50);
        sssTable.put(5249.99, 225.00);
        sssTable.put(5749.99, 247.50);
        sssTable.put(6249.99, 270.00);
        sssTable.put(6749.99, 292.50);
        sssTable.put(7249.99, 315.00);
        sssTable.put(7749.99, 337.50);
        sssTable.put(8249.99, 360.00);
        sssTable.put(8749.99, 382.50);
        sssTable.put(9249.99, 405.00);
        sssTable.put(9749.99, 427.50);
        sssTable.put(10249.99, 450.00);
        sssTable.put(10749.99, 472.50);
        sssTable.put(11249.99, 495.00);
        sssTable.put(11749.99, 517.50);
        sssTable.put(12249.99, 540.00);
        sssTable.put(12749.99, 562.50);
        sssTable.put(13249.99, 585.00);
        sssTable.put(13749.99, 607.50);
        sssTable.put(14249.99, 630.00);
        sssTable.put(14749.99, 652.50);
        sssTable.put(15249.99, 675.00);
        sssTable.put(15749.99, 697.50);
        sssTable.put(16249.99, 720.00);
        sssTable.put(16749.99, 742.50);
        sssTable.put(17249.99, 765.00);
        sssTable.put(17749.99, 787.50);
        sssTable.put(18249.99, 810.00);
        sssTable.put(18749.99, 832.50);
        sssTable.put(19249.99, 855.00);
        sssTable.put(19749.99, 877.50);
        sssTable.put(20249.99, 900.00);
        sssTable.put(20749.99, 922.50);
        sssTable.put(21249.99, 945.00);
        sssTable.put(21749.99, 967.50);
        sssTable.put(22249.99, 990.00);
        sssTable.put(22749.99, 1012.50);
        sssTable.put(23249.99, 1035.00);
        sssTable.put(23749.99, 1057.50);
        sssTable.put(24249.99, 1080.00);
        sssTable.put(Double.MAX_VALUE, 1125.00); // For salaries 24,750 and above
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Employee> employees = null;
            boolean running = true;

            while (running) {
                System.out.println("\n========== Employee Management System ==========");
                System.out.println("1. Load Employee Data");
                System.out.println("2. Search Employee by ID and Calculate Salary");
                System.out.println("3. Display Employee Details");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                if (!scanner.hasNextLine()) {
                    System.out.println("Error: No input detected. Please enter a valid option.");
                    break;
                }

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter file path (JSON/TXT): ");
                        String filePath = scanner.nextLine().trim();
                        employees = EmployeeFileReader.readEmployeeData(filePath, null);
                    }

                    case "2" -> {
                        if (employees == null) {
                            System.out.println("Please load employee data first (Option 1).");
                            break;
                        }
                        System.out.print("Enter Employee ID: ");
                        String idInput = scanner.nextLine().trim();

                        if (idInput.isEmpty() || !idInput.matches("\\d+")) {
                            System.out.println("Invalid ID format.");
                            break;
                        }

                        int userId = Integer.parseInt(idInput);
                        Optional<Employee> employee = employees.stream()
                                .filter(emp -> emp.getEmployeeId() == userId)
                                .findFirst();

                        if (employee.isPresent()) {
                            displayEmployeeSalaryDetails(employee.get());
                        } else {
                            System.out.println("Employee not found.");
                        }
                    }

                    case "3" -> {
                        if (employees == null) {
                            System.out.println("Please load employee data first (Option 1).");
                            break;
                        }
                        System.out.print("Enter Employee ID: ");
                        String idInput = scanner.nextLine().trim();

                        if (idInput.isEmpty() || !idInput.matches("\\d+")) {
                            System.out.println("Invalid ID format.");
                            break;
                        }

                        int userId = Integer.parseInt(idInput);
                        Optional<Employee> employee = employees.stream()
                                .filter(emp -> emp.getEmployeeId() == userId)
                                .findFirst();

                        if (employee.isPresent()) {
                            System.out.println(employee.get().getEmployeeDetails());
                        } else {
                            System.out.println("Employee not found.");
                        }
                    }

                    case "4" -> {
                        System.out.println("Exiting the system. Goodbye!");
                        running = false;
                    }

                    default -> System.out.println("Invalid choice! Please try again.");
                }
            }
        }
    }

    private static double getSSSContribution(double salary) {
        return sssTable.ceilingEntry(salary).getValue();
    }

    private static void displayEmployeeSalaryDetails(Employee employee) {
        double basicSalary = employee.getBasicSalary();

        // Deduction calculations
        double sssDeduction = getSSSContribution(basicSalary);
        double philHealthDeduction = basicSalary * 0.015; // 1.5% PhilHealth
        double pagIbigDeduction = 100; // Fixed Pag-ibig Deduction

        double totalDeductions = sssDeduction + philHealthDeduction + pagIbigDeduction;
        double taxableIncome = basicSalary - totalDeductions;

        // Apply the updated withholding tax calculation
        double withholdingTax = calculateWithholdingTax(taxableIncome);

        // Display formatted salary breakdown
        System.out.println("\n========== EMPLOYEE SALARY DETAILS ==========");
        System.out.printf("Monthly Salary:       P%,.2f\n", basicSalary);
        System.out.printf("SSS Deduction:        P%,.2f\n", sssDeduction);
        System.out.printf("PhilHealth Deduction: P%,.2f\n", philHealthDeduction);
        System.out.printf("Pag-ibig Deduction:   P%,.2f\n", pagIbigDeduction);
        System.out.printf("TOTAL DEDUCTIONS:     P%,.2f\n", totalDeductions);
        System.out.printf("TAXABLE INCOME:       P%,.2f\n", taxableIncome);
        System.out.printf("WITHHOLDING TAX:      P%,.2f\n", withholdingTax);
        System.out.println("=============================================");
    }

    private static double calculateWithholdingTax(double taxableIncome) {
        if (taxableIncome <= 20832) {
            return 0;
        } else if (taxableIncome <= 33333) {
            return (taxableIncome - 20833) * 0.20;
        } else if (taxableIncome <= 66667) {
            return 2500 + (taxableIncome - 33333) * 0.25;
        } else if (taxableIncome <= 166667) {
            return 10833 + (taxableIncome - 66667) * 0.30;
        } else if (taxableIncome <= 666667) {
            return 40833.33 + (taxableIncome - 166667) * 0.32;
        } else {
            return 200833.33 + (taxableIncome - 666667) * 0.35;
        }
    }
}
