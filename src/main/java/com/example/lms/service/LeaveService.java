package com.example.lms.service;

import com.example.lms.dto.LeaveData;
import com.example.lms.entity.Leave;
import com.example.lms.entity.Employee;
import com.example.lms.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveService {
    private final LeaveRepository repository;

    public LeaveService(LeaveRepository repository) {
        this.repository = repository;
    }

    public Leave applyLeave(Leave leave) {
        return repository.save(leave);
    }

    public List<Leave> getLeavesByEmployee(Employee emp) {
        return repository.findByEmployee(emp);
    }

    public List<Leave> getLeavesBetween(LocalDate from, LocalDate to) {
        return repository.findByFromDateBetween(from, to);
    }

    public List<LeaveData> getLeaveData() {
        List<Leave> allLeaves = repository.findAll();
        List<LeaveData> result = new ArrayList<>();

        for (Leave leave : allLeaves) {
            String empName = leave.getEmployee().getFirstName();
            String leaveType = leave.getLeaveType().toString();
            int days = (int) (leave.getToDate().toEpochDay() - leave.getFromDate().toEpochDay() + 1);

            boolean found = false;
            for (LeaveData data : result) {
                if (data.getEmployeeName().equals(empName) && data.getLeaveType().equals(leaveType)) {
                    data.setLeaveDays(data.getLeaveDays() + days);
                    found = true;
                    break;
                }
            }

            if (!found) {
                result.add(new LeaveData(empName, leaveType, days));
            }
        }

        return result;
    }
}