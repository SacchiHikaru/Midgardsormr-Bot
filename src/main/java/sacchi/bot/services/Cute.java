package sacchi.bot.services;

import java.util.Random;
import de.btobastian.javacord.entities.message.Message;

public class Cute {
	
	public static void cuteHandler(Message message){
		if(message.getContent().equalsIgnoreCase("!cute")
		|| message.getContent().equalsIgnoreCase("!cute ")){
			message.reply("Usage: !Cute [number] will pull up a cute picture of me corresponding to the number. "
					+ "\nCan also use !Cute random to pull up a random picture instead.");
		} else if(message.getContent().toLowerCase().contains("random")){
			randomCute(message);
		} else {
			cuteSpecific(message);
		}
	}
	
	public static void randomCute(Message message){
		Random random = new Random();
		int randomNumber = random.nextInt(3);
		
		switch(randomNumber){
			default:
				message.reply("Cuteness Failed!");
				break;
			case 0:
				message.reply("http://i.imgur.com/RGDbvaj.png");
				break;
			case 1:
				message.reply("http://i.imgur.com/4EPsdcg.jpg");
				break;
			case 2:
				message.reply("http://i.imgur.com/EsjNNkX.jpg");
		}
	}
	
	public static void cuteSpecific(Message message){
		if(message.getContent().contains("1")){
			message.reply("http://i.imgur.com/RGDbvaj.png");
		} else {
			if(message.getContent().contains("2")){
				message.reply("http://i.imgur.com/4EPsdcg.jpg");
			} else {
				if(message.getContent().contains("3")){
					message.reply("http://i.imgur.com/EsjNNkX.jpg");
				} else{
					message.reply("Error! Cuteness not found!");
				}
			}
		}
	}
}
