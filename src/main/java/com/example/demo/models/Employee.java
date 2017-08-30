package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private long cellNumber;

    private long workNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    // director_id is just the name we are choosing to call the JOIN column, you can name it anything you want, but
    // [entity-name]_id is convention
    @JoinColumn(name = "department_id")
//    @NotNull
    private Department department;


//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "departmentHead_id")
//    private Employee departmentHead;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(long cellNumber) {
        this.cellNumber = cellNumber;
    }

    public long getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(long workNumber) {
        this.workNumber = workNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
