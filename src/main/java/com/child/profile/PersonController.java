package com.child.profile;

import com.child.profile.data.Parent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPerson(@RequestBody List<Parent> parents) {
        return ResponseEntity.ok(personService.createFamily(parents));
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile() {
        File familyFile = personService.retrieveFamilyFile();
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
