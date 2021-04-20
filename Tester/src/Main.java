import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    static Runtime process = Runtime.getRuntime();
    
    public static void main (String[] args) throws Exception {
        
    	final String dir = System.getProperty("user.dir");
    	File dirf = new File(dir);
    	String parentPath = dirf.getParent();
    	System.out.println(parentPath);
    	String runProto_parancs;
    	
    	runProto_parancs = "java -jar " + parentPath + "\\proto.jar";

        Scanner scan = new Scanner(System.in);
        
        System.out.println("Honnan olvasson be a program? (0 = konzolról / 1 = parancs sorszám alapján) ");
        int be = scan.nextInt();
        runProto_parancs += " " + be;
        
        System.out.println("Hova írja a kimenetet a program? (0 = konzolra / 1 = output file-ba) ");
        int ki = scan.nextInt();
        runProto_parancs += " " + ki;
        
        int kod;
        if (be == 0)
            kod = -1;
        else {
            System.out.println("Futtatandó teszt sorszáma: ");
            kod = scan.nextInt();            
        }
        if(kod != 0)
        	runProto_parancs += " " + kod;
        
        scan.close();
        if(kod == 0) {
        	osszesTeszt(parentPath, runProto_parancs, ki);
        }
        else {
        System.out.println("A lefuttatott parancs: \"" + runProto_parancs + "\"");
//      process.exec(runProto_parancs).waitFor();
        int exit = process.exec(runProto_parancs).waitFor();
    	System.out.println(exit);
        
        if (ki == 1 && exit != 1) {
            // TODO: Itt kell a komparátort megkérdezni, jó e a kimenet, argumentumként < kod + "_out" > -ot kell átadni
        	String runComp_parancs = "java -jar " + parentPath + "\\comparator.jar " + kod;
        	int exit2 = process.exec(runComp_parancs).waitFor();
        	if(exit2 == 0 && kod != 0) {
        		System.out.println(kod + ".teszteset sikeres");
        	}
        	else if(exit2 == 1 && kod != 0) {
        		System.out.println(kod + ".teszteset sikertelen\nA hibas sorok:\n");
        		File error = new File(parentPath + "\\Tester\\errors.txt");
        		Scanner myReader = new Scanner(error);
//        		while(myReader.hasNextLine()) {
//        			System.out.println("Elvart: " + myReader.nextLine());
//        			//System.out.println("Kapott: " + myReader.nextLine());
//        		}
        		int k = 0;
        		while(myReader.hasNextLine()) {
        			if(k %2 == 0)
        				System.out.println("Elvart: " + myReader.nextLine());
        			else
	        			System.out.println("Kapott: " + myReader.nextLine());
        			k++;
        		}
        		myReader.close();
        	}
        }
        else if(ki == 0){
            System.out.println("A kimenet csak a konzolon jelent meg, a helyessége nem ellenõrizhetõ.");
        }
        else if(ki == 1 && exit == 1)
        	System.out.println("A teszt nem tudott lefutni");
        }
//        System.out.println(runProto_parancs);    
    }
    
    public static void osszesTeszt(String parentPath, String runProto_parancs, int ki) {
    	int i = 0;
    	int fails = 0;
    	String s = runProto_parancs;
    	File input = new File(parentPath + "\\input\\" + (i + 1) +".txt");
    	while(input.exists() && i < 33) {
    		try {
    			String p = s + " " + (i + 1);
    			System.out.println(p);
    			fails += process.exec(p).waitFor();
    			if(ki == 1) {
    				String runComp_parancs = "java -jar " + parentPath + "\\comparator.jar " + (i + 1);
    	        	int exit2 = process.exec(runComp_parancs).waitFor();
    	        	if(exit2 == 0) {
    	        		System.out.println((i + 1) + ".teszteset sikeres");
    	        	}
    	        	else if(exit2 == 1) {
    	        		System.out.println((i + 1) + ".teszteset sikertelen\nA hibas sorok:\n");
    	        		File error = new File(parentPath + "\\Tester\\errors.txt");
    	        		Scanner myReader = new Scanner(error);
    	        		/*while(myReader.hasNextLine()) {
    	        			System.out.println("Elvart: " + myReader.nextLine());
    	        			System.out.println("Kapott: " + myReader.nextLine());
    	        		}*/
    	        		int k = 0;
    	        		while(myReader.hasNextLine()) {
    	        			if(k %2 == 0)
    	        				System.out.println("Elvart: " + myReader.nextLine());
    	        			else
        	        			System.out.println("Kapott: " + myReader.nextLine());
    	        			k++;
    	        		}
    	        		myReader.close();
    	        	}
    			}
    		}
    		catch(Exception e) {
    			System.out.println(e.getMessage());
    		}
    		i++;
    	}
    	
    }
}
