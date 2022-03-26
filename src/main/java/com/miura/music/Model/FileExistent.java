package com.miura.music.Model;

import java.io.File;

/**
 * A class used to check for an existing of a file
 */
public class FileExistent {
    


    public static boolean check(File file) {
        if (!file.exists()) {
            System.err.println("Error reading file, file does not exist: " + file.getAbsolutePath());
            return false;
        }

        return true;
    }


}
