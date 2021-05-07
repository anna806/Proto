package grafika;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import proto.Aszteroida;
import proto.Jatek;
import proto.Szomszed;
import proto.Teleportkapu;

public class Jatekter extends Pane{
 //!!!!!! a game átkerült a jatekallpotba
	//ötletek-Luca
	private List<AszteroidaView> aszteroidak;
	private List<KapuView> kapuk;
	private AszteroidaView kivalasztott;
	private Jatek game;
	private Group g;
	
	 
	public Group felepit(Jatek _game, Scene _oldscene, Stage _primary){
		game=_game;
		int szeles=600;																	// jatekter merete
		int magas=500;
		int[] xek = null;																		//létrehozott aszteroidaviewek x koordinátája
		int[] yok = null;																		//y koordinátája
		List<Aszteroida> bolygok= new ArrayList<Aszteroida>();
		bolygok=game.GetOv().getAszteroidak();
		aszteroidak= new ArrayList<AszteroidaView>();
		kapuk=new ArrayList<KapuView>();
		List<Teleportkapu> tk= new ArrayList<Teleportkapu>();
		tk=game.GetOv().GetKapuk();
		
		g= new Group();
		Random rand = new Random();
		
		for(Aszteroida b : bolygok){
			int x = rand.nextInt(szeles);
			int y = rand.nextInt(magas);
			for(int i=0; i<aszteroidak.size();i++) {
				if(Math.abs(x-xek[i])<100 && Math.abs(y-yok[i])<100) {					//ha túl közel van,újraszámol, 
					x+=110;
					y+=110;
					xek[i] = x;
					yok[i] = y;
				}
			}
			AszteroidaView nezet= new AszteroidaView(b, x, y, g);
			aszteroidak.add(nezet);
			nezet.felepit();
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
		 
		//kell majd return 
		return g;
	}
	
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
