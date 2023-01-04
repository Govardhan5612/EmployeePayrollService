package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Govardhan Reddy
 */
public class EmployeePayrollService {
    /**
     * print the employee details in console
     * create enum class
     */
    public enum IOService {
        CONSOLE_IO,
        FILE_IO,
        DB_IO,
        REST_IO;
    }

    List<EmployeePayrollData> employeePayrollDataList;
    Scanner input = new Scanner(System.in);

    /**
     * create default constructor
     */
    public EmployeePayrollService() {

    }

    /**
     * create a parameterised constructor
     */
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollDataList) {
        this.employeePayrollDataList = employeePayrollDataList;
    }

    public void writeEmployeePayRollData() {
        /**
         * In this method add employee details to the list
         */
        System.out.print("Enter name : ");
        String name = input.next();
        System.out.print("Enter id : ");
        Integer id = input.nextInt();
        System.out.print("Enter salary : ");
        Double salary = input.nextDouble();
        employeePayrollDataList.add(new EmployeePayrollData(name, id, salary));
    }

    public void readEmployeePayrollData() {
        /**
         * In this method read the employee details list
         */
        System.out.println("Employee payroll data : " + employeePayrollDataList);
    }

    public long count() {
        long count = employeePayrollDataList.size();
        return count;
    }

    public static void main(String[] args) {
        /**
         * In this method call the object level methods
         */
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
        employeePayrollService.writeEmployeePayRollData();
        employeePayrollService.readEmployeePayrollData();

    }
}
