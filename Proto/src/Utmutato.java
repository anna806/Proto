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
	
	public boolean MindTorol(Nyersanyag ny, List<Nyersanyag> torlendo) {
		//for(Nyersanyag n : megvanLista) {
		for(int i = 0; i < megvanLista.size(); i++) {
			if(megvanLista.get(i).Kompatibilis(ny)) {
				System.out.println(megvanLista.get(i).getID());
				System.out.println(ny.getID());
				//t.RemoveNyersanyag(megvanLista.get(i));
				//megvanLista.remove(megvanLista.get(i));
				//t.RemoveNyersanyag(ny);
				torlendo.add(ny);
				megvanLista.remove(i);
				break;
			}
		}
		System.out.println(megvanLista.size() == 0);
		return megvanLista.size() == 0;
	}
}
