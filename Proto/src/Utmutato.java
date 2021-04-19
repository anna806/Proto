package src;
import java.util.ArrayList;
import java.util.List;

public class Utmutato {
	
	/**
	 * Az �tmutat� teljes�t�shez sz�ks�ges anyagok list�ja 
	 */
	private List<Nyersanyag> hozzavalok = new ArrayList<Nyersanyag>() ;
	/**
	 * Azon anyagok list�ja, amelyeket m�r megtal�ltunk a sz�ks�gesek k�z�l
	 */
	private List<Nyersanyag> megvanLista = new ArrayList<Nyersanyag>();
	
	/**
	 * Az �tmutat� konstruktora
	 * @param kellenek : a teljes�t�shez sz�ks�ges anyagok list�ja
	 */
	Utmutato(List<Nyersanyag> kellenek){
		hozzavalok = new ArrayList<Nyersanyag>();
		for(Nyersanyag n : kellenek) {
			hozzavalok.add(n);
		}
		megvanLista = new ArrayList<Nyersanyag>();
	}
	
	/**
	 * Megn�zi, hogy egy adott nyersanyag benne van e m�g a sz�ks�ges nyersanyagok k�z�tt, ha igen,
	 * azt �trakja a megtal�lt list�ba �s visszaadja, hogy megvan-e az �sszes sz�ks�ges anyag.
	 * @param ny : az adott nyersanyag
	 * @return Megvan e az �sszes anyag ami az �p�t�shez sz�ks�ges.
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
