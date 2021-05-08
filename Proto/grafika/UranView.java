package grafika;

import javafx.scene.paint.Color;
import proto.Nyersanyag;

public class UranView extends NyersanyagView{

	/**
	 * A SzenView konstruktora, átadja a paramétereket az õs konstruktorának, majd beállítja a kör szinét
	 * @param ny a nyersanyag
	 * @param kx a kör x koordinátája
	 * @param ky a kör y koordinátája
	 */
	public UranView(Nyersanyag ny, int kx, int ky) {
		super(ny, kx, ky);
		kor.setFill(Color.LIGHTGREEN);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Kitölti az aszteroida magjában lévõ kör színét
	 */
	public void kitolt() {
		kor.setFill(Color.SPRINGGREEN);
	}
}
