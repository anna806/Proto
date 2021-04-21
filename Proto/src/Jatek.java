
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
	 * A k�r�k sz�m�t nyilv�ntart� sz�ml�l�
	 */
	private int szamlalo=0;									
	/**
	 * Az aszteroida�v, amiben a j�t�k zajlik.
	 */
	private Aszteroidaov jatekter;
	
	/**
	 * A j�t�k kezdetekor l�trehozza a p�ly�t 20 �s 50k�z�tti aszteroid�val, be�ll�tja a szomsz�ds�gokat �s ufokat helyez el egyes aszteroid�kon
	 */
	public void Start() {
		jatekter = new Aszteroidaov();
		Random rand = new Random();
		int db= 20+rand.nextInt(30);
		for(int i=0; i<db; i++) {							//l�trehozza az aszteroid�kat �s be�ll�tja a magot
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
			
			
			if (rand.nextInt()%4==0) {
				Ufo ufo=new Ufo();
				uj.Befogad(ufo);
				jatekter.addUfo(ufo);
			}
			jatekter.addAszteroida(uj);
		}	
		/*Telepes t = new Telepes();
		t.AddNyersanyag(new Uran());
	    jatekter.addTelepes(t);
	    jatekter.GetAszteroida(db - 1).Befogad(t);
	    //t.kiir();	    
	    Robot r = new Robot();
	    jatekter.addRobot(r);
	    jatekter.GetAszteroida(db - 2).Befogad(r);
	    r.kiir();
	    for(int i = 0; i < jatekter.GetRobotokSize(); i++)
			jatekter.GetRobot(i).kiir();
	    Ufo u = new Ufo();
	    jatekter.addUfo(u);
	    jatekter.GetAszteroida(db - 3).Befogad(u);
	    r.kiir();
	    u.kiir();*/
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
	 * Minden k�r elej�n sorsol�dik, hogy az adott k�rben mely aszteroid�k vannak napk�zelben, 
	 * valamint az 5. k�rt�l kezdve napvihar is l�trej�het egy v�letlenszer�en kiv�lasztott aszteroida k�rnyezet�ben
	 * A j�t�k sor�n k�r�k futnak le, amelyekben a j�t�k minden r�sztvev�je sorraker�l.
	 * el�sz�r a telepesek l�phetnek - a  j�t�kosok ir�ny�t�s�val
	 * ezut�n a robotok, az ufok, �s a megkerg�lt teleportkapuk
	 * a sz�ml�l� k�r�nk�nt eggyel n�
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
	 * A j�t�k v�g�t int�z� f�ggv�ny, amely a kapott logikai �rt�kt�l f�gg�en vagy gy�zelemmel, 
	 * vagy veres�ggel z�rja le a j�t�kmenetet.
	 * @param a a kapott logikai �rt�k
	 */
	public void Vege(boolean a) {
		if (a==true) {
			System.out.println("Gy�zelem! Gratul�lunk, megnyert�k a j�t�kot!");
			
		}
		else {
			System.out.println("A j�t�k v�get �rt, sajnos vesztettek");
		}
	}
		
	/**
	 * a kapott aszteroida�vet a kapott nev� f�jlba szerializ�lja, ezzel menthet� egy j�t�k�ll�s
	 * @param ao a ki�rand� aszteroida�v
	 * @param filename a c�lf�jl, ahov� szerializ�ljuk az aszteroida�vet
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
	 * a param�terk�nt kapott f�jlb�l bet�lthet�nk egy mentett j�t�k�ll�st
	 * @param filename: a f�jl neve, ahonnan be szeretn�nk t�lteni a j�t�k�ll�st
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
	 * @return jatekter: visszaadja az aszteroida�vet, amelyben a j�t�k zajlik.
	 */
	public Aszteroidaov GetOv() {
		return jatekter;
	}

	/**
	 * Ha tesztel�s miatt ind�tott�k el a programot (args[0] == 1), akkor bet�lti a tesztel�shez sz�ks�ges p�lyaf�jlt, �s 
	 * �tadja a param�tereket a parancsok oszt�ly main f�ggv�ny�nek, ami feldolgozza azokat.
	 * Ha j�t�k c�lj�ra ind�tott�k el a programot (args[0] == 0), akkor a Start() f�ggv�ny seg�ts�g�vel k�sz�ti el a p�ly�t.
	 * @param args a param�terek, amit feldolgoz a Parancsok oszt�ly main f�ggv�nye
	 */
	public void field(String[] args) {
		if(args.length == 0) {
			Start();
			String[] args1 = {"0", "0", "-1"};
			Parancsok p1 = new Parancsok();
			p1.Main(args1);
		}
		else {
			if(args[0].equals("0"))
				Start();
			else {
				load("map.txt");
			}
			Parancsok p = new Parancsok();
			p.Main(args);
		}
	}
	
	/**
	 * @param ov: a param�terk�nt kapott aszteroida�vet be�ll�tja a j�t�k jatekter v�ltoz�jak�nt
	 */
	public void setOv(Aszteroidaov ov) {
		jatekter = ov;
	}
	
	/**
	 * lek�rdezi �s visszaadja azt az utat, ahov� a f�jlt ki kell �rni
	 * @param filename : a kapott f�jl neve
	 * @return az elk�sz�ttett path
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
	 * Visszaadja az aktu�lis k�r sz�m�t
	 */
	public int getKor() {
		return szamlalo;
	}
}
