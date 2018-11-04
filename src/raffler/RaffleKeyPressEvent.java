package raffler;

import raffler.RaffleAnimation;
import raffler.RaffleLogic;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class RaffleKeyPressEvent implements EventHandler<KeyEvent>
{
	RaffleAnimation raffleAnimation;
	RaffleLogic raffleLogic;
	
	public RaffleKeyPressEvent(File file, ArrayList<String> list, Text text, ArrayList<String> rafflerListCopy) 
	{
		raffleAnimation = new RaffleAnimation(text, list, rafflerListCopy);
		raffleLogic = new RaffleLogic(file, list, text, rafflerListCopy);
	}
	
	public void handle(KeyEvent event)
	{
		if (event.getCode() == KeyCode.SPACE) {
			raffleAnimation.animate();
    	}
		
		if (event.getCode() == KeyCode.ENTER) {
			raffleAnimation.stop();
			raffleLogic.raffle();
    	}
	}

}
