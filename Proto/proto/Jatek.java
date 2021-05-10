package proto;

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
	private int telepesSzamlalo = 0;
	/**
	 * Az aszteroidaöv, amiben a játék zajlik.
	 */
	private Aszteroidaov jatekter;
	
	/**
	 * A játék kezdetekor létrehozza a pályát 20 és 50közötti aszteroidával, beállítja a szomszédságokat és ufokat helyez el egyes aszteroidákon
	 */
	public void Start(int aszam) {
		jatekter = new Aszteroidaov();
		int db = 0;
		Random rand = new Random();
		if(aszam == 0) {
			db = 20+rand.nextInt(30);
		}
		else
			db = aszam;
		for(int i=0; i<db; i++) {							//létrehozza az aszteroidákat és beállítja a magot
			Aszteroida uj= new Aszteroida();
			Random mag= new Random();
			if(mag.nextInt()%5==0) {
				Uran u= new Uran();
				uj.setNyersanyag(u);
			}
			if(mag.nextInt()%4==1) {
				Vizjeg j= new Vizjeg();
				uj.setNyersanyag(j);
			}
			if(mag.nextInt()%4==2) {
				Szen sz= new Szen();
				uj.setNyersanyag(sz);
			}
			if(mag.nextInt()%4==3) {
				Vas v= new Vas();
				uj.setNyersanyag(v);
			}
			if(mag.nextInt()%11==0) {
				uj.setNyersanyag(null);
			}
			int kop= 2+ rand.nextInt(3);
			uj.setKopenyVastagsag(kop);
			
			
			if (rand.nextInt()%4==0) {
				Ufo ufo=new Ufo();
				uj.Befogad(ufo);
				jatekter.addUfo(ufo);
			}
			jatekter.addAszteroida(uj);
		}
		for (int j=0; j<db; j++) {
			for (int k=0; k<db; k++) {
				if(j!=k) {
					if( rand.nextInt()%2==0) {
						jatekter.GetAszteroida(j).addSzomszed(jatekter.GetAszteroida(k));
						jatekter.GetAszteroida(k).addSzomszed(jatekter.GetAszteroida(j));
					}
				}
			}
		}
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
		if(telepesSzamlalo < jatekter.GetTelepesekSize()) {
			//jatekter.getAktual().Lep();
			jatekter.setAktualByIndex(telepesSzamlalo);
			telepesSzamlalo++;
		}
		if(telepesSzamlalo == jatekter.GetTelepesekSize()) {
			telepesSzamlalo = 0;
			jatekter.DoNapkozel();
			
			if(szamlalo>=5){
				jatekter.DoNapvihar();
			}
			int i;
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
		
//		while(!done) {
//			int k = jatekter.GetTelepes(0).getKor();
//			for(int j = 0; j < jatekter.GetTelepesekSize(); j++) {
//				if(j != k)
//					
//			}
//		}
	}
	
	/**
	 * A játék végét intézõ függvény, amely a kapott logikai értéktõl függõen vagy gyõzelemmel, 
	 * vagy vereséggel zárja le a játékmenetet.
	 * @param a a kapott logikai érték
	 */
	public String Vege(boolean a) {
		if (a==true) {
//			System.out.println("Gyõzelem! Gratulálunk, megnyerték a játékot!");
			return "Gyõzelem! Gratulálunk, megnyerték a játékot!";
		}
		else {
//			System.out.println("A játék véget ért, sajnos vesztettek");
			return "A játék véget ért, sajnos vesztettek";
		}
	}
		
	/**
	 * a kapott aszteroidaövet a kapott nevû fájlba szerializálja, ezzel menthetõ egy játékállás
	 * @param ao a kiírandó aszteroidaöv
	 * @param filename a célfájl, ahová szerializáljuk az aszteroidaövet
	 */
	public void ser(Aszteroidaov ao, String filename) {
		final String dir = System.getProperty("user.dir");
    	File dirf = new File(dir);
    	String parentPath = dirf.getParent();
		try {
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
		if(args.length == 0) {
			Start(0);
			String[] args1 = {"0", "0", "-1"};
			Parancsok p1 = new Parancsok();
			p1.Main(args1);
		}
		else {
			if(args[0].equals("0"))
				Start(0);
			else {
				load("map.txt");
			}
			Parancsok p = new Parancsok();
			p.Main(args);
		}
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
