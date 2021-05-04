package grafika;
import proto.*;

public class KapuView {
	private Teleportkapu kapu;
	int x;
	int y;
	public Rectangle teglalap;
	boolean valasztott;
	
	public KapuView(Teleportkapu tk, int kx, int ky) {
		kapu= new Teleportkapu(); 
		kapu=tk;
		x=kx,
		y=ky;
		
		teglalap= new Rectangle();
		keret.setX(x);
		keret.setY(y);
		keret.setWidth(20);
		keret.setHeight(50);
		keret.SetFill(Color.WHITE);
	}
	public SetVal(boolean val) {
		if(val) {
			keret.setFill(Color.RED);
		}
		else
			keret.setFill(Color.RED);
	}
}
