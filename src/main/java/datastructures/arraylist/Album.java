package datastructures.arraylist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Album {
    @SuppressWarnings("unused")
    private String name;
    @SuppressWarnings("unused")
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    public boolean addSong(String title, double duration) {
        if (findSong(title) != null)
            return false;

        songs.add(new Song(title, duration));

        return true;
    }

    private Song findSong(String title) {
        for (Song song : songs)
            if (Objects.equals(song.getTitle(), title))
                return song;
        return null;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        if (trackNumber <= 0)
            return false;

        if (songs.size() < trackNumber)
            return false;

        playList.add(songs.get(trackNumber - 1));

        return true;
    }

    public boolean addToPlayList(String title, LinkedList<Song> playList) {
        for (Song song : songs)
            if (song.getTitle().equals(title)) {
                playList.add(song);
                return true;
            }

        return false;
    }
}
