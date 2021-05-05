package grafika;

import javafx.scene.paint.Color;
import proto.Nyersanyag;

public class VizjegView extends NyersanyagView{
	public VizjegView(Nyersanyag ny, int kx, int ky) {
		super(ny, kx, ky);
		kor.setFill(Color.LIGHTSKYBLUE);
		// TODO Auto-generated constructor stub
	}

	public void kitolt() {
		kor.setFill(Color.LIGHTBLUE);
	}
}
