package sacchi.bot.services;

import de.btobastian.javacord.entities.message.Message;

public class praise {
	
	public static void praiseHandler(Message message){
		if(message.getAuthor().getRoles(message.getChannelReceiver().getServer()).contains(message.getChannelReceiver().getServer().getRoleById("120559208650375168"))){
    		message.reply("You're better. You made me, after all.");
    	} else {
    		message.reply("'Tis nothing, I'm quite sure you're a much better individual than this old dragon will ever be.");
    	}
	}

}
