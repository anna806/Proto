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
	private List<KapuView> kapunezet;
	
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
			anyag.kitolt();
			
		}
		List<Teleportkapu> kapulista=new ArrayList<Teleportkapu>();
		for(int i=0; i<Main.game.GetOv().GetKapukSize(); i++) {
			if(Main.game.GetOv().GetKapu(i).getAszteroida().equals(data)) {
				kapulista.add(Main.game.GetOv().GetKapu(i));
			}
		}
		if(kapulista.size()>0) 
			for(int i=0; i<kapulista.size(); i++) {
			KapuView kv= new KapuView(kapulista.get(i), x+60, 100);
			kapunezet.add(kv);
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
	public void felepit(Stage stage) {
		Group group= new Group();
		group.getChildren().add(kor);
		if(anyag!=null) {
			anyag.feltesz(stage);
		}
		if(kapunezet.size()>0) {
			foreach(KapuView nezet in kapunezet){
				nezet.feltesz(stage);
			}
		}
		Scene scene= new Scene(group, 110, 110);
		stage.setScene(scene);
		stage.show();
		
	}
}
	
