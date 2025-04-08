package com.child.profile;

import com.child.profile.data.Child;
import com.child.profile.data.Parent;
import com.child.profile.data.PersonCreator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

public class ParentMockCreator {

    public static List<Parent> createParentsMock() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, Calendar.DECEMBER, 31);
        Parent parent1 = createParentDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Parent parent2 = createParentDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Child child1 = createChildDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Child child2 = createChildDtoMock(calendar.getTime());
        calendar.set(2020, Calendar.DECEMBER, 31);
        Child child3 = createChildDtoMock(calendar.getTime());
        List<Child> children = List.of(child1, child2, child3);
        parent1.setChildren(children);
        parent2.setChildren(children);
        return List.of(parent1, parent2);

    }

    public static List<Parent> createParentMockNo3Children() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, Calendar.DECEMBER, 31);
        Parent parent1 = createParentDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Parent parent2 = createParentDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Child child1 = createChildDtoMock(calendar.getTime());
        calendar.set(2020, Calendar.DECEMBER, 31);
        Child child2 = createChildDtoMock(calendar.getTime());
        List<Child> children = List.of(child1, child2);
        parent1.setChildren(children);
        parent2.setChildren(children);
        return List.of(parent1, parent2);
    }

    public static List<Parent> createParentNo18Mock() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, Calendar.DECEMBER, 31);
        Parent parent1 = createParentDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Parent parent2 = createParentDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Child child1 = createChildDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Child child2 = createChildDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Child child3 = createChildDtoMock(calendar.getTime());
        List<Child> children = List.of(child1, child2, child3);
        parent1.setChildren(children);
        parent2.setChildren(children);
        return List.of(parent1, parent2);
    }
    public static List<Parent> createParentNoChildId() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, Calendar.DECEMBER, 31);
        Parent parent1 = createParentDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Parent parent2 = createParentDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Child child1 = createChildDtoMock(calendar.getTime());
        child1.setId(0);
        calendar.set(1980, Calendar.DECEMBER, 31);
        Child child2 = createChildDtoMock(calendar.getTime());
        calendar.set(2020, Calendar.DECEMBER, 31);
        Child child3 = createChildDtoMock(calendar.getTime());
        List<Child> children = List.of(child1, child2, child3);
        parent1.setChildren(children);
        parent2.setChildren(children);
        return List.of(parent1, parent2);
    }

    public static List<Parent> createParentMockNoId() {
        PersonCreator deletedChildCreator = new PersonCreator();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, Calendar.DECEMBER, 31);
        Parent parent1 = createParentDtoMock(calendar.getTime());
        parent1.setId(-1);
        calendar.set(1980, Calendar.DECEMBER, 31);
        Parent parent2 = createParentDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Child child1 = createChildDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Child child2 = createChildDtoMock(calendar.getTime());
        calendar.set(2020, Calendar.DECEMBER, 31);
        Child child3 = createChildDtoMock(calendar.getTime());
        List<Child> children = List.of(child1, child2, child3);
        parent1.setChildren(children);
        parent2.setChildren(children);
        return List.of(parent1, parent2);
    }
    private static Parent createParentDtoMock(Date birthDate) {
        Parent parent = new Parent();
        parent.setId(1);
        parent.setName("John Doe");
        parent.setBirthDate(birthDate);
        parent.setPartner("Jane Doe");
        parent.setChildren(List.of(new Child(), new Child(), new Child()));

        return parent;
    }

    private static Child createChildDtoMock(Date birthDate) {
        Child child = new Child();
        child.setId(1);
        child.setName("Child Doe");
        child.setBirthDate(birthDate);
        child.setParent1("John Doe");
        child.setParent2("Jane Doe");
        return child;
    }
}
