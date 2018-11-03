package raffler;

import raffler.RaffleKeyEvent;

import javafx.scene.paint.Color;
import java.io.*;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.util.*;


public class RafflerWindow
{
	private BufferedReader in;
	
	ArrayList<String> rafflerList = new ArrayList<String>();
	ArrayList<String> rafflerListCopy = new ArrayList<String>();
	FileChooser fileChooser = new FileChooser();
	Text winner = new Text("RAFFLER!");
	File selectedFile;
	Stage mainStage;
	
	public RafflerWindow(Stage stage) 
	{
		mainStage = stage;
		mainStage.setMaximized(true);
		
		this.launchFileChooser();
	}
	
	private void launchWindow()
	{
		winner.setId("winner");
		winner.setFont(Font.font("Arial Black", FontWeight.BLACK, 120));
		winner.setFill(Color.web("ffc857"));
		
		VBox vbox = new VBox();
		vbox.setId("layout");
		vbox.getChildren().addAll(winner);
		vbox.setId("layout");
		vbox.setAlignment(Pos.CENTER);
				 
		Scene scene = new Scene(vbox, 300, 250);
		scene.setFill(Color.web("177e89"));
		scene.setOnKeyPressed(new RaffleKeyEvent(selectedFile, rafflerList, winner, rafflerListCopy));

		mainStage.setTitle("Raffler");
		mainStage.setScene(scene);
		mainStage.show();
	}
	
	private void launchFileChooser()
	{
		fileChooser.setTitle("Open Resource File");
    	selectedFile = fileChooser.showOpenDialog(mainStage);
    	if (selectedFile != null) {
    		try {
				FileReader fileReader = new FileReader(selectedFile.getPath());
				in = new BufferedReader(fileReader);
	            
	            String str;
				while((str = in.readLine()) != null){
					rafflerList.add(str);
					rafflerListCopy.add(str);
                }
				
				this.launchWindow();
			} catch (FileNotFoundException e) {
			} catch (IOException e) {}
    	}
	}
}
