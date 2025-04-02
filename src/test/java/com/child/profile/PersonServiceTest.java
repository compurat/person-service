package com.child.profile;

import com.child.profile.data.Person;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private PersonCreator personCreator;
    @InjectMocks
    private PersonService personService;

    @Test
    public void testRetrieveChildProfile() {
        PersonCreator deletedChildCreator = new PersonCreator();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, Calendar.DECEMBER, 31);
        Person person1 = createPersonDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Person person2 = createPersonDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Person child1 = createPersonDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Person child2 = createPersonDtoMock(calendar.getTime());
        calendar.set(2020, Calendar.DECEMBER, 31);
        Person child3 = createPersonDtoMock(calendar.getTime());
        List<Person> children = List.of(child1, child2, child3);
        person1.setChildren(children);
        person2.setChildren(children);
        when(personCreator.createPersons()).thenReturn(List.of(person1, person2));
        String content = convertFileToString(personService.retrieveChildProfile());

        assertEquals(872, content.length());
    }

    @Test
    public void testRetrieveChildProfileFilterNo3Children() {
        PersonCreator deletedChildCreator = new PersonCreator();
        when(personCreator.createPersons()).thenReturn(deletedChildCreator.deleteChild(3));
        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, Calendar.DECEMBER, 31);
        Person person1 = createPersonDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Person person2 = createPersonDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Person child1 = createPersonDtoMock(calendar.getTime());
        calendar.set(2020, Calendar.DECEMBER, 31);
        Person child2 = createPersonDtoMock(calendar.getTime());
        List<Person> children = List.of(child1, child2);
        person1.setChildren(children);
        when(personCreator.createPersons()).thenReturn(List.of(person1, person2));
        String content = convertFileToString(personService.retrieveChildProfile());
        assertEquals("VGhlcmUgd2hlcmUgbm8gMyBjaGlsZHJlbiBmb3VuZA==", content);
    }

    @Test
    public void testRetrieveChildProfileFilterNoEighteen() {
        PersonCreator deletedChildCreator = new PersonCreator();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, Calendar.DECEMBER, 31);
        Person person1 = createPersonDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Person person2 = createPersonDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Person child1 = createPersonDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Person child2 = createPersonDtoMock(calendar.getTime());
        calendar.set(1980, Calendar.DECEMBER, 31);
        Person child3 = createPersonDtoMock(calendar.getTime());
        List<Person> children = List.of(child1, child2, child3);
        person1.setChildren(children);
        person2.setChildren(children);
        when(personCreator.createPersons()).thenReturn(List.of(person1, person2));
        String content = convertFileToString(personService.retrieveChildProfile());
        assertEquals("Tm8gY2hpbGRyZW4gdW5kZXIgMTg=", content);

    }

    private Person createPersonDtoMock(Date birthDate) {
        Person person = new Person();
        person.setName("John Doe");
        person.setBirthDate(birthDate);
        person.setParent1(new Person());
        person.setParent2(new Person());
        person.setPartner(new Person());
        return person;
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