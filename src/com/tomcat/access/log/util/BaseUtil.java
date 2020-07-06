package com.tomcat.access.log.util;

import java.io.File;
import java.util.List;

public abstract class BaseUtil {
    public abstract String parseSingleFile(String map, final String fileName);

    public abstract String parseDirectory(String map, final String directory);

    protected void listFilesForFolder(final File folder, List<String> fileNameList) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                this.listFilesForFolder(fileEntry, fileNameList);
            } else {
                fileNameList.add(fileEntry.getAbsolutePath());
            }
        }
    }
}
