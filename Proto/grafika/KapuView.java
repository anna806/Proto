package grafika;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import proto.*;

public class KapuView extends SzomszedView{
	private Teleportkapu kapu;
	double x;
	double y;
	public Rectangle teglalap;
	boolean valasztott;
	Group group;
	
	public KapuView(Teleportkapu tk, double kx, double ky, Group g) {
		group = g;
		kapu= new Teleportkapu(); 
		kapu=tk;
		x=kx;
		y=ky;
		
		teglalap= new Rectangle();
		teglalap.setX(x);
		teglalap.setY(y);
		teglalap.setWidth(20);
		teglalap.setHeight(50);
		teglalap.setFill(Color.WHITE);
	}
	public void SetVal(boolean val) {
		if(val) {
			teglalap.setFill(Color.GREEN);
		}
		else
			teglalap.setFill(Color.WHITE);
	}
	
	public void SzomszedMutat(AszteroidaView av) {
		teglalap.setFill(Color.RED);
		
	}
	public void feltesz(Group group) {
		group.getChildren().add(teglalap);
//		Scene scene= new Scene(group, 60, 110);
//		stage.setScene(scene);
//		stage.show();
	}
	
	public Teleportkapu getKapu() {
		return kapu;
	}
}
