// Made by Sacchi Hikaru


package sacchi.bot.entities;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import sacchi.bot.services.MessageListener;
import sacchi.bot.services.Music;

public class MidgardsormrBot{

	public Music music = new Music();
        
	public MidgardsormrBot(){
	}
	
	public static void connect(){
		
		DiscordAPI api = Javacord.getApi("MjE0MTk2NTg0ODgyMTEwNDY0.CpFdqg.M5Beha_0ZhIjpinNQeAxgRG3V5Q", true);
		api.connectBlocking();
		api.setGame("with the WoL");
		MessageListener listen = new MessageListener();
		api.registerListener(listen);
		
	}
	
	public static void main(String[] args) {
		connect();
	}

	

}
