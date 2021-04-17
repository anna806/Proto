import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Parancsok {
	//args = be, ki, parancs
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
	void ParancsErtelmezo(String p) {
		String[] com = p.split(" ", 2);
		if(com.length!=2){throw new IllegalArgumentException();}
		String command = com[0];
		switch(command){
		case "telepes_mozog":
			//telepes_mozog t01 a05
			Aszteroidaov ov = jatek.GetOv();
			List<Telepes> tList = new ArrayList<Telepes>();
			String[] param = com[1].split(" ");
			String[] tID = param[0].split("0");
			Telepes t = ov.GetTelepes(Integer.parseInt(tID[1]));
			t.

			break;
		case "robot_mozog": break;
		case "ufo_mozog": break;
		case "telepes_fur": break;
		case "robot_fur": break;
		case "nyersanyag_kinyeres": break;
		case "napvihar": break;
		case "napkozel": break;
		case "teleportkapu_epites": break;
		case "robot_epites": break;
		case "bazis_epites": break;
		case "teleportkapu_elhelyezes": break;
		case "visszatoltes": break;
		case "plusz_telepes": break;
		case "plusz_robot": break;
		case "plusz_ufo": break;
		case "plusz_nyersanyag": break;
		case "plusz_teleportkapu": break;
		case "expozicio": break;
		case "informaciok": break;
		case "informaciok_jatek": break;
		case "list": break;
		case "palya_betoltes": break; //létrehozza az objektumokat?
		case "veletlen": break;
		case "fejlesztoi_mod": break;
		case "betolt": break;
		case "mentes": break;
		
			
		}
	}
	void Output(int p) {}
}

