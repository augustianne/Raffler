package raffler;

import raffler.RaffleLogic;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;


public class RaffleKeyEvent implements EventHandler<KeyEvent> 
{
	RaffleLogic raffle;
	
	public RaffleKeyEvent(File selectedFile, ArrayList<String> list, Text text, ArrayList<String> rafflerListCopy) 
	{
		 raffle = new RaffleLogic(selectedFile, list, text, rafflerListCopy);
	}
	
	public void handle(KeyEvent event)
	{
		if (event.getCode() == KeyCode.SPACE) {
//			try {
			raffle.animate();
				
//			} catch(InterruptedException e) {System.out.println(e.getMessage()); }
    	}
	}

}
