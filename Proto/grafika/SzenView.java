package grafika;
import javafx.scene.paint.Color;
import proto.*;

public class SzenView extends NyersanyagView{
	public SzenView(Nyersanyag ny, int kx, int ky) {
		super(ny, kx, ky);
		kor.setFill(Color.BLACK);
		// TODO Auto-generated constructor stub
	}

	public void kitolt() {
		kor.setFill(Color.BLACK);
	}
}
