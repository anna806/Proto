package grafika;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import proto.Aszteroida;
import proto.Jatek;
import proto.Szomszed;
import proto.Teleportkapu;

public class Jatekter extends Pane{
 //!!!!!! a game átkerült a jatekallpotba
	
	private List<AszteroidaView> aszteroidak;
	private List<KapuView> kapuk;
	private AszteroidaView kivalasztott;
	private Jatek game;
	private Group g;
	private Menusav m;
	private Pane p;
	
	 
	public Pane felepit(Jatek _game, Scene _oldscene, Stage _primary, Menusav menu){
		m = menu;
		game=_game;
		int szeles=600;																	// jatekter merete
		int magas=500;
		List<Integer> xek = new ArrayList<>();																		//létrehozott aszteroidaviewek x koordinátája
		List<Integer> yok = new ArrayList<>();																		//y koordinátája
		List<Aszteroida> bolygok= new ArrayList<Aszteroida>();
		bolygok=game.GetOv().getAszteroidak();
		aszteroidak= new ArrayList<AszteroidaView>();
		kapuk=new ArrayList<KapuView>();
		List<Teleportkapu> tk= new ArrayList<Teleportkapu>();
		tk=game.GetOv().GetKapuk();
		
//		g= new Group();
		p = new Pane();
		Random rand = new Random();
		
		for(Aszteroida b : bolygok){
			int x = rand.nextInt(szeles);
			int y = rand.nextInt(magas);
			for(int i=0; i<aszteroidak.size();i++) {
				if(Math.abs(x-xek.get(i))<100 && Math.abs(y-yok.get(i))<100) {					//ha túl közel van,újraszámol, 
					x+=110;
					y+=110;
				}
			}
			xek.add(x);
			yok.add(y);
			AszteroidaView nezet= new AszteroidaView(b, x, y, g);
			aszteroidak.add(nezet);
			nezet.felepit();
			nezet.kor.setOnMousePressed(KorLenyomasEventHandler);
		}
		for(Teleportkapu t : tk) {

			for (AszteroidaView av : aszteroidak){

				if(t.getAszter().equals(av.getAszteroida())) {
					KapuView uj= new KapuView(t, av.kor.getCenterX()-70, av.kor.getCenterY()-50, g);
					kapuk.add(uj);
					//uj.feltesz();
					g.getChildren().add(uj.teglalap);
				}
			}
		}
		
		
		p.setStyle("-fx-background-color: MIDNIGHTBLUE");
		return p;
		
	}
	EventHandler<MouseEvent> KorLenyomasEventHandler = 
	        new EventHandler<MouseEvent>() {
	 
	        @Override
	        public void handle(MouseEvent t) {
	        for(AszteroidaView av : aszteroidak) {
	           if(((Circle)(t.getSource())).equals(av.kor)){
	        	   if(kivalasztott!=null) {
	        	   kivalasztott.setValaszt(false);
	        	   }
	        	   av.setValaszt(true);
	        	   kivalasztott=av;
	        	   m.Update(kivalasztott, true);
	           }
	           }
	        }
	};
	
	public AszteroidaView getKivalasztott() {
		return kivalasztott;
	}
	
	public List<AszteroidaView> getaszteroidak(){
		return aszteroidak;
	}
	
	public List<KapuView> getKapuk(){
		return kapuk;
	}
	
	public void szomszedgeneral() {
		for (AszteroidaView av : aszteroidak) {
			for(Szomszed sz : av.getAszteroida().getSzomszedok()) {
				for(AszteroidaView avk : aszteroidak ) {
					if(av!=avk) {
						if(sz.equals(avk.getAszteroida()))
							av.addSzomszed(avk);
					}
				}
				for(KapuView kv: kapuk) {
					if(sz.equals(kv.getKapu().getAszter()))
						av.addSzomszed(kv);
				}
			}
		}
	}
	
	public AszteroidaView getAszteroidaView(Aszteroida a) {
		for(int i = 0; i < aszteroidak.size(); i++) {
			if(aszteroidak.get(i).getAszteroida().equals(a))
				return aszteroidak.get(i);
		}
		return null;
	}
}
	
