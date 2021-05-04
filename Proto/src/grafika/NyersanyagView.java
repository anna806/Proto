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
	
	
}
