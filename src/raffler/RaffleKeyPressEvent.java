package raffler;

import raffler.RaffleAnimation;
import raffler.RaffleLogic;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RaffleKeyPressEvent implements EventHandler<KeyEvent>
{
	RaffleAnimation raffleAnimation;
	RaffleLogic raffleLogic;
	ArrayList<KeyCode> keyCodeList = new ArrayList<KeyCode>();
	KeyCode entered;
	
	public RaffleKeyPressEvent(File file, ArrayList<String> list, Text text, ArrayList<String> rafflerListCopy, Stage stage) 
	{
		raffleAnimation = new RaffleAnimation(text, list, rafflerListCopy, stage);
		raffleLogic = new RaffleLogic(file, list, text, rafflerListCopy, stage);
	}
	
	public void handle(KeyEvent event)
	{
		if (event.getCode() == KeyCode.SPACE) {
			if (!keyCodeList.isEmpty()) {
				entered = keyCodeList.get(0);
			}
			
			if (keyCodeList.isEmpty() && event.getCode() == KeyCode.SPACE) {
				raffleAnimation.animate();
				keyCodeList.add(event.getCode());
	    	}
			
			if (entered == KeyCode.SPACE && event.getCode() == KeyCode.SPACE) {
				raffleAnimation.stop();
				raffleLogic.raffle();
				keyCodeList.remove(0);
				entered = null;
	    	}
		}
	}
}
