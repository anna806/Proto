import java.util.ArrayList;
import java.util.List;

public class Utmutato {
	private List<Nyersanyag> hozzavalok = new ArrayList<Nyersanyag>() ;
	private List<Nyersanyag> megvanLista = new ArrayList<Nyersanyag>();
	
	Utmutato(List<Nyersanyag> kellenek){
		hozzavalok = new ArrayList<Nyersanyag>();
		for(Nyersanyag n : kellenek) {
			hozzavalok.add(n);
		}
		megvanLista = new ArrayList<Nyersanyag>();
	}
	
	public boolean MindMegvan(Nyersanyag ny) {
		boolean answer;
		for(Nyersanyag n : hozzavalok) {
			answer = n.Kompatibilis(ny);
			if(answer) {
				hozzavalok.remove(n);
				megvanLista.add(n);
				return hozzavalok.size() == 0;
			}
		}
		return hozzavalok.size() == 0;
	}
}
