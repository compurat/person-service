package com.child.profile.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class PersonCreator {

    public List<Person> createPersons() {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        Person person2 = new Person();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, Calendar.DECEMBER, 31);
        person1.setId(1);
        person1.setName("John Doe");
        person1.setParent1(person1);
        person1.setParent2(person2);
        person1.setBirthDate(calendar.getTime());
        person1.setPartner(person2);
        calendar.set(1982, Calendar.JANUARY, 1);
        person1.setBirthDate(calendar.getTime());

        person2.setId(2);
        person2.setName("Jane Doe");
        person2.setParent1(person1);
        person2.setParent2(person2);
        person2.setBirthDate(calendar.getTime());
        person2.setPartner(person1);
        Person child1 = new Person();
        child1.setId(3);
        child1.setName("Child 1");
        child1.setParent1(person1);
        child1.setParent2(person2);
        calendar.set(2015, Calendar.JUNE, 21);
        child1.setBirthDate(calendar.getTime());

        Person child2 = new Person();
        calendar.set(2020, Calendar.NOVEMBER, 10);
        child2.setId(4);
        child2.setName("Child 2");
        child2.setParent1(person1);
        child2.setParent2(person2);
        child2.setBirthDate(calendar.getTime());

        Person child3 = new Person();
        calendar.set(2002, Calendar.AUGUST, 25);
        child3.setId(5);
        child3.setName("Child 3");
        child3.setParent1(person1);
        child3.setParent2(person2);
        child3.setBirthDate(calendar.getTime());
        child3.setParent1(person1);
        child3.setParent2(person2);


        person1.setChildren(List.of(child1, child2, child3));
        person2.setChildren(List.of(child1, child2, child3));
        persons.add(person1);
        persons.add(person2);
        return persons;
    }

    public void deletePerson(int id) {
        createPersons().removeIf(person -> person.getId() == id);
    }

    public void addPerson(Person person) {
        createPersons().add(person);
    }

    public void updatePerson(int id, Person updatedPerson) {
        List<Person> persons = createPersons();
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getId() == id) {
                persons.set(i, updatedPerson);
                break;
            }
        }
    }

    public List<Person> deleteChild(int id) {
        List<Person> deleted = createPersons();
        for (Person person : deleted) {
            List<Person> children = person.getChildren();
            if (children != null) {
                children.forEach(child -> {
                    deletePerson(id);
                });
            }
        }
        return deleted;
    }

    public List<Person> addOlderChild() {
        List<Person> deleted = createPersons();
        for (Person person : deleted) {
            List<Person> children = person.getChildren();

            List<Person> newChildren = new ArrayList<>(children);
            newChildren.removeIf(child -> child.getId() == 3);

            List<Person> childrenToAdd = new ArrayList<>();
            Person newChild = new Person();
            newChild.setId(6);
            newChild.setName("Deleted Child");
            newChild.setParent1(new Person());
            newChild.setParent2(new Person());
            Calendar calendar = Calendar.getInstance();
            calendar.set(1980, Calendar.DECEMBER, 31);
            newChild.setBirthDate(calendar.getTime());
            childrenToAdd.add(newChild);
            newChildren.addAll(childrenToAdd);
            person.setChildren(newChildren);
        }
        return deleted;    }


}
