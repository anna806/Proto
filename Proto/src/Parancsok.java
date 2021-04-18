import java.io.BufferedReader;
import java.io.FileReader;
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
	private Jatek jatek;
	public static void main(String[] args) {
		try {
			BufferedReader bir = new BufferedReader(new FileReader(args[0]+".txt"));
			String line = bir.readLine();
			while(line != null && line.length() != 0) {
				ParancsErtelmezo(line);
				line = bir.readLine();
			}
			bir.close();
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
			Nyersanyag ny = Main.game.GetOv().GetEntitas(com[1]).Banyaszat();
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
			Main.game.GetOv().GetTelepesByID(com[1]).BazisEpit();
			break;
		case "teleportkapu_elhelyezes": 
			Main.game.GetOv().GetTelepesByID(com[1]).KapuLerak();
			break;
		case "visszatoltes": 
			Main.game.GetOv().GetTelepesByID(com[1]).Visszatolt();
			break;
		case "plusz_telepes": 
			Telepes t = new Telepes();
			t.SetAszteroida(Main.game.GetOv().GetAszteroida(com[1]));
			t.SetID(com[2]);
			Main.game.GetOv().addTelepes(t);
			break;
		case "plusz_robot": 
			Robot r = new Robot();
			r.SetAszteroida(Main.game.GetOv().GetAszteroida(com[1]));
			r.SetID(com[2]);
			Main.game.GetOv().addRobot(r);
			break;
		case "plusz_ufo": 
			Ufo u = new Ufo();
			u.SetAszteroida(Main.game.GetOv().GetAszteroida(com[1]));
			u.SetID(com[2]);
			Main.game.GetOv().addUfo(u);
			break;
		case "plusz_nyersanyag": 
			for(int i = 2; i < com.length; i++)
				Main.game.GetOv().GetTelepesByID(com[1]).addNyersanyag() //ide még kellenek dolgok
			break;
		case "plusz_teleportkapu": 
			Teleportkapu tk = new Teleportkapu();
			tk.setAszter(Main.game.GetOv().GetTelepesByID(com[1]).getAszteroida());
			Main.game.GetOv().GetTelepesByID(com[1]).AddKapu(tk);
			break;
		case "expozicio": 
			for(int i = 0; i < Main.game.GetOv().GetTelepesekSize(); i++) {
				if(Main.game.GetOv().GetTelepes(i).getNyersanyag(com[1]).getID().equals(com[1]))
					Main.game.GetOv().GetTelepes(i).getNyersanyag(com[1]).setExp(Integer.parseInt(com[2])); //lehet expozicio novelo
			}																						  //az osszes nyersanyagban?
			break;
		case "informaciok": //jsonosen?
			
			break;
		case "informaciok_jatek": break;
		case "list": //itt be kell írni hogy Telepes? Ufo?
			Main.game.GetOv().List(com[1]);
			break;
		case "palya_betoltes": break; //létrehozza az objektumokat?
		case "veletlen": break;
		case "fejlesztoi_mod": break;
		case "betolt": 
			Main.game.load("jatek.txt");
			break;
		case "mentes": 
			Main.game.ser(Main.game.GetOv(), "jatek.txt");
			break;
		
			
		}
	}
	//JSONBE KIIRATNI!!!
	void Output(int p) {
		String ID = "a02";
		try {
			writeAszteroida("out"+p+".txt", ID);
			writeTelepes("out"+p+".txt", ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeAszteroida(String filename, String ID) throws Exception {
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
	    Files.write(Paths.get(filename), aszteroida.toJSONString().getBytes());
	    System.out.println(aszteroida);
	}
	
	public static void writeTelepes(String filename, String ID) throws Exception {
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
	    Files.write(Paths.get(filename), telepes.toJSONString().getBytes());
	    System.out.println(telepes);
	}
	
	public static void writeUran(String filename, String ID) throws Exception {
		//illegál?
		Uran u = (Uran) Main.game.GetOv().GetAszteroida(ID).getBelsoAnyag();
	    JSONObject uran = new JSONObject();
	    uran.put("ID", u.getID());
	    uran.put("expozicio", u.getExpozicio()); 	    
	    
	    Files.write(Paths.get(filename), uran.toJSONString().getBytes());
	    System.out.println(uran);
	}
	public static void writeKapu(String filename, String ID) throws Exception {
		Teleportkapu t = Main.game.GetOv().GetKapuByID(ID);
	    JSONObject kapu = new JSONObject();
	    kapu.put("ID", t.getID());
	    kapu.put("kergult", t.getMegkergult());
	    kapu.put("parja", t.getParja());
	    kapu.put("aszteroida", t.getAszter());
	    
	    Files.write(Paths.get(filename), kapu.toJSONString().getBytes());
	    System.out.println(kapu);
	}
}

