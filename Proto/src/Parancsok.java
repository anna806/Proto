
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Parancsok {
	//fejlesztõi mód?
	//args = be, ki, parancs
	//be = 0 konzol
	//be = 1 parancsszám
	//ki = 0 konzolra
	//ki = 1 outputfájlba
	//id-k stringek!!
	//cél hogy konzolra és fájlba is írjon
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
			Main.game.GetOv().GetTelepesByID(com[1]).kiir();
			break;
		case "robot_mozog": 
			Main.game.GetOv().GetRobot(com[1]).RandomMozgas(); 
			break;
		case "ufo_mozog": 
			Main.game.GetOv().GetUfo(com[1]).RandomMozgas();
			break;
		case "telepes_fur": 
			Main.game.GetOv().GetTelepesByID(com[1]).getAszteroida().kiir();
			Main.game.GetOv().GetTelepesByID(com[1]).Furas();
			Main.game.GetOv().GetTelepesByID(com[1]).getAszteroida().kiir();
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
			System.out.println("Itt akadok el");
			Main.game.GetOv().GetTelepesByID(com[1]).Visszatolt();
			//System.out.println(Main.game.GetOv().GetTelepesByID(com[1]).getAszteroida().getBelsoAnyag().getExp());
			System.out.println("Idaig eljutottam");
			break;
		case "plusz_telepes": 
			
			if(fejlesztoi) {
				
			Telepes t = new Telepes();
			Main.game.GetOv().GetAszteroida(com[1]).Befogad(t);
			t.SetID(com[2]);
			Main.game.GetOv().addTelepes(t);
			Main.game.GetOv().GetTelepesByID(com[2]).kiir();
			
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
					Main.game.GetOv().GetTelepesByID(com[1]).AddNyersanyag(new Uran());
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
				System.out.println("Itt vagyok");
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
			System.out.println("Betoltottem a palyat");
			break;
		case "veletlen": break;
		case "fejlesztoi_mod": 
			if(com[1].equals("true")){
				fejlesztoi = true;
				System.out.println("fejlesztoi - aktiv");
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
			for(int i = 0; i < out2.length; i++)
				System.out.println(out2[i]);
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
	
	public static void Output(String p, String out, String[] obj) throws Exception {
		JSONObject ki = new JSONObject();
		for(int i = 0; i < obj.length; i++) {
			System.out.println("Itt vagyok " + obj[i]);
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
	
	public static JSONObject writeTelepes(String filename, String ID, String out, JSONObject json, int i) throws Exception {
		Telepes t = Main.game.GetOv().GetTelepesByID(ID);
//	    JSONObject teleportkapu = new JSONObject();
//	    teleportkapu.put("Teleportkapu", t);
		String s = "Telepes" + i;
		json.put(s, t);
		//System.out.println("Hozza adtam");
	    if(out.equals("0")) {
	    	System.out.println("Telepes: " + t);
	    	return null;
	    }
	    else
	    	return json;
	}
	
	public static JSONObject writeKapu(String filename, String ID, String out, JSONObject json, int i) throws Exception {
		Teleportkapu t = Main.game.GetOv().GetKapuByID(ID);
//	    JSONObject aszteroida = new JSONObject();
//	    aszteroida.put("Aszteroida", a);
		String s = "Teleportkapu" + i;
		json.put(s, t);
	    if(out.equals("0")) {
	    	System.out.println("Teleportkapu: " + t);
	    	return null;
	    }
	    else
	    	return json;
	}
	
	public static JSONObject writeUran(String filename, String ID, String out, JSONObject json, int i) throws Exception {
		String s = Main.game.GetOv().GetNyersanyagByID(ID);
		Nyersanyag u = Main.game.GetOv().GetAszteroida(s).getBelsoAnyag(); 
		String s2 = "Uran" + i;
		json.put(s2, u);
	    if(out.equals("0")) {
	    	System.out.println("Uran: " + u);
	    	return null;
	    }
	    else
	    	return json;
	}
	
//	public static void writeAszteroida(String filename, String ID, String out) throws Exception {
//		Aszteroida a = Main.game.GetOv().GetAszteroida(ID);
//	    JSONObject aszteroida = new JSONObject();
//	    aszteroida.put("ID", a.getID());
//	    aszteroida.put("napkozel", a.getNapkozel());
//	    aszteroida.put("keregvastagsag", a.getKopenyVastagsag());
//	    aszteroida.put("belsoAnyag", a.getBelsoAnyagString());
//	    
//	    JSONArray entitasok = new JSONArray();
//	    for(int i = 0; i<a.EntitasokSize(); i++) {
//	    	entitasok.add(a.getEntitas(i));
//	    }
//	    JSONArray szomszedok = new JSONArray();
//	    for(int i = 0; i<a.SzomszedokSize(); i++) {
//	    	szomszedok.add(a.getSzomszed(i));
//	    }
//	    aszteroida.put("entitasok:", entitasok);
//	    aszteroida.put("szomszedok:", szomszedok);
//	    aszteroida.put("ID", a.getID());
//		aszteroida.put("Aszteroida", a);
//	    if(out.equals("0")) {
//	    	System.out.println(aszteroida);
//	    } else if(out.equals("1")) {
//	    	String dir = System.getProperty("user.dir");
//	    	File dirf = new File(dir);
//	    	String parentPath = dirf.getParent();
//	    	FileWriter writer = new FileWriter(parentPath + "\\output\\" + filename);
//	    	writer.write(aszteroida.toJSONString());
//	    	writer.flush();
//	    	writer.close();
//	    	//Files.write(Paths.get(filename), aszteroida.toJSONString().getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
//	    } 
//	}
	
//	public static void writeTelepes(String filename, String ID, String out) throws Exception {
//		Telepes t = Main.game.GetOv().GetTelepesByID(ID);
//	    JSONObject telepes = new JSONObject();
//	    telepes.put("ID", t.getID());
//	    telepes.put("aszteroida", t.getAszteroida().getID());
//	    	    
//	    JSONArray nyersanyagok = new JSONArray();
//	    for(int i = 0; i<t.NyersanyagokSize(); i++) {
//	    	nyersanyagok.add(t.getNyersanyagok(i));
//	    }
//	    JSONArray kapuk = new JSONArray();
//	    for(int i = 0; i<t.KapukSize(); i++) {
//	    	kapuk.add(t.getKapuk(i));
//	    }
//	    telepes.put("nyersanyagok:", nyersanyagok);
//	    telepes.put("kapuk:", kapuk);
//	    
//	    if(out.equals("0")) {
//	    	System.out.println(telepes);
//	    } else if(out.equals("1")) {
//	    	Files.write(Paths.get(filename), telepes.toJSONString().getBytes(),  StandardOpenOption.APPEND, StandardOpenOption.CREATE);
//	    }
//  
//	}
	
	/*public static void writeUran(String filename, String ID, String out) throws Exception {
		//nyersanyagid megy be
		String s = Main.game.GetOv().GetNyersanyagByID(ID);
		Nyersanyag u = Main.game.GetOv().GetAszteroida(s).getBelsoAnyag(); 
	    JSONObject uran = new JSONObject();
	    uran.put("ID", u.getID());
	    uran.put("expozicio", u.getExp()); 	    
	    if(out.equals("0")) {
	    	System.out.println(uran);
	    } else if(out.equals("1")){

	    	Files.write(Paths.get(filename), uran.toJSONString().getBytes(),  StandardOpenOption.APPEND, StandardOpenOption.CREATE);
	    }

	}
	public static void writeKapu(String filename, String ID, String out) throws Exception {
		Teleportkapu t = Main.game.GetOv().GetKapuByID(ID);
	    JSONObject kapu = new JSONObject();
	    kapu.put("ID", t.getID());
	    kapu.put("kergult", t.getMegkergult());
	    kapu.put("parja", t.getParja());
	    kapu.put("aszteroida", t.getAszter());
		if(out.equals("0")) {
			System.out.println(kapu);
		} else if(out.equals("1")) {
			Files.write(Paths.get(filename), kapu.toJSONString().getBytes(),  StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		}
	}*/
}

