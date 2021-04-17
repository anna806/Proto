import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Parancsok {
	//args = be, ki, parancs
	//be = 0 konzol
	//be = 1 parancssz�m
	//ki = 0 konzolra
	//ki = 1 outputf�jlba
	//id-k stringek!!
	//c�l hogy konzolra �s f�jlba is �rjon
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
	void ParancsErtelmezo(String p) {
		String[] com = p.split(" ");
		if(com.length!=2){throw new IllegalArgumentException();}
		String command = com[0];
		switch(command){
		case "telepes_mozog":
			//telepes_mozog t01 a05
			/*Aszteroidaov ov = jatek.GetOv();
			List<Telepes> tList = new ArrayList<Telepes>();
			String[] param = com[1].split(" ");
			String[] tID = param[0].split("0");
			Telepes t = ov.GetTelepes(Integer.parseInt(tID[1]));
			t.*/

			break;
		case "robot_mozog": 
			Main.game.GetOv().GetRobot(com[1]).Mozgas(Main.game.GetOv().GetAszteroida(com[2]));
			break;
		case "ufo_mozog": 
			Main.game.GetOv().GetUfo(com[1]).Mozgas(Main.game.GetOv().GetAszteroida(com[2]));
			break;
		case "telepes_fur": 
			Main.game.GetOv().GetTelepes(com[1]).Furas();
			break;
		case "robot_fur": 
			Main.game.GetOv().GetRobot(com[1]).Furas();
			break;
		case "nyersanyag_kinyeres": 
			//Betegy�nk egy absztrakt f�ggv�nyt a b�n�sz�sra is, hogy meg lehessen egy sorb�l sz�pen csin�lni?
			Nyersanyag ny = Main.game.GetOv().GetEntitas(com[1]).Banyaszat();
			break;
		case "napvihar": 
			Main.game.GetOv().GetAszteroida(com[1]).StartNapvihar();
			break;
		case "napkozel": 
			Main.game.GetOv().GetAszteroida(com[1]).setNapkozel(true);
			break;
		case "teleportkapu_epites": 
			Main.game.GetOv().GetTelepes(com[1]).KapuEpit();
			break;
		case "robot_epites": 
			Main.game.GetOv().GetTelepes(com[1]).RobotEpit();
			break;
		case "bazis_epites": break;
		case "teleportkapu_elhelyezes": break;
		case "visszatoltes": 
			Main.game.GetOv().GetTelepes(com[1]).Visszatolt();
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
				Main.game.GetOv().GetTelepes(com[1]).addNyersanyag() //ide m�g kellenek dolgok
			break;
		case "plusz_teleportkapu": 
			Teleportkapu tk = new Teleportkapu();
			tk.setAszter(Main.game.GetOv().GetTelepes(com[1]).getAszteroida());
			Main.game.GetOv().GetTelepes(com[1]).AddKapu(tk);
			break;
		case "expozicio": 
			for(int i = 0; i < Main.game.GetOv().GetTelepesek().size(); i++) {
				if(Main.game.GetOv().GetT(i).getNyersanyag(com[1]).getID().equals(com[1]))
					Main.game.GetOv().GetT(i).getNyersanyag(com[1]).setExp(Integer.parseInt(com[2])); //lehet expozicio novelo
			}																						  //az osszes nyersanyagban?
			break;
		case "informaciok": break;
		case "informaciok_jatek": break;
		case "list": break;
		case "palya_betoltes": break; //l�trehozza az objektumokat?
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
	void Output(int p) {}
}

