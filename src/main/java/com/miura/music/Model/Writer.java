package com.miura.music.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Writer {


    public static void write(File file, byte[] bytes) {
        if (!FileHandler.checkExist(file)) return;

        try (OutputStream os = new FileOutputStream(file)) {

            os.write(bytes);

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void write(String path, byte[] bytes) {
        Writer.write(new File(path), bytes);
    }

    public static void write(File file, String content) {
        Writer.write(file, content.getBytes());
    }

    public static void write(String path, String content) {
        Writer.write(new File(path), content.getBytes());
    }


    public static void writeMore(File file, byte[] bytes) {
        byte[] currentContent = Reader.read(file);

        byte[] newContent = new byte[currentContent.length + bytes.length];
        for (int i=0; i<currentContent.length; i++) newContent[i] = currentContent[i];
        for (int i=0; i<bytes.length; i++) newContent[i+currentContent.length] = bytes[i];

        Writer.write(file, newContent);
    }

    public static void writeMore(String path, byte[] bytes) {
        Writer.writeMore(new File(path), bytes);
    }

    public static void writeMore(File file, String content) {
        Writer.writeMore(file, content.getBytes());
    }

    public static void writeMore(String path, String content) {
        Writer.writeMore(new File(path), content.getBytes());
    }

}
