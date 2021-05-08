package grafika;

import proto.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class NyersanyagView {

	private int x;
	private int y;
	private Nyersanyag adat;
	public Circle kor;
	
	public NyersanyagView(Nyersanyag ny, int kx, int ky){
		if(ny != null)
			adat=ny;
		x=kx;
		y=ky;
		
		kor=new Circle();
		kor.setCenterX(x);
		kor.setCenterY(y);
		kor.setRadius(20.0f);
		
		
	}
	
	public void ures() {
		kor.setFill(Color.DARKSLATEBLUE);
	}
	
	public void kitolt() {}
	
	public void feltesz(Group group) {
		group.getChildren().add(kor);
//		Scene scene= new Scene(group, 41, 41);
//		stage.setScene(scene);
//		stage.show();
	}
	
	
}
