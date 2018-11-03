package raffler;

import raffler.RafflerWindow;

import javafx.application.Application;
import javafx.stage.Stage;

public class Raffler extends Application {
    
    @Override
    public void start(Stage stage) {
    	new RafflerWindow(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}