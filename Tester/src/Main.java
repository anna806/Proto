import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    static Runtime process = Runtime.getRuntime();
    
    public static void main (String[] args) throws Exception {
        
//        final String dir = System.getProperty("user.dir");
//        String runProto_parancs;
//        
//        runProto_parancs = "java -cp " + dir + " Main"; // ha csak class fileok vannak, ez mukodik parancssorbol
//        runProto_parancs = "java -cp " + dir + "\\test.jar Main"; // ha van jar file, ez mukodik parancssorbol
//        
//        Scanner scan = new Scanner(System.in);
//        
//        System.out.println("Honnan olvasson be a program? (0 = konzolr�l / 1 = parancs sorsz�m alapj�n) ");
//        int be = scan.nextInt();
//        runProto_parancs += " " + be;
//        
//        System.out.println("Hova �rja a kimenetet a program? (0 = konzolra / 1 = output file-ba) ");
//        int ki = scan.nextInt();
//        runProto_parancs += " " + ki;
//        
//        int kod;
//        if (be == 0)
//            kod = -1;
//        else {
//            System.out.println("Futtatand� teszt sorsz�ma: ");
//            kod = scan.nextInt();            
//        }
//        runProto_parancs += " " + kod;
//        
//        scan.close();
//        
//        System.out.println("A lefuttatott parancs: \"" + runProto_parancs + "\"");
//        process.exec(runProto_parancs).waitFor();
//        
//        
//        if (ki == 1) {
//            // TODO: Itt kell a kompar�tort megk�rdezni, j� e a kimenet, argumentumk�nt < kod + "_out" > -ot kell �tadni
//        }
//        else {
//            System.out.println("A kimenet csak a konzolon jelent meg, a helyess�ge nem ellen�rizhet�.");
//        }
    	//Runtime process = Runtime.getRuntime();
    	
    	System.out.println("Hello2");
    	final String dir = System.getProperty("user.dir");
    	File dirf = new File(dir);
    	String parentPath = dirf.getParent();
    	System.out.println(parentPath);
    	String runProto_parancs;
    	
    	runProto_parancs = "java -jar " + parentPath + "\\test.jar 1 1 12";
    	System.out.println(runProto_parancs);
    	int exit = process.exec(runProto_parancs).waitFor();
//    	Process p = Runtime.getRuntime().exec(runProto_parancs);
//    	int exit = p.waitFor();
    	System.out.println(exit);
//    	runProto_parancs = "java -jar " + parentPath + "\\test.jar 1 1 1 > " + parentPath + "\\a.txt";
//    	
//    	ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", runProto_parancs);
//    	System.out.println(runProto_parancs);
//    	Process z = builder.start();
//    	z.waitFor();
//    	int exit = z.exitValue();
//    	System.out.println(exit);
        
    }
}
