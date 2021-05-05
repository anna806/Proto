package grafika;

import proto.*;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
	private boolean valasztott;
	private boolean aktualis;
	
	public AszterG(Aszteroida a, boolean aktual, boolean valaszt) {
		data=a; aktualis=aktual; valasztott=valaszt;
	}
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
		
		int kapuk=0;
		for(int i=0; i<Main.game.GetOv().GetKapukSize(); i++) {
			if(Main.game.GetOv().GetKapu(i).getAszter().equals(data)) {
				kapuk+=1;
			}
		}
		
		List<Telepes> jatekosok=new ArrayList<Telepes>();
		for(int i=0; i<Main.game.GetOv().GetTelepesekSize(); i++) {
			if(Main.game.GetOv().GetTelepes(i).getAszteroida().equals(data)) {
				jatekosok.add(Main.game.GetOv().GetTelepes(i));
			}
		}
		for(int i=0; i<jatekosok.size(); i++) {
//			telepesek.Add(jatekosok.get(i).GetNev());
		}
		
		rbt=new Label("Robotok száma: "+String.valueOf(robotok));
		ufl=new Label("Ufok száma: "+String.valueOf(ufok));
		kpu= new Label("Kapuk száma: "+String.valueOf(kapuk));
		
		robot= new Polygon();
		robot=createTriangle(new Point2D(-1,0), new Point2D(0,1), new Point2D(1,0));
		
		napkozel=new Label();
		if(data.getNap())
			napkozel.setText("+");
		else
			napkozel.setText("-");
		
		keret=new Rectangle();
		keret.setX(0);
		keret.setY(220);
		keret.setWidth(200);
		keret.setHeight(200);
		keret.setArcWidth(20);
		keret.setArcHeight(20);
		
		keret.setFill(Color.TRANSPARENT);
		if(valasztott==true)
			keret.setStroke(Color.GREEN);
		if(aktualis==true)
			keret.setStroke(Color.RED);
		
		//már csak fel kéne pakolni
		
		
		Group group = new Group();
		group.getChildren().add(kereg);
		group.getChildren().add(napkozel);
		group.getChildren().add(telepesek);
		group.getChildren().add(robot);
		group.getChildren().add(rbt);
		group.getChildren().add(ufo);
		group.getChildren().add(ufl);
		group.getChildren().add(kpu);
		
		group.getChildren().add(kereg);
		group.getChildren().add(napkozel);
		
		StackPane sp=new StackPane();
		sp.getChildren().addAll(keret, group);
		Scene scene= new Scene(group, 220, 220);
		primary.setScene(scene);
		primary.show();
		
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
	
	public Aszteroida GetAszteroida() {
		return data;
	}
	
}

