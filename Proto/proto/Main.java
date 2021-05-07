package proto;

import grafika.Fomenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	//public static Jatek game = new Jatek();
	
	@Override
	public void start(Stage arg0) throws Exception {
		Fomenu menu = new Fomenu();
		//arg0.setScene(new Scene(menu.felepit()));
		//arg0.show();
		menu.felepit(arg0);
		
	}
	
	public static void main(String[] args) {
		//game.field(args);
		Application.launch(args);
	}

	
}
