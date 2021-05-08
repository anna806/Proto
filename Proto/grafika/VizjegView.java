package grafika;

import javafx.scene.paint.Color;
import proto.Nyersanyag;

public class VizjegView extends NyersanyagView{
	
	/**
	 * A SzenView konstruktora, átadja a paramétereket az õs konstruktorának, majd beállítja a kör szinét
	 * @param ny a nyersanyag
	 * @param kx a kör x koordinátája
	 * @param ky a kör y koordinátája
	 */
	public VizjegView(Nyersanyag ny, int kx, int ky) {
		super(ny, kx, ky);
		kor.setFill(Color.LIGHTSKYBLUE);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Kitölti az aszteroida magjában lévõ kör színét
	 */
	public void kitolt() {
		kor.setFill(Color.LIGHTBLUE);
	}
}
