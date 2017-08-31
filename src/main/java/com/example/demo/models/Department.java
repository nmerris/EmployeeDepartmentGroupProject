package com.example.demo.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String name;



    // unclear why this is necessary, need to undestand this
//    public Department() {
//        this.employees = new HashSet<Employee>();
//    }



    // CascadeType.ALL: basically will update everything in both tables at once, also if you delete a director, it
    // will delete all the movies associated with that director
    // FetchType.EAGER: will get ALL movies and ALSO ALL entities that are associated with each movie
    // so maybe a movie would also have 'stage hand' entity data associated with it, which will be available to a Director
    // as long as EAGER is used for FetchType
    // LAZY: would ONLY get the movies associated with this director, would NOT get any other entities data 'down the line'
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Employee> employees;

    // BIG NOTE: in the JPA world.. it is NOT enough to just 'set the Department entities deptHeadEmployee_id column to
    // and id of an employee'.. you MUST actually attach an entire Employee object to a Department and THEN save the department
    // othewise the relationship will not be complete.. you'll end up with a bunch of null values if you try to access,
    // for example, department.employee.name (I believe the ID is the only field in employee that would not be null if you
    // failed to attach a complete Employee when you were adding it to a Department
    public void addEmployee(Employee employee) {
        employee.setDepartment(this);
        employees.add(employee);
    }

    // in order to delete and employee, you must first remove it from it's department's Set of employees
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }



    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deptHeadEmployee_id")
    private Employee deptHeadEmployee;



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

    public Employee getDeptHeadEmployee() {
        return deptHeadEmployee;
    }

    public void setDeptHeadEmployee(Employee deptHeadEmployee) {
        this.deptHeadEmployee = deptHeadEmployee;
    }
}
