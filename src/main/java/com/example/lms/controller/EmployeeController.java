package com.example.lms.controller;

import com.example.lms.entity.Employee;
import com.example.lms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

   @Autowired
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = service.getAllEmployees();
        return ResponseEntity.ok(employees);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        Employee emp = service.get(id);
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emp);
    }


    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) {
        Employee saved = service.save(emp);
        return ResponseEntity.status(201).body(saved);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee emp) {
        Employee existing = service.get(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }


        existing.setFirstName(emp.getFirstName());
        existing.setLastName(emp.getLastName());
        existing.setEmail(emp.getEmail());
        existing.setPhoneNumber(emp.getPhoneNumber());
        existing.setDept(emp.getDept());

        Employee updated = service.save(existing);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Employee existing = service.get(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
