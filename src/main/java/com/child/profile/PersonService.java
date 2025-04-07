package com.child.profile;

import com.child.profile.config.FileStorageConfig;
import com.child.profile.data.Child;
import com.child.profile.data.Parent;
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
    private final FileStorageConfig fileStorageConfig;

    PersonService(PersonCreator personCreator, FileStorageConfig fileStorageConfig) {
        this.personCreator = personCreator;
        this.fileStorageConfig = fileStorageConfig;
    }
    public String createPerson(List<Parent> parent) {
        personCreator.addPerson(parent);
        return "Person created successfully";
    }
    public File retrieveChildProfile() {
        List<Parent> parents = personCreator.createPersons();
        StringBuilder personsInfo = new StringBuilder();
        for (Parent parent : parents) {
            List<Child> children = parent.getChildren();
            if (!(checkChildrenAge(children))) {
                return createFamilyFile("No children under 18");
            }

            if (children.size() == 3) {
                personsInfo.append("Name: ").append(parent.getName()).append(",");
                personsInfo.append("Birth Date: ").append(parent.getBirthDate()).append(",");

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
            familyFile = Files.writeString(Path.of(fileStorageConfig.getFileStoragePath() + "/family.txt"), Base64.getEncoder().encodeToString(content.getBytes())).toFile();
        } catch (IOException ioe) {
            System.out.println("Error writing to file: " + ioe.getMessage());
        }
        return familyFile;
    }

    private String childrenInfo(List<Child> children) {
        StringBuilder childrenInfo = new StringBuilder();
        for (Child child : children) {
            childrenInfo.append(child.getName()).append(",");
            childrenInfo.append("Birth Date: ").append(child.getBirthDate()).append(",");
            childrenInfo.append("Parent 1: ").append(child.getParent1()).append(",");
            childrenInfo.append("Parent 2: ").append(child.getParent2()).append(",");

        }
        return childrenInfo.toString();
    }

    private boolean checkChildrenAge(List<Child> children) {
        AtomicBoolean checkedAge = new AtomicBoolean(false);
        for (Child child : children) {
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
