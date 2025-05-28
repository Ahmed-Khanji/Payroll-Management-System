// ---------------------------------------------------------
// Assignment 2
// Written by: Ahmed Khanji 40312726
// For COMP 249 Section H – Winter 2025
// ---------------------------------------------------------

/*
Using Java file I/O, exception handling, abstract classes, and payroll processing to
calculates salaries, deductions, and generates payroll reports.
 */

import java.util.*;
import java.io.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Fixed-size arrays with a counter
        Employee[] validEmployees = new Employee[0]; int validCount = 0;
        String[] errorEmployees = new String[0]; int errorCount = 0;
        String[] allEmployees = new String[0]; int allCount = 0;

        System.out.println("--------------------------------");
        System.out.println("Welcome to Payroll Manager!");
        System.out.println("--------------------------------");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("payroll.txt"));
            System.out.println("> Opening and Reading file payroll...");
            String line;

            while ((line = br.readLine()) != null) {
                // Add every employee (valid or invalid) to `allEmployees`
                allEmployees = resizeArray(allEmployees, allCount + 1);
                allEmployees[allCount++] = line;

                try {
                    String[] parts = line.split(" "); 
                    if (parts.length < 5) { throw new Exception("Incomplete data"); }

                    Employee employee = new Employee(
                            Long.parseLong(parts[0]),
                            parts[1], parts[2],
                            Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4])
                    );

                    validEmployees = resizeEmployeeArray(validEmployees, validCount + 1);
                    validEmployees[validCount++] = employee;
                }
                catch (MinimumWageException e) {
                    errorEmployees = resizeArray(errorEmployees, errorCount + 1);
                    errorEmployees[errorCount++] = line;
                }
                catch (NumberFormatException e) {
                    errorEmployees = resizeArray(errorEmployees, errorCount + 1);
                    errorEmployees[errorCount++] = line;
                }
            }
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
        finally {
            if (br != null) {
                try { br.close(); } catch (IOException e) { System.err.println(e.getMessage()); }
            }

            for (String emp : allEmployees) {
                System.out.println(emp);
            }
        }

        try (PrintWriter wrt = new PrintWriter(new FileOutputStream("payrollError.txt"))) {
            for (String errEmp : errorEmployees) {
                wrt.println(errEmp);
            }
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.println();
        System.out.println("> " + allCount + " lines read from file payroll");
        System.out.println("> " + errorCount + " lines written to error file");
        System.out.println("> Calculating deductions");
        System.out.println("> Writing report file");
        generateReport(validEmployees);

    }

    // Resize a string array dynamically
    public static String[] resizeArray(String[] oldArray, int newSize) {
        String[] newArray = new String[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }

    // Resize an Employee array dynamically
    public static Employee[] resizeEmployeeArray(Employee[] oldArray, int newSize) {
        Employee[] newArray = new Employee[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }

    // Generate Report
    public static void generateReport(Employee[] employees) {
        try (PrintWriter wrt = new PrintWriter(new FileOutputStream("payrollReport.txt"))) {
            // Writing Header
            wrt.println("                              iDroid Solutions                               ");
            wrt.println("                             ------------------                                ");
            wrt.println();

            // Column Headers with Proper Formatting
            wrt.printf("%-16s %-15s %-15s %-15s %-12s %-12s%n",
                    "Employee Number", "Employee Name", "Last Name", "Gross Salary", "Deductions", "Net Salary");
            wrt.println("-------------------------------------------------------------------------------------------");

            for (Employee emp : employees) {
                if (emp != null) {
                    double grossSalary = emp.getAnnualGrossSalary();
                    Deductions[] deductions = {
                            new EI(),
                            new QPIP(),
                            new QPP(),
                            new ProvincialTax(),
                            new FederalTax()
                    };
                    double deduction = 0;
                    for (Deductions d : deductions) {
                        deduction += d.calculateTax(emp.getAnnualGrossSalary());
                    }
                    double netSalary = emp.getAnnualGrossSalary() - deduction;

                    // Printing Employee Data with Formatting
                    wrt.printf("%-16d %-15s %-15s $%-14.2f $%-11.2f $%-11.2f%n",
                            emp.getEmployeeNumber(), emp.getFirstName(), emp.getLastName(),
                            grossSalary, deduction, netSalary);
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}