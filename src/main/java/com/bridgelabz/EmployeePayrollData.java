package com.bridgelabz;

/**
 * @author Govardhan Reddy
 */
public class EmployeePayrollData {
    /**
     * create a pojo class for employee details
     */
    String name;
    Integer id;
    Double salary;

    /**
     * create parameterised constructor
     */

    public EmployeePayrollData(String name, Integer id, Double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    /**
     * Create a default constructor
     */
    public EmployeePayrollData() {
    }

    /**
     * Override the toString method
     * This method given the string representation of corresponding object
     */

    @Override
    public String toString() {
        return "EmployeePayrollData{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", salary=" + salary +
                '}';
    }
}
