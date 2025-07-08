package com.example.LMS.Service;

import com.example.LMS.Entity.Leave;
import com.example.LMS.Entity.Employee;
import com.example.LMS.Repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
}
