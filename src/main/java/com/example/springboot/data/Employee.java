package com.example.springboot.data;

public class Employee {
    private Long id;
    private String name;
    private String department;

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
