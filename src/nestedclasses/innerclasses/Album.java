package nestedclasses.innerclasses;

import java.util.ArrayList;
import java.util.LinkedList;

import datastructures.arraylist.Song;

public class Album {
	@SuppressWarnings("unused")
	private String name;
	@SuppressWarnings("unused")
	private String artist;
	private SongList songs;

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
			if (playListSong.getTitle() == song.getTitle())
				return false;

		playList.add(song);
		return true;
	}

	public boolean addToPlayList(String title, LinkedList<Song> playList) {
		Song song = songs.findSong(title);
		if (song == null)
			return false;

		for (Song playListSong : playList)
			if (playListSong.getTitle() == song.getTitle())
				return false;

		playList.add(song);
		return true;
	}

	public static class SongList {
		private ArrayList<Song> songs;

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
				if (title == song.getTitle())
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