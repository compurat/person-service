package com.child.profile.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parent extends Person {
    private List<Child> children = new ArrayList<>();
    private String partner;

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
