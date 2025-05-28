public class Employee {
    // Attributes
    private long employeeNumber;
    private String firstName;
    private String lastName;
    private double hoursWorked;
    private double hourlyWage;
    private double annualGrossSalary;

    // Constructor with Minimum Wage Exception and Copy Constructor
    public Employee(long employeeNumber, String firstName, String lastName,
                    double hoursWorked, double hourlyWage) throws MinimumWageException
    {
        if (hourlyWage < 15.75) {throw new MinimumWageException();}

        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hoursWorked = hoursWorked;
        this.hourlyWage = hourlyWage;
        this.annualGrossSalary = hoursWorked * hourlyWage * 52;
    }
    public Employee(Employee other) {
        this.employeeNumber = other.employeeNumber;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.hoursWorked = other.hoursWorked;
        this.hourlyWage = other.hourlyWage;
    }

    // Accessors & Mutators
    public long getEmployeeNumber() {return employeeNumber;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public double getHoursWorked() {return hoursWorked;}
    public double getHourlyWage() {return hourlyWage;}
    public double getAnnualGrossSalary() {return Math.round(annualGrossSalary * 100.0) / 100.0;}
    public void setEmployeeNumber(long employeeNumber) {this.employeeNumber = employeeNumber;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setHoursWorked(double hoursWorked) {this.hoursWorked = hoursWorked;}
    public void setHourlyWage(double hourlyWage) {this.hourlyWage = hourlyWage;}
    public void setAnnualGrossSalary(double annualGrossSalary) {this.annualGrossSalary = annualGrossSalary;}

    // toString & equals method
    public String toString() {
        return  "Employee Number: " + employeeNumber + ", First Name: " + firstName +
                ", Last Name: " + lastName + ", Hours Worked: " + hoursWorked +
                ", Hourly Wage: " + hourlyWage + ", Annual Gross Salary: " + annualGrossSalary;
    }
    public boolean equals(Object other) {
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        Employee otherEmployee = (Employee) other;

        return employeeNumber == otherEmployee.employeeNumber && firstName.equals(otherEmployee.firstName)
                && lastName.equals(otherEmployee.lastName) && hoursWorked == otherEmployee.hoursWorked
                && hourlyWage == otherEmployee.hourlyWage;
    }

}
