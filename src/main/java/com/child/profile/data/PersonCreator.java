package com.child.profile.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class PersonCreator {

    public List<Parent> createPersons() {
        List<Parent> parents = new ArrayList<>();
        Parent parent1 = new Parent();
        Parent parent2 = new Parent();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, Calendar.DECEMBER, 31);
        parent1.setId(1);
        parent1.setName("John Doe");
        parent1.setBirthDate(calendar.getTime());
        parent1.setPartner("Jane Doe");
        calendar.set(1982, Calendar.JANUARY, 1);
        parent1.setBirthDate(calendar.getTime());

        parent2.setId(2);
        parent2.setName("Jane Doe");
        parent2.setBirthDate(calendar.getTime());
        parent2.setPartner("John Doe");

        Child child1 = new Child();
        child1.setId(3);
        child1.setName("Child 1");
        calendar.set(2015, Calendar.JUNE, 21);
        child1.setBirthDate(calendar.getTime());
        child1.setParent1("John Doe");
        child1.setParent2("Jane Doe");

        Child child2 = new Child();
        calendar.set(2020, Calendar.NOVEMBER, 10);
        child2.setId(4);
        child2.setName("Child 2");
        child2.setParent1("John Doe");
        child2.setParent2("Jane Doe");
        child2.setBirthDate(calendar.getTime());

        Child child3 = new Child();
        calendar.set(2002, Calendar.AUGUST, 25);
        child3.setId(5);
        child3.setName("Child 3");
        child3.setParent1("John Doe");
        child3.setParent2("Jane Doe");
        child3.setBirthDate(calendar.getTime());


        parent1.setChildren(List.of(child1, child2, child3));
        parent2.setChildren(List.of(child1, child2, child3));
        parents.add(parent1);
        parents.add(parent2);
        return parents;
    }

    public void deletePerson(int id) {
        createPersons().removeIf(person -> person.getId() == id);
    }

    public String addPerson(List<Parent> newParents) {
        List<Parent> parents = createPersons();
        for (Parent person : newParents) {
            if (person.getId() == null) {
               return "Person ID cannot be null";
            }
        }
              parents.addAll(newParents)  ;
       return "Person added successfully";
    }

    public void updatePerson(int id, Parent updatedPerson) {
        List<Parent> persons = createPersons();
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getId() == id) {
                persons.set(i, updatedPerson);
                break;
            }
        }
    }

    public List<Parent> deleteChild(int id) {
        List<Parent> deleted = createPersons();
        for (Parent person : deleted) {
            List<Child> children = person.getChildren();
            if (children != null) {
                children.forEach(child -> {
                    deletePerson(id);
                });
            }
        }
        return deleted;
    }

    public List<Parent> addOlderChild() {
        List<Parent> deleted = createPersons();
        for (Parent person : deleted) {
            List<Child> children = person.getChildren();

            List<Child> newChildren = new ArrayList<>(children);
            newChildren.removeIf(child -> child.getId() == 3);

            List<Child> childrenToAdd = new ArrayList<>();
            Child newChild = new Child();
            newChild.setId(6);
            newChild.setName("Deleted Child");
            Calendar calendar = Calendar.getInstance();
            calendar.set(1980, Calendar.DECEMBER, 31);
            newChild.setBirthDate(calendar.getTime());
            childrenToAdd.add(newChild);
            newChildren.addAll(childrenToAdd);
            person.setChildren(newChildren);
        }
        return deleted;    }


}
