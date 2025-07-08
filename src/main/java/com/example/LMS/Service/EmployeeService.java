package com.example.LMS.Service;

import com.example.LMS.Entity.Employee;
import com.example.LMS.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
