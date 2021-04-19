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
//        System.out.println("Honnan olvasson be a program? (0 = konzolról / 1 = parancs sorszám alapján) ");
//        int be = scan.nextInt();
//        runProto_parancs += " " + be;
//        
//        System.out.println("Hova írja a kimenetet a program? (0 = konzolra / 1 = output file-ba) ");
//        int ki = scan.nextInt();
//        runProto_parancs += " " + ki;
//        
//        int kod;
//        if (be == 0)
//            kod = -1;
//        else {
//            System.out.println("Futtatandó teszt sorszáma: ");
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
//            // TODO: Itt kell a komparátort megkérdezni, jó e a kimenet, argumentumként < kod + "_out" > -ot kell átadni
//        }
//        else {
//            System.out.println("A kimenet csak a konzolon jelent meg, a helyessége nem ellenõrizhetõ.");
//        }
    	final String dir = System.getProperty("user.dir");
    	File dirf = new File(dir);
    	String parentPath = dirf.getParent();
    	String runProto_parancs;
    	
    	runProto_parancs = "java -jar " + parenPath + "\\file.jar 1 1 1";
    	
    	ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/C", runProto_parancs);
    	Process z = builder.start();
        
    }
}
