package raffler;

import java.io.*;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RaffleLogic 
{
	ArrayList<String> rafflerList;
	File selectedFile;
	ArrayList<String> rafflerListCopy;
	Text winnerText;
	Stage mainStage;
	
	public RaffleLogic(File file, ArrayList<String> list, Text text, ArrayList<String> listCopy, Stage stage) 
	{
		rafflerList = list;
		winnerText = text;
		selectedFile = file;
		rafflerListCopy = listCopy;
		mainStage = stage;
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
			Long fontSize = Math.round(0.085 * mainStage.getWidth());
			
    		String winner = RaffleLogic.getRandomFromList(rafflerList, true);
    		if (winner.length() > 16) {
    			fontSize = fontSize / 2;
    		}

			winnerText.setStyle("-fx-font-size: "+fontSize+"px;");
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
