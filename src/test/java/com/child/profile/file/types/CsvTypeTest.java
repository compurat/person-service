package com.child.profile.file.types;

import com.child.profile.ParentMockCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CsvTypeTest {
    FileType csvType = new CsvType();

    @Test
    public void testRetrieveContent() {
        assertTrue(csvType.retrieveContent(ParentMockCreator.createParentsMock()).length() > 700);
    }
}
