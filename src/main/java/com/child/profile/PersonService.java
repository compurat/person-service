package com.child.profile;

import com.child.profile.config.FileStorageConfig;
import com.child.profile.data.Parent;
import com.child.profile.data.PersonCreator;
import com.child.profile.file.types.CsvType;
import com.child.profile.file.types.FileType;
import com.child.profile.file.types.FileTypeFactory;
import com.child.profile.file.types.FileTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

@Service
public class PersonService {

    private final PersonCreator personCreator;
    private final FileTypeFactory fileTypeFactory;
    private final FileStorageConfig fileStorageConfig;

    @Autowired
    public PersonService(PersonCreator personCreator,
                         FileTypeFactory fileTypeFactory,
                         FileStorageConfig fileStorageConfig) {
        this.personCreator = personCreator;
        this.fileTypeFactory = fileTypeFactory;
        this.fileStorageConfig = fileStorageConfig;
    }

    public File retrieveFamilyFile() {
        List<Parent> parents = personCreator.createPersons();
        FileType csvType = fileTypeFactory.retrieveFileType(FileTypes.CSV);
        File familyFile = null;
        try {
           familyFile = Files.writeString(Path.of(fileStorageConfig.getFileStoragePath() + "/family.txt"), Base64.getEncoder().encodeToString(csvType.retrieveContent(parents).getBytes())).toFile();
        } catch (IOException ioException) {
            System.out.println("Error writing to file: " + ioException.getMessage());
        }
        return familyFile;
    }

    public String createFamily(List<Parent> parents) {
        personCreator.addPerson(parents);
        return "Person created successfully";
    }
}
