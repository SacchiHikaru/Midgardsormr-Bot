package sacchi.bot.entities;

import com.google.gson.annotations.SerializedName;

public class Song {
	private String name;
	private String description;
	@SerializedName("song_url")
	private String songURL;
	@SerializedName("lyrics_url")
	private String lyricsURL;

	public Song() {

	}

	public Song(String name, String description, String songURL, String lyricsURL) {
		this.name = name;
		this.description = description;
		this.songURL = songURL;
		this.lyricsURL = lyricsURL;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getLyrics() {
		return lyricsURL;
	}

	public void setLyrics(String lyricsURL) {
		this.lyricsURL = lyricsURL;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;

	}

	public void setLink(String songURL) {
		this.songURL = songURL;
	}

	public String getLink() {
		return songURL;
	}

	@Override
	public String toString() {
		return "Song: " + name + "\nDescription: " + description + "\nLink: " + songURL;
	}

}
