package com.example.demo.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String teamName;

    @ManyToMany(mappedBy = "teams")
    private Set<Employee> teamMembers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Set<Employee> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set<Employee> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public Team()
    {
        this.teamMembers=new HashSet<Employee>();
    }

    public void addteammember(Employee e){

        this.teamMembers.add(e);

    }

}
