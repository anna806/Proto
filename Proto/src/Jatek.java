import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Jatek {
	private Aszteroidaov jatekter;
	public void Start() {
	}
	
	public void Kor() {
	}
	
	public void Vege(boolean a) {
	}
	
	public Aszteroidaov init() {
		Aszteroidaov ov = new Aszteroidaov();
		Aszteroida a0 = new Aszteroida();
		Aszteroida a1 = new Aszteroida();
		Aszteroida a2 = new Aszteroida();
		Aszteroida a3 = new Aszteroida();
		Aszteroida a4 = new Aszteroida();
		Aszteroida a5 = new Aszteroida();
		Aszteroida a6 = new Aszteroida();
		Aszteroida a7 = new Aszteroida();
		Aszteroida a8 = new Aszteroida();
		Aszteroida a9 = new Aszteroida();
		Teleportkapu k3 = new Teleportkapu();
		k3.setAszter(a0);
		Teleportkapu k1 = new Teleportkapu();
		Teleportkapu k2 = new Teleportkapu();
		k1.setParja(k1);
		k2.setParja(k2);
		k1.setAszter(a5);
		k2.setAszter(a3);
		List<Szomszed> szomszedok = new ArrayList<Szomszed>();
		szomszedok.add(k3);
		szomszedok.add(a2);
		szomszedok.add(a6);
		newAszteroida(a0, new Uran(), true, 0, szomszedok);
		szomszedok.removeAll(szomszedok);
		szomszedok.add(a4);
		newAszteroida(a4, new Uran(), true, 0, szomszedok);
		szomszedok.removeAll(szomszedok);
		szomszedok.add(a5);
		szomszedok.add(a0);
		szomszedok.add(a9);
		newAszteroida(a2, new Vizjeg(), true, 1, szomszedok);
		szomszedok.removeAll(szomszedok);
		szomszedok.add(k2);
		szomszedok.add(a6);
		szomszedok.add(a4);
		newAszteroida(a3, null, true, 0, szomszedok);
		szomszedok.removeAll(szomszedok);
		szomszedok.add(a3);
		szomszedok.add(a1);
		newAszteroida(a4, null, false, 0, szomszedok);
		szomszedok.removeAll(szomszedok);
		szomszedok.add(a2);
		szomszedok.add(a8);
		szomszedok.add(k1);
		newAszteroida(a5, new Szen(), false, 3, szomszedok);
		szomszedok.removeAll(szomszedok);
		szomszedok.add(a3);
		szomszedok.add(a0);
		szomszedok.add(a9);
		szomszedok.add(a8);
		newAszteroida(a6, new Vas(), true, 0, szomszedok);
		szomszedok.removeAll(szomszedok);
		newAszteroida(a7, new Szen(), true, 3, null);
		szomszedok.removeAll(szomszedok);
		szomszedok.add(a5);
		szomszedok.add(a6);
		newAszteroida(a8, new Vas(), true, 3, szomszedok);
		szomszedok.removeAll(szomszedok);
		szomszedok.add(a2);
		szomszedok.add(a6);
		newAszteroida(a9, new Uran(), true, 1, szomszedok);
		ov.addAszteroida(a0);
		ov.addAszteroida(a1);
		ov.addAszteroida(a2);
		ov.addAszteroida(a3);
		ov.addAszteroida(a4);
		ov.addAszteroida(a5);
		ov.addAszteroida(a6);
		ov.addAszteroida(a7);
		ov.addAszteroida(a8);
		ov.addAszteroida(a9);
		ov.addKapu(k1);
		ov.addKapu(k2);
		ov.addKapu(k3);
		return ov;
	}
	
	public void newAszteroida(Aszteroida a, Nyersanyag ny, boolean napkozel, int kv, List<Szomszed> szomszedok) {
		a.setNyersanyag(ny);
		a.setKopenyVastagsag(kv);
		a.setNapkozel(napkozel);
		for(Szomszed sz : szomszedok)
			a.addSzomszed(sz);
	}
	
	public void ser(Aszteroidaov ao, String filename) {
		File tmp = new File(System.getProperty("user.dir") + File.separator + filename);
		try {
			if(tmp.exists()) {
				FileOutputStream f = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(f);
				out.writeObject(ao);
				out.close();
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void load(String filename) {
		File tmp = new File(System.getProperty("user.dir") + File.separator + filename);
		try {
			if(tmp.exists()) {
				FileInputStream f = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(f);
				jatekter = (Aszteroidaov)in.readObject();
				in.close();
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public Aszteroidaov GetOv() {
		return jatekter;
	}

	public void field(String[] args) {
		Aszteroidaov ov = init();
		ser(ov, "map.txt");
		load("map.txt");
		Parancsok.main(args);
	}
	
	public void setOv(Aszteroidaov ov) {
		jatekter = ov;
	}
}
