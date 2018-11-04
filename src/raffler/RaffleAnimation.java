package raffler;

import raffler.RaffleLogic;

import java.io.File;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class RaffleAnimation 
{
	ArrayList<String> rafflerList;
	ArrayList<String> rafflerListCopy;
	Text winnerText;
	Timeline animation;
	AudioClip beep;
	
	public RaffleAnimation(Text text, ArrayList<String> list, ArrayList<String> listCopy) 
	{
		winnerText = text;
		rafflerListCopy = listCopy;
		rafflerList = list;
	}
	
	public void animate() 
	{
		if (!rafflerList.isEmpty()) {
			winnerText.setStyle("-fx-font-size: 120;");
			animation = new Timeline();
			final IntegerProperty i = new SimpleIntegerProperty(0);
//			final int raffleSize = rafflerListCopy.size() * 50;
			
			beep = new AudioClip(new File("resources/spin1.mp3").toURI().toString());
			beep.play();
			
			KeyFrame keyFrame = new KeyFrame(
				Duration.millis(10),
				event -> {
					winnerText.setText(RaffleLogic.getRandomFromList(rafflerListCopy, false));
					i.set(i.get() + 1);
				}
			);
			
			animation.getKeyFrames().add(keyFrame);
			animation.setCycleCount(Animation.INDEFINITE);
			animation.play();
		}
		else {
			winnerText.setStyle("-fx-font-size: 70;");
			winnerText.setText("Congratulations to all the winners!");
		}
	}
	
	public void stop()
	{
		animation.stop();
		beep.stop();
	}
	

}
