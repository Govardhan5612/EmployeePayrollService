package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Govardhan Reddy
 */
public class EmployeePayrollServiceTest {
    @Test
    public void given3EmployeeWhenWrittenToFileShouldMatchEmployeeEntries() {
        EmployeePayrollData[] arrayOfEmployee = {
                new EmployeePayrollData("Govardhan", 1, 10000.0),
                new EmployeePayrollData("Raja", 2, 10000.0),
                new EmployeePayrollData("Vignesh", 3, 10000.0),
        };
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmployee));
        employeePayrollService.writeEmployeePayRollData();
        employeePayrollService.readEmployeePayrollData();
        long entries = employeePayrollService.count();
        Assert.assertEquals(3, entries);
    }
    @Test
    public void givenFileOnReadingFromFileMatchTheEmployeeCount(){
        EmployeePayrollService employee = new EmployeePayrollService();
        long entries = employee.count();
        Assert.assertEquals(3, entries);
    }
}
