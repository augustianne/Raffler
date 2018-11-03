package raffler;

import java.io.*;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class RaffleLogic 
{
	ArrayList<String> rafflerList;
	File selectedFile;
	ArrayList<String> rafflerListCopy;
	Text winnerText;
	
	
	public RaffleLogic(File file, ArrayList<String> list, Text text, ArrayList<String> listCopy) 
	{
		rafflerList = list;
		winnerText = text;
		selectedFile = file;
		rafflerListCopy = listCopy;
	}
	
	public String getRandomFromList(ArrayList<String> raffleContainer, boolean removePicked)
	{
		Random rand = new Random();
		int randomIndex = rand.nextInt(raffleContainer.size());
		String picked = raffleContainer.get(randomIndex);
		
		if (removePicked) {
			raffleContainer.remove(randomIndex);
		}
		
		return picked;
	}
	
	public String raffle()
	{
		if (!rafflerList.isEmpty()) {
    		String winner = this.getRandomFromList(rafflerList, true);
			winnerText.setText(winner);
//			System.out.println(rafflerList);
			
			try {
				this.writeBackToFile();
			} catch(IOException e) { System.out.println(e.getMessage()); }
			
			return winner;
		}
		
		return null;
	}
	
	public void animate() 
	{
		if (!rafflerList.isEmpty()) {
			Timeline timeline = new Timeline();
			final IntegerProperty i = new SimpleIntegerProperty(0);
//			final int raffleSize = rafflerListCopy.size() * 50;
			final int raffleSize = 450;
			
			AudioClip beep = new AudioClip(new File("resources/spin1.mp3").toURI().toString());
			beep.play();
			
			KeyFrame keyFrame = new KeyFrame(
				Duration.millis(10),
				event -> {
					
					if (i.get() >= raffleSize) {
						beep.stop();
						timeline.stop();
						this.raffle();
					} else {
						winnerText.setText(this.getRandomFromList(rafflerListCopy, false));
						i.set(i.get() + 1);
					}
            	
				}
			);
			
			timeline.getKeyFrames().add(keyFrame);
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
		}
	}
	
	private void writeBackToFile() throws IOException
	{
		BufferedWriter outputWriter = null;
		outputWriter = new BufferedWriter(new FileWriter(selectedFile.getPath()));
		for (int i = 0; i < rafflerList.size(); i++) {
		    outputWriter.write(rafflerList.get(i)+"");
		    outputWriter.newLine();
		}
		outputWriter.flush();  
		outputWriter.close(); 
	}

}
