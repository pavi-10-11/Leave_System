package com.example.lms.dto;

public class LeaveData {
    private String employeeName;
    private String leaveType;
    private int leaveDays;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public LeaveData(String employeeName, String leaveType, int leaveDays) {
        this.employeeName = employeeName;
        this.leaveType = leaveType;
        this.leaveDays = leaveDays;
    }

    public LeaveData() {
    }
}
