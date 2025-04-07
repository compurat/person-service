package com.child.profile.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parent extends Person {
    private List<Child> children = new ArrayList<>();
    private Parent partner;

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public Parent getPartner() {
        return partner;
    }

    public void setPartner(Parent partner) {
        this.partner = partner;
    }

    public boolean checkPartner() {
        return partner != null;
    }
}
