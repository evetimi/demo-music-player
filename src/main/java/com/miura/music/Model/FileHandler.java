package com.miura.music.Model;

import java.io.File;

/**
 * A class used to check for an existing of a file
 */
public class FileHandler {
    


    public static boolean checkExist(File file) {
        if (file==null) return false;

        if (!file.exists()) {
            System.err.println("Error reading file, file does not exist: " + file.getAbsolutePath());
            return false;
        }

        return true;
    }


    public static boolean isMusicFile(File file) {
        if (file==null) return false;
        
        String str = file.getName();
        String extension = str.substring(str.indexOf(".")+1, str.length());
        return extension.equals("wav");
    }

}
