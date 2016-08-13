package sacchi.bot.entities;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import sacchi.bot.services.MessageListener;
import sacchi.bot.services.Music;

public class MidgardsormrBot {
	public Music music = new Music();
        
	public MidgardsormrBot(){
	}
	
	public static void connect(){
		
		DiscordAPI api = Javacord.getApi("MjEzMTY2MDYyNTA0NTA5NDQw.Co2d7A.BPkp8a7tcef3T3pTMGEfs2bC8Dc", true);
		api.connectBlocking();
		MessageListener listen = new MessageListener();
		api.registerListener(listen);
		
	}
	
	public static void main(String[] args) {
		connect();
	}

	

}
