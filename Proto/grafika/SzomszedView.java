package grafika;

import java.util.List;

import javafx.scene.shape.Line;

public abstract class SzomszedView {
	
	/**
	 * Kisz�nezi a kapu p�rj�t, absztrakt, megval�s�t�sa KapuViewban
	 * @param av : az aszteroida, amin a szomsz�dja van
	 */
	public abstract void SzomszedMutat(AszteroidaView ref);
	
	/**
	 * Visszaadja a szomsz�dok k�zti �leket tartalmaz� list�t, absztrakt, 
	 * megval�s�t�sa AszteroidaViewban
	 * @return 
	 */
	public abstract List<Line> getVonalak();
}
