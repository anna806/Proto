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
	 * A KapuView-hoz tartoz� teleportkapu
	 */
	private Teleportkapu kapu;
	/**
	 * A Teleportkapu x koordin�t�ja
	 */
	double x;
	/**
	 * A Teleportkapu x koordin�t�ja
	 */
	double y;
	/**
	 * A Teleportkaput �br�zol� t�glalap
	 */
	public Rectangle teglalap;
	/**
	 * megadja, hogy ki van-e v�lasztva a teleportkapu
	 */
	boolean valasztott;
	
	
	List<Line> lines = new ArrayList<Line>();
	/**
	 * A kapuView konstruktora, be�ll�tja a param�tereket
	 * @param tk : A View-hoz tartoz� teleportkapu
	 * @param kx : A kapu x koordin�t�ja
	 * @param ky : A kapu y koordin�t�ja
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
	 * Be�ll�tja a teleportkapu sz�n�t, att�k f�gg�en, hogy ki van-e v�lasztva
	 * @param val : megadja, hogy ki van-e v�lasztva
	 */
	public void SetVal(boolean val) {
		if(val) {
			teglalap.setFill(Color.GREEN);
		}
		else
			teglalap.setFill(Color.WHITE);
	}
	
	/**
	 * Kisz�nezi a kapu p�rj�t
	 * @param av : az aszteroida, amin a szomsz�dja van
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
	 * Visszaadja a View-hoz tartoz� teleportkapu
	 * @return a View-hoz tartoz� teleportkapu
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
