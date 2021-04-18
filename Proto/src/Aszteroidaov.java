import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aszteroidaov implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5429131727800928080L;
	private List<Robot> robotok = new ArrayList<>();
	private List<Telepes> telepesek = new ArrayList<>();
	private List<Ufo> ufok = new ArrayList<>();
	private List<Aszteroida> aszteroidak = new ArrayList<>();
	private List<Teleportkapu> kapuk = new ArrayList<>();
	
	
	
	
	public void DoNapvihar() {
		Random rand = new Random();
		int n= rand.nextInt(aszteroidak.size());
		(aszteroidak.get(n)).StartNapvihar();
	}
	
	public void MinuszAszteroida(Aszteroida a) {
		aszteroidak.remove(a);
		
	}
	
	public void DoNapkozel() {
		for (Aszteroida x : aszteroidak)
		{
			Random rand= new Random();
			if(rand.nextInt()%2==0) x.setNapkozel(true);
			else x.setNapkozel(false);
		}
	}
	
	public void MinuszTelepes(Telepes t) {
		telepesek.remove(t);
	}
	
	public void MinuszRobot(Robot r) {
		robotok.remove(r);
	}
	
	public void MinuszUfo(Ufo u) {
		ufok.remove(u);
	}
	
	public void addAszteroida(Aszteroida a) {
		aszteroidak.add(a);
	}
	
	public void addKapu(Teleportkapu tk) {
		kapuk.add(tk);
	}
	
	public void addTelepes(Telepes t) {
		telepesek.add(t);
	}
	
	public void addRobot(Robot r) {
		robotok.add(r);
	}
	
	public void addUfo(Ufo u) {
		ufok.add(u);
	}
	
	public Telepes GetTelepes(int i) {
		return telepesek.get(i);
	}
	public Aszteroida GetAszteroida(int i) {
		return aszteroidak.get(i);
	}

	public Robot GetRobot(int i) {
		return robotok.get(i);
	}
	public Aszteroida GetAszteroida(String ID) {
		for(Aszteroida a : aszteroidak) {
			if(a.getID().equals(ID))
				return a;
		}
		return null;
	}
	public Ufo GetUfo(int i) {
		return ufok.get(i);
	}
	public Teleportkapu GetKapu(int i) {
		return kapuk.get(i);
	}
	
	public Robot GetRobot(String ID) {
		for(Robot r : robotok) {
			if(r.getID().equals(ID))
				return r;
		}
		return null;
	}
	
	public Ufo GetUfo(String ID) {
		for(Ufo u : ufok) {
			if(u.getID().equals(ID))
				return u;
		}
		return null;
	}
	
	public Telepes GetTelepesByID(String ID) {
		for(Telepes t : telepesek) {
			if(t.getID().equals(ID))
				return t;
		}
		return null;
	}
	
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
	
	public int GetTelepesekSize() {
		return telepesek.size();
	}
	
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

	public Teleportkapu GetKapuByID(String ID) {
		for(Teleportkapu k : kapuk) {
			if(k.getID().equals(ID))
				return k;
		}
		return null;
	}
}
