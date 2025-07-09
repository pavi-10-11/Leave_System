package com.example.lms.repository;

import com.example.lms.entity.Leave;
import com.example.lms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByEmployee(Employee employee);
    List<Leave> findByFromDateBetween(LocalDate fromDate, LocalDate toDate);
}
