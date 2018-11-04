package raffler;

import java.io.*;

import java.util.ArrayList;
import java.util.Random;

//import javafx.animation.Animation;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
//import javafx.util.Duration;

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
	
	public static String getRandomFromList(ArrayList<String> raffleContainer, boolean removePicked)
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
    		String winner = RaffleLogic.getRandomFromList(rafflerList, true);
    		if (winner.length() > 16) {
    			winnerText.setStyle("-fx-font-size: 70;");
    		}
			winnerText.setText(winner);
			
			try {
				this.writeBackToFile();
			} catch(IOException e) { System.out.println(e.getMessage()); }
			
			return winner;
		}
		
		return null;
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
