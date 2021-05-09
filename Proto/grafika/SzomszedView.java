package grafika;

import java.util.List;

import javafx.scene.shape.Line;

public abstract class SzomszedView {
	public abstract void SzomszedMutat(AszteroidaView ref);
	
	public abstract List<Line> getVonalak();
}
