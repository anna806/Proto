package grafika;

import proto.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class NyersanyagView {

	private int x;
	private int y;
	private Nyersanyag adat;
	public Circle kor;
	
	public NyersanyagView(Nyersanyag ny, int kx, int ky){
		adat= new Nyersanyag();
		adat=ny;
		x=kx;
		y=ky;
		
		kor=new Circle();
		kor.setCenterX(x);
		kor.setCenterY(y);
		kor.setRadius(20.0f);
		
		
	}
	
	public void kitolt() {}
	
	public void feltesz(Stage stage) {
		Group group=new Group();
		group.getChildren.add(kor);
		Scene scene= new Scene(group, 41, 41);
		stage.setScene(scene);
		stage.show();
	}
	
	
}