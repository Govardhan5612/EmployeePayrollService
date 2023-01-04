package com.bridgelabz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Govardhan Reddy
 */

public class EmployeePayIOService {
    static String payrollFileName = "payroll-file.txt";

    public void writeData(List<EmployeePayrollData> employeePayrollDataList) throws IOException {
        StringBuffer employeeBuffer = new StringBuffer();
        employeePayrollDataList.forEach(x -> {
            String employeeDataString = x.toString().concat("\n");
            employeeBuffer.append(employeeDataString);
        });
        try {
            Files.write(Paths.get(payrollFileName), employeeBuffer.toString().getBytes());
        } catch (IOException exception) {

        }
    }

    public void printData() {
        try {
            Files.lines(new File(payrollFileName).toPath()).forEach(System.out::println);
        } catch (IOException exception) {

        }
    }

    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File(payrollFileName).toPath()).count();
        } catch (IOException exception) {

        }
        return entries;
    }
}
