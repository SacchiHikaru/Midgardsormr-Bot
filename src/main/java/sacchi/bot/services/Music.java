package sacchi.bot.services;

import de.btobastian.javacord.entities.message.Message;
import sacchi.bot.entities.Song;
import java.util.ArrayList;

public class Music {
	static ArrayList<Song> songList;
	static Song primogenitor = new Song("The Primogenitor","My Battle Theme","https://www.youtube.com/watch?v=KPUZsva8RUs");
	static Song woe = new Song("Woe That Is Madness","Bismarck's Final Phase Theme","https://www.youtube.com/watch?v=r0JO_8Bo00Y");
	
	static {
		songList = new ArrayList<Song>(0);
		songList.add(primogenitor);
		songList.add(woe);
	}
	
	public static void musicHandler(Message message){
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
	
	public static String musicRandom(Message message){
		int randomNumber = (int)(Math.random()*songList.size());
		return songList.get(randomNumber).toString();
	}
	
	public static String musicSpecific(Message message){
		System.out.println("Music Specific is on the line.");
		message.getContent().toLowerCase().replace("!music ", "");
		for(int i = 0; i < songList.size(); i++){
			if(songList.get(i).getName().toLowerCase().contains(message.getContent().toLowerCase().replace("!music ", ""))){
				return songList.get(i).toString();
			}
		}
		return "Could not find song.";
	}
	
	public static void musicList(Message message){
		message.reply("The current songs are: ");
		for(int i = 0; i < songList.size(); i++){
			message.reply("\n" + songList.get(i).getName());
		}
	}		
}
