package com.child.profile.file.types;

import com.child.profile.data.Parent;

import java.io.File;
import java.util.List;

public interface FileType {
    public String retrieveContent(List<Parent> parents);
}
