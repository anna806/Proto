package grafika;

public class Jatekter {
 //!!!!!! a game �tker�lt a jatekallpotba
	//�tletek-Luca
	private List<AszteroidaView> aszteroidak;
	private Jatek game;
	
	
	public ... felepit(Jatek _game, Scene _oldscene, Stage _primary){
		game=_game;
		int szeles=600;																	// jatekter merete
		int magas=500
		int[] xek;																		//l�trehozott aszteroidaviewek x koordin�t�ja
		int[] yok;																		//y koordin�t�ja
		List<Aszteroida> bolygok= new ArrrayList<Aszteroida>();
		bolygok=game.GetOv().GetAszteroidak();
		aszteroidak= new ArrayList<AszteroidaView>();
		
		Group g= new Group();
		
		foreach(Aszteroida a in aszteroidak){
			int x = rand.nextInt(szeles);
			int y = rand.nextInt(magas);
			for(int i=0; i<aszteroidak.size()) {
				if(abs(x-xek.i)<100 && abs(y-yok.i)<100) {
					x+=110;
					y+=110;
				}
			}
			AszteroidaView nezet= new AszteroidaView(a, x, y, g);
			nezet.feltesz();
		}
	}
}
