package grafika;
import javafx.scene.paint.Color;
import proto.*;

public class VasView extends NyersanyagView {
	public VasView(Nyersanyag ny, int kx, int ky) {
		super(ny, kx, ky);
		kor.setFill(Color.DIMGREY);
		// TODO Auto-generated constructor stub
	}

	public void kitolt() {
		kor.setFill(Color.GREY);
	}
}
