package com.example.demo.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String firstName;

    private String lastName;

    private long cellNumber;

    private long workNumber;





    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
//    @NotNull
    private Department department;



    @OneToOne(mappedBy = "deptHeadEmployee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Department headOfThisDepartment;

    // use this to set this employee as a department head of Department d
    public void addAsDepartmentHead(Department d) {
        d.setDeptHeadEmployee(this);

        // this is it, nothing else to do here because there is only one
        // dept head for a dept, so there is no Set to add to, like in
        // Department.addEmployee
    }



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

    public Department getHeadOfThisDepartment() {
        return headOfThisDepartment;
    }

    public void setHeadOfThisDepartment(Department headOfThisDepartment) {
        this.headOfThisDepartment = headOfThisDepartment;
    }
}
