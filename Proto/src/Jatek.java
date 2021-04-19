package src;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Jatek {
	/**
	 * A körök számát nyilvántartó számláló
	 */
	private int szamlalo=0;									
	/**
	 * Az aszteroidaöv, amiben a játék zajlik.
	 */
	private Aszteroidaov jatekter;
	
	/**
	 * A játék kezdetekor létrehozza a pályát 20 és 50közötti aszteroidával, beállítja a szomszédságokat és ufokat helyez el egyes aszteroidákon
	 */
	public void Start() {
		jatekter = new Aszteroidaov();
		Random rand = new Random();
		int db= 20+rand.nextInt(30);
		for(int i=0; i<db; i++) {							//létrehozza az aszteroidákat és beállítja a magot
			Aszteroida uj= new Aszteroida();
			Random mag= new Random();
			if(mag.nextInt()%5==0) {
				Uran u= new Uran();
				uj.setNyersanyag(u);
			}
			if(mag.nextInt()%5==1) {
				Vizjeg j= new Vizjeg();
				uj.setNyersanyag(j);
			}
			if(mag.nextInt()%5==2) {
				Szen sz= new Szen();
				uj.setNyersanyag(sz);
			}
			if(mag.nextInt()%3==0) {
				Vas v= new Vas();
				uj.setNyersanyag(v);
			}
			if(mag.nextInt()%4==0) {
				uj.setNyersanyag(null);
			}
			int kop= 2+ rand.nextInt(3);
			uj.setKopenyVastagsag(kop);
			
			
			if (rand.nextInt()%4==0) {						//0.25 valószínûséggel tesz az aszteroidára ufot
				Ufo ufo=new Ufo();
				uj.Befogad(ufo);
				jatekter.addUfo(ufo);
			}
			jatekter.addAszteroida(uj);						//hozzáadja az aszteroidaövhöz
		}	
		Telepes t = new Telepes();
		t.AddNyersanyag(new Uran());
	    jatekter.addTelepes(t);
	    jatekter.GetAszteroida(db - 1).Befogad(t);
	    //t.kiir();	    
	    Robot r = new Robot();
	    jatekter.addRobot(r);
	    jatekter.GetAszteroida(db - 2).Befogad(r);
	    r.kiir();
	    System.out.println("Elso");
	    for(int i = 0; i < jatekter.GetRobotokSize(); i++)
			jatekter.GetRobot(i).kiir();
	    Ufo u = new Ufo();
	    jatekter.addUfo(u);
	    jatekter.GetAszteroida(db - 3).Befogad(u);
	    r.kiir();
	    u.kiir();
		for (int j=0; j<db; j++) {							//beállítja a szomszédságokat 0.5 valószínûséggel
			for (int k=0; k<db; k++) {
				if(j!=k) {
					if( rand.nextInt()%2==0) {
						jatekter.GetAszteroida(j).addSzomszed(jatekter.GetAszteroida(k));
						jatekter.GetAszteroida(k).addSzomszed(jatekter.GetAszteroida(j));
					}
				}
			}
		}
		//.GetAszteroida(db - 3).kiir();
		System.out.println("Masodik");
		for(int i = 0; i < jatekter.GetRobotokSize(); i++)
			jatekter.GetRobot(i).kiir();
//		while(true) {
//			Kor();
//		}
		
	}
	
	/**
	 * Minden kör elején sorsolódik, hogy az adott körben mely aszteroidák vannak napközelben, 
	 * valamint az 5. körtõl kezdve napvihar is létrejöhet egy véletlenszerûen kiválasztott aszteroida környezetében
	 * A játék során körök futnak le, amelyekben a játék minden résztvevõje sorrakerül.
	 * elõször a telepesek léphetnek - a  játékosok irányításával
	 * ezután a robotok, az ufok, és a megkergült teleportkapuk
	 * a számláló körönként eggyel nõ
	 */
	public void Kor() {
		
	 
	jatekter.DoNapkozel();
	
	if(szamlalo>=5){
	jatekter.DoNapvihar();
	}
		
	int i =0;
	 while(i < jatekter.GetTelepesekSize()) {
		 jatekter.GetTelepes(i).Lep();
		 i++;
	 }
	 i=0;
	 while (i < jatekter.GetRobotokSize()) {
		 jatekter.GetRobot(i).Lep();
		 i++;
	 }
	 i=0;
	 while (i < jatekter.GetUfokSize()) {
		 jatekter.GetUfo(i).Lep();
		 i++;
	 }
	 i=0;
	 while (i < jatekter.GetKapukSize()){
		 jatekter.GetKapu(i).RandomMozgas();
	 }
	 szamlalo+=1;
	}
	
	/**
	 * A játék végét intézõ függvény, amely a kapott logikai értéktõl függõen vagy gyõzelemmel, 
	 * vagy vereséggel zárja le a játékmenetet.
	 * @param a a kapott logikai érték
	 */
	public void Vege(boolean a) {
		if (a==true) {
			System.out.println("Gyõzelem! Gratulálunk, megnyerték a játékot!");
			
		}
		else {
			System.out.println("A játék véget ért, sajnos vesztettek");
		}
		//System.exit(0);
	}
	
	
	/*public Aszteroidaov init() {
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
		Teleportkapu k0 = new Teleportkapu();
		k0.setAszter(a5);
		Teleportkapu k1 = new Teleportkapu();
		Teleportkapu k2 = new Teleportkapu();
		k1.setParja(k2);
		k2.setParja(k1);
		k1.setAszter(a3);
		k2.setAszter(a0);
		List<Szomszed> szomszedok = new ArrayList<Szomszed>();
		szomszedok.add(k2);
		szomszedok.add(a2);
		szomszedok.add(a6);
		newAszteroida(a0, new Uran(), true, 0, szomszedok);
		szomszedok.removeAll(szomszedok);
		szomszedok.add(a4);
		newAszteroida(a1, new Uran(), true, 0, szomszedok);
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
	}*/
	
	/*public void newAszteroida(Aszteroida a, Nyersanyag ny, boolean napkozel, int kv, List<Szomszed> szomszedok) {
		a.setNyersanyag(ny);
		a.setKopenyVastagsag(kv);
		a.setNapkozel(napkozel);
		if(szomszedok != null) {
			for(Szomszed sz : szomszedok)
				a.addSzomszed(sz);
		}
	}*/
	
	/**
	 * a kapott aszteroidaövet a kapott nevû fájlba szerializálja, ezzel menthetõ egy játékállás
	 * @param ao a kiírandó aszteroidaöv
	 * @param filename a célfájl, ahová szerializáljuk az aszteroidaövet
	 */
	public void ser(Aszteroidaov ao, String filename) {
		//File tmp = new File(getAddress(filename));
		final String dir = System.getProperty("user.dir");
    	File dirf = new File(dir);
    	String parentPath = dirf.getParent();
    	System.out.print(parentPath);
		try {
			//if(tmp.exists()) {
			if(dirf.exists()) {
				FileOutputStream f = new FileOutputStream(parentPath + "\\" + filename);
				ObjectOutputStream out = new ObjectOutputStream(f);
				out.writeObject(ao);
				out.close();
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * a paraméterként kapott fájlból betölthetünk egy mentett játékállást
	 * @param filename: a fájl neve, ahonnan be szeretnénk tölteni a játékállást
	 */
	public void load(String filename) {
		//File tmp = new File(getAddress(filename));
		final String dir = System.getProperty("user.dir");
    	File dirf = new File(dir);
    	String parentPath = dirf.getParent();
		try {
			if(dirf.exists()) {
				FileInputStream f = new FileInputStream(parentPath + "\\" + filename);
				ObjectInputStream in = new ObjectInputStream(f);
				jatekter = (Aszteroidaov)in.readObject();
				in.close();
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @return jatekter: visszaadja az aszteroidaövet, amelyben a játék zajlik.
	 */
	public Aszteroidaov GetOv() {
		return jatekter;
	}

	/**
	 * Ha tesztelés miatt indították el a programot (args[0] == 1), akkor betölti a teszteléshez szükséges pályafájlt, és 
	 * átadja a paramétereket a parancsok osztály main függvényének, ami feldolgozza azokat.
	 * Ha játék céljára indították el a programot (args[0] == 0), akkor a Start() függvény segítségével készíti el a pályát.
	 * @param args a paraméterek, amit feldolgoz a Parancsok osztály main függvénye
	 */
	public void field(String[] args) {
		if(args[0].equals("0"))
			Start();
		else 
			load("map.txt");
		Parancsok p = new Parancsok();
		p.Main(args);
	}
	
	/**
	 * @param ov: a paraméterként kapott aszteroidaövet beállítja a játék jatekter változójaként
	 */
	public void setOv(Aszteroidaov ov) {
		jatekter = ov;
	}
	
	/**
	 * lekérdezi és visszaadja azt az utat, ahová a fájlt ki kell írni
	 * @param filename : a kapott fájl neve
	 * @return az elkészíttett path
	 */
	public static String getAddress(String filename) {
		String addr1 = System.getProperty("user.dir");
		String[] address = addr1.split("\\\\");
		String addr2 = "";
		for(int i = 0; i < address.length - 1; i++) {
			if(i == 0)
				addr2 = address[i];
			else
				addr2 = addr2 + File.separator + address[i];
		}
		addr2 = addr2 + File.separator + filename;
		return addr2;
	}
	
	/**
	 * Visszaadja az aktuális kör számát
	 */
	public int getKor() {
		return szamlalo;
	}
}
