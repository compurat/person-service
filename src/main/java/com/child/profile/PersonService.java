package com.child.profile;

import com.child.profile.data.Person;
import com.child.profile.data.PersonCreator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PersonService {

    private final PersonCreator personCreator;

    PersonService(PersonCreator personCreator) {
        this.personCreator = personCreator;
    }

    public File retrieveChildProfile() {
        List<Person> persons = personCreator.createPersons();
        StringBuilder personsInfo = new StringBuilder();
        for (Person person : persons) {
            List<Person> children = person.getChildren();
            if (!(checkChildrenAge(children))) {
                return createFamilyFile("No children under 18");
            }

            if (children.size() == 3) {
                personsInfo.append("Name: ").append(person.getName()).append(",");
                personsInfo.append("Birth Date: ").append(person.getBirthDate()).append(",");
                personsInfo.append("Parent 1: ").append(person.getParent1().getName()).append(",");
                personsInfo.append("Parent 2: ").append(person.getParent2().getName()).append(",");

                if (!children.isEmpty()) {
                    personsInfo.append(childrenInfo(children));
                }

            } else {
               return createFamilyFile("There where no 3 children found");
            }
        }
        if (!personsInfo.isEmpty()) {
           return createFamilyFile(personsInfo.toString());
        }
        return createFamilyFile("No data found");
    }

    private File createFamilyFile(String content) {
        File familyFile = null;
        try {
            familyFile = Files.writeString(Path.of("family.txt"), Base64.getEncoder().encodeToString(content.getBytes())).toFile();
        } catch (IOException ioe) {
            System.out.println("Error writing to file: " + ioe.getMessage());
        }
        return familyFile;
    }

    private String childrenInfo(List<Person> children) {
        StringBuilder childrenInfo = new StringBuilder();
        for (Person child : children) {
            childrenInfo.append(child.getName()).append(",");
            childrenInfo.append("Birth Date: ").append(child.getBirthDate()).append(",");
            childrenInfo.append("Parent 1: ").append(child.getParent1().getName()).append(",");
            childrenInfo.append("Parent 2: ").append(child.getParent2().getName()).append(",");

        }
        return childrenInfo.toString();
    }
    private boolean checkChildrenAge(List<Person> children) {
        AtomicBoolean checkedAge = new AtomicBoolean(false);
        for (Person child : children) {
            if (child.getBirthDate() != null) {
                long ageInMillis = System.currentTimeMillis() - child.getBirthDate().getTime();
                long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);
                checkedAge.set(ageInYears < 18);
                if (checkedAge.get()) {
                    break;
                }
            }
        }
        return checkedAge.get();
    }
}
