package proto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Aszteroidaov implements Serializable{
	/**
	 * szerializ???st seg?t? ID
	 */
	private static final long serialVersionUID = -5429131727800928080L;
	/**
	 * A robotok list?ja az aszteroida?vben
	 */
	private List<Robot> robotok = new ArrayList<>();
	/**
	 * A telepesek list?ja az aszteroida?vben
	 */
	private List<Telepes> telepesek = new ArrayList<>();
	/**
	 * Az uf?k list?ja az aszteroida?vben
	 */
	private List<Ufo> ufok = new ArrayList<>();
	/**
	 * Az aszteroid?k list?ja az aszteroida?vben
	 */
	private List<Aszteroida> aszteroidak = new ArrayList<>();
	/**
	 * A kapuk list?ja az aszteroida?vben
	 */
	private List<Teleportkapu> kapuk = new ArrayList<>();
	/**
	 * Az adott k?rben l?p? telepes
	 */
	private Telepes aktualTelepes;
	
	
	public List<Aszteroida> getAszteroidak(){
		return aszteroidak;
	}
	
	public List<Teleportkapu> GetKapuk(){
		return kapuk;
	}
	/**
	 * Int?zi az Aszteroida?vben a napvihart, megh?vja az Aszteroid?kon a Napvihar f?ggv?ny?ket.
	 */
	public void DoNapvihar() {
		Random rand = new Random();
		int n= rand.nextInt(aszteroidak.size());
		(aszteroidak.get(n)).StartNapvihar();
		Alert a = new Alert(AlertType.INFORMATION);
		a.setContentText("A napvihar " +n+"db aszteroid?t ?rintett!");
		a.show();
	}
	
	/**
	 * Kit?r?l egy aszteroid?t az aszteroida?vb?l
	 * @param a : a kit?rlend? aszteroida
	 */
	public void MinuszAszteroida(Aszteroida a) {
		aszteroidak.remove(a);
	}
	
	/**
	 * Kisorsolja az aszteroida?vben, hogy mely aszteroid?k vannak napk?zelben
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
	 * Kit?rli a telepest az aszteroida?vb?l
	 * @param t : a kit?rlend? telepes
	 */
	public void MinuszTelepes(Telepes t) {
		telepesek.remove(t);
	}
	
	/**
	 * Kit?rli a robotot az aszteroida?vb?l
	 * @param r : a kit?rlend? robot
	 */
	public void MinuszRobot(Robot r) {
		robotok.remove(r);
	}
	
	/**
	 * Kit?rli az uf?t az aszteroida?vb?l
	 * @param u : a kit?rlend? uf?
	 */
	public void MinuszUfo(Ufo u) {
		ufok.remove(u);
	}
	/**
	 * Hozz?ad egy aszteroid?t az aszteroida?vh?z
	 * @param a : a hozz?adand? aszteroida
	 */
	public void addAszteroida(Aszteroida a) {
		aszteroidak.add(a);
	}
	
	/**
	 * Hozz?ad egy kaput az aszteroida?vh?z
	 * @param a : a hozz?adand? kapu
	 */
	public void addKapu(Teleportkapu tk) {
		kapuk.add(tk);
	}
	
	/**
	 * Hozz?ad egy telepest az aszteroida?vh?z
	 * @param a : a hozz?adand? telepes
	 */
	public void addTelepes(Telepes t) {
		telepesek.add(t);
	}
	
	/**
	 * Hozz?ad egy robotot az aszteroida?vh?z
	 * @param a : a hozz?adand? robot
	 */
	public void addRobot(Robot r) {
		robotok.add(r);
	}
	
	/**
	 * Hozz?ad egy uf?t az aszteroida?vh?z
	 * @param a : a hozz?adand? uf?
	 */
	public void addUfo(Ufo u) {
		ufok.add(u);
	}
	
	/**
	 * Visszaadja a megindexelt telepest az aszteroida?vb?l
	 * @param i : index
	 * @return A k?rt telepes
	 */
	public Telepes GetTelepes(int i) {
		return telepesek.get(i);
	}
	
	/**
	 * Visszaadja a megindexelt aszteroid?t az aszteroida?vb?l
	 * @param i : index
	 * @return A k?rt aszteroida
	 */
	public Aszteroida GetAszteroida(int i) {
		return aszteroidak.get(i);
	}
	
	/**
	 * Visszaadja a megindexelt robotot az aszteroida?vb?l
	 * @param i : index
	 * @return Az k?rt robot
	 */
	public Robot GetRobot(int i) {
		return robotok.get(i);
	}
	
	/**
	 * Visszaadja az azonos ID-vel rendelkez? aszteroid?t az aszteroida?vb?l
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
	 * Visszaadja a megindexelt uf?t az aszteroida?vb?l
	 * @param i : index
	 * @return A k?rt  uf?
	 */
	public Ufo GetUfo(int i) {
		return ufok.get(i);
	}
	
	/**
	 * Visszaadja a megindexelt kaput az aszteroida?vb?l
	 * @param i : index
	 * @return A k?rt kapu
	 */
	public Teleportkapu GetKapu(int i) {
		return kapuk.get(i);
	}
	
	/**
	 * Visszaadja az azonos ID-vel rendelkez? robotot az aszteroida?vb?l
	 * @param ID : ID
	 * @return A keresett robot
	 */
	public Robot GetRobot(String ID) {
		for(Robot r : robotok) {
			if(r.getID().equals(ID))
				return r;
		}
		return null;
	}
	
	/**
	 * Visszaadja az azonos ID-vel rendelkez? uf?t az aszteroida?vb?l
	 * @param ID : ID
	 * @return A keresett uf?
	 */
	public Ufo GetUfo(String ID) {
		for(Ufo u : ufok) {
			if(u.getID().equals(ID))
				return u;
		}
		return null;
	}
	
	/**
	 * Visszaadja az azonos ID-vel rendelkez? telepest az aszteroida?vb?l
	 * @param ID : ID
	 * @return A keresett telepes
	 */
	public Telepes GetTelepesByID(String ID) {
		
		for(Telepes t : telepesek) {
			if(t.getID().equals(ID))
				return t;
		}
		return null;
	}
	
	/**
	 * Visszaadja az azonos ID-vel rendelkez? entit?s az aszteroida?vb?l
	 * @param ID : ID
	 * @return A keresett entit?s
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
	 * Visszaadja a telepesek sz?m?t
	 * @return a telepesek sz?ma
	 */
	public int GetTelepesekSize() {
		return telepesek.size();
	}
	
	/**
	 * Visszaadja a robotok sz?m?t
	 * @return a robotok sz?ma
	 */
	public int GetRobotokSize() {
		return robotok.size();
	}
	
	/**
	 * Visszaadja az uf?k sz?m?t
	 * @return az uf? sz?ma
	 */
	public int GetUfokSize() {
		return ufok.size();
	}
	
	/**
	 * Visszaadja a kapuk sz?m?t
	 * @return a kapuk sz?ma
	 */
	public int GetKapukSize() {
		return kapuk.size();
	}
	
	/**
	 * Visszaadja a beadott t?pus? Objektumok ID-jait az aszteroida?vben
	 * @param type : A kiirand? objektum t?pusa
	 */
	public void List(String type) {
		String out = "List: \n";
		switch(type) {
		case "Aszteroida":
			for(Aszteroida a : aszteroidak) {
				out += a.getID() + "\n";
			}
			break;
		case "Telepes": 
			for(Telepes t : telepesek) {
				out += t.getID() + "\n";
			}
			break;
		case "Robot": 
			for(Robot r: robotok) {
				out += r.getID() + "\n";
			}
			break;
		case "Ufo": 
			for(Ufo u: ufok) {
				out += u.getID() + "\n";
			}
			break;
		case "Teleportkapu": 
			for(Teleportkapu k: kapuk) {
				out += k.getID() + "\n";
			}
			break;
		}
		System.out.println(out);
	}

	/**
	 * Visszaadja az azonos ID-vel rendelkez? kaput az aszteroida?vb?l
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
	 * Visszaadja az azonos ID-vel rendelkez? objektumot az aszteroida?vb?l
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
	 * Ki?rja a j?t?k aktu?lis ?llapot?t
	 */
	public void Helyzet() {
		
		System.out.println("Kor szama: "+ Main.game.getKor());
		System.out.println("Telepesek: "+ telepesek.size());
		System.out.println("Robotok: "+ robotok.size());
		System.out.println("Ufok: "+ ufok.size());
		System.out.println("Kapuk: "+ kapuk.size());
		System.out.println("Aszteroidak: "+ aszteroidak.size());
	}

	/**
	 * Visszaadja egy aszteroida belsej?ben l?v? nyersanyagot ID szerint
	 * @param ID : a keresett nyersanyag ID-ja
	 * @return Az aszteroida ID-ja amiben a nyersanyag van
	 */
	public String GetNyersanyagByID(String ID) {
		for(int i = 0; i < aszteroidak.size(); i++) {
			if(aszteroidak.get(i).getBelsoID().equals(ID)){
				return aszteroidak.get(i).getID();
			}
		}
		return null;
	}

	/**
	 * Visszaad egy zomsz?dot ID szerint
	 * @param ID : a keresett szomsz?d ID-ja
	 * @return a szomsz?d maga
	 */
	public Szomszed GetSzomszed(String ID) {
		for(Aszteroida a : aszteroidak) {
			if(a.getID().equals(ID))
				return a;
		}		
		for(Teleportkapu k : kapuk) {
			if(k.getID().equals(ID))
				return k;
		}
		return null;
	}
	
	/**
	 * Visszaadja az aktu?lis soron l?v? Telepest.
	 * @return az aktu?lisan soron l?v? Telepes
	 */
	public Telepes getAktual() {
		return aktualTelepes;
	}
	
	public void setAktual(Telepes t) {
		aktualTelepes = t;
	}
	public void setAktualByIndex(int i) {
		aktualTelepes = telepesek.get(i);
	}
	
	/**
	 * Tov?bbl?pteti az aktu?lis Telepes miut?n v?gzett a soron k?vetkez? Telepesre.
	 */
	public void aktualKesz() {
		for(int i = 0; i+1 < telepesek.size(); i++) {
			if(telepesek.get(i).equals(aktualTelepes)) {
				aktualTelepes = telepesek.get(i + 1);
				break;
			}
		}
	}
}
