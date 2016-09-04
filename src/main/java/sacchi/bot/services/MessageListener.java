package sacchi.bot.services;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import sacchi.bot.entities.MidgardsormrBot;

public class MessageListener extends MidgardsormrBot implements MessageCreateListener{
        public void onMessageCreate(DiscordAPI api, Message message){	
            if(message.getContent().equalsIgnoreCase("!poem")){
                 poem.poemHandler(message);
            } else if (message.getContent().toLowerCase().startsWith("!music")) {
                music.musicHandler(message);
            } else if (message.getContent().toLowerCase().startsWith("!cute")){
                  Cute.cuteHandler(message);
            } else if (message.getContent().toLowerCase().startsWith("!lyrics")){
                Lyrics.lyricsHandler(message, music);
            } else if (message.getContent().toLowerCase().equalsIgnoreCase("You're the best, Middy.")){
            	praise.praiseHandler(message);
            }
            
        }
}
