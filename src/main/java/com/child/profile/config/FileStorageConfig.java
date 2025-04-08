package com.child.profile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

    @Component
    @Configuration
    public class FileStorageConfig {

        @Value("${file.storage.path}")
        private String fileStoragePath;

        public String getFileStoragePath() {
            return fileStoragePath;

        }

}
