package com.miura.music.Model;

public class FormattedMusicFile {
    

    // public static String convert(String musicName, String musicPath) {
    //     return musicName + ":" + musicPath;
    // }

    public static String convert(Music music) {
        return music.getName() + ">" + music.getFile().getAbsolutePath();
    }

}
