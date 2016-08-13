package sacchi.bot.services;

import de.btobastian.javacord.entities.message.Message;
import sacchi.bot.entities.Song;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Music {
	ArrayList<Song> songList = new ArrayList<Song>(0);;
	Song primogenitor = new Song("The Primogenitor","My Battle Theme","https://www.youtube.com/watch?v=KPUZsva8RUs","");
	Song woe = new Song("Woe That Is Madness","Bismarck's Final Phase Theme","https://www.youtube.com/watch?v=r0JO_8Bo00Y","");
	Song fiend = new Song("The Fiend", "Sephirot's Final Phase Theme", "https://www.youtube.com/watch?v=xdV_bzJgRxE", "http://pastebin.com/bB6ypJ78");
	
	public Music() throws IOException{
		/*BufferedReader reader = new BufferedReader(new FileReader("songList.txt"));
		String[] split = new String[4];
		while(reader.readLine() != null){
			split = reader.readLine().split("\\w+");
			
		}*/
		songList.add(primogenitor);
		songList.add(woe);
		songList.add(fiend);
		//reader.close();
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
