package sacchi.bot.services;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import sacchi.bot.entities.MidgardsormrBot;

public class MessageListener extends MidgardsormrBot implements MessageCreateListener{
        public void onMessageCreate(DiscordAPI api, Message message){	
            if(message.getContent().equalsIgnoreCase("!poem")){
                 message.reply("*Thy life is a riddle, to bear rapture and sorrow."
                      + "\nTo listen, to suffer, to entrust unto tomorrow."
                      + "\nIn one fleeting moment, from the land doth life flow."
                      + "\nYet in one fleeting moment, for anew it doth grow."
                      + "\nIn the same fleeting moment, thou must live, die, and know.*");
            } else if (message.getContent().toLowerCase().startsWith("!music")) {
                music.musicHandler(message);
            } else if (message.getContent().toLowerCase().startsWith("!cute")){
                  Cute.cuteHandler(message);
            } else if (message.getContent().toLowerCase().startsWith("!lyrics")){
                Lyrics.lyricsHandler(message, music);
            }
        }
}
