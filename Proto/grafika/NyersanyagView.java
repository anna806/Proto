package grafika;

import proto.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class NyersanyagView {

	/**
	 * A megjelenítéshez szükséges x koordináta
	 */
	private int x;
	
	/**
	 * A megjelenítéshez szükséges y koordináta
	 */
	private int y;
	
	/**
	 * A nyersanyag adatait tartalmazó adattag, ebben van többek között a megjelenítéshez szükséges típusa
	 */
	private Nyersanyag adat;
	
	/**
	 * A nyersanyagot reprezentáló Circle grafikus objektum
	 */
	public Circle kor;
	
	
	/**
	 * A grafikus megjelenítés beállításait kezelõ konstruktor
	 * 
	 * @param ny - A megjelenítendõ nyersanyag objektum
	 * @param kx - A megjelenítéshez szükséges x koordináta
	 * @param ky - A megjelenítéshez szükséges y koordináta
	 */
	public NyersanyagView(Nyersanyag ny, int kx, int ky){
//		if(ny != null)
			adat=ny;
		x=kx;
		y=ky;
		
		kor=new Circle();
		kor.setCenterX(x);
		kor.setCenterY(y);
		kor.setRadius(15.0f);
		
		
	}
	
	/**
	 * Üres aszteroida esetén beállítja a nyersanyag kitöltését az aszteroidáéval megegyezõre
	 */
	public void ures() {
		kor.setFill(Color.DARKSLATEBLUE);
	}
	
	/**
	 * Betöltött nyersanyag esetén beállítja a kitöltést a megfelelõ színûre
	 */
	public void kitolt() {
		if(adat.Kompatibilis(new Uran())) {
			kor.setFill(Color.SPRINGGREEN);
		}
		else if(adat.Kompatibilis(new Szen())) {
			kor.setFill(Color.BLACK);
		}
		else if(adat.Kompatibilis(new Vas())) {
			kor.setFill(Color.GREY);
		}
		else if(adat.Kompatibilis(new Vizjeg())) {
			kor.setFill(Color.LIGHTBLUE);
		}		
	}
	
	/**
	 * Felveszi a nyersanyagot a listára
	 * 
	 * @param group - a Group objektum ami a nyersanyagokat tárolja
	 */
	public void feltesz(Pane pane) {
		pane.getChildren().add(kor);
	}
	
	public void setData(Nyersanyag d) {
		adat = d;
	}
	
	public Nyersanyag getData() {
		return adat;
	}
}
