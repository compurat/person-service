package com.child.profile.file.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileTypeFactoryTest {

    FileTypeFactory fileTypeFactory = new FileTypeFactory();

    @Test
    public void retrieveFileType() {
        assertNotNull(fileTypeFactory.retrieveFileType(FileTypes.CSV));
        assertEquals(CsvType.class, fileTypeFactory.retrieveFileType(FileTypes.CSV).getClass());
    }
}