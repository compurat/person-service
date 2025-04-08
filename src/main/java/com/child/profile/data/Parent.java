package com.child.profile.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parent {
    private List<Child> children = new ArrayList<>();
    private String partner;
    private Integer id;
    private String name;
    private Date birthDate;

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
    public List<Child> getChildren() {
        return children;
    }
    public void setChildren(List<Child> children) {
        this.children = children;
    }
    public String getPartner() {
        return partner;
    }
    public void setPartner(String partner) {
        this.partner = partner;
    }
    public boolean checkPartner() {
        return partner != null;
    }
}
