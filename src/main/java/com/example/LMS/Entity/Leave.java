package com.example.LMS.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "leave_table") // Optional, because 'leave' is a reserved SQL keyword
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;

    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;


    public Leave() {}


    public Leave(Employee employee, LocalDate fromDate, LocalDate toDate, String reason, LeaveType leaveType) {
        this.employee = employee;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.leaveType = leaveType;
    }



    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }
}
