package grafika;

import javafx.scene.paint.Color;
import proto.Nyersanyag;

public class VizjegView extends NyersanyagView{
	
	/**
	 * A SzenView konstruktora, �tadja a param�tereket az �s konstruktor�nak, majd be�ll�tja a k�r szin�t
	 * @param ny a nyersanyag
	 * @param kx a k�r x koordin�t�ja
	 * @param ky a k�r y koordin�t�ja
	 */
	public VizjegView(Nyersanyag ny, int kx, int ky) {
		super(ny, kx, ky);
		kor.setFill(Color.LIGHTSKYBLUE);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Kit�lti az aszteroida magj�ban l�v� k�r sz�n�t
	 */
	public void kitolt() {
		kor.setFill(Color.LIGHTBLUE);
	}
}
