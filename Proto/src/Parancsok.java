import java.io.BufferedReader;
import java.io.FileReader;

public class Parancsok {
	//c�l hogy konzolra �s f�jlba is �rjon
	public static void main(String[] args) {
		try {
			BufferedReader bir = new BufferedReader(new FileReader("Input\\"+args[0]+".txt"));
			String line = bir.readLine();
			while(line != null && line.length() != 0) {
				ParancsErtelmezo(line);
				line = bir.readLine();
			}
			bir.close();
		}catch(Exception e){}
	}
	static void ParancsErtelmezo(String p) {
		//do while?
		String[] com = p.split(" ", 2);
		if(com.length!=2){throw new IllegalArgumentException();}
		String command = com[0];
		//String code = tokens[1];
		switch(command){
		case "telepes_mozog":
			Telepes t = new Telepes(); //ezt elvileg a p�lyaf�jl hozza l�tre?
			String[] tID = com[1].split("0");
			t.SetId((int)tID[1]); //ilyen f�ggv�ny az valid ugye?
			//t.SetId(Integer.parseInt(tID[1]));   //ez lehet kicsit szebb, mint a kasztol�s
			String[] hID = com[1].split("0");
			//kapukat aszteroid�kat k�l�n jel�lni?
			t.Mozgas((int)hID[1]);
			//lehet megkereshetn�nk az az�szteroid�v list�iban, hogy melyik szomsz�dnak van ez az id-je, �s azt a szomsz�dot
			//�tadni a mozg�snak
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
		case "palya_betoltes": break; //l�trehozza az objektumokat?
		case "veletlen": break;
		case "fejlesztoi_mod": break;
		case "betolt": break;
		case "mentes": break;
		
			
		}
	}
	void Output(int p) {}
}

