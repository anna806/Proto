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
	
	/**
	 * Megn�zi, hogy az adott nyersanyagot ki kell e t�r�lni a telepest�l, ha igen,
	 * berakja a t�rlend� list�ba �s kiszedi a megvanlist�b�l
	 * @param ny : a telepesn�l vizsg�lt nyersanyag
	 * @param torlendo A telepes sz�m�ra t�rlend� anyagok list�ja
	 * @return siker�lt e kit�r�lni az �sszes sz�ks�ges nyersanyagot
	 */
	public boolean MindTorol(Nyersanyag ny, List<Nyersanyag> torlendo) {
		for(int i = 0; i < megvanLista.size(); i++) {
			if(megvanLista.get(i).Kompatibilis(ny)) {
				torlendo.add(ny);
				megvanLista.remove(i);
				break;
			}
		}
		return megvanLista.size() == 0;
	}
}
