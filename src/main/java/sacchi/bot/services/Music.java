package sacchi.bot.services;

import de.btobastian.javacord.entities.message.Message;
import sacchi.bot.entities.Song;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Music{
	ArrayList<Song> songList = new ArrayList<Song>(0);
	BufferedReader reader = null;
	public Music() {
		try{
			reader = new BufferedReader(new FileReader("songList.txt"));
			String[] split = new String[4];
			String line = reader.readLine();
			while(line != null){
				split = line.split("-----");
				songList.add(new Song(split[0], split[1], split[2], split[3]));
				line = reader.readLine();
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to do stuff.");
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException s) {
					
				}
		}
	}
	
	public void musicHandler(Message message){
		if(message.getContent().equalsIgnoreCase("!music")
		|| message.getContent().equalsIgnoreCase("!music ")){
			message.reply("Usage: !Music [songname]"
					+ "\nCan also use !Music List for all song names and descriptions"
					+ "\nAnd !Music random for a random song.");
		} else if(message.getContent().toLowerCase().contains("random")){
			message.reply(musicRandom(message));
		} else if (message.getContent().toLowerCase().contains("list")) {
			musicList(message);
		} else {
			message.reply(musicSpecific(message));
		}
	}
	
	public String musicRandom(Message message){
		int randomNumber = (int)(Math.random()*songList.size());
		return songList.get(randomNumber).toString();
	}
	
	public String musicSpecific(Message message){
		System.out.println("Music Specific is on the line.");
		message.getContent().toLowerCase().replace("!music ", "");
		for(int i = 0; i < songList.size(); i++){
			if(songList.get(i).getName().toLowerCase().contains(message.getContent().toLowerCase().replace("!music ", ""))){
				return songList.get(i).toString();
			}
		}
		return "Could not find song.";
	}
	
	public void musicList(Message message){
		message.reply("The current songs are: ");
		for(int i = 0; i < songList.size(); i++){
			message.reply("\n" + songList.get(i).getName());
		}
	}		
}
