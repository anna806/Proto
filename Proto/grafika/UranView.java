package grafika;

import javafx.scene.paint.Color;
import proto.Nyersanyag;

public class UranView extends NyersanyagView{

	public UranView(Nyersanyag ny, int kx, int ky) {
		super(ny, kx, ky);
		// TODO Auto-generated constructor stub
	}

	public void kitolt() {
		kor.setFill(Color.SPRINGGREEN);
	}
}
