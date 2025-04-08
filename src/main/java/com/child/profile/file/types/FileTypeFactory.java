package com.child.profile.file.types;

import com.child.profile.config.FileStorageConfig;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FileTypeFactory {

    private final Map<FileTypes, FileType> fileTypeMap;

    public FileTypeFactory() {
        this.fileTypeMap = new HashMap<>();
        fileTypeMap.put(FileTypes.CSV, new CsvType());
    }

    public FileType retrieveFileType(FileTypes fileType) {
        return fileTypeMap.get(fileType);
    }
}
