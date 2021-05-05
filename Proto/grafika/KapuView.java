package grafika;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
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
			teglalap.setFill(Color.RED);
		}
		else
			teglalap.setFill(Color.RED);
	}
	public void feltesz(Stage stage) {
		Group group=new Group();
		group.getChildren().add(teglalap);
		Scene scene= new Scene(group, 60, 110);
		stage.setScene(scene);
		stage.show();
	}
}
