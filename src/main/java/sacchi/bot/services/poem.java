package sacchi.bot.services;

import de.btobastian.javacord.entities.message.Message;

public class poem {
	
	public static void poemHandler(Message message){
		message.reply("*Thy life is a riddle, to bear rapture and sorrow."
                + "\nTo listen, to suffer, to entrust unto tomorrow."
                + "\nIn one fleeting moment, from the land doth life flow."
                + "\nYet in one fleeting moment, for anew it doth grow."
                + "\nIn the same fleeting moment, thou must live, die, and know.*");
	}

}
