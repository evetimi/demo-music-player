/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miura.music.Model;

import java.io.File;
import java.util.Objects;

/**
 *
 * @author Waffle
 */
public class Music {
    
    private String name;
    private File file;

    public Music() {
    }

    public Music(String name, File file) {
        this.name = name;

        if (!FileHandler.isMusicFile(file)) {
            System.err.println("Cannot instanciate Music object. Music can only be .wav");
            this.file = null;
        } else this.file = file;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        if (!FileHandler.isMusicFile(file)) {
            System.err.println("Music can only be .wav");
            return;
        }
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Music)) {
            return false;
        }
        Music music = (Music) o;
        return Objects.equals(file, music.file);
    }

    @Override
    public String toString() {
        // return "Music {" +
        //     " name='" + getName() + "'" +
        //     ", file='" + getFile() + "'" +
        //     "}";

        return this.getName();
    }

}
