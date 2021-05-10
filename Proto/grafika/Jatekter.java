package grafika;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import proto.Aszteroida;
import proto.Jatek;
import proto.Szomszed;
import proto.Teleportkapu;

public class Jatekter extends Pane{
	
	/**
	 * Az aszteroida�v �sszes aszteroid�j�nak megjelen�se
	 * Az aszteroida�vben l�v� kapuk megjelen�se
	 * A kiv�laszott (z�ld keretes) aszteroida megjelen�se
	 * A j�t�klogika
	 * A jobboldali, t�bl�zatokat t�rol� men�s�v
	 * A j�t�kt�r alapja, amire az elemeket tessz�k
	 */
	private List<AszteroidaView> aszteroidak;
	private List<KapuView> kapuk;
	private AszteroidaView kivalasztott;
	private Jatek game;
	private Menusav m;
	private Pane p;
	
	 
	/**
	 * @param _game: j�t�klogika
	 * @param _oldscene: a kor�bbi megjelen�s
	 * @param _primary: a f� b�zisa a j�t�knak, amire az elemek �s t�rol�k ker�lnek
	 * @param menu: a j�t�k men�s�vja (jobboldai t�bl�zatos fel�let)
	 * @return
	 */
	public Pane felepit(Jatek _game, Scene _oldscene, Stage _primary, Menusav menu){
		m = menu;
		game=_game;
		int szeles=550;	
		int magas=450;
		List<Integer> xek = new ArrayList<>();																		
		List<Integer> yok = new ArrayList<>();																	
		List<Aszteroida> bolygok= new ArrayList<Aszteroida>();
		bolygok=game.GetOv().getAszteroidak();
		aszteroidak= new ArrayList<AszteroidaView>();
		kapuk=new ArrayList<KapuView>();
		List<Teleportkapu> tk= new ArrayList<Teleportkapu>();
		tk=game.GetOv().GetKapuk();
		
		p = new Pane();
		Random rand = new Random();
		
		for(Aszteroida b : bolygok){
			int x = rand.nextInt(szeles);
			if(x<50) x+=50;
			int y = rand.nextInt(magas);
			for(int i=0; i<aszteroidak.size();i++) {
				if(Math.abs(x-xek.get(i))<100 && Math.abs(y-yok.get(i))<100) {	 
					x+=110;
					y+=110;
				}
			}
			xek.add(x);
			yok.add(y);
			AszteroidaView nezet= new AszteroidaView(b, x, y, p, this);
			aszteroidak.add(nezet);
			nezet.felepit();
			nezet.kor.setOnMousePressed(KorLenyomasEventHandler);
		}
		for(Teleportkapu t : tk) {

			for (AszteroidaView av : aszteroidak){

				if(t.getAszter().equals(av.getAszteroida())) {
					KapuView uj= new KapuView(t, av.kor.getCenterX()+40, av.kor.getCenterY()-30);
					kapuk.add(uj);
					p.getChildren().add(uj.teglalap);
				}
			}
		}
		
		szomszedgeneral();
		p.setStyle("-fx-background-color: MIDNIGHTBLUE");
		return p;
		
	}
	/**
	 * Aszteroida kiv�laszt�sa az aszteroida�vb�l
	 */
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
	
	/**
	 * Kiv�lasztott (z�ld keretes) aszteroida kiad�sa
	 * @return kiv�laszott aszteroida megjelen�se
	 */
	public AszteroidaView getKivalasztott() {
		return kivalasztott;
	}
	
	/**
	 * 	Az aszteroida�vben l�v� aszteroid�k list�j�nak visszaad�sa
	 * @return aszteroid�k list�ja
	 */
	public List<AszteroidaView> getaszteroidak(){
		return aszteroidak;
	}
	
	/**
	 * Az aszteroida�vben l�v� meg�p�tett kapuk list�j�nak visszaad�sa
	 * @return kapuk list�ja
	 */
	public List<KapuView> getKapuk(){
		return kapuk;
	}
	
	/**
	 * Szomsz�ds�gi viszonyok be�ll�t�sa minden AszteroidaView-ra
	 */
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
	
	/**
	 * Szomsz�ds�gvonalak friss�t�se az aktu�lis j�t�kos aszteroid�j�val 
	 * @param av: aktu�lis aszteroida n�zete
	 * @param akt: igaz, ha az aktu�lis aszteroid�ra h�v�dik meg
	 */
	public void Update(AszteroidaView av, boolean akt) {
		if(akt) {
				for (Line l : av.vonalak){
					l.setFill(Color.RED);
				}
		}
	}
	
	/**
	 * Visszaadja a param�terk�nt megadott aszteroida n�zet�t
	 * @param a: aszteroida, amelynek az AszteroidaViewj�t meg szeretn�nk kapni
	 * @return AszteroidaView
	 */
	public AszteroidaView getAszteroidaView(Aszteroida a) {
		for(int i = 0; i < aszteroidak.size(); i++) {
			if(aszteroidak.get(i).getAszteroida().equals(a))
				return aszteroidak.get(i);
		}
		return null;
	}
}
	
