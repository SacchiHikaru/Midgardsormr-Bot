package sacchi.bot.services;

import de.btobastian.javacord.entities.message.Message;
import sacchi.bot.entities.Song;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Music {
	ArrayList<Song> songList = new ArrayList<Song>(0);
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	String fileData = null;
	Song[] songs = null;

	public Music() {
		try {
			fileData = new String(Files.readAllBytes(Paths.get("songList.json")), StandardCharsets.UTF_8);
			songs = gson.fromJson(fileData, Song[].class);
			for (int i = 0; i < songs.length; i++) {
				songList.add(songs[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to do stuff.");
		} finally {

		}
	}

	public void musicHandler(Message message) {
		if (message.getContent().equalsIgnoreCase("!music") || message.getContent().equalsIgnoreCase("!music ")) {
			message.reply("Usage: !Music [songname]" + "\nCan also use !Music List for all song names and descriptions"
					+ "\nAnd !Music random for a random song."
					+ "\nIf you have the necessary permissions, you can use !Music add to add new songs."
					+ "\nInstructions on how to add songs are given when using !music add (all lowercase).");
		} else if (message.getContent().equalsIgnoreCase("!music random")) {
			message.reply(musicRandom(message));
		} else if (message.getContent().equalsIgnoreCase("!music list")) {
			musicList(message);
		} else if (message.getContent().startsWith("!Music add")) {
			message.reply(addSong(message));
		} else if (message.getContent().toLowerCase().contains("!music add")) {
			message.reply("Due to code limitations, this command is case-sensitive."
					+ "\nPlease use ``!Music add [song name]-----[song description]-----[song link]------[pastebin link to song lyrics]`` to properly add a song to the list."
					+ "\nBefore you ask - that is **FIVE** dashes (-). There are no spaces between the dashes and the info provided."
					+ "\nIf the song has no lyrics, please input ``none``."
					+ "\nYes, the programmer is bad, both him and I are very aware of it.");
		} else {
			message.reply(musicSpecific(message));
		}
	}

	public String musicRandom(Message message) {
		int randomNumber = (int) (Math.random() * songList.size());
		return songList.get(randomNumber).toString();
	}

	public String musicSpecific(Message message) {
		for (int i = 0; i < songList.size(); i++) {
			if (songList.get(i).getName().toLowerCase()
					.contains(message.getContent().toLowerCase().replace("!music ", ""))
					|| songList.get(i).getDescription().toLowerCase()
							.contains(message.getContent().toLowerCase().replace("!music ", ""))) {
				return songList.get(i).toString();
			}
		}
		return "Could not find song.";
	}

	public void musicList(Message message) {

		String s = String.join(", ", songList.stream().map(x -> "\n" + x.getName() + " ----- " + x.getDescription())
				.collect(Collectors.toList()));
		message.reply(s.replace(",", ""));
	}

	public String addSong(Message message) {
		FileWriter writer = null;
		File file = new File("songList.json");
		String[] split = new String[4];
		if (message.getAuthor().getRoles(message.getChannelReceiver().getServer())
				.contains(message.getChannelReceiver().getServer().getRoleById("214097524955283456"))) {
			try {
				writer = new FileWriter(file, false);
				split = message.getContent().replace("!Music add ", "").split("-----");
				Song addedSong = new Song(split[0], split[1], split[2], split[3]);
				songList.add(addedSong);
				writer.write(gson.toJson(songList));
				return "Song added.";
			} catch (IOException e) {
				throw new RuntimeException("Failed to add song.");
			} finally {
				try {
					if (writer != null)
						writer.flush();
					writer.close();
				} catch (IOException e) {

				}
			}
		} else {
			return "You do not have the permission to add songs.";
		}
	}
}
