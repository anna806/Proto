package grafika;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import proto.*;

public class KapuView extends SzomszedView{
	/**
	 * A KapuView-hoz tartozó teleportkapu
	 */
	private Teleportkapu kapu;
	/**
	 * A Teleportkapu x koordinátája
	 */
	double x;
	/**
	 * A Teleportkapu x koordinátája
	 */
	double y;
	/**
	 * A Teleportkaput ábrázoló téglalap
	 */
	public Rectangle teglalap;
	/**
	 * megadja, hogy ki van-e választva a teleportkapu
	 */
	boolean valasztott;
	
	
	List<Line> lines = new ArrayList<Line>();
	/**
	 * A kapuView konstruktora, beállítja a paramétereket
	 * @param tk : A View-hoz tartozó teleportkapu
	 * @param kx : A kapu x koordinátája
	 * @param ky : A kapu y koordinátája
	 */
	public KapuView(Teleportkapu tk, double kx, double ky) {
		kapu= new Teleportkapu(); 
		kapu=tk;
		x=kx;
		y=ky;
		
		teglalap= new Rectangle();
		teglalap.setX(x);
		teglalap.setY(y);
		teglalap.setWidth(20);
		teglalap.setHeight(50);
		teglalap.setFill(Color.WHITE);
	}
	
	/**
	 * Beállítja a teleportkapu színét, attók függõen, hogy ki van-e választva
	 * @param val : megadja, hogy ki van-e választva
	 */
	public void SetVal(boolean val) {
		if(val) {
			teglalap.setFill(Color.GREEN);
		}
		else
			teglalap.setFill(Color.WHITE);
	}
	
	/**
	 * Kiszínezi a kapu párját
	 * @param av : az aszteroida, amin a szomszédja van
	 */
	public void SzomszedMutat(AszteroidaView av) {
		teglalap.setFill(Color.RED);
		
	}
	
	/**
	 * Kirajzolja a kaput
	 * @param pane : erre rajzolja a kaput
	 */
	public void feltesz(Pane pane) {
		pane.getChildren().add(teglalap);
	}
	
	/**
	 * Visszaadja a View-hoz tartozó teleportkapu
	 * @return a View-hoz tartozó teleportkapu
	 */
	public Teleportkapu getKapu() {
		return kapu;
	}

	@Override
	public List<Line> getVonalak() {
		Line l = new Line();
		l.setStartX(teglalap.getX());
		l.setStartY(teglalap.getX());
		l.setEndX(teglalap.getX());
		l.setEndY(teglalap.getX());
		l.setStroke(Color.TRANSPARENT);
		lines.add(l);
		return lines;
	}
}
