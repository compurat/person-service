package com.child.profile;

import com.child.profile.config.FileStorageConfig;
import com.child.profile.data.Child;
import com.child.profile.data.Parent;
import com.child.profile.data.PersonCreator;
import com.child.profile.file.types.CsvType;
import com.child.profile.file.types.FileTypeFactory;
import com.child.profile.file.types.FileTypes;
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
    private FileTypeFactory fileTypeFactory;
    @Mock
    private FileStorageConfig fileStorageConfig;
    @InjectMocks
    private PersonService personService;


    @Test
    public void testRetrieveChildProfile() {
        when(personCreator.createPersons()).thenReturn(ParentMockCreator.createParentsMock());
        when(fileTypeFactory.retrieveFileType(FileTypes.CSV)).thenReturn(new CsvType());
        when(fileStorageConfig.getFileStoragePath()).thenReturn("/tmp/");
        String content = convertFileToString(personService.retrieveFamilyFile());

         assertEquals(988, content.length());
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