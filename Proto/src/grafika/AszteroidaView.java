package grafika;

import proto.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class AszteroidaView {
	private Aszteroida data;
	public Circle kor;
	public int x;
	public int y;
	public NyersanyagView anyag;
	private boolean aktualis;
	private boolean valasztott; 
	
	public AszteroidaView(Aszteroida a, int kx, int ky) {
		data=a;
		x=kx;
		y=ky;
		
		kor= new Circle();
		kor.setCenterX(x);
		kor.setCenterY(y);
		kor.setRadius(50.0f);
		kor.setFill(Color.CORNFLOWERBLUE);
		if (data.getKopenyVastagsag()==0) {
			anyag=new NyersanyagView(data.getBelsoAnyag(), x, y);
		}
		
	}
	public void setAktual(boolean akt) {
		if (akt)
			kor.setStroke(Color.RED);
		else
			kor.setStroke(Color.TRANSPARENT);
	}
	
	public void setValaszt(boolean val) {
		if(val)
			kor.setStroke(Color.GREEN);
		else
			kor.setStroke(Color.TRANSPARENT);
	}
}
	
