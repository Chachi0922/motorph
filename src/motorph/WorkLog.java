package motorph;

import java.time.Duration;
import java.time.LocalDateTime;

public class WorkLog {
    private int employeeId;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
    private long totalWorkMinutes = 0;

    public WorkLog(int employeeId) {
        this.employeeId = employeeId;
    }

    public void logIn() {
        if (loginTime != null) {
            System.out.println("Employee " + employeeId + " is already logged in.");
            return;
        }
        loginTime = LocalDateTime.now();
        System.out.println("Employee " + employeeId + " logged in at: " + loginTime);
    }

    public void logOut() {
        if (loginTime == null) {
            System.out.println("Employee " + employeeId + " has not logged in yet.");
            return;
        }
        logoutTime = LocalDateTime.now();
        long minutesWorked = Duration.between(loginTime, logoutTime).toMinutes();
        totalWorkMinutes += minutesWorked;

        System.out.println("Employee " + employeeId + " logged out at: " + logoutTime);
        System.out.println("Work session duration: " + minutesWorked + " minutes");

        loginTime = null;
    }

    public void displayTotalHours() {
        System.out.println("Employee " + employeeId + " has worked " + (totalWorkMinutes / 60) + " hours and " + (totalWorkMinutes % 60) + " minutes.");
    }
}
