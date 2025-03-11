package motorph;

public class Employee {
    private final int employeeId;
    private final String lastName, firstName, birthday, address, phoneNumber, sss, philHealth, tin, pagIbig, status, position;
    private final double basicSalary, riceAllowance, subsidy, phoneAllowance, clothingAllowance, grossSemiMonthlyRate, hourlyRate;

    public Employee(int employeeId, String lastName, String firstName, String birthday, String address, String phoneNumber, 
                    String sss, String philHealth, String tin, String pagIbig, String status, String position, 
                    double basicSalary, double riceAllowance, double subsidy, double phoneAllowance, 
                    double clothingAllowance, double grossSemiMonthlyRate, double hourlyRate) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sss = sss;
        this.philHealth = philHealth;
        this.tin = tin;
        this.pagIbig = pagIbig;
        this.status = status;
        this.position = position;
        this.basicSalary = basicSalary;
        this.riceAllowance = riceAllowance;
        this.subsidy = subsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
        this.hourlyRate = hourlyRate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getGrossSemiMonthlyRate() {
        return grossSemiMonthlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public String getStatus() {
        return status;
    }

    public String getPosition() {
        return position;
    }

    public String getBirthday() {
        return birthday;
    }

    /**
     * Returns a formatted string with employee details.
     * @return Employee name, number, and birthday.
     */
    public String getEmployeeDetails() {
        return String.format("Employee Name: %s %s | Employee ID: %d | Birthday: %s", firstName, lastName, employeeId, birthday);
    }
}
