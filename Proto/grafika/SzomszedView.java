package grafika;

import java.util.List;

import javafx.scene.shape.Line;

public abstract class SzomszedView {
	
	/**
	 * Kiszínezi a kapu párját, absztrakt, megvalósítása KapuViewban
	 * @param av : az aszteroida, amin a szomszédja van
	 */
	public abstract void SzomszedMutat(AszteroidaView ref);
	
	/**
	 * Visszaadja a szomszédok közti éleket tartalmazó listát, absztrakt, 
	 * megvalósítása AszteroidaViewban
	 * @return 
	 */
	public abstract List<Line> getVonalak();
}
