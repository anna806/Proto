package proto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.simple.JSONObject;

public class Parancsok {
	//args = be, ki, parancs
	//be = 0 konzol
	//be = 1 parancssz�m
	//ki = 0 konzolra
	//ki = 1 outputf�jlba
	/**
	 * megadja, hogy fejeleszt�i m�dban tesztel�nk-e
	 */
	private static boolean fejlesztoi;
	public void Main(String[] args) {
		try {
			String line = "";
			int arg = Integer.parseInt(args[2]);
			
			Files.deleteIfExists(Paths.get("out"+arg+".json"));
			
			BufferedReader file = null;
			final String dir = System.getProperty("user.dir");
	    	File dirf = new File(dir);
	    	String parentPath = dirf.getParent();
			if(arg > 0)
			file = new BufferedReader(new FileReader(parentPath + "\\input\\" + args[2]+".txt"));
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in)); 
			if(args[0].equals("0")) {
				line = console.readLine();
			} else if(args[0].equals("1")) {
				line = file.readLine();
			}
			
			while(line != null && line.length() != 0) {
				String parancsszam = args[2];
				String ki = args[1];
				ParancsErtelmezo(line, parancsszam, ki);
				if(args[0].equals("0")) {
					line = console.readLine();
				} else if(args[0].equals("1")) {
					line = file.readLine();
				}
			}
			file.close();
			console.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	/**
	 * �rtelmezi a megkapott parancsot �s v�grehajtja a megfelel� parancsot
	 * @param p : a parancs
	 * @param ps : A teszteset sz�ma
	 * @param ki : f�jlba, vagy konzolra kell �rnia a v�geredm�nyt?
	 */
	static void ParancsErtelmezo(String p, String ps, String ki) {
		String[] com = p.split(" ");
		String command = com[0];
		switch(command){
		case "telepes_mozog":
			try {
				if(com.length != 3)
					throw new Exception("Tul keves argumentum");
				Main.game.GetOv().GetTelepesByID(com[1]).Mozgas(Main.game.GetOv().GetSzomszed(com[2]));
			} catch(Exception e) {
				Main.game.GetOv().GetTelepesByID(com[1]).Mozgas(null);				
			}
			break;
		case "robot_mozog": 
			Main.game.GetOv().GetRobot(com[1]).RandomMozgas(); 
			break;
		case "ufo_mozog": 
			Main.game.GetOv().GetUfo(com[1]).RandomMozgas();
			break;
		case "telepes_fur": 
			Main.game.GetOv().GetTelepesByID(com[1]).Furas();
			break;
		case "robot_fur": 
			Main.game.GetOv().GetRobot(com[1]).Furas();
			break;
		case "nyersanyag_kinyeres": 
			Main.game.GetOv().GetEntitas(com[1]).Banyaszat();
			break;
		case "napvihar": 
			Main.game.GetOv().GetAszteroida(com[1]).StartNapvihar();
			break;
		case "napkozel": 
			Main.game.GetOv().GetAszteroida(com[1]).setNapkozel(true);
			Main.game.GetOv().GetAszteroida(com[1]).getBelsoAnyag().Napkozel(Main.game.GetOv().GetAszteroida(com[1]));
			break;
		case "teleportkapu_epites": 
			Main.game.GetOv().GetTelepesByID(com[1]).KapuEpit();
			break;
		case "robot_epites": 
			Main.game.GetOv().GetTelepesByID(com[1]).RobotEpit();
			break;
		case "bazis_epites": 
			Main.game.GetOv().GetTelepesByID(com[1]).getAszteroida().BazisEpit();
			break;
		case "teleportkapu_elhelyezes": 
			Main.game.GetOv().GetTelepesByID(com[1]).KapuLerak();
			break;
		case "visszatoltes": 
			Main.game.GetOv().GetTelepesByID(com[1]).Visszatolt();
			break;
		case "plusz_telepes": 
			
			if(fejlesztoi) {
				
			Telepes t = new Telepes();
			Main.game.GetOv().GetAszteroida(com[1]).Befogad(t);
			t.SetID(com[2]);
			Main.game.GetOv().addTelepes(t);
			
			}
			break;
		case "plusz_robot": 
			if(fejlesztoi) {
			Robot r = new Robot();
			r.SetAszteroida(Main.game.GetOv().GetAszteroida(com[1]));
			r.SetID(com[2]);
			Main.game.GetOv().GetAszteroida(com[1]).Befogad(r);
			Main.game.GetOv().addRobot(r);
			}
			break;
		case "plusz_ufo": 
			if(fejlesztoi) {
			Ufo u = new Ufo();
			u.SetAszteroida(Main.game.GetOv().GetAszteroida(com[1]));
			u.SetID(com[2]);
			Main.game.GetOv().GetAszteroida(com[1]).Befogad(u);
			Main.game.GetOv().addUfo(u);
			}
			break;
		case "plusz_nyersanyag": 
			if(fejlesztoi) {
			for(int i = 2; i < com.length; i++) {
				switch(com[i].charAt(0)) {
				case 'v':
					if(com[i].charAt(1)=='j') {
						Main.game.GetOv().GetTelepesByID(com[1]).AddNyersanyag(new Vizjeg());
					} else {
						Main.game.GetOv().GetTelepesByID(com[1]).AddNyersanyag(new Vas());
					}
					break;
				case 'u': 
					Uran u = new Uran();
					u.setID("u05");
					Main.game.GetOv().GetTelepesByID(com[1]).AddNyersanyag(u);
					break;
				case 's': 
					Main.game.GetOv().GetTelepesByID(com[1]).AddNyersanyag(new Szen());
					break;
				}
			}	
			}
			break;
		case "plusz_teleportkapu": 
			if(fejlesztoi) {
			Teleportkapu tk = new Teleportkapu();
			Teleportkapu tkpar = new Teleportkapu();
			tkpar.setAszter(Main.game.GetOv().GetAszteroida("a07"));
			tk.setParja(tkpar);
			Main.game.GetOv().GetTelepesByID(com[1]).AddKapu(tk);
			}
			break;
		case "expozicio": 
			if(fejlesztoi) {
				for(int i = 0; i < Main.game.GetOv().GetTelepesekSize(); i++) {
					if(Main.game.GetOv().GetTelepes(i).getNyersanyag(com[1]) != null) {
						Main.game.GetOv().GetTelepes(i).getNyersanyag(com[1]).setExp(Integer.parseInt(com[2])); 
						break;
					}
				}
			}
			break;
		case "informaciok": 
			if(fejlesztoi) {
			Main.game.GetOv().Kiir(com[1]);
			}
			break;
		case "informaciok_jatek": 
			if(fejlesztoi) {
			Main.game.GetOv().Helyzet();
			}
			break;
		case "list": 
			if(fejlesztoi) {
				Main.game.GetOv().List(com[1]);
			}
			break;
		case "palya_betoltes": 
			Main.game.load("map.txt");
			break;
		case "veletlen": break;
		case "fejlesztoi_mod": 
			if(com[1].equals("true")){
				fejlesztoi = true;
			}
			break;
		case "betolt": 
			Main.game.load("jatek.txt");
			break;
		case "mentes": 
			Main.game.ser(Main.game.GetOv(), "jatek.txt");
			break;
		case "kiir": 
			String out1 = "";
			for(int i = 1; i < com.length; i++)
				out1 += com[i] + " ";
			String[] out2 = out1.split(" ");
			try{
				Output(ps, ki, out2);
			}
			catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
			break;
			
		}
	}
	
	/**
	 * Kiiratja a kimenetre a megfelel� objektumokat
	 * @param p : a teszteset neve
	 * @param out : a kimenet f�jl, vagy konzol-e
	 * @param obj : a kiirand� objektumok list�ja
	 * @throws Exception
	 */
	public static void Output(String p, String out, String[] obj) throws Exception {
		JSONObject ki = new JSONObject();
		for(int i = 0; i < obj.length; i++) {
			switch(obj[i].charAt(0)) {
			case 't':
				ki = writeTelepes("out"+p+".json", obj[i], out, ki, i);
				break;
			case 'a':
				ki = writeAszteroida("out"+p+".json", obj[i], out, ki, i);
				break;
			case 'u':
				ki = writeUran("out"+p+".json", obj[i], out, ki, i);
				break;
			case 'k':
				ki = writeKapu("out"+p+".json", obj[i], out, ki, i);
				break;
			}
		}
		if(ki != null) {
			String dir = System.getProperty("user.dir");
	    	File dirf = new File(dir);
	    	String parentPath = dirf.getParent();
	    	FileWriter writer = new FileWriter(parentPath + "\\output\\" + "out" + p + ".json");
	    	writer.write(ki.toJSONString());
	    	writer.flush();
	    	writer.close();
		}
	}
	
	/**
	 * Visszaad egy json objektumot, amibe belerak egy aszteroid�t ID alapj�n
	 * @param filename : a f�jl neve
	 * @param ID : az aszteroida ID-ja
	 * @param out : f�jlba, vagy konzolra �rja
	 * @param json : json objektum, amibe az adatokat rakjuk
	 * @param i : a sorsz�ma
	 * @return a f�jlba �rand� objektum
	 * @throws Exception
	 */
	public static JSONObject writeAszteroida(String filename, String ID, String out, JSONObject json, int i) throws Exception {
		Aszteroida a = Main.game.GetOv().GetAszteroida(ID);
		String s = "Aszteroida" + i;
		json.put(s, a);
	    if(out.equals("0")) {
	    	System.out.println("Aszteroida" + a);
	    	return null;
	    }
	    else
	    	return json;
	}
	
	/**
	 * Visszaad egy json objektumot, amibe belerak egy telepest ID alapj�n
	 * @param filename : a f�jl neve
	 * @param ID : a telepes ID-ja
	 * @param out : f�jlba, vagy konzolra �rja
	 * @param json : json objektum, amibe az adatokat rakjuk
	 * @param i : a sorsz�ma
	 * @return a f�jlba �rand� objektum
	 * @throws Exception
	 */
	public static JSONObject writeTelepes(String filename, String ID, String out, JSONObject json, int i) throws Exception {
		Telepes t = Main.game.GetOv().GetTelepesByID(ID);
		String s = "Telepes" + i;
		json.put(s, t);
	    if(out.equals("0")) {
	    	System.out.println("Telepes: " + t);
	    	return null;
	    }
	    else
	    	return json;
	}
	
	/**
	 * Visszaad egy json objektumot, amibe belerak egy kaput ID alapj�n
	 * @param filename : a f�jl neve
	 * @param ID : a kapu ID-ja
	 * @param out : f�jlba, vagy konzolra �rja
	 * @param json : json objektum, amibe az adatokat rakjuk
	 * @param i : a sorsz�ma
	 * @return a f�jlba �rand� objektum
	 * @throws Exception
	 */ 
	public static JSONObject writeKapu(String filename, String ID, String out, JSONObject json, int i) throws Exception {
		Teleportkapu t = Main.game.GetOv().GetKapuByID(ID);
		String s = "Teleportkapu" + i;
		json.put(s, t);
	    if(out.equals("0")) {
	    	System.out.println("Teleportkapu: " + t);
	    	return null;
	    }
	    else
	    	return json;
	}
	
	/**
	 * Visszaad egy json objektumot, amibe belerak egy ur�nt ID alapj�n
	 * @param filename : a f�jl neve
	 * @param ID : az ur�n ID-ja
	 * @param out : f�jlba, vagy konzolra �rja
	 * @param json : json objektum, amibe az adatokat rakjuk
	 * @param i : a sorsz�ma
	 * @return a f�jlba �rand� objektum
	 * @throws Exception
	 */
	public static JSONObject writeUran(String filename, String ID, String out, JSONObject json, int i) throws Exception {
		String s = Main.game.GetOv().GetNyersanyagByID(ID);
		//Nyersanyag u = Main.game.GetOv().GetAszteroida(s).getBelsoAnyag(); 
		String s2 = "Uran" + i;
		//json.put(s2, u);
	    json.put(s2,  Main.game.GetOv().GetAszteroida(s).getBelsoAnyag());
		if(out.equals("0")) {
	    	//System.out.println("Uran: " + u);
	    	System.out.println("Uran: " + Main.game.GetOv().GetAszteroida(s).getBelsoAnyag());
			return null;
	    }
	    else
	    	return json;
	}
}

