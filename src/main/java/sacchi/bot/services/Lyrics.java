package sacchi.bot.services;

import de.btobastian.javacord.entities.message.Message;

public class Lyrics{
    public static void lyricsHandler(Message message, Music music){
        if(message.getContent().equalsIgnoreCase("!Lyrics")){
            message.reply("Usage: !Lyrics [song name] to receive a link to the Song's lyrics. If the song has none, I will tell you so.");
        } else{
            message.reply(lyricsCheck(message,music));
        }
    }
    
    public static String lyricsCheck(Message message, Music music){
        for(int i = 0;i < music.songList.size();i++){
            if(music.songList.get(i).getName().toLowerCase().contains(message.getContent().toLowerCase().replace("!lyrics ", ""))){
                if(music.songList.get(i).getLyrics().equalsIgnoreCase("none")){
                    return "This song has no lyrics.";
                } else {
                    return music.songList.get(i).getName() + "'s Lyrics: " + music.songList.get(i).getLyrics();
                }
            }             
        }        
        return "Song not found.";
    }
}
