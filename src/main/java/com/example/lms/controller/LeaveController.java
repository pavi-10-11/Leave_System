package com.example.lms.controller;

import com.example.lms.dto.LeaveData;
import com.example.lms.entity.Leave;
import com.example.lms.entity.Employee;
import com.example.lms.service.EmployeeService;
import com.example.lms.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private final LeaveService leaveService;

    @Autowired
    private final EmployeeService empService;

    @Autowired
    private final EmployeeController employeeController;

    public LeaveController(LeaveService leaveService, EmployeeService empService, EmployeeController employeeController) {
        this.leaveService = leaveService;
        this.empService = empService;
        this.employeeController = employeeController;
    }


    @PostMapping("/apply")
    public ResponseEntity<Leave> applyLeave(@RequestBody Leave leave) {
        Employee employee = employeeController.get(leave.getEmployee().getEmployeeId()).getBody();
        if (employee == null) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request
        }
        Leave l = new Leave();
        l.setEmployee(employee);
        l.setLeaveId(leave.getLeaveId());
        l.setLeaveType(leave.getLeaveType());
        l.setReason(leave.getReason());
        l.setFromDate(leave.getFromDate());
        l.setToDate(leave.getToDate());

        Leave savedLeave = leaveService.applyLeave(l);
        return ResponseEntity.ok(savedLeave);
    }


    @GetMapping("/by-employee/{empId}")
    public ResponseEntity<List<Leave>> getLeavesByEmployee(@PathVariable String empId) {
        Employee emp = empService.getById(empId);
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }

        List<Leave> leaves = leaveService.getLeavesByEmployee(emp);
        return ResponseEntity.ok(leaves);
    }


    @GetMapping("/between")
    public ResponseEntity<List<Leave>> getLeavesBetween(
            @RequestParam String fromDate,
            @RequestParam String toDate) {
        try {
            LocalDate from = LocalDate.parse(fromDate);
            LocalDate to = LocalDate.parse(toDate);
            List<Leave> leaves = leaveService.getLeavesBetween(from, to);
            return ResponseEntity.ok(leaves);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/leaveDetails")
    public ResponseEntity<List<LeaveData>> getLeaveDetails() {
        List<LeaveData> leaveSummary = leaveService.getLeaveData();
        return ResponseEntity.ok(leaveSummary);
    }
}