package com.child.profile.file.types;

import com.child.profile.config.FileStorageConfig;
import com.child.profile.data.Child;
import com.child.profile.data.Parent;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Base64;
import java.util.List;

public class CsvType implements FileType{

    private final Logger logger = LoggerFactory.getLogger(CsvType.class);
    @Override
    public String retrieveContent(List<Parent> parents) {
        String family = "";
        try(StringWriter stringWriter = new StringWriter()) {
            createParents(parents, stringWriter);
            parents.forEach(parent -> {
                try {
                    createChildren(parent.getChildren(), stringWriter);
                } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                    logger.error("Could not convert csv file",e);
                }
            });
            family = stringWriter.toString();
        } catch (IOException ioe) {
            logger.error("Error writing to file: ", ioe);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            logger.error("Could not convert csv file", e);;
        }
        return family;
    }

    private void createParents(List<Parent> parents, StringWriter stringWriter) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        StatefulBeanToCsv<Parent> writer = new StatefulBeanToCsvBuilder<Parent>(stringWriter)
                .withSeparator(',')
                .build();
        writer.write(parents);
    }
    private void createChildren(List<Child> children, StringWriter stringWriter) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        StatefulBeanToCsv<Child> writer = new StatefulBeanToCsvBuilder<Child>(stringWriter)
                .withSeparator(',')
                .build();
        writer.write(children);
    }

}
