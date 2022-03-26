package com.miura.music.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Reader {


    public static byte[] read(File file) {
        if (!FileExistent.check(file)) return null;

        try {
            byte[] arr = Files.readAllBytes(file.toPath());
            return arr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static byte[] read(String path) {
        return Reader.read(new File(path));
    }

}
