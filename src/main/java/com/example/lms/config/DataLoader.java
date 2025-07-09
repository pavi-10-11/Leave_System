package com.example.lms.config;

import com.example.lms.entity.Employee;
import com.example.lms.entity.Leave;
import com.example.lms.entity.LeaveType;
import com.example.lms.service.EmployeeService;
import com.example.lms.service.LeaveService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveService leaveService;

    @Override
    public void run(String... args) {
        try {
            ObjectMapper mapper = new ObjectMapper();


            System.out.println(" Loading employees...");
            InputStream empStream = new ClassPathResource("data/employee.json").getInputStream();
            List<Employee> employees = Arrays.asList(mapper.readValue(empStream, Employee[].class));
            for (Employee emp : employees) {
                employeeService.save(emp);
            }
            System.out.println(" Employees loaded successfully!");

            // Load leaves
            System.out.println("Loading leaves...");
            InputStream leaveStream = new ClassPathResource("data/leaves.json").getInputStream();
            List<Map<String, Object>> leaveData = mapper.readValue(leaveStream, new TypeReference<>() {});
            for (Map<String, Object> data : leaveData) {
                try {
                    String empId = data.get("employeeId").toString();
                    Employee emp = employeeService.get(empId);
                    if (emp != null) {
                        Leave leave = new Leave();
                        leave.setEmployee(emp);
                        leave.setLeaveType(LeaveType.valueOf(data.get("leaveType").toString()));
                        leave.setReason(data.get("reason").toString());
                        leave.setFromDate(LocalDate.parse(data.get("fromDate").toString()));
                        leave.setToDate(LocalDate.parse(data.get("toDate").toString()));
                        leaveService.applyLeave(leave);
                    } else {
                        System.err.println("Employee not found for ID: " + empId);
                    }
                } catch (Exception e) {
                    System.err.println(" Error parsing leave record: " + data);
                    e.printStackTrace();
                }
            }
            System.out.println(" Leaves loaded successfully!");

        } catch (Exception e) {
            System.err.println(" Data loading failed:");
            e.printStackTrace();
        }
    }
}
