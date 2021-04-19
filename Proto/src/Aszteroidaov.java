import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aszteroidaov implements Serializable{
	/**
	 * szerializáéást segítõ ID
	 */
	private static final long serialVersionUID = -5429131727800928080L;
	/**
	 * A robotok listája az aszteroidaövben
	 */
	private List<Robot> robotok = new ArrayList<>();
	/**
	 * A telepesek listája az aszteroidaövben
	 */
	private List<Telepes> telepesek = new ArrayList<>();
	/**
	 * Az ufók listája az aszteroidaövben
	 */
	private List<Ufo> ufok = new ArrayList<>();
	/**
	 * Az aszteroidák listája az aszteroidaövben
	 */
	private List<Aszteroida> aszteroidak = new ArrayList<>();
	/**
	 * A kapuk listája az aszteroidaövben
	 */
	private List<Teleportkapu> kapuk = new ArrayList<>();
	
	/**
	 * Intézi az Aszteroidaövben a napvihart, meghívja az Aszteroidákon a Napvihar függvényüket.
	 */
	public void DoNapvihar() {
		Random rand = new Random();
		int n= rand.nextInt(aszteroidak.size());
		(aszteroidak.get(n)).StartNapvihar();
	}
	
	/**
	 * Kitöröl egy aszteroidát az aszteroidaövbõl
	 * @param a : a kitörlendõ aszteroida
	 */
	public void MinuszAszteroida(Aszteroida a) {
		aszteroidak.remove(a);
	}
	
	/**
	 * Kisorsolja az aszteroidaövben, hogy mely aszteroidák vannak napközelben
	 */
	public void DoNapkozel() {
		for (Aszteroida x : aszteroidak)
		{
			Random rand= new Random();
			if(rand.nextInt()%2==0) x.setNapkozel(true);
			else x.setNapkozel(false);
		}
	}
	
	/**
	 * Kitörli a telepest az aszteroidaövbõl
	 * @param t : a kitörlendõ telepes
	 */
	public void MinuszTelepes(Telepes t) {
		telepesek.remove(t);
	}
	
	/**
	 * Kitörli a robotot az aszteroidaövbõl
	 * @param r : a kitörlendõ robot
	 */
	public void MinuszRobot(Robot r) {
		robotok.remove(r);
	}
	
	/**
	 * Kitörli az ufót az aszteroidaövbõl
	 * @param u : a kitörlendõ ufó
	 */
	public void MinuszUfo(Ufo u) {
		ufok.remove(u);
	}
	/**
	 * Hozzáad egy aszteroidát az aszteroidaövhöz
	 * @param a : a hozzáadandó aszteroida
	 */
	public void addAszteroida(Aszteroida a) {
		aszteroidak.add(a);
	}
	
	/**
	 * Hozzáad egy kaput az aszteroidaövhöz
	 * @param a : a hozzáadandó kapu
	 */
	public void addKapu(Teleportkapu tk) {
		kapuk.add(tk);
	}
	
	/**
	 * Hozzáad egy telepest az aszteroidaövhöz
	 * @param a : a hozzáadandó telepes
	 */
	public void addTelepes(Telepes t) {
		telepesek.add(t);
	}
	
	/**
	 * Hozzáad egy robotot az aszteroidaövhöz
	 * @param a : a hozzáadandó robot
	 */
	public void addRobot(Robot r) {
		robotok.add(r);
	}
	
	/**
	 * Hozzáad egy ufót az aszteroidaövhöz
	 * @param a : a hozzáadandó ufó
	 */
	public void addUfo(Ufo u) {
		ufok.add(u);
	}
	
	/**
	 * Visszaadja a megindexelt telepest az aszteroidaövbõl
	 * @param i : index
	 * @return A kért telepes
	 */
	public Telepes GetTelepes(int i) {
		return telepesek.get(i);
	}
	
	/**
	 * Visszaadja a megindexelt aszteroidát az aszteroidaövbõl
	 * @param i : index
	 * @return A kért aszteroida
	 */
	public Aszteroida GetAszteroida(int i) {
		return aszteroidak.get(i);
	}
	
	/**
	 * Visszaadja a megindexelt robotot az aszteroidaövbõl
	 * @param i : index
	 * @return Az kért robot
	 */
	public Robot GetRobot(int i) {
		return robotok.get(i);
	}
	
	/**
	 * Visszaadja az azonos ID-vel rendelkezõ aszteroidát az aszteroidaövbõl
	 * @param ID : ID
	 * @return A keresett aszteroida
	 */
	public Aszteroida GetAszteroida(String ID) {
		for(Aszteroida a : aszteroidak) {
			if(a.getID().equals(ID))
				return a;
		}
		return null;
	}
	
	/**
	 * Visszaadja a megindexelt ufót az aszteroidaövbõl
	 * @param i : index
	 * @return A kért  ufó
	 */
	public Ufo GetUfo(int i) {
		return ufok.get(i);
	}
	
	/**
	 * Visszaadja a megindexelt kaput az aszteroidaövbõl
	 * @param i : index
	 * @return A kért kapu
	 */
	public Teleportkapu GetKapu(int i) {
		return kapuk.get(i);
	}
	
	/**
	 * Visszaadja az azonos ID-vel rendelkezõ robotot az aszteroidaövbõl
	 * @param ID : ID
	 * @return A keresett robot
	 */
	public Robot GetRobot(String ID) {
		for(Robot r : robotok) {
			System.out.println(r.getID() + " " + ID);
			if(r.getID().equals(ID))
				return r;
		}
		//System.out.println("Null-nal lepek ki.");
		return null;
	}
	
	/**
	 * Visszaadja az azonos ID-vel rendelkezõ ufót az aszteroidaövbõl
	 * @param ID : ID
	 * @return A keresett ufó
	 */
	public Ufo GetUfo(String ID) {
		for(Ufo u : ufok) {
			if(u.getID().equals(ID))
				return u;
		}
		return null;
	}
	
	/**
	 * Visszaadja az azonos ID-vel rendelkezõ telepest az aszteroidaövbõl
	 * @param ID : ID
	 * @return A keresett telepes
	 */
	public Telepes GetTelepesByID(String ID) {
		
		for(Telepes t : telepesek) {
			if(t.getID().equals(ID))
				//System.out.println("telepesid");
				return t;
		}
		return null;
	}
	
	/**
	 * Visszaadja az azonos ID-vel rendelkezõ entitás az aszteroidaövbõl
	 * @param ID : ID
	 * @return A keresett entitás
	 */
	public Entitas GetEntitas(String ID) {
		for(Robot r : robotok) {
			if(r.getID().equals(ID))
				return r;
		}
		for(Telepes t : telepesek) {
			if(t.getID().equals(ID))
				return t;
		}
		for(Ufo u : ufok) {
			if(u.getID().equals(ID))
				return u;
		}
		return null;
	}
	
	/**
	 * Visszaadja a telepesek számát
	 * @return a telepesek száma
	 */
	public int GetTelepesekSize() {
		return telepesek.size();
	}
	
	public int GetRobotokSize() {
		return robotok.size();
	}
	
	public int GetUfokSize() {
		return ufok.size();
	}
	
	public int GetKapukSize() {
		return kapuk.size();
	}
	
	/**
	 * Visszaadja a beadott típusú Objektumok ID-jait az aszteroidaövben
	 * @param type : A kiirandó objektum típusa
	 * @return Azobjektumok ID-jának listája
	 */
	public String List(String type) {
		String out = "List: ";
		switch(type) {
		case "Aszteroida":
			for(Aszteroida a : aszteroidak) {
				out += " " + a.getID();
			}
			break;
		case "Telepes": 
			for(Telepes t : telepesek) {
				out += " " + t.getID();
			}
			break;
		case "Robot": 
			for(Robot r: robotok) {
				out += " " + r.getID();
			}
			break;
		case "Ufo": 
			for(Ufo u: ufok) {
				out += " " + u.getID();
			}
			break;
		case "Teleportkapu": 
			for(Teleportkapu k: kapuk) {
				out += " " + k.getID();
			}
			break;
		}
		return out;
	}

	/**
	 * Visszaadja az azonos ID-vel rendelkezõ kaput az aszteroidaövbõl
	 * @param ID : ID
	 * @return Az keresett kapu
	 */
	public Teleportkapu GetKapuByID(String ID) {
		for(Teleportkapu k : kapuk) {
			if(k.getID().equals(ID))
				return k;
		}
		return null;
	}
	
	/**
	 * Visszaadja az azonos ID-vel rendelkezõ objektumot az aszteroidaövbõl
	 * @param id : ID
	 */
	public void Kiir(String id) {
		if(GetTelepesByID(id) != null)
			GetTelepesByID(id).kiir();
		if(GetRobot(id) != null)
			GetRobot(id).kiir();
		if(GetUfo(id) != null)
			GetUfo(id).kiir();
		if(GetAszteroida(id) != null)
			GetAszteroida(id).kiir();
		if(GetKapuByID(id) != null)
			GetKapuByID(id).kiir();
	}
	
	/**
	 * Kiírja a játék aktuális állapotát
	 */
	public void Helyzet() {
		
		System.out.println("Kor szama: "+ Main.game.getKor());
		System.out.println("Telepesek: "+ telepesek.size());
		System.out.println("Robotok: "+ robotok.size());
		System.out.println("Ufok: "+ ufok.size());
		System.out.println("Kapuk: "+ kapuk.size());
		System.out.println("Aszteroidak: "+ aszteroidak.size());
	}
}
