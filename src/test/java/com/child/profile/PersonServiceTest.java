package com.child.profile;

import com.child.profile.config.FileStorageConfig;
import com.child.profile.data.Child;
import com.child.profile.data.Parent;
import com.child.profile.data.PersonCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private PersonCreator personCreator;
    @Mock
    private FileStorageConfig fileStorageConfig;
    @InjectMocks
    private PersonService personService;

    @Test
    public void testRetrieveChildProfile() {
        PersonCreator deletedChildCreator = new PersonCreator();
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
        when(personCreator.createPersons()).thenReturn(List.of(parent1, parent2));
        when(fileStorageConfig.getFileStoragePath()).thenReturn("src/test/resources");
        String content = convertFileToString(personService.retrieveChildProfile());

         assertEquals(864, content.length());
    }

    @Test
    public void testRetrieveChildProfileFilterNo3Children() {
        PersonCreator deletedChildCreator = new PersonCreator();
        when(personCreator.createPersons()).thenReturn(deletedChildCreator.deleteChild(3));
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
        when(personCreator.createPersons()).thenReturn(List.of(parent1, parent2));
        when(fileStorageConfig.getFileStoragePath()).thenReturn("src/test/resources");
        String content = convertFileToString(personService.retrieveChildProfile());
        assertEquals("VGhlcmUgd2hlcmUgbm8gMyBjaGlsZHJlbiBmb3VuZA==", content);
    }

    @Test
    public void testRetrieveChildProfileFilterNoEighteen() {
        PersonCreator deletedChildCreator = new PersonCreator();
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
        when(personCreator.createPersons()).thenReturn(List.of(parent1, parent2));
        when(fileStorageConfig.getFileStoragePath()).thenReturn("src/test/resources");
        String content = convertFileToString(personService.retrieveChildProfile());
        assertEquals("Tm8gY2hpbGRyZW4gdW5kZXIgMTg=", content);

    }

    @Test
    public void testAddPersonNoParentId() {
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
        String content = personService.createPerson(List.of(parent1, parent2));
        assertEquals("Parent data is not valid", content);

    }


    private Parent createParentDtoMock(Date birthDate) {
        Parent parent = new Parent();
        parent.setName("John Doe");
        parent.setBirthDate(birthDate);
        parent.setPartner("Jane Doe");
        parent.setChildren(List.of(new Child(), new Child(), new Child()));
        return parent;
    }


    private Child createChildDtoMock(Date birthDate) {
        Child child = new Child();
        child.setName("Child Doe");
        child.setBirthDate(birthDate);
        child.setParent1("John Doe");
        child.setParent2("Jane Doe");
             return child;
    }

    public String convertFileToString(File fileToConvert) {
        String content = null;
        try {
            content = Files.readString(fileToConvert.toPath());
            System.out.println("File content: " + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}