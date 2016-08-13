package sacchi.bot.services;

import de.btobastian.javacord.entities.message.Message;
import sacchi.bot.entities.Song;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		} else if(message.getContent().equalsIgnoreCase("!music random")){
			message.reply(musicRandom(message));
		} else if (message.getContent().equalsIgnoreCase("!music list")) {
			musicList(message);
		} else if(message.getContent().startsWith("!Music add")){
			message.reply(addSong(message));
		} else if(message.getContent().toLowerCase().contains("!music add")){
			message.reply("Due to code limitations, this command is case-sensitive."
					+ "\nPlease use ``!Music add [song name]-----[song description]-----[song link]------[song lyrics]`` to properly add a song to the list."
					+ "\nBefore you ask - that is **FIVE** dashes (-). "
					+ "If the song has no lyrics, please input ``none``."
					+ "\nYes, the programmer is bad, both him and I are very aware of it.");
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
	
	public String addSong(Message message){
		FileWriter writer = null; 
		BufferedWriter buffer = null;
		PrintWriter print = null;
		String[] split = new String [4];
		if(message.getAuthor().getRoles(message.getChannelReceiver().getServer()).contains(message.getChannelReceiver().getServer().getRoleById("214097524955283456"))){
			try{
				writer = new FileWriter("songList.txt",true);
				buffer = new BufferedWriter(writer);
				print = new PrintWriter(buffer);
				split = message.getContent().replace("!Music add ", "").split("-----");
				print.print("\n"+split[0]+"-----"+split[1]+"-----"+split[2]+"-----"+split[3]);
				songList.add(new Song(split[0], split[1], split[2], split[3]));
				return "Song added.";
			} catch (IOException e){
				throw new RuntimeException("Failed to add song.");
			} finally {
				if(print != null)
					print.close();
			}
		} else {
			return "You do not have the permission to add songs.";
		}
	}
}
