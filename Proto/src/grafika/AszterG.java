package grafika;

import proto.*;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AszterG extends Pane{

	private Aszteroida data;
	public Rectangle keret;
	public Polygon robot;
	public Circle ufo;
	public TableView<String> telepesek;
	public Label rbt;
	public Label ufl;
	public Label kpu;
	public Label kereg;
	public Label napkozel;
	public Label napvihar;
	
	public void felepit(Stage primary) {
		kereg= new Label();
		kereg.setText(String.valueOf(data.getKopenyVastagsag()));
		
		int robotok=0;
		for(int i=0; i<Main.game.GetOv().GetRobotokSize(); i++) {
			if(Main.game.GetOv().GetRobot(i).getAszteroida().equals(data)) {
				robotok+=1;
			}
		}
		
		int ufok=0;
		for(int i=0; i<Main.game.GetOv().GetUfokSize(); i++) {
			if(Main.game.GetOv().GetUfo(i).getAszteroida().equals(data)) {
				ufok+=1;
			}
		}
		
		List<Telepes> jatekosok=new ArrayList<Telepes>();
		for(int i=0; i<Main.game.GetOv().GetTelepesekSize(); i++) {
			if(Main.game.GetOv().GetTelepes(i).getAszteroida().equals(data)) {
				jatekosok.add(Main.game.GetOv().GetTelepes(i));
			}
		}
		for(int i=0; i<jatekosok.size(); i++) {
			telepesek.Add
		}
		
		rbt=new Label(String.valueOf(robotok));
		ufl=new Label(String.valueOf(ufok));
		
		robot= new Polygon();
		robot=createTriangle(new Point2D(-1,0), new Point2D(0,1), new Point2D(1,0));
		
		
		
	}
	
	Polygon createTriangle(Point2D p1, Point2D p2, Point2D p3){
	    Point2D centre = p1.midpoint(p2).midpoint(p3);
	    Point2D p1Corrected = p1.subtract(centre);
	    Point2D p2Corrected = p2.subtract(centre);
	    Point2D p3Corrected = p3.subtract(centre);
	    Polygon polygon = new Polygon(
	            p1Corrected.getX(), p1Corrected.getY(),
	            p2Corrected.getX(), p2Corrected.getY(),
	            p3Corrected.getX(), p3Corrected.getY()
	    );
	    polygon.setLayoutX(centre.getX());
	    polygon.setLayoutY(centre.getY());
	    return polygon;
	}
	
}

