package com.child.profile.data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class Child {
    private Integer id;
    private String name;
    private Date birthDate;
    private String parent1;
    private String parent2;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public String getParent1() {
        return parent1;
    }
    public void setParent1(String parent1) {
        this.parent1 = parent1;
    }
    public String getParent2() {
        return parent2;
    }
    public void setParent2(String parent2) {
        this.parent2 = parent2;
    }

}
