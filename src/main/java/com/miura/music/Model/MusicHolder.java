package com.miura.music.Model;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

public final class MusicHolder {

    private final static String path = "Musics\\Tracks";
    private final static File tracks = new File(MusicHolder.path);
    private List<Music> musics;

    public MusicHolder() {
        this.musics = readMusicFile();
    }

    public List<Music> getMusics() {
        return this.musics;
    }

    private List<Music> readMusicFile() {
        List<Music> musics = new LinkedList<>();

        // *System.out.println(tracks.getAbsolutePath());
        // name:path|name:path
        String musicStr = "";
        try {
            musicStr = new String(Reader.read(MusicHolder.tracks), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] musicArr = (musicStr.length()>0) ? this.split(musicStr,'|') : null;

        if (musicArr==null) return null;

        // System.out.println(musicArr.length);
        // System.out.println(musicStr);
        // System.out.println(Reader.read(path).toString());
        // for (int i=0; i<musicArr.length; i++) System.out.print(musicArr[i]);
        
        for (String music : musicArr) {
            String[] name = music.split(">");
            try {
                musics.add(new Music(name[0], new File(name[1])));
            } catch (Exception e) {
                System.err.println("An error occurred while reading music: " + music);
                e.printStackTrace();
            }
        }

        return musics;
    }

    public void insertMusic(String musicName, File file) {
        if (!FileExistent.check(file)) return;

        if (!checkExtension(file)) {
            System.err.println("Error: A file extension must be .wav");
            return;
        }

        if (this.musics==null) this.musics = new LinkedList<>();

        Music newMusic = new Music(musicName, file);
        this.musics.add(newMusic);

        try {
            long size = Files.size(tracks.toPath());
            Writer.writeMore(tracks, (size==0) ? FormattedMusicFile.convert(newMusic) : "|" + FormattedMusicFile.convert(newMusic));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertMusic(String musicName, String path) {
        this.insertMusic(musicName, new File(path));
    }

    private boolean checkExtension(File file) {
        String str = file.getName();
        String extension = str.substring(str.indexOf(".")+1, str.length());
        return extension.equals("wav");
    }

    public void deleteMusic(int index) {
        if (index>=this.musics.size() || index<0) {
            System.err.println("Cannot delete music! Index out of bounds!");
            return;
        }

        String musicsInFile = "";
        try {
            musicsInFile = new String(Reader.read(MusicHolder.tracks), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int startStr = 0, endStr = 0;

        for (int i=0; i<index; i++) startStr = musicsInFile.indexOf("|", startStr + 1);
        endStr = musicsInFile.indexOf("|", startStr + 1) + 1;

        if (endStr<1) endStr = musicsInFile.length();

        String separated = (startStr>0 && endStr<musicsInFile.length()) ? "|" : "";
        String remainMusics = musicsInFile.substring(0, startStr) + separated + musicsInFile.substring(endStr, musicsInFile.length());

        Writer.write(MusicHolder.tracks, remainMusics);
        musics.remove(index);
    }


    public void deleteMusic(Music musicToDelete) {
        List<Integer> deleteIndex = new LinkedList<>();
        {
            int index = 0;
            for (Music music : this.musics) {
                if (music.equals(musicToDelete)) deleteIndex.add(index);
                index++;
            }
        }


        if (deleteIndex.size()==0) {
            System.err.println("There were no such music given in the MusicHolder");
            return;
        }


        int i = 0;
        for (int index : deleteIndex) {
            this.deleteMusic(index - i++);
        }
    }





    private String[] split(String str, char regex) {
        if (!str.contains(new String(new char[]{regex}))) return new String[]{str};
        List<StringBuffer> splitted = new LinkedList<>();
        StringBuffer singleStr = new StringBuffer();
        for (int i=0; i<str.length(); i++) {
            char o = str.charAt(i);
            if (o != regex) {
                singleStr.append(o);
            } else {
                splitted.add(singleStr);
                singleStr = new StringBuffer();
            }
        }

        splitted.add(singleStr);

        String[] splitArr = new String[splitted.size()];
        int i=0;
        for (StringBuffer split : splitted) {
            splitArr[i++] = split.toString();
        }

        return splitArr;
    }

}
