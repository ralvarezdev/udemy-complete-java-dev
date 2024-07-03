package nestedclasses.innerclasses;

import datastructures.arraylist.Song;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Album {
    @SuppressWarnings("unused")
    private String name;
    @SuppressWarnings("unused")
    private String artist;
    private final SongList songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new SongList();
    }

    public boolean addSong(String title, double duration) {
        return songs.add(new Song(title, duration));
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        Song song = songs.findSong(trackNumber - 1);
        if (song == null)
            return false;

        for (Song playListSong : playList)
            if (Objects.equals(playListSong.getTitle(), song.getTitle()))
                return false;

        playList.add(song);
        return true;
    }

    public boolean addToPlayList(String title, LinkedList<Song> playList) {
        Song song = songs.findSong(title);
        if (song == null)
            return false;

        for (Song playListSong : playList)
            if (Objects.equals(playListSong.getTitle(), song.getTitle()))
                return false;

        playList.add(song);
        return true;
    }

    public static class SongList {
        private final ArrayList<Song> songs;

        private SongList() {
            songs = new ArrayList<>();
        }

        private boolean add(Song song) {
            if (song == null || findSong(song.getTitle()) != null)
                return false;

            songs.add(song);
            return true;
        }

        private Song findSong(String title) {
            for (Song song : songs)
                if (Objects.equals(title, song.getTitle()))
                    return song;

            return null;
        }

        private Song findSong(int trackNumber) {
            if (trackNumber <= 0 || trackNumber > songs.size())
                return null;

            return songs.get(trackNumber - 1);
        }
    }
}