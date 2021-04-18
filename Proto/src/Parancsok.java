import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	private static Jatek jatek;
	private static boolean fejlesztoi;
	public static void main(String[] args) {
		try {
			String line = "";
			BufferedReader file = new BufferedReader(new FileReader(args[2]+".txt"));
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in)); 
			if(args[0].equals("0")) {
				line = console.readLine();
				jatek = new Jatek();
				jatek.Start();
			} else if(args[0].equals("1")) {
				line = file.readLine();
			}
			
			while(line != null && line.length() != 0) {
				ParancsErtelmezo(line);
				if(args[0].equals("0")) {
					line = console.readLine();
				} else if(args[0].equals("1")) {
					line = file.readLine();
				}
			}
			file.close();
			console.close();
			Output(args[2], args[1]); //tesztesetnél
		}catch(Exception e){}
	}
	static void ParancsErtelmezo(String p) {
		String[] com = p.split(" ");
		if(com.length!=2){throw new IllegalArgumentException();}
		String command = com[0];
		switch(command){
		case "telepes_mozog":
			Main.game.GetOv().GetTelepesByID(com[1]).Mozgas(Main.game.GetOv().GetAszteroida(com[2]));
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
			//Betegyünk egy absztrakt függvényt a bánászásra is, hogy meg lehessen egy sorból szépen csinálni?
			Main.game.GetOv().GetEntitas(com[1]).Banyaszat();
			break;
		case "napvihar": 
			Main.game.GetOv().GetAszteroida(com[1]).StartNapvihar();
			break;
		case "napkozel": 
			Main.game.GetOv().GetAszteroida(com[1]).setNapkozel(true);
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
			t.SetAszteroida(Main.game.GetOv().GetAszteroida(com[1]));
			t.SetID(com[2]);
			Main.game.GetOv().addTelepes(t);
			}
			break;
		case "plusz_robot": 
			if(fejlesztoi) {
			Robot r = new Robot();
			r.SetAszteroida(Main.game.GetOv().GetAszteroida(com[1]));
			r.SetID(com[2]);
			Main.game.GetOv().addRobot(r);
			}
			break;
		case "plusz_ufo": 
			if(fejlesztoi) {
			Ufo u = new Ufo();
			u.SetAszteroida(Main.game.GetOv().GetAszteroida(com[1]));
			u.SetID(com[2]);
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
			tk.setAszter(Main.game.GetOv().GetTelepesByID(com[1]).getAszteroida());
			Main.game.GetOv().GetTelepesByID(com[1]).AddKapu(tk);
			}
			break;
		case "expozicio": 
			if(fejlesztoi) {
			for(int i = 0; i < Main.game.GetOv().GetTelepesekSize(); i++) {
				if(Main.game.GetOv().GetTelepes(i).getNyersanyag(com[1]).getID().equals(com[1]))
					Main.game.GetOv().GetTelepes(i).getNyersanyag(com[1]).setExp(Integer.parseInt(com[2])); //lehet expozicio novelo
			}}																						  //az osszes nyersanyagban?
			break;
		case "informaciok": 
			Main.game.GetOv().Kiir(com[1]);
			break;
		case "informaciok_jatek": break;
		case "list": //itt be kell írni hogy Telepes? Ufo?
			if(fejlesztoi) {
				Main.game.GetOv().List(com[1]); //!!!!!!!!!!!!!!!!
			}
			
			break;
		case "palya_betoltes": 
			Main.game.load("map.txt");
			break; //létrehozza az objektumokat?
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
		
			
		}
	}
	//JSONBE KIIRATNI!!!
	public static void Output(String p, String out) {
		String ID = "a02";
		try {
			writeAszteroida("out"+p+".txt", ID, out);
			writeTelepes("out"+p+".txt", ID, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeAszteroida(String filename, String ID, String out) throws Exception {
		Aszteroida a = Main.game.GetOv().GetAszteroida(ID);
	    JSONObject aszteroida = new JSONObject();
	    aszteroida.put("ID", a.getID());
	    aszteroida.put("napkozel", a.getNapkozel());
	    aszteroida.put("keregvastagsag", a.getKopenyVastagsag());
	    aszteroida.put("belsoAnyag", a.getBelsoAnyagString());
	    
	    JSONArray entitasok = new JSONArray();
	    for(int i = 0; i<a.EntitasokSize(); i++) {
	    	entitasok.add(a.getEntitas(i));
	    }
	    JSONArray szomszedok = new JSONArray();
	    for(int i = 0; i<a.SzomszedokSize(); i++) {
	    	szomszedok.add(a.getSzomszed(i));
	    }
	    aszteroida.put("entitasok:", entitasok);
	    aszteroida.put("szomszedok:", szomszedok);
	    if(out.equals("0")) {
	    	System.out.println(aszteroida);
	    } else if(out.equals("1")) {
	    	Files.write(Paths.get(filename), aszteroida.toJSONString().getBytes());
	    }
	    
	}
	
	public static void writeTelepes(String filename, String ID, String out) throws Exception {
		Telepes t = Main.game.GetOv().GetTelepesByID(ID);
	    JSONObject telepes = new JSONObject();
	    telepes.put("ID", t.getID());
	    telepes.put("aszteroida", t.getAszteroida());
	    	    
	    JSONArray nyersanyagok = new JSONArray();
	    for(int i = 0; i<t.NyersanyagokSize(); i++) {
	    	nyersanyagok.add(t.getNyersanyagok(i));
	    }
	    JSONArray kapuk = new JSONArray();
	    for(int i = 0; i<t.KapukSize(); i++) {
	    	kapuk.add(t.getKapuk(i));
	    }
	    telepes.put("nyersanyagok:", nyersanyagok);
	    telepes.put("kapuk:", kapuk);
	    if(out.equals("0")) {
	    	System.out.println(telepes);
	    } else if(out.equals("1")) {
	    	Files.write(Paths.get(filename), telepes.toJSONString().getBytes());
	    }
  
	}
	
	public static void writeUran(String filename, String ID, String out) throws Exception {
		//illegál?
		Uran u = (Uran) Main.game.GetOv().GetAszteroida(ID).getBelsoAnyag();
	    JSONObject uran = new JSONObject();
	    uran.put("ID", u.getID());
	    uran.put("expozicio", u.getExpozicio()); 	    
	    if(out.equals("0")) {
	    	System.out.println(uran);
	    } else if(out.equals("1")){
	    	Files.write(Paths.get(filename), uran.toJSONString().getBytes());
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
			Files.write(Paths.get(filename), kapu.toJSONString().getBytes());
		}
	}
}

