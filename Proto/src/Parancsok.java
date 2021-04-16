import java.io.BufferedReader;
import java.io.FileReader;

public class Parancsok {
	//cél hogy konzolra és fájlba is írjon
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
		//ilyesmit, csak most el kell mennem, majd folytatom
		String[] tokens = p.split(" ");
		if(tokens.length!=2){throw new IllegalArgumentException();}
		String command = tokens[0];
		String code = tokens[1];
	}
	void Output(int p) {}
}
