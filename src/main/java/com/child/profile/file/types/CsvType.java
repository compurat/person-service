package com.child.profile.file.types;

import com.child.profile.config.FileStorageConfig;
import com.child.profile.data.Child;
import com.child.profile.data.Parent;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.Base64;
import java.util.List;

public class CsvType implements FileType{




    @Override
    public String retrieveContent(List<Parent> parents) {
        String family = "";
        try(StringWriter stringWriter = new StringWriter()) {
            createParents(parents, stringWriter);
            parents.forEach(parent -> {
                try {
                    createChildren(parent.getChildren(), stringWriter);
                } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                    System.out.println("Could not convert csv file");
                }
            });
            family = stringWriter.toString();
        } catch (IOException ioe) {
            System.out.println("Error writing to file: " + ioe.getMessage());
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            System.out.println("Could not convert csv file");;
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
