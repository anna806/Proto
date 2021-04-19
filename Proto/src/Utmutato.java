package src;
import java.util.ArrayList;
import java.util.List;

public class Utmutato {
	
	/**
	 * Az útmutató teljesítéshez szükséges anyagok listája 
	 */
	private List<Nyersanyag> hozzavalok = new ArrayList<Nyersanyag>() ;
	/**
	 * Azon anyagok listája, amelyeket már megtaláltunk a szükségesek közül
	 */
	private List<Nyersanyag> megvanLista = new ArrayList<Nyersanyag>();
	
	/**
	 * Az útmutató konstruktora
	 * @param kellenek : a teljesítéshez szükséges anyagok listája
	 */
	Utmutato(List<Nyersanyag> kellenek){
		hozzavalok = new ArrayList<Nyersanyag>();
		for(Nyersanyag n : kellenek) {
			hozzavalok.add(n);
		}
		megvanLista = new ArrayList<Nyersanyag>();
	}
	
	/**
	 * Megnézi, hogy egy adott nyersanyag benne van e még a szükséges nyersanyagok között, ha igen,
	 * azt átrakja a megtalált listába és visszaadja, hogy megvan-e az összes szükséges anyag.
	 * @param ny : az adott nyersanyag
	 * @return Megvan e az összes anyag ami az építéshez szükséges.
	 */
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
	
	public boolean MindTorol(Nyersanyag ny, Telepes t) {
		for(Nyersanyag n : megvanLista) {
			if(n.Kompatibilis(ny)) {
				t.RemoveNyersanyag(n);
				megvanLista.remove(n);
			}
		}
		return megvanLista.size() == 0;
	}
}
