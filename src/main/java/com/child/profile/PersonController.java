package com.child.profile;

import com.child.profile.config.FileStorageConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PersonController {

    private final PersonService personService;
    private final FileStorageConfig fileStorageConfig;
    public PersonController(PersonService personService, FileStorageConfig fileStorageConfig) {
        this.personService = personService;
        this.fileStorageConfig = fileStorageConfig;
    }


    @GetMapping("/profile")
    public ResponseEntity<Resource> downloadFile() {
        File familyFile = personService.retrieveChildProfile();
        try {
            Resource resource = new UrlResource(familyFile.toPath().toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + familyFile.getName() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
