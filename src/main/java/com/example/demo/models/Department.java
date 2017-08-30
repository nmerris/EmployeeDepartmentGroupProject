package com.example.demo.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String name;

    // one Director may have many movies
    // will look for a field called director in another entity
    // CascadeType.ALL: basically will update everything in both tables at once, also if you delete a director, it
    // will delete all the movies associated with that director
    // FetchType.EAGER: will get ALL movies and ALSO ALL entities that are associated with each movie
    // so maybe a movie would also have 'stage hand' entity data associated with it, which will be available to a Director
    // as long as EAGER is used for FetchType
    // LAZY: would ONLY get the movies associated with this director, would NOT get any other entities data 'down the line'
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Employee> employees;


//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // when an administrator is entering a new department, they must specify the dept head employee id
    // if it's not in the employee repo, show a message saying that the employee was not found
//    public Employee departmentHead;

//    public Employee departmentHead;

    //@NotNull
    private long departmentHeadEmployeeId;
    private String departmentHeadFullName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

//    public long getDepartmentHeadEmployeeId() {
//        return departmentHeadEmployeeId;
//    }
//
//    public void setDepartmentHeadEmployeeId(long departmentHeadEmployeeId) {
//        this.departmentHeadEmployeeId = departmentHeadEmployeeId;
//    }
//
//    public Employee getDepartmentHead() {
//        return departmentHead;
//    }
//
//    public void setDepartmentHead(Employee departmentHead) {
//        this.departmentHead = departmentHead;
//    }


    public long getDepartmentHeadEmployeeId() {
        return departmentHeadEmployeeId;
    }

    public void setDepartmentHeadEmployeeId(long departmentHeadEmployeeId) {
        this.departmentHeadEmployeeId = departmentHeadEmployeeId;
    }

    public String getDepartmentHeadFullName() {
        return departmentHeadFullName;
    }

    public void setDepartmentHeadFullName(String departmentHeadFullName) {
        this.departmentHeadFullName = departmentHeadFullName;
    }
}
