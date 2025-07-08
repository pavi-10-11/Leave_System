package com.example.LMS.Repository;

import com.example.LMS.Entity.Leave;
import com.example.LMS.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByEmployee(Employee employee);
    List<Leave> findByFromDateBetween(LocalDate fromDate, LocalDate toDate);
}
