/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miura.music.Model;

import java.io.File;

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
        this.file = file;
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
        this.file = file;
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
