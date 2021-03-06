package proto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aszteroida extends Szomszed{
	
	/**
	 * a szerializ?l?shoz sz?ks?ges ID
	 */
	private static final long serialVersionUID = 3753080766421296425L;
	/**
	 * az aszteroida k?peny?nek vastags?ga
	 */
	private int kopenyVastagsag;								
	/**
	 * megadja, hogy az aszzteroida napk?zelben van-e
	 */
	private boolean napkozel;									
	/**
	 * az aszteroid?n tart?zkod? entit?sok list?ja
	 */
	private List<Entitas> entitasok = new ArrayList<>();		
	/**
	 * az aszteroida szomsz?dainak list?ja
	 */
	private List<Szomszed> szomszedok = new ArrayList<>();		
	/**
	 * az aszteroida bels? anyaga
	 */
	private Nyersanyag belsoAnyag;								
	/**
	 * sz?molja, hogy h?nyszor p?ld?nyos?tott?k az oszt?lyt
	 */
	private static int count=0;
	
	/**
	 * aszteroida konstruktora, l?trehozza az entitasok, szomszedok list?kat, 
	 * a napk?zel alap?rtelmezett ?rt?ke a false, valamint be?ll?tja az egyedi ID-t
	 */
	public Aszteroida() {
		entitasok = new ArrayList<Entitas>();
		szomszedok = new ArrayList<Szomszed>();
		napkozel = false;
		ID="a0"+count++;
	}
	
	/**
	 * be?ll?tja a napkozel v?ltoz? ?rt?k?t
	 * @param b ez a logikai ?rt?k lesza v?ltoz? ?rt?ke
	 */
	public void setNapkozel(boolean b) {
		napkozel=b;
		if(belsoAnyag != null)
			belsoAnyag.setExp(belsoAnyag.getExp() + 1);
	}
	
	
	/**Az aszteroida bels? anyag?nak elt?vol?t?sa
	 * @return visszaadja az aszteroid?ban l?v? nyersanyagot
	 */
	public Nyersanyag AnyagKinyeres() {
		if(kopenyVastagsag == 0)
			return belsoAnyag;
		else
			return null;
	}
	
	
	/**a kapott entit?st elt?vol?tja az entit?sok list?b?l
	 * @param a : az az entit?s, amelyet el kell t?vol?tani
 	 */
	public void Ledob(Entitas a) {
		entitasok.remove(a);
	}
	
	/**a kapott entit?st felveszi az entit?sok list?ba
	 * @param a : az az entit?s, amelyet fel kell venni
	 */
	public void Befogad(Entitas a) {
		entitasok.add(a);
		a.SetAszteroida(this);
	}
	
	/**
	 *a napvihar abban az esetben, ha az aszteroida nem ?reges, meg?li az aszteroid?n tart?zkod? 
	 *entit?sokat. megh?vja a szomsz?dok megkerg?l f?ggv?ny?t, ami az?rt felel, hogy az aszteroid?n
	 * l?v? kapuk meg tudjanak kerg?lni
	 */
	public void Napvihar() {
		if(belsoAnyag != null&&entitasok.size()!=0) {
			for(Entitas e: entitasok) {
				e.Halal();
			}	
			entitasok.removeAll(entitasok);
		}
		for(Szomszed sz: szomszedok) {
			sz.MegKergul();
		}
	}
	
	/**
	 *?res t?rzs? f?ggv?ny, mert az Aszteroida nem tud megkerg?lni
	 */
	public void MegKergul() {}
	
	/**
	 * A param?terk?nt kapott szomsz?dot t?rli a szomsz?d list?b?l
	 * @param a: a t?rlend? szomsz?d
	 */
	public void SzomszedTorol(Szomszed a) {
		szomszedok.remove(a);
		
	}
	
	/**
	 * b?zis?p?t?s kezdem?nyez?se, a f?ggv?ny ellen?rzi, hogy megvannak-e a megfelel? anyagok.
	 * Ha megvannak, a j?t?k v?g?t true ?rt?kkel h?vja- ilyenkor a j?t?kosok nyernek
	 */
	public String BazisEpit() {
		List<Nyersanyag> kell = new ArrayList<Nyersanyag>();
			
		kell.add(new Uran());
		kell.add(new Uran());
		kell.add(new Uran());
		
		kell.add(new Vas());
		kell.add(new Vas());
		kell.add(new Vas());
		
		kell.add(new Vizjeg());
		kell.add(new Vizjeg());
		kell.add(new Vizjeg());
		
		kell.add(new Szen());
		kell.add(new Szen());
		kell.add(new Szen());
		
		Utmutato bazis = new Utmutato(kell);
		boolean bazisepitheto = false;
		String eredmeny = "Nem siker?lt b?zist ?p?teni.";
		for (int j = 0; j < entitasok.size(); j++) {
			bazisepitheto = entitasok.get(j).BazisEpit(bazis);
			if(bazisepitheto) {
				for(int i = 0; i < entitasok.size(); i++) {
					if(entitasok.get(i).AnyagokTorol(bazis)) {
						eredmeny = Main.game.Vege(bazisepitheto);
						break;
					}
				}
				break;
			}
		}
		return eredmeny;
	}
	
	/**
	 * cs?kkenti az aszteroida k?rg?t 1-gyel. ha a k?reg 0-ra cs?kkent, ?s az 
	 * aszteroida napk?zelben van, megh?vja a bels? anyag napk?zel f?ggv?ny?t
	 */
	public void KeregCsokken() {
		kopenyVastagsag--;
		if (kopenyVastagsag == 0 && belsoAnyag != null) {
			if (napkozel) {
				belsoAnyag.Napkozel(this);
			}
		}
	}
	
	/**
	 * Az aszterida felrobban, ennek sor?n megh?vja a rajta tart?zkod? entit?sok felrobban f?ggv?ny?t, 
	 * ?s elt?vol?tja az aszteroida bels? anyag?t. ezut?n az aszteroida elt?vol?t?sra ker?l az ?sszes 
	 * szomsz?d szomsz?dai k?z?l.
	 */
	public void Robban() { 
		for(Entitas e: entitasok) {
			e.Felrobban();
		}
		entitasok.removeAll(entitasok);
		AnyagTorol();
		for(Szomszed e: szomszedok) {
			e.SzomszedTorol(this);
		}
		Main.game.GetOv().MinuszAszteroida(this);
	}
	
	/**
	 * @return visszaad egy szomsz?dot v?letlenszer?en a szomsz?dok k?z?l
	 */
	public Szomszed SzomszedotAd() {
		Random rand = new Random();
		int i = 0;
		if(!szomszedok.isEmpty()) {
			i = rand.nextInt(szomszedok.size());
			return szomszedok.get(i);
		} 
		else {
			return null;
		}
	}
	
	/**
	 * Rakt?roz? f?ggv?ny, amely a
	 * @param a kapott nyersanyagot
	 * @param t kapott telepest?l
	 * elveszi ?s a belsej?ben elhelyezi abban az esetben, ha az aszteroida ki van f?rva ?s ?reges
	 * ha az aszteroida ?pp napk?zelben van, a nyersanyag Napkozel f?ggv?nye h?v?dik meg
	 */
	public void Raktaroz(Nyersanyag a, Telepes t) {
		if(belsoAnyag == null && kopenyVastagsag == 0) {
			t.RemoveNyersanyag(a);
			belsoAnyag = a;
		}
		if(napkozel) {
			belsoAnyag.Napkozel(this);
		}
	}
	
	/**
	 * kit?rli az aszteroida bels? anyag?t, ha a k?regvastags?g 0
	 */
	public void AnyagTorol() {
		if (kopenyVastagsag==0)
			belsoAnyag = null;
	}
	
	/**
	 * Napvihart ind?t az adott aszteroid?n ?s azok szomsz?dain is
	 */
	public void StartNapvihar() {
		this.Napvihar();
		for(Szomszed sz: szomszedok) {
			sz.Napvihar();
		}
	}	
	
	/**
	 * a kapott teleportkaput felveszi az aszteroida szomsz?dai k?z?
	 *@param k a teleportkapu amelyet fel kell venni
	 */
	public void KapuBefogad(Teleportkapu k) {
		szomszedok.add(k);
	}
	
	/**
	 * A param?terk?nt kapott nyersanyagot be?ll?tja az aszteroida magjak?nt
	 * @param ny a nyersanyag amelyet be kell ?ll?tani
	 */
	public void setNyersanyag(Nyersanyag ny) {
		belsoAnyag = ny;
	}
	
	/**
	 * @return visszat?r?si ?rt?k?ben megadja az aszteroida k?peny?nek vastags?g?t
	 */
	public int getKopenyVastagsag() {
		return kopenyVastagsag;
	}
	
	/**
	 * a kapott sz?mot be?ll?tja az aszteroida k?penyvastags?g?nak
	 * @param kv a sz?m amilyen vastags?g? legyen a k?peny
	 */
	public void setKopenyVastagsag(int kv) {
		kopenyVastagsag = kv;
	}
	
	/**
	 * a param?terben kapott szomsz?dot beteszi a szomsz?dok list?ba
	 * @param sz a kapott szomsz?d
	 */
	public void addSzomszed(Szomszed sz) {
		szomszedok.add(sz);
	}

	/**
	 * Visszaadja, hogy napk?zelben van-e az adott aszteroida
	 * @return az aszteroida napkozel tagv?ltoz?ja String-ben
	 */
	public String getNapkozel() {
		if(napkozel) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * Visszaadja az aszteroida bels? anyag?nak nev?t
	 * @return az aszteroida belsoanyaga Stringk?nt
	 */
	public String getBelsoAnyagString() {
		if(belsoAnyag == null) {
			return "Ureges";
		} else {
			return belsoAnyag.toString();
		}
	}
	
	/**
	 * Visszaadja az aszteroida bels? anyag?t
	 * @return az aszteroida belsoanyag param?tere
	 */
	public Nyersanyag getBelsoAnyag() {
		return belsoAnyag;
	}

	/**
	 * Visszaadja az entitasok lista m?ret?t
	 * @return az entitasok lista m?rete
	 */
	public int EntitasokSize() {
		return entitasok.size();
	}

	/**
	 * Visszaadja az entit?sok list?b?l a param?terben kapott helyen l?v? entit?s ID-j?t
	 * @param i az entit?s sorsz?ma a list?ban
	 * @return az entit?s ID-je
	 */
	public String getEntitas(int i) {
		return entitasok.get(i).getID();
	}

	/**
	 * Visszaadja a szomszedok lista m?ret?t
	 * @return a szomszedok lista m?rete
	 */
	public int SzomszedokSize() {
		return szomszedok.size();
	}

	/**
	 * Visszaadja a szomszedok list?b?l a param?terk?nt kapott helyen l?v? szomsz?d ID-j?t
	 * @param i a szomsz?d sorsz?ma a list?ban
	 * @return a szomsz?d ID-je
	 */
	public String getSzomszed(int i) {
		return szomszedok.get(i).getID();
	}

	/**
	 * Ki?rja az adott aszteroida k?l?nb?z? tulajdon?gait a konzolra
	 */
	public void kiir() {
		System.out.println("Aszteroida " + this);
	}
	
	/**
	 * Ki?rja az adott aszteroida k?l?nb?z? tulajdon?gait egy srtingbe ?s visszaadja azt
	 * @return Az azsteroid?t le?r? string
	 */
	@Override
	public String toString() {
		String s = "";
		s += "\n\"ID\": \"" + ID + "\"\n\"keregvastagsag\": " + kopenyVastagsag +
				"\n\"napkozel\": \"" + napkozel;
		if(belsoAnyag != null)
			s += "\"\n\"belsoAnyag\": \"" + belsoAnyag.getID();
		if(belsoAnyag == null)
			s += "\"\n\"belsoAnyag\": \"Ureges\"\n";
		s += "\"\n\"entitasok\": [";
		for(int i = 0; i < entitasok.size(); i++) {
			s += "\n\t{\n\t\t\"ID: \"" + entitasok.get(i).getID() + "\"\n\t},\n";
		}	
		s += "]\n\"szomszedok\": [";
		for(int i = 0; i < szomszedok.size(); i++)
			s += "\n\t{\n\t\t\"ID: \"" + szomszedok.get(i).getID() + "\"\n\t},\n";
		s += "]\n";
		return s;
	}
	
	/**
	 * Visszaadja a bels? anyag ID-j?t
	 * @return bels? anyag ID-je
	 */
	public String getBelsoID() {
		if(belsoAnyag != null)
			return belsoAnyag.getID();
		else	
			return "Ureges";
	}
	
	public boolean getNap() {
		if(napkozel)
			return true;
		else
			return false;
	}

	public List<Szomszed> getSzomszedok(){
		return szomszedok;
	}
	
	public Entitas getEntitasObj(int i) {
		return entitasok.get(i);
	}
	public boolean Kompatibilis(Szomszed sz) {
		return sz instanceof Aszteroida;
	}
	public Szomszed getSzomszedObj(int i) {
		return szomszedok.get(i);

	}
	public List<String> getNevek(){
		List<String> nevek= new ArrayList<String>();
		for(Entitas e : entitasok) {
			if(e.getNev()!=null)
			nevek.add(e.getNev());
		}
	return nevek;
	}
}
