package grafika;

import proto.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class NyersanyagView {

	/**
	 * A megjelen�t�shez sz�ks�ges x koordin�ta
	 */
	private int x;
	
	/**
	 * A megjelen�t�shez sz�ks�ges y koordin�ta
	 */
	private int y;
	
	/**
	 * A nyersanyag adatait tartalmaz� adattag, ebben van t�bbek k�z�tt a megjelen�t�shez sz�ks�ges t�pusa
	 */
	private Nyersanyag adat;
	
	/**
	 * A nyersanyagot reprezent�l� Circle grafikus objektum
	 */
	public Circle kor;
	
	
	/**
	 * A grafikus megjelen�t�s be�ll�t�sait kezel� konstruktor
	 * 
	 * @param ny - A megjelen�tend� nyersanyag objektum
	 * @param kx - A megjelen�t�shez sz�ks�ges x koordin�ta
	 * @param ky - A megjelen�t�shez sz�ks�ges y koordin�ta
	 */
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
	
	/**
	 * �res aszteroida eset�n be�ll�tja a nyersanyag kit�lt�s�t az aszteroid��val megegyez�re
	 */
	public void ures() {
		kor.setFill(Color.DARKSLATEBLUE);
	}
	
	/**
	 * Bet�lt�tt nyersanyag eset�n be�ll�tja a kit�lt�st a megfelel� sz�n�re
	 */
	public void kitolt() {}
	
	/**
	 * Felveszi a nyersanyagot a list�ra
	 * 
	 * @param group - a Group objektum ami a nyersanyagokat t�rolja
	 */
	public void feltesz(Group group) {
		group.getChildren().add(kor);
//		Scene scene= new Scene(group, 41, 41);
//		stage.setScene(scene);
//		stage.show();
	}
	
	
}
