package grafika;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proto.Aszteroida;
import proto.Jatek;

public class Jatekter {
 //!!!!!! a game átkerült a jatekallpotba
	//ötletek-Luca
	private List<AszteroidaView> aszteroidak;
	private AszteroidaView kivalasztott;
	private Jatek game;
	
	 
	public void felepit(Jatek _game, Scene _oldscene, Stage _primary){
		game=_game;
		int szeles=600;																	// jatekter merete
		int magas=500;
		int[] xek;																		//létrehozott aszteroidaviewek x koordinátája
		int[] yok;																		//y koordinátája
		List<Aszteroida> bolygok= new ArrayList<Aszteroida>();
		bolygok=game.GetOv().GetAszteroidak();
		aszteroidak= new ArrayList<AszteroidaView>();
		
		Group g= new Group();
		Random rand = new Random();
		
		for(AszteroidaView a : aszteroidak){
			int x = rand.nextInt(szeles);
			int y = rand.nextInt(magas);
			for(int i=0; i<aszteroidak.size();i++) {
				if(abs(x-xek.i)<100 && abs(y-yok.i)<100) {					//ha túl közel van,újraszámol, 
					x+=110;
					y+=110;
				}
			}
			AszteroidaView nezet= new AszteroidaView(a, x, y, g);
			nezet.feltesz();
		}
		//kell majd return 
	}
	
	public AszteroidaView getKivalasztott() {
		return kivalasztott;
	}
}
