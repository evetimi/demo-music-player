package com.miura.music.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.miura.music.Model.Music;
import com.miura.music.Model.MusicHolder;
import com.miura.music.Model.FileExistent;

public class MusicController {
    
    public static final Music noMusicObj = new Music("No musics imported", null);
    private MusicHolder musicHolder;
    private Clip clip;
    private boolean isClipSelected;

    public MusicController() {
        this.musicHolder = new MusicHolder();
        this.isClipSelected = false;
    }

    public MusicController(MusicHolder musicHolder) {
        this.musicHolder = musicHolder;
        this.isClipSelected = false;
    }


    /**
     * This method will check for existence music in the Music Holder, if there is any musics do not exist, this will delete those music from file.
     * @return Deleted music has been detected. Otherwise, return null.
     */
    public List<Music> checkForExistenceMusic() {

        List<Music> musics = musicHolder.getMusics();

        if (musics==null) return null;

        int index = 0;
        List<Integer> deleteIndex = new ArrayList<>();
        List<Music> deletedMusics = new LinkedList<>();
        for (Music music : musics) {
            if (!FileExistent.check(music.getFile())) {
                deletedMusics.add(music);
                deleteIndex.add(index);
            }
            index++;
        }

        if (deletedMusics.size()>0) {
            for (int i=0; i<deleteIndex.size(); i++)
                musicHolder.deleteMusic(deleteIndex.get(i) - i);
            return deletedMusics;
        }

        return null;
    }


    /**
     * Get all musics in the MusicHolder
     */
    public List<Music> getMusics() {
        return musicHolder.getMusics();
    }

    /**
     * Get all music's names in the MusicHolder
     * @return null is no musics, List<String> is there is at least 1 music.
     */
    public List<String> getMusicsName() {
        List<Music> musics = this.getMusics();

        if (musics==null) return null;

        List<String> musicsName = new LinkedList<>();

        for (Music music : musics) {
            musicsName.add(music.getName());
        }

        return musicsName;
    }




    public void insertMusic(String path) {
        // TODO: Insert Music here
        File file = new File(path);
        this.musicHolder.insertMusic(file.getName(), file);
    }



    public void deleteMusic() {
        // TODO: Delete Music here (from Music\Tracks, not original file)

    }





    /**
     * Choose the music in the given index, index must be inside a current number of musics
     * @param index
     */
    public void choose(int index) {

        List<Music> musics = this.musicHolder.getMusics();

        if (index < 0 || index >= musics.size()) {
            System.err.println("Index provided exceeds the current number of musics");
            return;
        }

        this.stop();

        File file = musics.get(index).getFile();
		
		try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
    		this.clip = AudioSystem.getClip();
            this.clip.open(audio);
            this.isClipSelected = true;
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }



    /**
     * Choose the music provided that needn't have to be added to Music Holder
     * @param music
     */
    public void choose(Music music) {
        if (!FileExistent.check(music.getFile())) {
            System.err.println("No such music found in your path with the Music provided.");
            return;
        }

        this.stop();

        File file = music.getFile();
		
		try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
    		this.clip = AudioSystem.getClip();
            this.clip.open(audio);
            this.isClipSelected = true;
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }




    /**
     * Play the current selected music.
     * If the music is not selected yet: You won't hear anything.
     * If the music is currently playing: It will still remain playing.
     */
    public void play() {
        if (!this.isClipSelected) {
            System.err.println("You have not selected a music");
            return;
        }

        if (!this.clip.isRunning()) this.clip.start();
    }


    /**
     * Stop the current running music.
     */
    public void stop() {
        if (!this.isClipSelected) {
            System.err.println("You have not selected a music");
            return;
        }

        if (this.clip.isRunning()) this.clip.stop();
    }



    /**
     * Forwarding the current running music by {@seconds} seconds.
     * @param seconds
     */
    public void forward(int seconds) {
        // TODO: Forwarding the current music
    }


    /**
     * Back the current running music by {@seconds} seconds.
     * @param seconds
     */
    public void back(int seconds) {
        // TODO: Code this
    }

}
